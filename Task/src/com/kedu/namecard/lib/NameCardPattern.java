package com.kedu.namecard.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameCardPattern {
	Pattern p;
	Matcher m;
	InputScan scan = new InputScan();
	// "^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$" 전화번호
	// "^[가-힣]*" 한글 입력
	//"^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$" 이메일 정규식

	public String reg(String reg, String val, String msg){
		boolean ck = false;
		p = Pattern.compile(reg);
		if(!val.equals("")){
		m = p.matcher(val);
			while(!ck){
				ck = m.matches();
				if(!ck){
					System.out.println(msg);
					val = scan.StringInput();
					m = p.matcher(val);
				}
			}
		}else{
			System.out.println("빈값을 입력할수 없습니다.");
			return reg(reg,scan.StringInput(),msg);
		}
		return val;
	}
}
