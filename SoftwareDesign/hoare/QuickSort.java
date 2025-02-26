package hoare;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		System.out.println("Before " + Arrays.toString(arr));
		sort(arr);
		System.out.println("After: " + Arrays.toString(arr));
	}

	public static void sort(int[] arr) {
		/**
	 	{P: true} 
	  	partition(arr)
	   	{Q: arr[i] < arr[i+1], 0 <= i < arr.length - all the array is sorted}
	 	*/
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(int[] arr, int low, int high) {
		if (low < high) {
			/**
		 	{P: 0 <= low < high < arr.length} 
		  	partition(arr, low, high)
		   	{Q: arr[low],..., arr[p-1], p, arr[p + 1], ..., arr[high]  
				to the left of p - elements of arr, that less than p, 
				to the right of p - elements of arr, that greater than p		   		
		   	}
		 	*/
			int p = partition(arr, low, high);
			
			/**
		 	{P: low < (p - 1)} 
		  	sort(arr, low, p - 1)
		   	{Q: arr[i] < arr[i+1], low <= i < (p - 1) - part of array from low to (p - 1) is sorted}
		 	*/
			
			sort(arr, low, p - 1);
			
			/**
		 	{P: (p + 1) < high} 
		  	sort(arr, p + 1, high)
		   	{Q: arr[i] < arr[i+1], (p + 1) <= i < high - part of array from (p + 1) to high is sorted}
		 	*/
			sort(arr, p + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[(low + high) / 2];

		int i = low;
		int j = high;

		while (true) {

			while (arr[i] < pivot) {
				i = i + 1;
			}
			while (arr[j] > pivot) {
				j = j - 1;
			}
			if (i >= j) {
				return j;
			}
			swap(arr, i++, j--);
		}
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[b];
		arr[b] = arr[a];
		arr[a] = temp;
	}

}
