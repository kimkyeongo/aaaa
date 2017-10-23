package com.kedu.namecard.lib;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kedu.namecard.vo.NameCardVO;

public class NameCardInsert {
	StringBuilder sb = null;
	public StringBuilder insert(){
		sb = new StringBuilder();
		sb.append("INSERT INTO namecard ");
		sb.append("VALUES(namecard_no_seq.nextval	");
		sb.append("      ,			?			 	");
		sb.append("      ,			?			 	");
		sb.append("      ,			?			 	");
		sb.append("      ,			?			 	");
		sb.append(")");
		
		return sb;
	}
	
	public PreparedStatement setPs(NameCardVO nVo,PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, nVo.getName());
		pstmt.setString(2, nVo.getMobile());
		pstmt.setString(3, nVo.getEmail());
		pstmt.setString(4, nVo.getCompany());
		return pstmt;
	}
}
