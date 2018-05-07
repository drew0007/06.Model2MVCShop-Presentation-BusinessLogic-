package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.user.UserDao;


@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDAO{

	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public int addPurchase(List<Purchase> list) throws Exception {
		// TODO Auto-generated method stub
		int tranNo = sqlSession.selectOne("PurchaseMapper.addPurchase");
		
		for(int i=0; i<list.size(); i++) {
			Purchase purchase = list.get(i);
			purchase.setTranNo(tranNo);
			sqlSession.insert("PurchaseMapper.addPurchase2", purchase);
			sqlSession.update("PurchaseMapper.addPurchase3", purchase);
			sqlSession.delete("PurchaseMapper.addPurchase4", purchase);
		}
		
		return tranNo;
	}

	@Override
	public Map<String,Object> getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub		
		int totalCount = sqlSession.selectOne("PurchaseMapper.getPurchaseTotalCount", tranNo);
		List<User> list = sqlSession.selectList("PurchaseMapper.getPurchase", tranNo);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> mapIn = new HashMap<String, Object>();
		mapIn.put("search", search);
		mapIn.put("buyerId", buyerId);	
		
		int totalCount = sqlSession.selectOne("PurchaseMapper.getPurchaseListTotalCount", mapIn);
		List<User> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", mapIn);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search, int prodNo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> mapIn = new HashMap<String, Object>();
		mapIn.put("search", search);
		mapIn.put("prodNo", prodNo);	
		
		int totalCount = sqlSession.selectOne("PurchaseMapper.getSaleListTotalCount", mapIn);
		List<User> list = sqlSession.selectList("PurchaseMapper.getSaleList", mapIn);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
		sqlSession.update("PurchaseMapper.updateTranCode2", purchase);
	}
	

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchaseListCount", search);
	}
	
}