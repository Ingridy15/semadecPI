package com.semadec.semadec.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semadec.semadec.models.Evento;
import com.semadec.semadec.models.Usuario;
import com.semadec.semadec.repository.EventoRepository;
import com.semadec.semadec.repository.UsuarioRepository;

@Controller
public class SemadecController {
	
	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private EventoRepository er;
	
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
	
		ur.save(usuario);
		return "redirect:/cadastrarU";
	}
	
	@RequestMapping(value = "/cadastrarE", method = RequestMethod.GET)
	public String cadastrarE() {
		return "semadec/cadastrarEvento";
	}
	
	@RequestMapping(value= "/cadastrarE", method = RequestMethod.POST)
	public String cadastrarE(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
	
		er.save(evento);
		return "redirect:/cadastrarE";
	}
}
