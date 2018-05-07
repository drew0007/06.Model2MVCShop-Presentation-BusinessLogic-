package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDAO;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;


@Repository("cartDaoImpl")
public class CartDaoImpl implements CartDAO{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public CartDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("CartMapper.addCart", cart);
	}

	@Override
	public Map<String, Object> getCartList(Search search, String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> mapIn = new HashMap<String, Object>();
		mapIn.put("search", search);
		mapIn.put("userId", userId);	
		
		int totalCount = sqlSession.selectOne("CartMapper.getCartListTotalCount", mapIn);
		List<Cart> list = sqlSession.selectList("CartMapper.getCartList", mapIn);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getCartList2(String cartNo) throws Exception {
		// TODO Auto-generated method stub
		String[] cartList = cartNo.split(",");

		int totalCount = sqlSession.selectOne("CartMapper.getCartList2TotalCount", cartList);
		List<Cart> list = sqlSession.selectList("CartMapper.getCartList2", cartList);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}

	@Override
	public void updateCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("CartMapper.updateCart", cart);
	}

	@Override
	public void deleteCart(int cartNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("CartMapper.deleteCart", cartNo);
	}

	@Override
	public Cart getCart(String userId, int prodNo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> mapIn = new HashMap<String, Object>();
		mapIn.put("userId", userId);
		mapIn.put("prodNo", prodNo);
		
		return sqlSession.selectOne("CartMapper.getCart", mapIn);
	}

}
