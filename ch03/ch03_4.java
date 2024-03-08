package ch03;

// 3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
// comparator 구현 실습
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련 함수(메소드) 전체를 작성하는 훈련
 */
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ch03_4 {
	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);

		showList("정렬 전 배열[]", data);

		int key = 13;
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(13): result = " + resultIndex);
		System.out.println();

		sortData(data);
		// Arrays.sort(data);
		showList("정렬 후 배열[]", data);
		
		key = 19;
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(19): result = " + resultIndex);

		key = 10;
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(10): result = " + resultIndex);

	}

	static int linearSearch(int[] item, int key) {
        int index = -1;
        for (int n = 0; n < item.length; n++) {
            if (item[n] == key) {
                index = n;
                break;
            }
        }
        return index;
    }

	static int binarySearch(int[] item, int key) {
	       int pl = 0;
	        int pr = item.length - 1;
	        while (pl <= pr) {
	            int pc = (pl + pr) / 2;
	            if (item[pc] == key) {
	                return pc;
	            } else if (item[pc] < key) {
	                pl = pc + 1;
	            } else {
	                pr = pc - 1;
	            }
	        }
	        return -1;
	}

	static void inputData(int[] data) {
		Random rnd = new Random();
		for (int i = 0; i < data.length; i++)
			data[i] = rnd.nextInt(100);
	}

	static void showList(String topic, int[] data) {
		System.out.println(topic);
		for (int item : data)
			System.out.println(item);
	}

	static void sortData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] > data[j])
					swap(data, i, j);
			}
		}
	}

	static void swap(int[] arr, int ind1, int ind2) {
		int tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
}
