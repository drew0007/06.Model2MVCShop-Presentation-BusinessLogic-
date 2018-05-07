package com.model2.mvc.service.domain;

public class Cart {
	private int cartNo;
	private User cartUser;
	private Product cartProd;
	private int cartCnt;

	public Cart() {
		
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public User getCartUser() {
		return cartUser;
	}

	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}

	public Product getCartProd() {
		return cartProd;
	}

	public void setCartProd(Product cartProd) {
		this.cartProd = cartProd;
	}

	public int getCartCnt() {
		return cartCnt;
	}

	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
	}
	
	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", cartUser=" + cartUser
				+ ", cartProd=" + cartProd + ", cartCnt=" + cartCnt +"]";
	}
}
