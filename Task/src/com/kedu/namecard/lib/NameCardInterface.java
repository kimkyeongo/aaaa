package com.kedu.namecard.lib;

import java.sql.SQLException;

import com.kedu.namecard.vo.NameCardVO;

public class NameCardInterface {
	NameCardVO nVo = null;
	String commack;
	InputScan scan;
	public void start(){
		scan = new InputScan();
		Connecting cn = new Connecting();
		NameCardPattern pat = new NameCardPattern();
		int ck = 0;
		do{
			mainInterface();// 메뉴 1 등록 2 조회 3 종료
			ck = scan.intInput(1, 3);
			switch (ck) {
			case 1:
				cn.conInsert(insertInput(pat,scan)); // 메뉴 및 입력받은 후  db 등록
				break;
			case 2:
				cn.conSelect(1,0,new NameCardVO()); // 전체 조회
				subInterface(cn,scan,pat); // 1 상세검색 2 수정 3 삭제
				break;
			case 3:
				print(" 종료합니다.");
				break;
			}
		}while(ck != 3);
		try {
			cn.closeCon();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void mainInterface(){
		print(" 메뉴를 선택하세요 \n"
			+ " [ 1 ]. 등록 \n"
			+ " [ 2 ]. 조회 \n"
			+ " [ 3 ]. 종료");
	}
	
	public void subInterface(Connecting cn,InputScan scan,NameCardPattern pat){
		int check = 0;
		while (check != 3) {
			print(" 메뉴를 선택하세요 \n"
				+ " [ 1 ]. 상세검색 \n"
				+ " [ 2 ]. 삭제 \n"
				+ " [ 3 ]. 이전");
			check = scan.intInput(1, 3);
			switch (check) {
				case 1:
					detailSerch(cn,scan,pat);
					break;
				case 2:
					cn.condelete(deleteInput(scan));
					break;
			}
		}
	}
	
	
	public NameCardVO deleteInput(InputScan scan){
		nVo = new NameCardVO();
		print(" 삭제할 인덱스를 입력해주세요");
		nVo.setNo(scan.intInput());
		return nVo;
	}
	
	public NameCardVO updateInput(NameCardPattern pat,InputScan scan,NameCardVO nVo){
		commack = "";
		print(" 이름을 수정 하시겠습니까? \n"
			+ " 1. 입력 \n"
			+ " 2. 입력안함");
		if(scan.intInput(1, 2) == 1){
			print(" 이름을 입력해주세요");
			commack += "1";
			nVo.setName(pat.reg("^[가-힣]*"
					, scan.StringInput()
					, "한글을 입력해주세요"
					));
		}
		
		print(" 핸드폰 번호를 수정 하시겠습니까? "
			+ "( '-' 없이 입력하세요 ) \n"
			+ " 1. 입력 \n"
			+ " 2. 입력안함");
		if(scan.intInput(1, 2) == 1){
			print(" 핸드폰 번호를 입력해주세요");
			commack += "2";
			nVo.setMobile(pat.reg("^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$"
					, scan.StringInput()
					, "전화번호를 입력해주세요"
					));
		}
		
		print(" 이메일을 수정 하시겠습니까? \n"
				+ " 1. 입력 \n"
				+ " 2. 입력안함");
		if(scan.intInput(1, 2) == 1){
			print(" 이메일을 입력해주세요");
			commack += "3";
			print(" ( 이메일 형식입력 : xxx@xxx.xxx ");
			nVo.setEmail(pat.reg("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
					, scan.StringInput()
					, "이메일을 입력해주세요"));
		}

		print(" 회사명을 수정 하시겠습니까? \n"
				+ " 1. 입력 \n"
				+ " 2. 입력안함");
		if(scan.intInput(1, 2) == 1){
			print(" 회사명을 입력해주세요");
			commack += "4";
			nVo.setCompany(scan.StringInput());
		}
		return nVo;
	}
	
	public NameCardVO insertInput(NameCardPattern pat,InputScan scan){
		nVo = new NameCardVO();
		print(" 이름을 입력하세요");
		nVo.setName(pat.reg("^[가-힣]*"
				, scan.StringInput()
				, "한글이름을 입력해주세요"
				));
		
		print(" 핸드폰 번호를 입력하세요 \n "
			+ "( '-' 없이 입력하세요 )");
		nVo.setMobile(pat.reg("^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$"
				, scan.StringInput()
				, "전화번호를 입력해주세요"
				));
		
		print(" 이메일을 입력하시겠습니까? \n"
			+ " 1. 입력 \n"
			+ " 2. 입력안함");
		if(scan.intInput(1, 2) == 1){
			print(" 이메일 형식으로 입력해주세요  \n ex) xxx@xxx.xxx ");
			nVo.setEmail(pat.reg("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
					, scan.StringInput()
					, "이메일을 입력해주세요"));
		}
		
		print(" 회사명을 입력하시겠습니까? \n"
			+ " 1. 입력 \n"
			+ " 2. 입력안함");
		if(scan.intInput(1, 2) == 1){
			print("회사명을 입력해주세요.");
			nVo.setCompany(scan.StringInput());
		}
		
		return nVo;
	}
	
	public void detailSerch(Connecting cn,InputScan scan,NameCardPattern pat){
		nVo = new NameCardVO();
		print(" [ 1 ]. 인덱스 검색 \n"
			+ " [ 2 ]. 이름 검색 \n"
			+ " [ 3 ]. 전화번호 검색 \n"
			+ " [ 4 ]. 이메일 검색 \n"
			+ " [ 5 ]. 회사 검색 \n"
			+ " [ 6 ]. 키워드 검색 " );
		int input = scan.intInput(1, 6);
		switch (input) {
			case 1:
				print(" 검색할 인덱스를 입력하세요");
				nVo.setNo(scan.intInput());
				break;
			case 2:
				print(" 검색할 이름을 입력하세요");
				nVo.setName(pat.reg("^[가-힣]*"
						, scan.StringInput()
						, "한글을 입력해주세요"
						));
				break;
			case 3:
				print(" 검색할 전화번호를 입력하세요 \n "
					+ "( '-' 없이 입력하세요 )");
				nVo.setMobile(pat.reg("^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$"
						, scan.StringInput()
						, "전화번호를 입력해주세요"
						));
				break;
			case 4:
				print(" 검색할 이메일 입력하세요 \n"
					+ " ( 이메일 형식입력 : xxx@xxx.xxx ");
				nVo.setEmail(pat.reg("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
						, scan.StringInput()
						, "이메일을 입력해주세요"));
				break;
			case 5:
				print(" 검색할 회사명을 입력하세요");
				nVo.setCompany(scan.StringInput());
				break;
			case 6:
				print(" 검색할 키워드를 입력하세요");
				//키워드 검색
				break;
		}
		cn.conSelect(2, input,nVo); // 여기서 상세 검색을 해야하지
		
		insInterface(cn,scan,pat,nVo);
	}
	
	public void insInterface(Connecting cn,InputScan scan,NameCardPattern pat,NameCardVO nVo){
		print(" 수정 할수 있습니다. 수정 하시겠습니까? \n" 
				+ " [ 1 ]. 수정"
				+ " [ 2 ]. 이전");
		
		if(scan.intInput(1, 2) == 1){
			cn.conUpdate(updateInput(pat,scan,nVo),commack);
		}
	}
	
	public void print(String val){
		System.out.println(val);
	}
}
