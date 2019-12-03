package com.semadec.semadec.repository;

import org.springframework.data.repository.CrudRepository;

import com.semadec.semadec.models.Evento;
import com.semadec.semadec.models.Relacao;
import com.semadec.semadec.models.Usuario;

public interface RelacaoRepository extends CrudRepository<Relacao, String>{

	Iterable<Relacao> findByUsuario(Usuario usuario);
	Iterable<Relacao> findByEvento(Evento evento);
	Relacao findByIdR(long idR);
}
