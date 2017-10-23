package com.kedu.namecard.lib;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kedu.namecard.vo.NameCardVO;

public class NameCardUpdate {
	StringBuilder sb = null;
	public StringBuilder update(String commack){
		sb = new StringBuilder();
		int i = 0;
		sb.append("UPDATE namecard SET 	");
		if(commack.contains("1")){
			++i;
			sb.append("name = ? 			");
		}
		if(commack.contains("2")){
			if(i>0 && i<commack.length()){
				sb.append(",");
			}
			sb.append("mobile = ? 			");
			++i;
		}
		if(commack.contains("3")){
			if(i>0 && i<commack.length()){
				sb.append(",");
			}
			sb.append("email = ? 			");
			++i;
		}
		if(commack.contains("4")){
			sb.append("company = ? 			");
		}
		sb.append("WHERE NO = ? ");
		
		return sb;
	}
	
	public PreparedStatement setPs(NameCardVO nVo,PreparedStatement pstmt,String commack) throws SQLException {
			if(commack.contains("1")){
				pstmt.setString(1, nVo.getName());
			}
			if(commack.contains("2")){
				pstmt.setString(commack.indexOf("2")+1, nVo.getMobile());
			}
			if(commack.contains("3")){
				pstmt.setString(commack.indexOf("3")+1, nVo.getEmail());
			}
			if(commack.contains("4")){
				pstmt.setString(commack.indexOf("4")+1, nVo.getCompany());
			}
		pstmt.setInt(commack.length()+1, nVo.getNo());
		return pstmt;
	}
}
