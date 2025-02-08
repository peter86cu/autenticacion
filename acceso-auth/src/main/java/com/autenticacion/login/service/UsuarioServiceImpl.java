package com.autenticacion.login.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autenticacion.login.JwtUtil;
import com.autenticacion.login.dao.UsuarioDao;
import com.autenticacion.login.modelo.Usuarios;
import com.autenticacion.login.vo.RequestAddUsuario;
import com.google.gson.Gson;

import static com.autenticacion.login.utils.Constants.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	AuthenticationManager authManager;

	public UsuarioServiceImpl(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Autowired
	UsuarioDao dao;

	@Override
	public ResponseEntity<String> agregarUsuario(String datos) {
		RequestAddUsuario request = new Gson().fromJson(datos, RequestAddUsuario.class);
		String pw1 = new BCryptPasswordEncoder().encode(request.getUsuario().getPassword());
		request.getUsuario().setPassword(pw1);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		request.getUsuario().setFechaalta(formato.format(calendar.getTime()));
		// request.getUsuario().setEmpleado(null);
		// if(dao.recuperarUsuarioPorId(request.getUsuario().getIdusuario())==null) {
		dao.agregarUsuario(request.getUsuario());
		return new ResponseEntity<String>(com.autenticacion.login.utils.Constants.RESULTADO_OK, HttpStatus.OK);
		// }
		// return new
		// ResponseEntity<String>(util.Constants.RESULTADO_NOK,HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public ResponseEntity<String> listadoUsuarios(String token) {

		try {
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
					List<Usuarios> lstUsuarios = dao.devolverUsuarios();
					if (!lstUsuarios.isEmpty()) {
						// String json = new Gson().toJson(lstUsuarios);
						return new ResponseEntity<String>(new Gson().toJson(lstUsuarios), HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("No existen usuarios en la base de datos.",
								HttpStatus.BAD_REQUEST);
					}

				} else {
					return new ResponseEntity<String>("El token espiro o no es valido.", HttpStatus.BAD_REQUEST);

				}

			} else {
				return new ResponseEntity<String>("Token no enviado.", HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	/*
	 * @Override public ResponseEntity<String> actualizarUsuario(Usuarios Usuario) {
	 * if(dao.recuperarUsuarioPorId(Usuario.getIdusuario())!=null) {
	 * dao.actualizarUsuario(Usuario); }
	 * 
	 * }
	 */

	@Override
	public ResponseEntity<String> buscarUsuario(String user, String pwd) {
		try {

			Usuarios usuBloqueo = dao.recuperarUsuario(user);
			if (usuBloqueo == null) {
				return new ResponseEntity<String>("El usuario no existe.", HttpStatus.BAD_REQUEST);

			} else {
				if (usuBloqueo.getEstado() == 1) {
					Authentication autentication = authManager
							.authenticate(new UsernamePasswordAuthenticationToken(user, pwd));
					// si el usuario está autenticado, se genera el token
					if (autentication.isAuthenticated()) {
						return new ResponseEntity<String>(getToken(autentication), HttpStatus.OK);

					} else {
						return new ResponseEntity<String>("Usuario o password incorrectos.", HttpStatus.BAD_REQUEST);
					}
				} else {
					String estado = "";

					if (usuBloqueo.getEstado() == 2)
						estado = "INACTIVO";
					if (usuBloqueo.getEstado() == 3)
						estado = "ELIMINADO";
					if (usuBloqueo.getEstado() == 4)
						estado = "BLOQUEADO";
					return new ResponseEntity<String>("El usuario esta " + estado, HttpStatus.BAD_REQUEST);

				}

			}

		} catch (BadCredentialsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> recuperarUsuarioPorUser(String token, String user) {
		try {
			if (token != null) {
				// Se procesa el token y se recupera el usuario y los roles.

				Claims claims = Jwts.parserBuilder()
				        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
				        .build()
				        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
				        .getBody();
				String usuario = claims.getSubject();
				List<String> authorities = (List<String>) claims.get("authorities");

				// creamos el objeto con la información del usuario
				Usuarios usuarioresult = dao.recuperarUsuario(user);
				if (usuarioresult != null)
					return new ResponseEntity<String>(new Gson().toJson(usuarioresult), HttpStatus.OK);
				else
					return new ResponseEntity<String>("No existe el usuario en la base de datos.", HttpStatus.OK);

			} else {
				return new ResponseEntity<String>("Token no enviado.", HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@Override
	public ResponseEntity<String> obtenerUsuarioPorIdEmpresa(String token, String id) {

		try {
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

					List<Usuarios> lstUsuarios = dao.getUsuariosPorIdEmpresa(id);
					if (!lstUsuarios.isEmpty()) {
						return new ResponseEntity<String>(new Gson().toJson(lstUsuarios), HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("No existen usuarios en la base para ese id de empresa.",
								HttpStatus.BAD_REQUEST);
					}

				} catch (Exception e) {
					return new ResponseEntity<String>("Token vencido.", HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<String>("Token no enviado.", HttpStatus.NOT_ACCEPTABLE);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@Override
	public ResponseEntity<String> buscarUsuarioPorId(String token, String id) {

		try {
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

					Usuarios user = dao.recuperarUsuarioPorId(id);
					if (user != null) {
						return new ResponseEntity<String>(new Gson().toJson(user), HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("No se encontro el usuario.", HttpStatus.BAD_REQUEST);
					}

				} catch (Exception e) {
					return new ResponseEntity<String>("Token vencido.", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>("Token no enviado.", HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@Override
	public ResponseEntity<String> eliminarUsuarioPorId(String id) {
		try {
			dao.eliminarUsuarioPorId(id);
			return new ResponseEntity<String>(com.autenticacion.login.utils.Constants.DELETE_USUARIO_OK, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	private String getToken(Authentication authentication) {
	    return Jwts.builder()
	            .setIssuedAt(new Date()) // Fecha de creación
	            .setSubject(authentication.getName()) // Usuario
	            .claim("authorities", authentication.getAuthorities().stream()
	                    .map(GrantedAuthority::getAuthority)
	                    .collect(Collectors.toList()))
	            .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_VIDA)) // Expiración
	            .signWith(JwtUtil.getSecretKey()) // ✅ Usar una clave segura correctamente
	            .compact(); // Generación del token
	}

	@Override
	public ResponseEntity<String> cambiarPassword(String token, String idUsuario, String pass) {

		try {
			Claims claims = Jwts.parserBuilder()
			        .setSigningKey(JwtUtil.getSecretKey()) // ✅ Usar la misma clave segura
			        .build()
			        .parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
			        .getBody();
			String usuario = claims.getSubject();
			List<String> authorities = (List<String>) claims.get("authorities");
			String pw1 = new BCryptPasswordEncoder().encode(pass);
			dao.cambiarPassword(idUsuario, pw1);

			return new ResponseEntity<String>("Contraseña cambiada correctamente.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
	}

}
