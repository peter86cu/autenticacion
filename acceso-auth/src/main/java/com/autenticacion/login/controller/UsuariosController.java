package com.autenticacion.login.controller;

import static com.autenticacion.login.utils.Constants.ENCABEZADO;
import static com.autenticacion.login.utils.Constants.PREFIJO_TOKEN;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.autenticacion.login.JwtUtil;
import com.autenticacion.login.service.UsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping("/api/v1/gesventas")
public class UsuariosController {
	@Autowired
	UsuarioService service;

	@GetMapping(value = "usuario/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> recuperarUsuarios(HttpServletRequest request) throws Exception {
		String token = request.getHeader(ENCABEZADO);
		return service.listadoUsuarios(token);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "usuario/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recuperarUsuarioPorUser(@RequestParam("id") String id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		return service.obtenerUsuarioPorIdEmpresa(token, id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "usuario/id-usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recuperarUsuarioPorId(@RequestParam("id") String id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		return service.buscarUsuarioPorId(token, id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@DeleteMapping(value = "usuario/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> eliminarUsuarioPorId(@RequestParam("id") String id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			try {
				Claims claims = Jwts.parserBuilder()
				        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
				        .build()
				        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
				        .getBody();
				String usuario = claims.getSubject();
				List<String> authorities = (List<String>) claims.get("authorities");

				return service.eliminarUsuarioPorId(id);

			} catch (Exception e) {
				return new ResponseEntity<String>("Token vencido.", HttpStatus.NOT_ACCEPTABLE);
			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@GetMapping(value = "usuario/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recuperarUsuarios(@RequestParam("user") String user, HttpServletRequest request)
			throws Exception {

		String token = request.getHeader(ENCABEZADO);
		return service.recuperarUsuarioPorUser(token, user);

	}

	@PostMapping(value = "usuario/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarUsuario(@RequestBody String datos, HttpServletRequest request)
			throws Exception {
		String token = request.getHeader(ENCABEZADO);

		System.out.println("Json add" + datos);
		if (token != null) {

			try {

				// Se procesa el token y se recupera el usuario y los roles.
				Claims claims = Jwts.parserBuilder()
				        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
				        .build()
				        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
				        .getBody();
				String usuario = claims.getSubject();
				List<String> authorities = (List<String>) claims.get("authorities");

				return service.agregarUsuario(datos);

			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}

		}
		return new ResponseEntity<String>("Token vencido.", HttpStatus.NOT_ACCEPTABLE);

	}
	@ResponseStatus(HttpStatus.CREATED)	
	@PostMapping(value = "usuario/password/cambio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cambiarPassword(@RequestParam("id") String id, @RequestParam("pass") String pass,
			HttpServletRequest request) throws Exception {
		String token = request.getHeader(ENCABEZADO);

		return service.cambiarPassword(token, id, pass);

	}

	/*
	 * @PutMapping(value = "usuarios", consumes = MediaType.APPLICATION_JSON_VALUE)
	 * public void actualizarUsuario(@RequestBody Usuarios Usuario) {
	 * service.actualizarUsuario(Usuario); }
	 * 
	 * @DeleteMapping(value = "usuarios/{id}") public void
	 * eliminarPorId(@PathVariable("id") String idUsuario) {
	 * service.eliminarUsuario(idUsuario);
	 * 
	 * }
	 */

}
