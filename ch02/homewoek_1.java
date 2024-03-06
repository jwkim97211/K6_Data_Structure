package ch02;

public class homewoek_1 {
	public static void main(String[] args) {
		String[] data = { "apple", "grape", "persimmon", "pear", "blueberry", "strawberry", "melon", "oriental melon" };

		showData(data);
		sortData(data);
		showData(data);
	}

	static void showData(String[] arr) {	//확장형 for문 사용
		for(String item:arr)
			System.out.print(item+" ");
	}

	static void swap(String[] arr, int ind1, int ind2) {

	}

	static void sortData(String[] arr) {
		for(int i=0;)
			for(int j=i+1;)
				if(arr[i]>arr[j])	//compareTo() 사용
					swap(arr,i,j);
	}

}
