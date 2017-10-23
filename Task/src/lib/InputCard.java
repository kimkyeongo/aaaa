package lib;

import java.util.HashMap;
import java.util.Scanner;

public class InputCard {
	
	public void add (HashMap<Integer, Person> cards, Scanner scan) {
		String [] inArr = new String[6];
		
		System.out.print("이름 : ");
		inArr[1] = (scan.nextLine()).trim();
		System.out.print("전화(ex. 01X-XXXX-XXXX) : ");
		inArr[2] = (scan.nextLine()).trim();
		System.out.print("그룹 : ");
		inArr[3] = (scan.nextLine()).trim();
		System.out.print("직책 : ");
		inArr[4] = (scan.nextLine()).trim();
		System.out.print("성별(ex. 여=2 | 남=1) : ");
		inArr[5] = (scan.nextLine()).trim();
		
		add(cards, inArr, true);
	}
	
	public void add (HashMap<Integer, Person> cards, String [] inArr) {
		add(cards, inArr, false);
	}
	
	public void add (HashMap<Integer, Person> cards, String [] inArr, boolean chk) {
		Person p = new Person();
		
		p.setName	(inArr[1].trim());
		p.setTel	(inArr[2].trim());
		p.setGroup	(inArr[3].trim());
		p.setTitle	(inArr[4].trim());
		p.setSex	(inArr[5].trim());
		
		
		if (chk) {
			CardStore cs = new CardStore();
			cs.storeCard(cards.size()+1, p);
			cards.put(cards.size()+1, p);
		} else {
			cards.put(Integer.parseInt(inArr[0].trim()), p);
		}
	}
}
