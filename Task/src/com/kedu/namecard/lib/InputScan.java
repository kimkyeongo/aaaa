package com.kedu.namecard.lib;

import java.util.Scanner;

public class InputScan {
	Scanner scan = new Scanner(System.in);
	
	public String StringInput(){
		return scan.nextLine();
	}
	
	public int intInput(int str, int end){
		int input = 0;
		try {
			input = Integer.parseInt(scan.nextLine());
			if(!(input >= str && input <= end)){
				Exception e = new Exception();
				throw e;
			}
		} catch (Exception e) {
			System.out.println(str+" ~ "+end+" 의 숫자만 입력하세요.");
			return intInput(str,end);
		}
		return input;
	}
	
	public int intInput(){
		int input = 0;
		boolean ck = false;
		do {
			try {
				ck = false;
				input = scan.nextInt();
			} catch (Exception e) {
				System.out.println(" 숫자만 입력하세요.");
				ck = true;
			} finally {
				scan.nextLine();
			}
		} while (ck);
		return input;
	}
}
