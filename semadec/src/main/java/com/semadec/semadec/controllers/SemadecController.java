package com.semadec.semadec.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semadec.semadec.models.Evento;
import com.semadec.semadec.models.Relacao;
import com.semadec.semadec.models.Usuario;
import com.semadec.semadec.repository.EventoRepository;
import com.semadec.semadec.repository.RelacaoRepository;
import com.semadec.semadec.repository.UsuarioRepository;

@Controller
public class SemadecController {
	
	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private EventoRepository er;
	@Autowired
	private RelacaoRepository rr;
	
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/cadastrarU", method = RequestMethod.GET)
	public String cadastrarU() {
		return "semadec/cadastrarUsuario";
	}
	
	@RequestMapping(value= "/cadastrarU", method = RequestMethod.POST)
	public String cadastrarU(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
	
		Usuario usuVerifica = ur.findByLogin(usuario.getLogin());
		if(usuVerifica != null) {
			throw new UsernameNotFoundException("Usuario já existe");
			
		}
		
		String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senha);
		ur.save(usuario);
		return "redirect:/cadastrarU";
	}
	
	@RequestMapping(value = "/cadastrarE", method = RequestMethod.GET)
	public ModelAndView cadastrarE() {
		ModelAndView mv = new ModelAndView("semadec/cadastrarEvento");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);

		return mv;
	}
	
	@RequestMapping(value= "/cadastrarE", method = RequestMethod.POST)
	public String cadastrarE(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
	
		er.save(evento);
		return "redirect:/cadastrarE";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		
		ModelAndView mv = new ModelAndView("semadec/listaE");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}",method=RequestMethod.GET)
	public ModelAndView detalhesEvento (@PathVariable("codigo")long codigo) {
	  Evento evento = er.findByCodigo(codigo);
	  ModelAndView mv = new ModelAndView("semadec/detalhesEvento");
	  mv.addObject("evento", evento );  
	  
	  return mv;
	  
	}
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
		
	}
	//RELACAO
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String cadastrarRelacao(Relacao relacao,@PathVariable("codigo")long codigo,BindingResult result, RedirectAttributes attributes) {
		
		UsuarioController uc = new UsuarioController();

		String usu = uc.getUsuario().getLogin();
		Usuario usuario = ur.findByLogin(usu);
		
		relacao.setUsuario(usuario);
			
		Evento evento = er.findByCodigo(codigo);
		relacao.setEvento(evento);	
		
		rr.save(relacao);
		return "redirect:/eventos";
	}
}
