package com.semadec.semadec.repository;

import org.springframework.data.repository.CrudRepository;

import com.semadec.semadec.models.Evento;


public interface EventoRepository extends CrudRepository<Evento, String>{
  Evento findByCodigo (long codigo);
  
	
}