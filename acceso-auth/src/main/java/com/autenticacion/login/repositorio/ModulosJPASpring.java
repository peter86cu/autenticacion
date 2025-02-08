package com.autenticacion.login.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.autenticacion.login.modelo.Modulos;





public interface ModulosJPASpring extends JpaRepository <Modulos, Integer> {

	@Query(value="SELECT m.idmodulo, m.modulo from  usuarios u  JOIN  roles_modulos rm ON(u.idrol=rm.roles_idrol) JOIN modulos m ON(m.idmodulo=rm.modulos_idmodulo) where  u.idusuario=:id", nativeQuery=true)
	List<Modulos> obtenerListaModulosPorUsuario(@Param("id") String id);
	
	@Query(value="SELECT g.*,a.* FROM  modulos_gestion mg JOIN gestion g ON(g.idgestion=mg.idgestion) JOIN accion_gestion ag ON(ag.gestion_idgestion=g.idgestion) \r\n"
			+ "JOIN accion a ON (a.idaccion=ag.gestion_idgestion) WHERE mg.modulos_idmodulo=:id", nativeQuery=true)
     List<String> obtenerPermisosAccesoPorModulos(@Param("id") int id);


}
