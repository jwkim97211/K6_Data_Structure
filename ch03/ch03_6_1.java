package ch03;

/*
 * Comparable 구현
 */
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

class PhyscData2 implements Comparable<PhyscData2> {
	String name;
	int height;
	double vision;

	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}

	@Override
	public int compareTo(PhyscData2 p) {
		return this.name.compareTo(p.name);

	}

	public int equals(PhyscData2 p) {
		return this.name.compareTo(p.name);
	}
}

public class ch03_6_1 {
	static void showData(String topic, PhyscData2[] arr) {
		System.out.println(topic + " : ");
		for (PhyscData2 item : arr)
			System.out.print(item + " ");
		System.out.println();
	}
	
	static void swap(PhyscData2[] arr, int ind1, int ind2) {
		PhyscData2 tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
	
	static void reverse(PhyscData2[] a) {
		for (int i = 0; i < a.length / 2; i++)
			swap(a, i, a.length - i - 1);
	}
	
	static void sortData(PhyscData2[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].compareTo(arr[j]) > 0)
					swap(arr, i, j);
			}
		}
	}
	static int linearSearch(PhyscData2 [] data, PhyscData2 key) {
        int index = -1;
        for (int n = 0; n < data.length; n++) {
            if (data[n].equals(key)==0) {
                index = n;
                break;
            }
        }
        return index;
    }
	static int binarySearch(PhyscData2 [] data , PhyscData2 key) {
	       int pl = 0;
	        int pr = data.length - 1;
	        while (pl <= pr) {
	            int pc = (pl + pr) / 2;
	            if (data[pc].compareTo(key)==0) {
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
		PhyscData2[] data = { new PhyscData2("홍길동", 162, 0.3),
							  new PhyscData2("나동", 164, 1.3),
							  new PhyscData2("최길", 152, 0.7), 
							  new PhyscData2("김홍길동", 172, 0.3), 
							  new PhyscData2("박동", 182, 0.6),
							  new PhyscData2("이동", 167, 0.2), 
							  new PhyscData2("길동", 167, 0.5),
							  };
		
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data);// 사용된다 그 이유는?
		showData("Arrays.sort() 정렬후", data);

		PhyscData2 key = new PhyscData2("길동", 167, 0.2);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,02>): result = " + resultIndex);

		key = new PhyscData2("박동", 182, 0.6);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);// comparable를 사용
		System.out.println("\nbinarySearch(<박동,182,0.6>): result = " + resultIndex);
		key = new PhyscData2("이동", 167, 0.6);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);// comparable를 사용
		System.out.println("\nArrays.binarySearch(<이동,167,0.6>): result = " + resultIndex);
	}


}
