package linkedList;

public class AdditionalTask {

	public static LinkedList sum(LinkedList list1, LinkedList list2) {
		if (list1 == null || list2 == null)
			return new LinkedList();
		
		LinkedList sum = new LinkedList();
		Node node1 = list1.head;
		Node node2 = list2.head;

		while (node1 != null) {
			sum.addInTail(new Node(node1.value + node2.value));
			node1 = node1.next;
			node2 = node2.next;
		}
		
		return sum;
	}
}
