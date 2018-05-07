package com.model2.mvc.service.product;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


public interface ProductService {

	//상품 등록
	public void addProduct(Product product) throws Exception;

	//상품 조회
	public Product getProduct(int prodNo) throws Exception;

	//상품 리스트 조회
	public Map<String,Object> getProductList(Search search) throws Exception;

	//상품 정보 수정
	public void updateProduct(Product product) throws Exception;
	
}