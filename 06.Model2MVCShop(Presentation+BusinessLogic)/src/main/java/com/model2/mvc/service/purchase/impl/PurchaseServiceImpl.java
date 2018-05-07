package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.product.ProductDAO;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	///Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDAO purchaseDAO;
	public void setProductDao(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}

	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public int addPurchase(List<Purchase> list) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDAO.addPurchase(list);
	}

	@Override
	public Map<String, Object> getPurchase(int tranNo) throws Exception {
		return purchaseDAO.getPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDAO.getPurchaseList(search, buyerId);
	}

	@Override
	public Map<String, Object> getSaleList(Search search, int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDAO.getSaleList(search, prodNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDAO.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDAO.updateTranCode(purchase);
	}
	

}
