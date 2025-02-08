package com.autenticacion.login.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.login.modelo.AdministracionLog;





public interface AdminLogJpaSpring extends JpaRepository<AdministracionLog, Integer>{

}
