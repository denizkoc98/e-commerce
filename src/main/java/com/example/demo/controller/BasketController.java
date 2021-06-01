package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.example.demo.Basket;
import com.example.demo.InvoicePdfExporter;
import com.example.demo.PurchaseDetail;
import com.example.demo.service.BasketService;
import com.itextpdf.text.pdf.codec.Base64;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/product")
public class BasketController {

	@Autowired
	BasketService basketService;
	
	@PostMapping("/basket/{menuItemId}/{customerId}")
    public void addToCart(@PathVariable  ("menuItemId") int menuItemId, 
    		
    		@PathVariable  ("customerId") int customerId ) {
		
         basketService.addToCart(menuItemId,  customerId);
        
    }
	
	@GetMapping("/basket/{customerId}")
    public Basket myCartDisplay(@PathVariable  ("customerId") int customerId ) {
		
         return basketService.myCart( customerId);
       
    }
	
	@GetMapping("/cancelorder/{pId}")
    public List<PurchaseDetail> cancelOrder(@PathVariable  ("pId") int customerId ) {
		
         return basketService.cancelOrder( customerId);
       
    }
	@GetMapping("/mypurchase/{pId}")
    public List<PurchaseDetail> myPurchase(@PathVariable  ("pId") int pId ) {
		
         return basketService.myPurchase( pId);
       
    }
	@GetMapping("/basket/remove/{menuitemId}/{customerId}")
    public Basket remove(@PathVariable  ("menuitemId") int menuitemId ,@PathVariable  ("customerId") int customerId) {
		
         return basketService.removeItem( menuitemId,customerId );
       
    }
	@GetMapping("/basket/add/{menuitemId}/{customerId}")
    public Basket basket(@PathVariable  ("menuitemId") int menuitemId ,@PathVariable  ("customerId") int customerId) {
		
         return basketService.add2( menuitemId,customerId);
       
    }
	
	@GetMapping("/getpurchases")
    public List<PurchaseDetail> purchases() {
		
         return basketService.getPurchases();
         
	}
	@GetMapping("/getpurchase/{pId}")
    public PurchaseDetail getPurchaseByPurchaseId(@PathVariable  ("pId") int pId ) {
		
         return basketService.getPurchaseById(pId);
       
    }
	
	@GetMapping("/basket/purchase/{customerId}")
    public PurchaseDetail purchase(@PathVariable  ("customerId") int customerId ) {
		
        return basketService.doPurchase(customerId);
         
	}
	
	@GetMapping("/askrefund/{purId}/{productId}")
    public PurchaseDetail askRefund(@PathVariable  ("purId") int purId , @PathVariable  ("productId") int productId) {
		
        return basketService.askRefund(purId, productId);
         
	}

	

	

	
	@GetMapping(value = "/downloadFile")
    public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws URISyntaxException {

        File file = new File(getClass().getClassLoader().getResource("templates/more/si.pdf").toURI());

        //viewing in web browser
        response.setContentType("application/pdf");
        //for downloading the file directly if viewing is not possible
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());

        file = null;

        //put the directory architecture according to your target directory
        // generated during compilation in maven spring boot
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("templates/more/si.pdf");

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
    }

	
	

  @RequestMapping(value = "/pdfreport//{purId}", method = RequestMethod.GET, produces =
  MediaType.APPLICATION_PDF_VALUE) public ResponseEntity<InputStreamSource> citiesReport(@PathVariable  ("purId") int purId) throws IOException {
  
  List<PurchaseDetail> purchases = (List<PurchaseDetail>)
  basketService.getPurchases();
  
  PurchaseDetail pd= basketService.getOnePurchase(purId);
  
  InputStreamSource bis = InvoicePdfExporter.citiesReport(pd);
  
 
  HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
  "inline; filename=citiesreport.pdf");
  
  return
  ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
  .body((bis)) ; } }
 
	
	
//	
//
//	
	/*
	 * @GetMapping("/pdfreport") public void citiesReport(HttpServletResponse
	 * response) throws IOException {
	 * 
	 * List<PurchaseDetail> cities = basketService.getPurchases();
	 * 
	 * ByteArrayInputStream bis = InvoicePdfExporter.citiesReport(cities); String
	 * value = "R04jArrrw45jNH6bV02="; byte[] buffer = new byte[8192];
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); int bytesRead;
	 * while ((bytesRead = bis.read(buffer)) != -1) { baos.write(buffer, 0,
	 * bytesRead); }
	 * 
	 * 
	 * response.setHeader("Content-Disposition","inline; filename=filename.pdf");
	 * response.setContentType("text/plain; charset=utf-8"); ServletOutputStream
	 * outputStream = response.getOutputStream(); baos.writeTo(outputStream);
	 * 
	 * 
	 * // base64.decode(response.body)
	 * 
	 * 
	 * 
	 * }
	 */
	



	