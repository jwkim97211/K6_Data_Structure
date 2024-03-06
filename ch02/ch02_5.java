package ch02;

import java.util.Arrays;
import java.util.Random;

public class ch02_5 {
	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		showData(data);
		
//		sortData(data);
//		showData(data);
		
		reverse(data);// 역순으로 재배치 - 정렬 아님
		showData(data);

		reverseSort(data);
		showData(data);

		//Arrays.sort(data); //java library를 이용한 정렬
	}

	static void showData(int[] data) {
		for (int num : data)
			System.out.print(num + " ");
		System.out.println();
	}

	static void inputData(int[] data) {
		Random rnd = new Random();
		for (int i = 0; i < data.length; i++)
			data[i] = rnd.nextInt(100);
	}

	static void swap(int[] arr, int ind1, int ind2) {
		int tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}

	static void sortData(int[] arr) {
		for(int i =0;i<arr.length;i++) {
			for(int j =i+1;j<arr.length;j++) {
				if(arr[i]>arr[j])
					swap(arr,i,j);
			}
		}
	}

	static void reverse(int[] a) {
		for (int i = 0; i < a.length / 2; i++)
			swap(a, i, a.length - i - 1);
	}

	static void reverseSort(int[] arr) {
		for(int i =0;i<arr.length;i++) {
			for(int j =i+1;j<arr.length;j++) {
				if(arr[i]<arr[j])
					swap(arr,i,j);
			}
		}
	}

}
