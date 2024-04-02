package ch10;

import java.util.Scanner;

//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//

class Node {
	private int key; // 키값
	Node next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

	public Node(int element) {
		key = element;
		next = null;
	}

	public int getKey() {
		return key;
	}
}

class SimpleChainHash {
	private int size; // 해시 테이블의 크기
	private Node[] table; // 해시 테이블

	public SimpleChainHash(int capacity) {
		table = new Node[capacity];
		this.size = capacity;
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(int key) {
		int hash = key % size;
		Node p = table[hash];

		while (p != null) {
			if (p.getKey() == key)
				return 1;
			p = p.next;
		}
		return -1;
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int key) {
		int hash = key % size;
		Node p = table[hash];
		Node q = null;
		Node temp = new Node(key);

		if (table[hash] == null) {
			table[hash] = temp;
			return 1;
		}
		while (p != null && p.getKey() < key) {
			q = p;
			p = p.next;
		}

		if (p != null && p.getKey() == key)
			return 0;

		if (q == null) {
			temp.next = table[hash];
			table[hash] = temp;
		} else {
			temp.next = p;
			q.next = temp;
		}

		return 1;
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int delete(int key) {
		int hash = key % size;
		Node p = table[hash];
		Node q = null;

		while (p != null) {
			if (p.getKey() == key) {
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

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			Node p = table[i];
			while (p != null) {
				System.out.print(p.getKey() + " ");
				p = p.next;
			}
			System.out.println();
		}
	}
}

public class ch10_3 {

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

//체인법에 의한 해시 사용 예
	public static void main(String[] args) {
		Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				System.out.println();
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data");
				}
				break;
			case Delete:
				// Delete
				System.out.println("Search Key:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
			case Search:
				System.out.println("Search Key:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);

	}
}
