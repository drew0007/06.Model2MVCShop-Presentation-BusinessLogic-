package com.model2.mvc.service.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseDAO {

	//구매 신청	
	public int addPurchase(List<Purchase> list) throws Exception;

	//구매 상세 조회
	public Map<String,Object> getPurchase(int tranNo) throws Exception;

	//구매 리스트 조회
	public Map<String,Object> getPurchaseList(Search search,String buyerId) throws Exception;

	//판매 리스트 조회
	public Map<String,Object> getSaleList(Search search,int prodNo) throws Exception;

	//구매 정보 수정
	public void updatePurchase(Purchase purchase) throws Exception;

	//배송 상태 변경
	public void updateTranCode(Purchase purchase) throws Exception;
	
	//list count
	public int getTotalCount(Search search) throws Exception ;
	
}