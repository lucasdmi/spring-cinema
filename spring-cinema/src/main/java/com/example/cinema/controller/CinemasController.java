package com.example.cinema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.cinema.models.Cinema;
import com.example.cinema.repository.Cinemas;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/cinemas")
public class CinemasController {

	@Autowired
	Cinemas cinemas;



	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaCinemas");
		mv.addObject(new Cinema());	
		mv.addObject("cinemas",cinemas.findAll());
		return mv;
	}

	@RequestMapping(value="",method=RequestMethod.POST)
	public String gravar(Cinema c) {
		cinemas.save(c);
		return "redirect:/cinemas";
	}

	@RequestMapping(value="alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("ListaCinemas");
		Cinema cinema = cinemas.getOne(id);
		mv.addObject(cinema);
		mv.addObject("cinemas",cinemas.findAll());
		return mv;
	}

	//Excluir
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		cinemas.deleteById(id);
		//attributes.addFlashAttribute("mensagem", "Proprietario excluído com sucesso!");
		return "redirect:/cinemas";
	}
}

