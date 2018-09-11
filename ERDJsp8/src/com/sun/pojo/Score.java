package com.sun.pojo;
/**
* @author ����:Chaoguo Sun
* @createDate ����ʱ�䣺2018��8��8�� ����4:31:34
*/
public class Score {
	private  Integer id;
	private int e_id;
	private int p_id;
	private Integer value;
	private String grade;
	private Employee employee;
	private Project project;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Score(Integer id, int e_id, int p_id, int value, String grade) {
		super();
		this.id = id;
		this.e_id = e_id;
		this.p_id = p_id;
		this.value = value;
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	

}
