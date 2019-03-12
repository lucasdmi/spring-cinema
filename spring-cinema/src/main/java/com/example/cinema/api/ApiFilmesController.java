package com.example.cinema.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.models.Filme;
import com.example.cinema.repository.Filmes;

@RestController
@RequestMapping("/api/filme")
public class ApiFilmesController {

	@Autowired
	private Filmes filmes;

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection <Filme> allFilmes() {
		return filmes.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional <Filme> oneFilme(@PathVariable("id") Long id){
		return filmes.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeFilme(@PathVariable("id") Long id) {
		Optional<Filme> f = filmes.findById(id);
		if (f == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		filmes.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public  ResponseEntity<?> saveFilme(@RequestBody Filme filme) {
		return new ResponseEntity<Filme> (filmes.save(filme), HttpStatus.OK);
	}

}