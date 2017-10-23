package lib;

public class Person {
	private String sex = "";
	private String name = "";
	private String tel = "";
	private String group = "";
	private String title = "";
	
	public String getSex() {
		return sex;
	}
	
	public int getIntSex() {
		int intSex = 0;
		try {
			if (sex.equals("남자")) {
				intSex = 1;
			} else if (sex.equals("여자")) {
				intSex = 2;
			} else {
				intSex = 0;
			}
		} catch (Exception e) {
			intSex = 0;
		}
		return intSex;
	}
	
	public void setSex(String sex) {
		try {
			if (sex.equals("1")) {
				this.sex = "남자";
			} else if (sex.equals("2")) {
				this.sex = "여자";
			} else {
				this.sex = "모름";
			}
		} catch (Exception e) {
			this.sex = "모름";
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return 
				 "\n┌───────────┐"
				+"\n  이름 : "+name
				+"\n  전화 : "+tel
				+"\n  그룹 : "+group
				+"\n  직책 : "+title
				+"\n  성별 : "+sex
				+"\n└───────────┘";
	}
	
}
