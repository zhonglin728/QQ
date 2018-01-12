package com.eumji.zblog.controller.user.vo;

public class Student {
	

    private String age;
    private Integer sex;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Student() {
		super();
	}
	public Student(String age, Integer sex) {
		super();
		this.age = age;
		this.sex = sex;
	}
	
     


}
