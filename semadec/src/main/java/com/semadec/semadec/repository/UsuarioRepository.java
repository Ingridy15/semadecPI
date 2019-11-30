package com.semadec.semadec.repository;

import org.springframework.data.repository.CrudRepository;

import com.semadec.semadec.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{
	Usuario findById (long id);
	Usuario findByLogin (String login);  
}	  