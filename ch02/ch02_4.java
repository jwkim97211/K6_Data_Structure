package ch02;

import java.util.Random;

public class ch02_4 {
	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		showData(data);
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);
	}

	static void showData(int[] data) {
		for (int num : data) {
			System.out.print(num + " ");
		}
	}

	public static void inputData(int[] data) {
		Random rnd = new Random();
		for (int i = 0; i < data.length; i++)
			data[i] = rnd.nextInt(100);
	}

	static int findMax(int[] items) {
		int max = 0;
		for (int i = 0; i < items.length; i++) {
			if(items[i]>max) {
				max=items[i];
			}
		}
		return max;
	}

	static boolean findValue(int[] items, int value) {	// items[]에 value 값이 있는지를 찾는 알고리즘 구현
		boolean flag=false;
		for(int i=0;i<items.length;i++) {
			if(items[i]==value)
				flag=true;
		}
		return flag;
	}

}
