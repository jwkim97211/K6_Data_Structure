package ch03;

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;
import java.util.List;

public class homework_1 {
	static void showList(String topic, String[] list) {
		System.out.println(topic);
		for (String item : list)
			System.out.print(item + "\t");
	}

	static String[] mergeList(String[] s1, String[] s2) {
		int i = 0, j = 0, k = 0;
		String[] s3 = new String[10];

		while (i < s1.length && j < s2.length) {
			if (s1[i].compareTo(s2[j]) < 0) {
				s3[k] = s1[i];
				i++;
				k++;
			} else if (s1[i].compareTo(s2[j]) > 0) {
				s3[k] = s2[j];
				j++;
				k++;
			}
		}
		while (i == s1.length) {
			s3[k] = s2[j];
			break;
		}
		while (j == s2.length) {
			s3[k] = s1[i];
			break;
		}
		return s3;
	}

	public static void main(String[] args) {
		String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신" };
		String[] s2 = { "독도", "울릉도", "한산도", "영도", "우도" };
		Arrays.sort(s1);
		Arrays.sort(s2);

		showList("s1배열", s1);
		System.out.println();
		showList("s2배열", s2);

		String[] s3 = mergeList(s1, s2);
		System.out.println();
		showList("스트링 배열 s3 = s1 + s2", s3);
	}
}
