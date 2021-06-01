package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.EmailRequestDto;
import com.example.demo.InvoicePdfExporter;
import com.example.demo.PurchaseDetail;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.sun.istack.ByteArrayDataSource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
 
@Service
public class MailService {
 
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private BasketService basketService;
 
    @Autowired
    private Configuration configuration;
 
    public String sendMail(EmailRequestDto request, Map<String, String> model, int purId) {
 
        String response;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
 
            
            //ClassPathResource pdf = new ClassPathResource("static/attachment.pdf");
           
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
 
            helper.setTo(request.getTo());
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);
            
            PurchaseDetail cities = (PurchaseDetail)basketService.getOnePurchase(purId);
            		  
            InputStreamSource bis = InvoicePdfExporter.citiesReport(cities);
            		  
            		  
            		  HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
            		  "inline; filename=citiesreport.pdf");
            		  
            		  
            		  
            helper.addAttachment("citiesreport.pdf",bis, "application/pdf" );
            	
 
            mailSender.send(message);
            response = "Email has been sent to :" + request.getTo();
        } catch (MessagingException | IOException | TemplateException e) {
            response = "Email send failure to :" + request.getTo();
        }
        return response;
    }
    

	
	 
	



}
