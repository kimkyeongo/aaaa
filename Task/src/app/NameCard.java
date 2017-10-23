package app;

import java.util.HashMap;
import java.util.Scanner;

import lib.*;

public class NameCard {
	
	static HashMap<Integer, Person> cards = new HashMap<>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		InputCard ic = new InputCard();
		ViewCard vc = new ViewCard();
		CardStore lc = new CardStore();
		
		int sel = 99;
		try {
			lc.loadBook(cards);
		} catch (Exception e) {
				System.out.println("[!] 파일 로드 중 문제가 발생하였습니다.");
				sel = 0;
		}
		
		while (sel != 0) {
			showMenu();
			try {
				sel = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				sel = 99;
				System.out.println("[!] ERROR : 입력된 값이 숫자가 아닙니다.");
			}
			
			switch (sel) {
				case 1: // 목록
					vc.findCard(cards, scan);
					break;
				case 2: // 검색
					vc.findCard(cards, scan, 0);
					break;
				case 3: // 입력
					ic.add(cards, scan);
					break;
				case 4: // 삭제
					vc.findCard(cards, scan, 4);
					break;
				default:
					if (sel != 0) {
						System.out.println("[!] ERROR : 1 ~ 4의 숫자를 입력해주세요.");
					} else {
						System.out.println("# 이용해주셔서 감사합니다.");
					}
			}
		}
		scan.close();
	}
	
	static void showMenu() {
		System.out.println();
		System.out.println("# 안녕하세요 명함관리 프로그램입니다.");
		System.out.println("# 0. 종료");
		System.out.println("# 1. 목록 보기");
		System.out.println("# 2. 명함 검색");
		System.out.println("# 3. 명함 입력");
		System.out.println("# 4. 명함 삭제");
		System.out.print  ("# 원하시는 메뉴의 번호를 입력하세요 : ");
	}

}
