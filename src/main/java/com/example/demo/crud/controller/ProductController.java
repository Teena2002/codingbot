package com.example.demo.crud.controller;

import java.util.List;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.crud.model.Product;
import com.example.demo.crud.service.ProductServices;
 

@Controller
public class ProductController {
	 
    private ProductServices productService;

	public ProductController(ProductServices productService) {
		super();
		this.productService= productService;
	}
	@RequestMapping("/products")
	public List<Product> list() {
	    return productService.listAllPro();
	}
	@RequestMapping("/")
	public String viewHomePage(Model model) {
	    List<Product> listProducts = productService.listAllPro();
	    model.addAttribute("listProducts", listProducts);
	     
	    return "index";
	}
	
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.savePro(product);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = productService.getPro(id);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		productService.deletePro(id);
	    return "redirect:/";       
	}

}
