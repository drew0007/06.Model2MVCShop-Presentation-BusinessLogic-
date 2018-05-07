package com.model2.mvc.service.user.test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
/*
	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		product.setProdName("테스트용 상품");
		product.setProdDetail("테스트용 상품 상세 정보");
		product.setPrice(9876);
		product.setFileName("");
		product.setManuDate("20180430");
		product.setProdCnt(100);
		
		productService.addProduct(product);
		
		product = productService.getProduct(10037);
		
		//==> API 확인
		Assert.assertEquals("테스트용 상품", product.getProdName());
		Assert.assertEquals("테스트용 상품 상세 정보", product.getProdDetail());
		Assert.assertEquals(9876, product.getPrice());
		Assert.assertEquals("20180430", product.getManuDate());
		Assert.assertEquals(100, product.getProdCnt());
		Assert.assertNull(product.getFileName());
	}
	
	//@Test
	public void testGetProduct() throws Exception {

		Product product = new Product();

		product = productService.getProduct(10037);

		Assert.assertEquals("테스트용 상품", product.getProdName());
		Assert.assertEquals("테스트용 상품 상세 정보", product.getProdDetail());
		Assert.assertEquals(9876, product.getPrice());
		Assert.assertEquals("20180430", product.getManuDate());
		Assert.assertEquals(100, product.getProdCnt());
		Assert.assertNull(product.getFileName());

		//Assert.assertNotNull(userService.getUser("user02"));
		//Assert.assertNotNull(userService.getUser("user05"));
	}
		
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10036);
		Assert.assertNotNull(product);

		Assert.assertEquals("테스트용 상품", product.getProdName());
		Assert.assertEquals("테스트용 상품 상세 정보", product.getProdDetail());
		Assert.assertEquals(9876, product.getPrice());
		Assert.assertEquals("20180430", product.getManuDate());
		Assert.assertEquals(100, product.getProdCnt());
		Assert.assertNull(product.getFileName());
		

		product.setProdName("변경된 테스트 상품");
		product.setProdDetail("zzzzz");
		product.setPrice(111111111);
		product.setManuDate("20200202");
		product.setProdCnt(999);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10036);
		Assert.assertNotNull(product);

		Assert.assertEquals("변경된 테스트 상품", product.getProdName());
		Assert.assertEquals("zzzzz", product.getProdDetail());
		Assert.assertEquals(111111111, product.getPrice());
		Assert.assertEquals("20200202", product.getManuDate());
		Assert.assertEquals(999, product.getProdCnt());
		Assert.assertNull(product.getFileName());
	 }
	*/
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(5, list.size());
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(5, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 /*
	 @Test
	 public void testGetProductListByProdName() throws Exception{
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("자");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(5);
	 	search.setSearchCondition("0");
		search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(5, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 }*/
}