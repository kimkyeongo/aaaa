package com.kedu.namecard.lib;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectPrint {
	public void rsPrint(ResultSet rs) throws SQLException{
		while (rs.next()) {
			print(" 인덱스 : "+rs.getString("no")
			   +"\n 이름 : "+rs.getString("name")
			   +"\n 핸드폰번호 : "+rs.getString("mobile")
			   +"\n 이메일 : "+rs.getString("email")
			   +"\n 회사명 : "+rs.getString("company")
				);
		}
	}
	public void print(String val){
		System.out.println(val);
	}
}
