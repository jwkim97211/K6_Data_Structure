package ch03;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현

import java.util.Arrays;
import java.util.Comparator;

class PhyscData3 {
	String name;
	int height;
	double vision;
	
	public PhyscData3(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}
}

class compname implements Comparator<PhyscData3> {

	@Override
	public int compare(PhyscData3 o1, PhyscData3 o2) {
		if (o1.name.compareTo(o2.name) > 0)
			return 1;
		else if (o1.name.compareTo(o2.name) < 0)
			return -1;
		else
			return 0;
	}
}
class compheight implements Comparator<PhyscData3> {
	
	@Override
	public int compare(PhyscData3 o1, PhyscData3 o2) {
		if (o1.height>o2.height)
			return 1;
		else if (o1.height<o2.height)
			return -1;
		else
			return 0;
	}
}

public class ch03_8 {
	static void showData(String topic, PhyscData3[] data) {
		System.out.println(topic + " : ");
		for (PhyscData3 item : data)
			System.out.println(item.toString() + " ");
	}

	public static void main(String[] args) {
		PhyscData3[] data = { new PhyscData3("홍길동", 162, 0.3), 
							  new PhyscData3("홍동", 164, 1.3),
							  new PhyscData3("홍길", 152, 0.7), 
							  new PhyscData3("김홍길동", 172, 0.3), 
							  new PhyscData3("길동", 182, 0.6),
							  new PhyscData3("길동", 167, 0.2), 
							  new PhyscData3("길동", 167, 0.5), 
							  };

		Arrays.sort(data, new compname());	//이진검색은 먼저 정렬을 해야함
		PhyscData3 key1 = new PhyscData3("김홍길동", 172, 0.3);
		int idx1 = Arrays.binarySearch(data, key1, new compname());
		System.out.println("Arrays.binarySearch()를 사용한 이름 정렬 : result = " + idx1);
		System.out.println();
		showData("이름 정렬후 객체 배열", data);
		System.out.println();
		
		Arrays.sort(data, new compheight());
		PhyscData3 key2 = new PhyscData3("길동", 167, 0.2);
		int idx2 = Arrays.binarySearch(data, key2, new compheight());
		System.out.println("Arrays.binarySearch()를 사용한 키 정렬 : result = " + idx2);
		System.out.println();
		showData("키 정렬후 객체 배열", data);
		System.out.println();
		
		PhyscData3 key3 = new PhyscData3("김홍길동", 172, 0.3);
		PhyscData3 key4 = new PhyscData3("홍동", 164, 1.3);
		int idx3 = Arrays.binarySearch(data, key3, new compname());
		System.out.println("Arrays.binarySearch(): result = " + idx3);
		int idx4 = Arrays.binarySearch(data, key4, new compheight());
		System.out.println("Arrays.binarySearch(): result = " + idx4);
	}

}
