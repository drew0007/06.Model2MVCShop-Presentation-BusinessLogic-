package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDAO;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.purchase.PurchaseDAO;

@Service("cartServiceImpl")
public class CartServiceImpl implements CartService {
	
	///Field
	@Autowired
	@Qualifier("cartDaoImpl")
	private CartDAO cartDAO;
	public void setProductDao(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	@Override
	public void addCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		cartDAO.addCart(cart);
	}

	@Override
	public Map<String, Object> getCartList(Search search, String userId) throws Exception {
		// TODO Auto-generated method stub
		return cartDAO.getCartList(search, userId);
	}

	@Override
	public Map<String, Object> getCartList2(String cartNo) throws Exception {
		// TODO Auto-generated method stub
		return cartDAO.getCartList2(cartNo);
	}

	@Override
	public void updateCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		cartDAO.updateCart(cart);
	}

	@Override
	public void deleteCart(int cartNo) throws Exception {
		// TODO Auto-generated method stub
		cartDAO.deleteCart(cartNo);
	}

	@Override
	public Cart getCart(String userId, int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return cartDAO.getCart(userId, prodNo);
	}

}
