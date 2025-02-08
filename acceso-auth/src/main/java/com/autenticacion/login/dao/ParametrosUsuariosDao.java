package com.autenticacion.login.dao;


import java.util.List;

import com.autenticacion.login.modelo.EstadoUsuarios;
import com.autenticacion.login.modelo.Moneda;

public interface ParametrosUsuariosDao {

    //Estado usuarios
    List<EstadoUsuarios> listaEstadosUsuarios();
    
    List<Moneda> obtenerMoneda();
}
