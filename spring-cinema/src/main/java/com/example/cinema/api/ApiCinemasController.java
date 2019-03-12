package com.example.cinema.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.models.Cinema;
import com.example.cinema.repository.Cinemas;



@RestController
@RequestMapping("/api/cinema")
public class ApiCinemasController {

	@Autowired
	Cinemas cinemas;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection <Cinema> allCinemas() {
		return cinemas.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional <Cinema> oneCinema(@PathVariable("id") Long id){
		return cinemas.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeCinema(@PathVariable("id") Long id) {
		Optional<Cinema> f = cinemas.findById(id);
		if (f == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		cinemas.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public  ResponseEntity<?> saveCinema(@RequestBody Cinema cinema) {
		return new ResponseEntity<Cinema> (cinemas.save(cinema), HttpStatus.OK);
	}
	
}