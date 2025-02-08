package com.autenticacion.login.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.autenticacion.login.modelo.Moneda;





public interface MonedaJPASpring extends JpaRepository <Moneda, String> {



}
