package com.sun.pojo;
/**
* @author ����:Chaoguo Sun
* @createDate ����ʱ�䣺2018��8��7�� ����10:21:15
*/
public class Employee {
	private int id;
	private String name;
	private String sex;
	private int age;
	private Integer d_id;
	private Dept dept;
	private String pic;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int id, String name, String sex, int age, Integer d_id) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.d_id = d_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Integer getD_id() {
		return d_id;
	}
	public void setD_id(Integer d_id) {
		this.d_id = d_id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	

}
