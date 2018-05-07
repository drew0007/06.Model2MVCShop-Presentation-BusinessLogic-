package com.model2.mvc.service.cart;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;


public interface CartDAO {
	
	//장바구니 담기
	public void addCart(Cart cart) throws Exception;

	//장바구니 리스트
	public Map<String,Object> getCartList(Search search, String userId) throws Exception;
	
	//선택한 장바구니 리스트
	public Map<String,Object> getCartList2(String cartNo) throws Exception;

	//수량 변경
	public void updateCart(Cart cart) throws Exception;
	
	//장바구니 삭제
	public void deleteCart(int cartNo) throws Exception;
	
	//장바구니 중복 체크용 조회
	public Cart getCart(String userId, int prodNo) throws Exception;

}
