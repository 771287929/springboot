package com.artbrain.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.artbrain.dao.UserDetailsServiceDAO;
import com.artbrain.entity.Product;
import com.artbrain.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @Autowired
  UserDetailsServiceDAO userDetailsServiceDAO;

  //handle when logged user go to login page
  @RequestMapping("/login")
  public String login(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if(auth instanceof AnonymousAuthenticationToken){
      return "login";
    }else{
      return "home";
    }
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String registration(User newUser){
    try {
      if(userDetailsServiceDAO.loadUserEntityByUsername(newUser.getUsername()) != null){
        return "redirect:" + "/login?registration&error";
      }else{
        userDetailsServiceDAO.saveUser(newUser);
        return "redirect:" + "/login?registration&success";
      }
    }catch (Exception e){
      e.printStackTrace();
      return "redirect:" + "/login?registration&errorServer";
    }
  }

  @RequestMapping("/profile")
  public String profile(Model model) throws ParseException{
	  User user=new User();
	  user.setUsername("周威");
	  
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  Product p1=new Product(sdf.parse("2016-08-19"), new BigDecimal(2.5), "红色");
	  Product p2=new Product(sdf.parse("2016-07-20"), new BigDecimal(2.8), "蓝色");
	  Product p3=new Product(sdf.parse("2016-06-09"), new BigDecimal(3.5), "白色");
	  Product p4=new Product(sdf.parse("2016-08-22"), new BigDecimal(2.0), "黑色");
	  List<Product> productList=new ArrayList<Product>();
	  productList.add(p1);
	  productList.add(p2);
	  productList.add(p3);
	  productList.add(p4);
	  
	  model.addAttribute("user",user);
	  model.addAttribute("productList",productList);
    return "profile";
  }
  
  
  @RequestMapping("/profile/ajax")
  @ResponseBody
  public List<Product> findProduct() throws ParseException{
	  
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  Product p1=new Product(sdf.parse("2016-08-19"), new BigDecimal(2.5), "红色");
	  Product p2=new Product(sdf.parse("2016-07-20"), new BigDecimal(2.8), "蓝色");
	  Product p3=new Product(sdf.parse("2016-06-09"), new BigDecimal(3.5), "白色");
	  Product p4=new Product(sdf.parse("2016-08-22"), new BigDecimal(2.0), "黑色");
	  List<Product> productList=new ArrayList<Product>();
	  productList.add(p1);
	  productList.add(p2);
	  productList.add(p3);
	  productList.add(p4);
    return productList;
  }
}
