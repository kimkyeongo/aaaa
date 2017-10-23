package com.kedu.namecard.lib;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kedu.namecard.vo.NameCardVO;

public class NameCardDelete {
	StringBuilder sb = null;
	public StringBuilder delete(){
		sb = new StringBuilder();
		sb.append("DELETE FROM namecard WHERE NO = ?");
		return sb;
	}
	
	public PreparedStatement setPs(NameCardVO nVo,PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, nVo.getNo());
		return pstmt;
	}
}
