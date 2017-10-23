package com.kedu.namecard.lib;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kedu.namecard.vo.NameCardVO;

public class NameCardSelect {
	StringBuilder sb = null;
	public StringBuilder selectAll(){
		sb = new StringBuilder();
		sb.append("SELECT * 		");
		sb.append("  FROM namecard	");
		return sb;
	}
	public StringBuilder selectOne(int key){
		sb = new StringBuilder();
		sb.append("SELECT * 		");
		sb.append("  FROM namecard	");
		sb.append(" WHERE 1=1		");
		switch (key) {
		case 1:
			sb.append(" AND NO = ?		");
			break;
		case 2:
			sb.append(" AND NAME = ?	");
			break;
		case 3:
			sb.append(" AND mobile = ?	");
			break;
		case 4:
			sb.append(" AND email = ?	");
			break;
		case 5:
			sb.append(" AND company = ?	");
			break;
		}
		return sb;
	}
	public PreparedStatement setPs(int key,PreparedStatement pstmt,NameCardVO nVo) throws SQLException {
		switch (key) {
		case 1:
			pstmt.setInt(1, nVo.getNo());
			break;
		case 2:
			pstmt.setString(1, nVo.getName());
			break;
		case 3:
			pstmt.setString(1, nVo.getMobile());
			break;
		case 4:
			pstmt.setString(1, nVo.getEmail());
			break;
		case 5:
			pstmt.setString(1, nVo.getCompany());
			break;
		}
		return pstmt;
	}
}
