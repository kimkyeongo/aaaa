package com.kedu.namecard.vo;

public class NameCardVO {
	private int no;
	private String name = "";
	private String mobile = "";
	private String email = "";
	private String company = "";
	
	public NameCardVO() {
	}
	public NameCardVO(int no
			         ,String name
			         ,String mobile
			         ,String email
			         ,String company) {
		
		this.no = no;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "namecardVO [no=" + no 
						+", name=" + name == null ? "" : name 
						+ ", mobile=" + mobile == null ? "" : mobile
						+ ", email=" + email == null ? "" : email
						+ ", company="	+ company == null ? "" : company + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
