package ch08;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
	}

	public boolean Delete(int element) {// 전달된 element 값이 존재 하면 삭제하고 true로 리턴
		Node1 p = first;
		Node1 q = null;
		while (p != null) {
			if (p.data == element) {
				if (p == first) {
					first = p.link;
				} else {
					q.link = p.link;
				}
				return true;
			}
			q = p;
			p = p.link;
		}
		return false;
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		while (p != null) {
			System.out.print(p.data + " ");
			p = p.link;
		}
	}

	public void Add(int element) {// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node1 newNode = new Node1(element);
		if (first == null) {// insert into empty list
			first = newNode;
			return;
		}
		if (element <= first.data) {
			newNode.link = first;
			first = newNode;
			return;
		}

		Node1 p = first;
		Node1 q = null;

		while (p != null) {
			if (element > p.data) {
				q = p;
				p = p.link;
			} else {
				break;
			}
		}
		if (p == null) {
			q.link = newNode;
		} else {
			newNode.link = p;
			q.link = newNode;
		}
	}

	public boolean Search(int data) { // 전달된 data 값을 찾아 존재하면 true로 리턴, 없으면 false로 리턴
		Node1 ptr = first;
		while (ptr != null) {
			if (ptr.data == data) {
				return true;
			}
			ptr = ptr.link;
		}
		return false;
	}

//	void Merge(LinkedList1 b) {
//		/*
//		 * 연결리스트 a,b에 대하여 a = a + b
//		 * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현
//		 * 난이도 등급: 최상
//		 * a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
//		 */
//	}
}

public class ch08_1 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		// "Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		// 생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		// enum 상수가 언제 정의되는가를 찾아 보아야 한다
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다
			message = string;
			System.out.println("\nMenu 생성자 호출 :: " + message);
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
				// n%3은 3으로 나누어 나머지를 계산한다
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())// 메뉴 출력시에 다음행에 출력하라는 의미
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();// 메뉴 선택 번호로 입력된 값이 key이다
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());// 입력된 key가 음수이거나 Exit에 대한 enum 숫자보다 크면 다시 입력받는다
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 참조 변수일 뿐이다
		Random rand = new Random();
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int count = 0; // 난수 생성 갯수
		int data = 0;
		do {
			switch (menu = SelectMenu()) {// Menu 생성자 호출 - menu 객체를 리턴한다
			case Add: // 난수를 삽입하는데 올림차순으로 정렬되도록 구현
				data = rand.nextInt(50);
				l.Add(data);
				System.out.print("삽입 후 리스트 : ");
				l.Show();
				System.out.println();
				break;
			case Delete:
				System.out.println("삭제할 값을 입력: ");
				data = sc.nextInt();
				boolean tag = l.Delete(data);
				System.out.println("삭제 데이터 존재여부: " + tag);
				System.out.print("삭제 후 리스트 : ");
				l.Show();
				System.out.println();
				break;
			case Show: // 리스트 전체를 출력
				System.out.print("리스트 : ");
				l.Show();
				System.out.println();
				break;
			case Search: // 입력 숫자 n을 검색한다.
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
//			case Merge://리스트 l과 l2를 합병하여 올림차순 정렬이 되게 구현한다 
//				LinkedList1 l2 = new LinkedList1();
//				for (int i =0; i < count; i++) {
//					data = rand.nextInt(20);
//					l2.Add(data);
//				}
//				l.Merge(l2);//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
//				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
