package com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	private String fileName;		//	등록파일명
	private String manuDate;		//	생산일
	private int price;				//	가격
	private String prodDetail;		//	상품상세정보
	private String prodName;		//	상품명
	private int prodNo;				//	상품번호
	private Date regDate;			//	등록일
	private String prodTranCode;		//	상품상태
	private int prodCnt;			//	재고수량
	
	public Product(){
	}
	
	public String getProdTranCode() {
		return prodTranCode;
	}
	public void setProdTranCode(String prodTranCode) {
		this.prodTranCode = prodTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getProdCnt() {
		return prodCnt;
	}

	public void setProdCnt(int prodCnt) {
		this.prodCnt = prodCnt;
	}

	// Override
	public String toString() {
		return "Product : [fileName]" + fileName
				+ "[manuDate]" + manuDate+ "[price]" + price + "[prodDetail]" + prodDetail
				+ "[prodName]" + prodName + "[prodNo]" + prodNo + "[prodTranCode]" + prodTranCode
				+ "[prodCnt]" + prodCnt + "[regDate]" + regDate;
	}
}