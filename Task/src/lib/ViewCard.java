package lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class ViewCard {
	
	public void findCard (HashMap<Integer, Person> cards, Scanner scan) {
		findCard(cards, scan, 0, 0, "");
	}
	
	public void findCard (HashMap<Integer, Person> cards, Scanner scan, int mtd) {
		int type = 0;
		do {
			try {
				showFindMenu();
				type = Integer.parseInt(scan.nextLine());
				
				if (type>0 && type<6) {
					System.out.print  ("-> 검색어 입력 : ");
					String val = scan.nextLine();
					
					findCard(cards, scan, mtd, type, val);
					
				} else if (type == 0) {
					System.out.println("[!] 이전 메뉴로 돌아갑니다.");
				} else {
					System.out.println("[!] ERROR : 다시 입력해 주십시오.");
				}
			} catch (Exception e) {
				type = 99;
					System.out.println("[!] ERROR : 다시 입력해 주십시오.");
			}
		} while (type != 0);
	}
	
	public void findCard (HashMap<Integer, Person> cards, Scanner scan, int mtd, int type, String val) {
		Iterator<Integer> keySetIterator = cards.keySet().iterator();
		
		while (keySetIterator.hasNext()) {
			int key = keySetIterator.next();
			String getVal = "";
			
			switch (type) {
				case 1 :
					getVal = (cards.get(key)).getName();
					break;
				case 2 :
					getVal = (cards.get(key)).getTel();
					break;
				case 3 :
					getVal = (cards.get(key)).getGroup();
					break;
				case 4 :
					getVal = (cards.get(key)).getTitle();
					break;
				case 5 :
					getVal = (cards.get(key)).getSex();
					break;
			}
			
			if ((!"".equals(val) && getVal.equals(val)) || type == 0) {
				System.out.println(cards.get(key));
				if (mtd == 4) {
					System.out.println("[!] 삭제를 위한 번호 : "+key);
				}
			}
		}
		
		if (mtd == 4) {
			System.out.print("[!] 삭제하실 데이터 번호 : ");
			int rmVal = Integer.parseInt(scan.nextLine());
			if (rearrangeCards(cards, rmVal)) {
				System.out.println("rearrangeCards");
			}
		}
	}
	
	public boolean rearrangeCards (HashMap<Integer, Person> cards, int rmVal) {
		boolean ret = false;
		boolean chk = false;
		CardStore cs = new CardStore();
		int i = 1;
		
		try {
			cards.remove(rmVal);
			
			Iterator<Integer> keySetIterator = cards.keySet().iterator();
			while (keySetIterator.hasNext()) {
				int key = keySetIterator.next();
				if (i!=1) { chk = true; }
				cs.storeCard(i++, cards.get(key), chk);
			}
			ret = true;
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}
	
	public void showFindMenu () {
		System.out.println();
		System.out.println("# 유형을 선택하세요.");
		System.out.println("# 0. 이전 메뉴");
		System.out.println("# 1. 이름");
		System.out.println("# 2. 전화번호");
		System.out.println("# 3. 그룹명");
		System.out.println("# 4. 직업");
		System.out.println("# 5. 성별");
		System.out.print  ("# 메뉴 입력 : ");
	}

}
