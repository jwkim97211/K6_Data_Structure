package ch02;

import java.util.Arrays;

class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}

	@Override
	public int compareTo(PhyscData p) {
		if(name.compareTo(p.name)>0)
			return 1;
		else if (name.compareTo(p.name)<0)
			return -1;
		else
			return 0;
	}

	public int equals(PhyscData p) {
		if(name.compareTo(p.name)>0)
			return 1;
		else if (name.compareTo(p.name)<0)
			return -1;
		else
			return 0;
	}
}

public class homework_2 {
	static void swap(PhyscData[] arr, int ind1, int ind2) {
		PhyscData tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}

	static void sortData(PhyscData[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].compareTo(arr[j]) > 0)
					swap(arr, i, j);
			}
		}
	}

	static boolean search(PhyscData[] arr, PhyscData p) {
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(p) == 0) {
				flag = true;
				System.out.println("일치합니다.");
			} else {
				System.out.println("불일치합니다.");
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		PhyscData[] data = { 
				new PhyscData("홍길동", 162, 0.3), 
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7), 
				new PhyscData("김홍길동", 172, 0.3), 
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2), 
				new PhyscData("최길동", 169, 0.5), 
				};
		showData(data);
		sortData(data);
		// Arrays.sort(null, null);//comparator가 필요하다
		showData(data);
		PhyscData myobject = new PhyscData("박길동", 167, 0.2);
		search(data, myobject);
	}

	static void showData(PhyscData[] arr) {
		for (PhyscData item : arr)
			System.out.print(item.toString() + " ");
		System.out.println();
	}
}
