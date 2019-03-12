package com.example.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.cinema.models.Filme;

import com.example.cinema.repository.Cinemas;
import com.example.cinema.repository.Filmes;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/filmes")
public class FilmesController {

	@Autowired
	Filmes filmes;
	
	@Autowired 
	Cinemas cinemas;
	
	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaFilmes");
		mv.addObject(new Filme());
		mv.addObject("cinemas",cinemas.findAll());
		mv.addObject("filmes",filmes.findAll());
		return mv;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String gravar(Filme f) {
		filmes.save(f);
		return "redirect:/filmes";
	}
	
	@RequestMapping(value="alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("FilmesListagem.html");
		Filme filme = filmes.getOne(id);
		mv.addObject(filme);
		mv.addObject("cinemas",cinemas.findAll());
		mv.addObject("filmes",filmes.findAll());
		return mv;
	}
	
	//Excluir
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		filmes.deleteById(id);
		//attributes.addFlashAttribute("mensagem", "Proprietario excluído com sucesso!");
		return "redirect:/filmes";
	}
	
	
	
}

