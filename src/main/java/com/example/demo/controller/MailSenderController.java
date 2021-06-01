package com.example.demo.controller;
import java.util.HashMap;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmailRequestDto;
import com.example.demo.PurchaseDetail;
import com.example.demo.repository.PurchasedDetailsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BasketService;
import com.example.demo.service.MailService;
 

@RestController
@RequestMapping("/product")
public class MailSenderController {
	@Autowired
    private MailService mailService;
	

 
    @GetMapping("/sendmail/{purId}")
    public ResponseEntity<String> sendMail( @PathVariable  ("purId") int purId) {
        Map<String, String> model = new HashMap<>();
       
        EmailRequestDto emailRequest2= new EmailRequestDto("kocdeniz098@gmail.com","kocdeniz098@gmail.com", "Purchase Invoice","User");
        model.put("name", emailRequest2.getName());
        model.put("value", "Thank you for preferring BookShop!!");
        
        String response = mailService.sendMail(emailRequest2, model, purId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
   
    	//@RequestBody EmailRequestDto emailRequest,
    	
    	
    	
    }
	


