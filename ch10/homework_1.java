package ch10;

import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;

	String no; // 회원번호
	String name; // 이름

	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject5() {
		no = null;
		name = null;
	}

	public void scanData(String string, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(string + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) {
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return (Integer.parseInt(d1.no) - Integer.parseInt(d2.no) > 0) ? 1
					: (Integer.parseInt(d1.no) - Integer.parseInt(d2.no) < 0) ? -1 : 0;
		}
	}
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		// --- 생성자(constructor) ---//
		public Node2(SimpleObject5 element) {
			data = element;
			next = null;
		}
		public String getKey() {
			return data.no;
		}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		table = new Node2[capacity];
		this.size = capacity;
	}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = Integer.parseInt(st.no) % size;
		Node2 p = table[hash];

		while (p != null) {
			if (c.compare(p.data, st)==0)
				return 1;
			p = p.next;
		}
		return -1;
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = Integer.parseInt(st.no) % size;
		Node2 p = table[hash];
		Node2 q = null;
		Node2 temp = new Node2(st);

		if (table[hash] == null) {
			table[hash] = temp;
			return 0;
		}
		while (p != null && c.compare(p.data, st)<0) {
			q = p;
			p = p.next;
		}

		if (p != null && c.compare(p.data, st)==0)
			return 1;

		if (q == null) {
			temp.next = table[hash];
			table[hash] = temp;
		} else {
			temp.next = p;
			q.next = temp;
		}

		return 0;
	}

//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = Integer.parseInt(st.no) % size;
		Node2 p = table[hash];
		Node2 q = null;

		while (p != null) {
			if (c.compare(p.data, st)==0) {
				if (q == null)
					table[hash] = p.next;
				else
					q.next = p.next;
				return 1;
			}
			q = p;
			p = p.next;
		}
		return -1;
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			Node2 p = table[i];
			while (p != null) {
				System.out.print(p.getKey() + " ");
				p = p.next;
			}
			System.out.println();
		}
	}
}

public class homework_1 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
