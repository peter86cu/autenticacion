package com.autenticacion.login.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.login.modelo.EstadoProceso;





public interface EstadoProcesoJpaSpring extends JpaRepository<EstadoProceso, Integer>{

}
