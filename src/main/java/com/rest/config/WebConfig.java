package com.rest.config;


import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.rest")
public class WebConfig extends WebMvcConfigurerAdapter{
	public static final String REST_SERVICE_URI = "http://localhost:8080/zUltimo";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registy){
		registy.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setViewClass(JstlView.class);
		view.setPrefix("WEB-INF/jsp/");
		view.setSuffix(".jsp");
		return view;
	}
	
	@Bean 
	public HttpHeaders getHttpHeaders(){
    	HttpHeaders httpHeaders = new  HttpHeaders();
    	String plainCredentials="pumas:pumas123";
    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    	
    	httpHeaders.add("Authorization", "Basic " + base64Credentials);
    	httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	return httpHeaders;
	}
	
	@Bean
	public RestTemplate restTemplate(){return new RestTemplate();}

}
