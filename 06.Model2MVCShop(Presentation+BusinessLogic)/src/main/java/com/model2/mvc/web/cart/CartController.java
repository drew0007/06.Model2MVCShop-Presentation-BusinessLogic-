package com.model2.mvc.web.cart;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

@Controller
public class CartController {

	///Field
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;

	public CartController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addCart.do")
	public String addCart(@RequestParam("prodNo") String prodNo,
						  @RequestParam("tranCnt") String tranCnt,
						  HttpSession session) throws Exception{

		User user = (User)session.getAttribute("user");
		
		Product product = new Product();
		product.setProdNo(Integer.parseInt(prodNo));
		
		Cart cart = cartService.getCart(user.getUserId(), Integer.parseInt(prodNo));

		if(cart == null) {
			cart = new Cart();
			cart.setCartCnt(Integer.parseInt(tranCnt));
			cart.setCartProd(product);
			cart.setCartUser(user);
			
			cartService.addCart(cart);
		}else {
			cart.setCartCnt(Integer.parseInt(tranCnt));
			
			cartService.updateCart(cart);
		}
		
		return "forward:/listCart.do";
	}
	
	@RequestMapping("/deleteCart.do")
	public String deleteCart(@RequestParam("cartNo") String cartNo) throws Exception{
		
		cartService.deleteCart(Integer.parseInt(cartNo));
		
		return "forward:/listCart.do";
	}
	
	@RequestMapping("/listCart.do")
	public String listCart(@ModelAttribute("search") Search search, HttpSession session, Model model) throws Exception{

		User user = (User)session.getAttribute("user");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = cartService.getCartList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/cart/listCart.jsp";
	}
	
	@RequestMapping("/updateCart.do")
	public String updateCart(@ModelAttribute("cart") Cart cart) throws Exception{
		
		cartService.updateCart(cart);
		
		return "forward:/listCart.do";
	}
}
