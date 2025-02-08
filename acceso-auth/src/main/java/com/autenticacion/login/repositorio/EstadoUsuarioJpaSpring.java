
package com.autenticacion.login.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.login.modelo.EstadoUsuarios;


public interface EstadoUsuarioJpaSpring extends JpaRepository<EstadoUsuarios, Integer>{
	
	
	
}
