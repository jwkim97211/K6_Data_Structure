package ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ch03_3_list {
	
	public static void removeElement(List<String> list, int index) {
		list.remove(index);
	}

	static void getList(List<String> list) {
		list.add("서울");
		list.add("북경");
		list.add("상해");
		list.add("서울");
		list.add("도쿄");
		list.add("뉴욕");
		list.add("런던");
		list.add("로마");
		list.add("방콕");
		list.add("북경");
		list.add("도쿄");
		list.add("서울");
		list.add(1, "LA");
	}

	static void showList(String topic, List<String> list) {
		System.out.println(topic + " : ");
		for (String item : list)
			System.out.println(item);
	}

	static void swap(String[] list, int ind1, int ind2) {
		String tmp = list[ind1];
		list[ind1] = list[ind2];
		list[ind2] = tmp;
	}

	static void sortList(List<String> list) {
		//list.sort(String.CASE_INSENSITIVE_ORDER);
		// list.sort(Comparator.naturalOrder());
		// 방법2 리스트를 스트링 배열로 변환
		String cities[] = new String[0];
		cities = list.toArray(cities);
		for (int i = 0; i < cities.length; i++) {
			for (int j = i + 1; j < cities.length; j++) {
				if (cities[i].compareTo(cities[j]) > 0)
					swap(cities, i, j);
			}
		}
		list.clear();
		list.addAll(Arrays.asList(cities));
	}

	static void removeDuplicateList(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if(list.get(i).equals(list.get(j))) {
					removeElement(list,j);
					j--;	//List에서 index가 j인 요소삭제 후 j 뒤에 있는 요소들이 앞으로 떙겨지기 때문에  
				}
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		getList(list);
		showList("입력후", list);
		// sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

//			    Collections.sort(list);

		// 리스트의 정렬
		sortList(list);
		System.out.println();
		showList("정렬후", list);
		// 리스트에서 중복제거
		removeDuplicateList(list);
		System.out.println();
		showList("중복제거후", list);
	}
}
