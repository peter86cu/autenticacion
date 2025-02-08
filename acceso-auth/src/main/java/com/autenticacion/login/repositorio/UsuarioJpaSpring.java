package com.autenticacion.login.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.autenticacion.login.modelo.Usuarios;

public interface UsuarioJpaSpring extends JpaRepository<Usuarios, String>{
	Usuarios findByEmail(String email);

	@Query(value="SELECT usuarios.* FROM usuarios join empleado on(empleado.idempleado=usuarios.idempleado) WHERE empleado.idempresa=:idEmpresa", nativeQuery=true)
	List<Usuarios> findByIdEmpresa(@Param("idEmpresa") String idEmpresa);
	
	@Query(value="SELECT * FROM usuarios WHERE usuario=:user", nativeQuery=true)
	Usuarios buscarPorUsuario(@Param("user") String usuario);
	
	
	@Query(value="SELECT * FROM usuarios WHERE estado=1 AND usuarios.usuario=:usuario", nativeQuery=true)
	Usuarios findByUsuarioBloqueado(@Param("usuario") String usuario);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE usuarios us SET us.password=:pass WHERE us.idusuario=:id",nativeQuery = true)
	void updatePasswordByUser(@Param("id") String id,@Param("pass") String pass);
	
}
