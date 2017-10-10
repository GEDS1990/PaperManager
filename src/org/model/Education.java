package org.model;

public class Education {
	private int educationNo;
	private String educationName;
	private String educationTel;
	private String eduPassword;
	public int getEducationNo() {
		return educationNo;
	}
	public void setEducationNo(int educationNo) {
		this.educationNo = educationNo;
	}
	public String getEducationName() {
		return educationName;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	public String getEducationTel() {
		return educationTel;
	}
	public void setEducationTel(String educationTel) {
		this.educationTel = educationTel;
	}
	public String getEduPassword() {
		return eduPassword;
	}
	public void setEduPassword(String eduPassword) {
		this.eduPassword = eduPassword;
	}
	@Override
	public String toString() {
		return "Educaton [educationNo=" + educationNo + ", educationName=" + educationName + ", educationTel="
				+ educationTel + "]";
	}
	
}
