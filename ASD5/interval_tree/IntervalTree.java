package interval_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class IntervalTree {
	
	Node head;
	
	public IntervalTree(Set<Interval> intervalList) {
		head = new Node();
		build(intervalList, head);
	}

	public Set<Interval> find(int point){
		return find(point, head);
		
	}
	
	private Set<Interval> find(int point, Node node){
		if(node.minFrom == -1)
			return new TreeSet<>();
		
		if(node.minFrom >  point) {
			return find(point, node.left);
		}
		
		if(node.maxTo < point) {
			return find(point, node.right);
		}	
		
		Set<Interval> result = new TreeSet<>();

		for (Interval interval : node.intervals) {
			if(point >= interval.from && point <=interval.to) {
				result.add(interval);
			}
		}
		if(node.left.maxTo > point) {
			result.addAll(find(point, node.left));
		}
		if(node.right.minFrom < point) {
			result.addAll(find(point, node.right));
		}
		return result;
	}
	
	private void build(Set<Interval> intervalList, Node parent) {
		int median = getMedian(intervalList);
		
		TreeSet<Interval> intervalsLeft = new TreeSet<>();
		TreeSet<Interval> intervalsRight = new TreeSet<>();
		
		for (Interval interval : intervalList) {
			if(interval.from > median) {
				intervalsRight.add(interval);
				continue;
			}
			if(interval.to < median) {
				intervalsLeft.add(interval);
				continue;
			}
			parent.intervals.add(interval);
		}
		parent.minFrom = parent.intervals.first().from;
		parent.maxTo = parent.intervals
								.stream()
								.max(Comparator.comparing(Interval::getTo))
								.orElseThrow(NoSuchElementException::new).getTo();

		parent.left = new Node();
		if(intervalsLeft.size() > 0)
			build(intervalsLeft, parent.left);
		
		parent.right = new Node();
		if(intervalsRight.size() > 0)
			build(intervalsRight, parent.right);	
	}

	private int getMedian(Set<Interval> intervalSet) {
		List<Integer> medians = new ArrayList<Integer>();
		for (Interval interval : intervalSet) {
			medians.add(interval.getMedian());
		}
		medians.sort((o1, o2) -> o1 - o2);
		
		return medians.get(medians.size()/2);
	}

}
