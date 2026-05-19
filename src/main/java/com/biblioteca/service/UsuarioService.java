package com.biblioteca.service;

import com.biblioteca.dto.UsuarioRequest;
import com.biblioteca.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse crearUsuario(UsuarioRequest request);

    UsuarioResponse actualizarUsuario(String id, UsuarioRequest request);

    void eliminarUsuario(String id);

    UsuarioResponse consultarUsuario(String id);

    List<UsuarioResponse> listarUsuarios();
}
