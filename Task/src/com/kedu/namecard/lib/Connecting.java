package com.kedu.namecard.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kedu.namecard.vo.NameCardVO;

public class Connecting {
	private String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String USER = "kedu";
	private String PASS = "1234";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Connecting() {
		con();
	}

	public Connecting(String USER,String PASS) {
		this.USER = USER;
		this.PASS = PASS;
		con();
	}
	public Connecting(String URL, String USER, String PASS) {
		this.URL = URL;
		this.USER = USER;
		this.PASS = PASS;
		con();
	}

	public void con(){
		try {
			con = DriverManager.getConnection(this.URL,this.USER,this.PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void conSelect(int flag, int key, NameCardVO nVo) { // flag 가 1 일때 전체
																// 2일때 조건검색 3일때
		NameCardSelect cs = new NameCardSelect();
		SelectPrint sp = new SelectPrint();
		try {
			switch (flag) {
			case 1:
				pstmt = this.con.prepareStatement(cs.selectAll().toString());
				break;
			case 2:
				pstmt = this.con.prepareStatement(cs.selectOne(key).toString());
				pstmt = cs.setPs(key, pstmt, nVo);
				break;
			}

			rs = pstmt.executeQuery();
			sp.rsPrint(rs);
			if (rs == null) {
				System.out.println("값이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRs(rs);
				closePst(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void conInsert(NameCardVO nVo) {
		NameCardInsert ci = new NameCardInsert();
		try {
			pstmt = this.con.prepareStatement(ci.insert().toString());
			pstmt = ci.setPs(nVo, pstmt);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상 등록");
			} else {
				System.out.println("비정상");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRs(rs);
				closePst(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void conUpdate(NameCardVO nVo, String commack) {
		NameCardUpdate cu = new NameCardUpdate();
		try {
			this.con.setAutoCommit(false);
			pstmt = this.con.prepareStatement(cu.update(commack).toString());
			pstmt = cu.setPs(nVo, pstmt, commack);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상 등록");
			} else {
				System.out.println("비정상");
			}
			this.con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				closeRs(rs);
				closePst(pstmt);
				this.con.setAutoCommit(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void condelete(NameCardVO nVo) {
		NameCardDelete cd = new NameCardDelete();
		try {
			pstmt = this.con.prepareStatement(cd.delete().toString());
			pstmt = cd.setPs(nVo, pstmt);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상");
			} else {
				System.out.println("비정상");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRs(rs);
				closePst(pstmt);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void closeCon() throws SQLException {
		if (this.con != null) {
			this.con.close();
		}
	}

	public void closePst(PreparedStatement pstmt) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
	}

	public void closeRs(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
}
