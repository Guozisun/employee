package com.sun.pojo;
/**
* @author 作者:Chaoguo Sun
* @createDate 创建时间：2018年8月7日 上午10:21:24
* 部门实体
*/
public class Dept {
	private Integer id;
	private String name;
	private int emp_count;
	private Project project;
	
	

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Dept(Integer id, String name, int emp_count) {
		super();
		this.id = id;
		this.name = name;
		this.emp_count = emp_count;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEmp_count() {
		return emp_count;
	}
	public void setEmp_count(int emp_count) {
		this.emp_count = emp_count;
	}
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

}
