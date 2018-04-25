package tests;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.model.TaxUser;
import com.rest.utilities.WrapperClass;

public class test2 {

 public static final String REST_SERVICE_URI = "http://localhost:8080/zUltimo";
 	RestTemplate restTemplate = null; 
	 private static HttpHeaders getHeaders(){
	    	String plainCredentials="pumas:pumas123";
	    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Authorization", "Basic " + base64Credentials);
	    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    	return headers;
	    }
	 public void obtenerLista(){
		 restTemplate = new RestTemplate(); 
		 HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		 
		 ResponseEntity<WrapperClass> response = restTemplate.exchange(REST_SERVICE_URI+"/listaUsuarios2/", HttpMethod.GET,request,WrapperClass.class);
		 
		 WrapperClass wrapperResponse = (WrapperClass)response.getBody();
		 System.out.println("El tamaño de la lista" + wrapperResponse.getData());
		 System.out.println("error code : " + wrapperResponse.getErroCode());
		 System.out.println("el mensaje que trae : " + wrapperResponse.getMessage());
	 }
	 public static void main(String[] args){
		 test2 prueba = new test2();
		 prueba.obtenerLista();
	 }
}
