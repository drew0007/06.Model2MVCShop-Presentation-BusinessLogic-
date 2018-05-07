package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product) throws Exception{
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.addProduct(product);
		
		return "forward:/product/addProductView.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") String prodNo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Cookie[] cookie = request.getCookies();
		String history = "";
		if(cookie != null) {
			for(int i=0; i<cookie.length; i++) {
				if(cookie[i].getName().equals("history")) {
					history = cookie[i].getValue();
					String[] h = history.split(",");
					for (int j = 0; j < h.length; j++) {
						if (h[j].equals(prodNo)) {
							break;
						}
						if(j == h.length-1) {
							history += ","+prodNo;
						}
					}
				}
			}
		}
		if(history.equals("")) {
			history = prodNo;
		}
		
		Cookie c = new Cookie("history", history);
		c.setMaxAge(60*60);
		response.addCookie(c);
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		
		model.addAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("/listProduct.do")
	public String getProductList(@ModelAttribute("search") Search search, Model model) throws Exception{
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo") String prodNo, Model model) throws Exception{
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		
		model.addAttribute("product", product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("product") Product product, @RequestParam("menu") String menu, Model model) throws Exception{
		
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.updateProduct(product);
		
		model.addAttribute("prodNo", product.getProdNo());
		model.addAttribute("menu", menu);
		
		return "redirect:/getProduct.do";
	}
}
