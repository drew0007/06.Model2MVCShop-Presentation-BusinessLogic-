package com.model2.mvc.service.user.test;

import java.sql.Date;
import java.util.ArrayList;
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
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	public void testAddPurchase() throws Exception {
		List<Purchase> list = new ArrayList<Purchase>();
		
		User user = new User();
		user.setUserId("user01");
		
		Product product = new Product();
		product.setProdNo(10036);

		Purchase purchase = new Purchase();
		purchase.setTranCode("1");
		purchase.setTranCnt(100);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("SCOTT");
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);

		User user2 = new User();
		user2.setUserId("user01");

		Product product2 = new Product();
		product2.setProdNo(10037);

		Purchase purchase2 = new Purchase();
		purchase2.setTranCode("1");
		purchase2.setTranCnt(50);
		purchase2.setPaymentOption("2");
		purchase2.setReceiverName("SCOTT");
		purchase2.setBuyer(user2);
		purchase2.setPurchaseProd(product2);
		
		list.add(purchase);
		list.add(purchase2);

		purchaseService.addPurchase(list);
	}
	/*
	
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
	*/
		
	//@Test
	public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = new Purchase();
		purchase.setTranNo(10000);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("변경");
		purchase.setReceiverPhone("999");
		purchase.setDivyAddr("띠용");
		purchase.setDivyRequest("없다");
		
		purchaseService.updatePurchase(purchase);
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception{	
		Product product = new Product();
		product.setProdNo(10023);
		
		Purchase purchase = new Purchase();
		purchase.setTranNo(10071);
		purchase.setTranCode("5");
		purchase.setTranCnt(1);
		purchase.setPurchaseProd(product);
		
		purchaseService.updateTranCode(purchase);
	}
	
	//@Test
	public void testGetPurchaseList() throws Exception{
	 
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(5, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(5);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = purchaseService.getPurchaseList(search, "user01");
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(5, list.size());
		
		//==> console 확인
		//System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	@Test
	public void testGetSaleList() throws Exception{
	
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		Map<String,Object> map = purchaseService.getSaleList(search, 10000);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(5, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(5);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = purchaseService.getSaleList(search, 10000);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(5, list.size());
		
		//==> console 확인
		//System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
}