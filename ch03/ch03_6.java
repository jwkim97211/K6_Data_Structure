package ch03;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;

public class ch03_6 {
	static void reverse(String[] a) {// 교재 67페이지
			for (int i = 0; i < a.length / 2; i++)
				swap(a, i, a.length - i - 1);
		}
	
	static void showData(String topic,String[] data) {
		System.out.println(topic + " : ");
		for (String item : data)
			System.out.println(item + " ");
	}
	
	static void sortData(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].compareTo(arr[j]) > 0)
					swap(arr, i, j);
			}
		}
	}
	static void swap(String[] arr, int ind1, int ind2) {
		String tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
	
	static int linearSearch(String [] data, String key) {
        int index = -1;
        for (int n = 0; n < data.length; n++) {
            if (data[n] == key) {
                index = n;
                break;
            }
        }
        return index;
    }
	static int binarySearch(String [] data, String key) {
	       int pl = 0;
	        int pr = data.length - 1;
	        while (pl <= pr) {
	            int pc = (pl + pr) / 2;
	            if (data[pc] == key) {
	                return pc;
	            } else if (data[pc].compareTo(key)<0) {
	                pl = pc + 1;
	            } else {
	                pr = pc - 1;
	            }
	        }
	        return -1;
	}
	

	public static void main(String[] args) {
		String[] data = { "사과", "포도", "복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외" };// 홍봉희 재배 과수

		showData("정렬전", data);
		System.out.println();
		sortData(data);
		showData("정렬후", data);
		System.out.println();
		reverse(data);// 역순으로 재배치
		showData("역순 재배치후", data);
		System.out.println();
		Arrays.sort(data);// Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후", data);
		System.out.println();

		String key = "포도";
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(포도): result = " + resultIndex);

		key = "배";
		/*
		 * 교재 109~113
		 */

		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(배): result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): result = " + resultIndex);

	}

}
