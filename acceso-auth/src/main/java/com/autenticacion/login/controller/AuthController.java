package com.autenticacion.login.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autenticacion.login.JwtUtil;
import com.autenticacion.login.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


import static com.autenticacion.login.utils.Constants.CLAVE;
import static com.autenticacion.login.utils.Constants.ENCABEZADO;
import static com.autenticacion.login.utils.Constants.PREFIJO_TOKEN;
import static com.autenticacion.login.utils.Constants.TIEMPO_VIDA;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	ShoppingService service;
	
	@PostMapping("login/token")
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> login(@RequestParam("user") String user, @RequestParam("pwd") String pwd, @RequestParam("userType") String userType) throws BadCredentialsException {

		return service.buscarUsuario(user,pwd,userType);
		
	}
	
	
	@PostMapping("confirmar/user")
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> confirmarRegistroUser(HttpServletRequest request) throws BadCredentialsException {
		String token = request.getHeader(ENCABEZADO);
		return service.confirmarRegistroUsuario(token);
		
	}
	
	@PostMapping("obtener/user-token")
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> obtenerUserPorToken(HttpServletRequest request) throws BadCredentialsException {
		String token = request.getHeader(ENCABEZADO);
		return service.obtenrUsuarioPorToken(token);
		
	}

	
	@PostMapping("login/validar")
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	private boolean validarToken(HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.
			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String user = claims.getSubject();
			List<String> authorities=(List<String>) claims.get("authorities");
			if (user != null) {
				//creamos el objeto con la información del usuario
				return true;
			}
			return false;
		}
		//throw new Exception("Debe enviar un token");
		return false;
	}
	
	@PostMapping(value="login/salir")  
	@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
	    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
	        if (auth != null){      
	           new SecurityContextLogoutHandler().logout(request, response, auth);  
	        }  
	         return "redirect:/";  
	     }  
		
}
