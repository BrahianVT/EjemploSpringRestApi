package tests;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.model.CatRol;
import com.rest.model.CatStatusUser;
import com.rest.model.TaxUser;

public class Cliente {

	 public static final String REST_SERVICE_URI = "http://localhost:8080/zUltimo";
	 
	 private static HttpHeaders getHeaders(){
	    	String plainCredentials="pumas:pumas123";
	    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Authorization", "Basic " + base64Credentials);
	    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    	return headers;
	    }
	 

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static void listAllUsers(){
	        System.out.println("\nTesting listAllUsers API-----------");
	        RestTemplate restTemplate = new RestTemplate(); 
	        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
	        
	        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/user/", HttpMethod.GET, request, List.class);
	        List<LinkedHashMap<String, Object>> custumers = (List<LinkedHashMap<String, Object>>)response.getBody();
	        
	        if(custumers!=null){
	            for(LinkedHashMap<String, Object> map : custumers){
	                System.out.println("User : id="+map.get("id"));
	            }
	        }else{
	            System.out.println("No user exist----------");
	        }
	    }
	 
	 // todos los usuarios
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public void listUsers(){
		RestTemplate restTemplate = new RestTemplate(); 
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		
		ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/listaUsuarios/", HttpMethod.GET,request,List.class);
		ObjectMapper mappe = new ObjectMapper();
		List<TaxUser> users = mappe.convertValue((List<TaxUser>)response.getBody(), new TypeReference<List<TaxUser>>() { });
		if(users != null){
		for(TaxUser index: users){
			System.out.println(" Nombres " + index.getName());
		}
		}else{
			System.out.println("Vacia!!");
		}
	}
	
	public TaxUser guardarUsuario(){
		RestTemplate restTemplate = new RestTemplate(); 
		
		TaxUser guardarUsuario = new TaxUser();
		CatRol catRol = new CatRol();
		catRol.setIdRol(1);
		catRol.setDescription("activo");
		CatStatusUser catStatusUser = new CatStatusUser();
		catStatusUser.setIdStatusUser(1);
		catStatusUser.setDescription("Cajero");
		guardarUsuario.setIdUser(1);
		guardarUsuario.setName("Brahian Velazquez");
		guardarUsuario.setUsername("bra");
		guardarUsuario.setPassword("pumas123");
		guardarUsuario.setCatStatusUser(catStatusUser);
		guardarUsuario.setCatRol(catRol);
		HttpEntity<Object> request = new HttpEntity<Object>(guardarUsuario, getHeaders());
		
		ResponseEntity<TaxUser> response = restTemplate.exchange(REST_SERVICE_URI+"/crearActualizarUsuario/", HttpMethod.POST,request,TaxUser.class);
		TaxUser user = (TaxUser) response.getBody();
		if(user != null)
			System.out.println("Se creo usuario" + user.getName());
		else
			System.out.println("No se obtuvo ningun dato");
		return null;
	}
	
	public TaxUser iniciarSession(String user , String contra){
		System.out.println("entro");
		RestTemplate restTemplate = new RestTemplate(); 
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		String url = REST_SERVICE_URI+"/iniciarSession/"+user+"/"+contra;
		System.out.println("El URL es " + url);
		ResponseEntity<TaxUser> response = restTemplate.exchange(url, HttpMethod.GET,request,TaxUser.class);
		TaxUser usuario = response.getBody();
		System.out.println("Lo que llego" + usuario);
		CatStatusUser ver = usuario.getCatStatusUser();
		System.out.println("Id : " + ver.getIdStatusUser());
		System.out.println("String : " + ver.getDescription());
		return usuario;
	}
	 
	public static void main(String[] args){
		
		Cliente cliente = new Cliente();
		System.out.println("Buscando sesion paps");
		try{
			cliente.listUsers();
			//cliente.iniciarSession("bra", "pumas123");
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println( ex.getMessage());
		}
		
		
	}
}
