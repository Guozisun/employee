package com.sun.utils;


/**
* @author 作者:Chaoguo Sun
* @createDate 创建时间：2018年8月27日 下午2:57:27
* 分页功能
*/
public class PaginiTion {

	private int nowPage;
	private int pages;
	private int beginYe;
	private int endYe;
	private int begin;
	
	
	
	public PaginiTion(int nowPage, int count, int page_size, int page_num ) {
//		super();
		this.nowPage=nowPage;
		if (this.nowPage<=1) {
			this.nowPage=1;
		}
		pages = count % page_size == 0 ? count / page_size : count / page_size + 1;
		
		if (this.nowPage>=pages) {
			this.nowPage=pages;
		}
		
		
		beginYe=this.nowPage-page_num/2;
		if (beginYe<=1) {
			beginYe=1;
		}
		endYe=beginYe+page_num-1;
		if (endYe>=pages) {
			endYe=pages;
			beginYe=endYe-page_num+1;
		}
		if (beginYe<=1) {
			beginYe=1;
		}
		begin = (this.nowPage - 1) * page_size;
		
	}
	
	public int getBegin() {
		return begin;
	}

	public int getNowPage() {
		return nowPage;
	}
	public int getPages() {
		return pages;
	}
	public int getBeginYe() {
		return beginYe;
	}
	public int getEndYe() {
		return endYe;
	}
	

}
