package ch03;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현

import java.util.Arrays;
import java.util.Comparator;

class PhyscData3 {
	String name;
	int height;
	double vision;

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
	static final Comparator<PhyscData3> HEIGHT_ORDER = new compname();

	static void showData(String topic, String[] data) {
		System.out.println(topic + " : ");
		for (String item : data)
			System.out.println(item + " ");
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

		Arrays.binarySearch(data, key, HEIGHT_ORDER);
		Arrays.binarySearch(data, key, new compheight());
		showData("정렬전 객체 배열", data);
		Arrays.sort(data, HEIGHT_ORDER);

		showData("정렬후 객체 배열", data);
		PhyscData3 key = new PhyscData3("길동", 167, 0.2);

		int idx = Arrays.binarySearch(data, key, HEIGHT_ORDER);
		System.out.println("\nArrays.binarySearch(): result = " + idx);
	}

}
