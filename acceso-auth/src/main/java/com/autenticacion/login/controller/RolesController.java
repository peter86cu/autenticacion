package com.autenticacion.login.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autenticacion.login.JwtUtil;
import com.autenticacion.login.modelo.Modulos;
import com.autenticacion.login.modelo.Roles;
import com.autenticacion.login.service.RolesService;
import com.autenticacion.login.vo.ObjectPermisos;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static com.autenticacion.login.utils.Constants.CLAVE;
import static com.autenticacion.login.utils.Constants.ENCABEZADO;
import static com.autenticacion.login.utils.Constants.PREFIJO_TOKEN;


@RestController
@RequestMapping("/api/v1/gesventas")
public class RolesController {
	@Autowired
	RolesService service;
	
	@GetMapping(value="modulos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recuperarModulosPorUsuario(@RequestParam("id") String id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String user = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (user != null) {
				// creamos el objeto con la información del usuario
				List<Modulos> lstModulos = service.listaModulosUsuarios(id);
				if (!lstModulos.isEmpty()) {
					//String json = new Gson().toJson(lstUsuarios);
					return new ResponseEntity<String>(new Gson().toJson(lstModulos), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("No el usuario no tiene modulos asignados.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.NOT_ACCEPTABLE);

			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}		
		 
	}
	
	
	@GetMapping(value="modulos/permisos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerPermisosPorIdModulo(@RequestParam("id") int id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String user = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (user != null) {
				// creamos el objeto con la información del usuario
				List<ObjectPermisos> permisos = service.obtenerPermisosPorModulos(id);
				if (permisos!=null) {
					//String json = new Gson().toJson(lstUsuarios);
					return new ResponseEntity<String>(new Gson().toJson(permisos), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("No el usuario no tiene modulos asignados.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.NOT_ACCEPTABLE);

			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}		
		 
	}
	
	
	
	/*@GetMapping(value="roles/lista/gestiones",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> listadoGestionesPorModulo(@RequestParam("id") int id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			Claims claims = Jwts.parser().setSigningKey(CLAVE).parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
					.getBody();
			String user = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (user != null) {
				// creamos el objeto con la información del usuario
				List<Gestiones> lstGestiones = service.listadoGestionesPorModulos(id);
				if (!lstGestiones.isEmpty()) {
					//String json = new Gson().toJson(lstUsuarios);
					return new ResponseEntity<String>(new Gson().toJson(lstGestiones), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("El modulos seleccionado no tiene gestiones asignadas.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.NOT_ACCEPTABLE);

			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}		
		 
	}*/
	
	
	@GetMapping(value="roles/lista/roles",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> listadoRoles( HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String user = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (user != null) {
				// creamos el objeto con la información del usuario
				List<Roles> lstRoles = service.listarRoles();
				if (!lstRoles.isEmpty()) {
					//String json = new Gson().toJson(lstUsuarios);
					return new ResponseEntity<String>(new Gson().toJson(lstRoles), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("El modulos seleccionado no tiene gestiones asignadas.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.NOT_ACCEPTABLE);

			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}		
		 
	}
	
	
	
	@GetMapping(value="roles/buscar/idusuario",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerRolPorUsuario(@RequestParam("id") String id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String user = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (user != null) {
				// creamos el objeto con la información del usuario
				Roles rol = service.obtenerRolPorUsuario(id);
				if (rol!=null) {
					//String json = new Gson().toJson(lstUsuarios);
					return new ResponseEntity<String>(new Gson().toJson(rol), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("El usuario con id: "+id+" no tiene rol asignado. Contactar con un administrador.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.NOT_ACCEPTABLE);

			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}		
		 
	}
	
	@GetMapping(value="roles/buscar/id",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>  buscarRolPorId(@RequestParam("id") int id, HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.

			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String user = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (user != null) {
				// creamos el objeto con la información del usuario
				Roles rol = service.buscarRol(id);
				if (rol!=null) {
					//String json = new Gson().toJson(lstUsuarios);
					return new ResponseEntity<String>(new Gson().toJson(rol), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("El rol con id: "+id+ " no existe. Consulte a un Administrador.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.NOT_ACCEPTABLE);

			}

		} else {
			return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

		}		
		 
	}
	
	
	@PostMapping(value="/roles/add",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> guardarRol(@RequestBody String datos, HttpServletRequest request) {	
		
		String token = request.getHeader(ENCABEZADO);
		System.out.println("Json add" + datos);
		if (token != null) {
			// Se procesa el token y se recupera el usuario y los roles.
			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String usuario = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			if (usuario != null) {
				// creamos el objeto con la información del usuario
				return service.agregarRol(datos);
			} else {
				return new ResponseEntity<String>("Token vencido.", HttpStatus.NOT_ACCEPTABLE);
			}

		}
		return new ResponseEntity<String>("Token vencido.", HttpStatus.NOT_ACCEPTABLE);
		
		
		
		
	}
	
	/*@PutMapping(value="roles",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizarRol(@RequestBody Roles rol) {		
		service.actualizarRol(rol);
	}
		
	@DeleteMapping(value="roles/{id}")
	public void eliminarPorId(@PathVariable("id") String idRol) {
		service.eliminarRol(idRol);
		
	}
	
	@GetMapping(value="descripcion/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public String descripcionRolUsuario(@PathVariable String id) {		
		return service.obtenerDescripcionRol(id);
	}*/
}
