package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.product.ProductDAO;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	///Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDAO productDAO;
	public void setProductDao(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}
	
	public void addProduct(Product product) throws Exception{
		productDAO.addProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.getProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {

		return productDAO.getProductList(search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDAO.updateProduct(product);
	}

}
