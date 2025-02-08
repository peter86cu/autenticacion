package com.autenticacion.login.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.autenticacion.login.modelo.AccionesGestion;
import com.autenticacion.login.modelo.Gestiones;




public interface GestionesJPASpring extends JpaRepository <Gestiones, Integer> {

	/*@Query(value="SELECT DISTINCT g.* FROM gestion g JOIN gestion_roles_modulos grm ON (g.idgestion=grm.idgestion) WHERE grm.idmodulo=:idmodulo", nativeQuery=true)
	List<Gestiones> obtenerGestionesPorModulos(@Param("idmodulo") int idmodulo);*/
	
	@Query(value="SELECT gestion.* FROM  gestion   JOIN  modulos_gestion  ON(gestion.idgestion=modulos_gestion.idgestion) WHERE modulos_gestion.modulos_idmodulo=:idmodulo",nativeQuery=true)
	List<Gestiones> obtenerGestionPorModulo(@Param("idmodulo") int idmodulo);
	
	@Query(value="SELECT a.* FROM  accion_gestion ag JOIN accion a ON (a.idaccion=ag.gestion_idgestion) WHERE ag.gestion_idgestion=:idgestion",nativeQuery=true)
	List<AccionesGestion> obtenerAccionesPorGestion(@Param("idgestion") int idgestion);
	
}
