package com.model2.mvc.web.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {

	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(@RequestParam(value="prodNo", required=false) String prodNo, 
										@RequestParam(value="tranCnt", required=false) String tranCnt, 
										@RequestParam(value="cartList", required=false) String cartList) throws Exception{

		ModelAndView modelAndView = new ModelAndView();

		if (prodNo != null) {
			
			Product product = productService.getProduct(Integer.parseInt(prodNo));
			
			Cart cart = new Cart();
			cart.setCartProd(product);
			cart.setCartCnt(Integer.parseInt(tranCnt));
			
			List<Cart> list = new ArrayList<Cart>();
			list.add(cart);
			
			modelAndView.addObject("list", list);
			
		}else {

			Map<String, Object> map = cartService.getCartList2(cartList);

			modelAndView.addObject("list", map.get("list"));
		}
		
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(@ModelAttribute("purchase") Purchase purchase,
									@RequestParam("prodNoList") String prodNoList, 
									@RequestParam("tranCntList") String tranCntList,
									HttpSession session) throws Exception{
		
		String[] prodNo = prodNoList.split(",");
		String[] tranCnt = tranCntList.split(",");
		
		
		List<Purchase> list = new ArrayList<Purchase>();
		for(int i=0; i<prodNo.length; i++) {
			Product product = new Product();
			product.setProdNo(Integer.parseInt(prodNo[i]));
			
			User user = (User)session.getAttribute("user");
			
			Purchase purchase2 = new Purchase();
			purchase2.setPurchaseProd(product);
			purchase2.setBuyer(user);
			purchase2.setDivyAddr(purchase.getDivyAddr());
			purchase2.setDivyRequest(purchase.getDivyRequest());
			purchase2.setPaymentOption(purchase.getPaymentOption());
			purchase2.setReceiverName(purchase.getReceiverName());
			purchase2.setReceiverPhone(purchase.getReceiverPhone());
			purchase2.setDivyDate(purchase.getDivyDate().replaceAll("-", ""));
			purchase2.setTranCode("1");
			purchase2.setTranCnt(Integer.parseInt(tranCnt[i]));
			
			list.add(purchase2);
		}
		
		int tranNo = purchaseService.addPurchase(list);
		
		Map<String, Object> map = purchaseService.getPurchase(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/getPurchase.do")
	public ModelAndView getPurchase(@RequestParam("tranNo") String tranNo) throws Exception{

		Map<String, Object> map = purchaseService.getPurchase(Integer.parseInt(tranNo));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.setViewName("/purchase/getPurchase.jsp");

		return modelAndView;
	}
	
	@RequestMapping("/historyPurchase.do")
	public ModelAndView historyPurchase(@ModelAttribute("search") Search search,
										@RequestParam("prodNo") String prodNo) throws Exception{
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getSaleList(search, Integer.parseInt(prodNo));
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		
		if(prodNo.equals("0")) {
			modelAndView.setViewName("/purchase/saleListPurchase.jsp");
		}else {
			modelAndView.setViewName("/purchase/historyPurchase.jsp");
		}
		System.out.println(modelAndView.getViewName());
		
		return modelAndView;
	}
	
	@RequestMapping("/listPurchase.do")
	public ModelAndView listPurchase(@ModelAttribute("search") Search search,
									 HttpSession session) throws Exception{

		User user = (User)session.getAttribute("user");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/updatePurchase.do")
	public ModelAndView updatePurchase(@ModelAttribute("purchase") Purchase purchase,
									   @RequestParam("tranNo") String tranNo) throws Exception{
		
		purchase.setTranNo(Integer.parseInt(tranNo));
		purchase.setDivyDate(purchase.getDivyDate().replaceAll("-", ""));
		
		purchaseService.updatePurchase(purchase);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tranNo", tranNo);
		
		modelAndView.setViewName("/getPurchase.do");
		
		return modelAndView;
	}
	
	@RequestMapping("/updatePurchaseView.do")
	public ModelAndView updatePurchaseView(@RequestParam("tranNo") String tranNo) throws Exception{
		
		Map<String, Object> map = purchaseService.getPurchase(Integer.parseInt(tranNo));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		
		modelAndView.setViewName("/purchase/updatePurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/updateTranCode.do")
	public ModelAndView updateTranCode(@RequestParam("tranNo") String tranNo,
									   @RequestParam("tranCnt") String tranCnt,
									   @RequestParam("prodNo") String prodNo,
									   @RequestParam("tranCode") String tranCode) throws Exception{

		Product product = new Product();
		product.setProdNo(Integer.parseInt(prodNo));
		
		Purchase purchase = new Purchase();
		purchase.setTranNo(Integer.parseInt(tranNo));
		purchase.setTranCnt(Integer.parseInt(tranCnt));
		purchase.setTranCode(tranCode);
		purchase.setPurchaseProd(product);
		
		purchaseService.updateTranCode(purchase);
		
		ModelAndView modelAndView = new ModelAndView();

		if (tranCode.equals("2") || tranCode.equals("5")) {
			modelAndView.addObject("prodNo", prodNo);
			modelAndView.setViewName("/historyPurchase.do");
		}else {
			modelAndView.setViewName("/listPurchase.do");
		}
		
		return modelAndView;
	}
}
