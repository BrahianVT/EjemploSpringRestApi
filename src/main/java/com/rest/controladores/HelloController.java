package com.rest.controladores;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.rest.config.WebConfig;
import com.rest.model.CatRol;

/*
 *  Controlador para la pantalla del navegador web xD
 * */
@Controller
@SuppressWarnings({"unchecked","rawtypes"})
public class HelloController {

	@Autowired
	HttpHeaders httpHeader;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping(value="/")
	public String inicio(Model model){
		HttpEntity<String> request = new HttpEntity<String>(httpHeader);
		String url = WebConfig.REST_SERVICE_URI + "/listaRol";
		
		ResponseEntity<List> listaRoles =  restTemplate.exchange(url, HttpMethod.GET,request,List.class);
		List<LinkedHashMap<String, Object>> lista = (List<LinkedHashMap<String, Object>>) listaRoles.getBody();
		model.addAttribute("rol" ,new CatRol());
		model.addAttribute("lista", lista);
		System.out.println("Tamano " + lista.size());
		return "index";
	}
	
	@RequestMapping(value="/saveOrUdpateRol", method= RequestMethod.POST)
	public String addRol(@ModelAttribute("rol") CatRol rol){
		HttpEntity<Object> request = new HttpEntity<Object>(rol,httpHeader);
		String url = WebConfig.REST_SERVICE_URI + "/guardarActualizarRol";
		ResponseEntity<CatRol> rolResponse = restTemplate.exchange(url, HttpMethod.POST, request, CatRol.class);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete" , method=RequestMethod.POST)
	public String deleteRol(@ModelAttribute("rol") CatRol rol){
		HttpEntity<Object> request = new HttpEntity<Object>(rol,httpHeader);
		String url = WebConfig.REST_SERVICE_URI+"/eliminarRol";
		ResponseEntity<CatRol> deleteResponse = restTemplate.exchange(url,HttpMethod.POST,request,CatRol.class);
		return "redirect/";
	}
	
	

}
