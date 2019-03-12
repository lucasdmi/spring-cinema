package com.example.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema.models.Filme;

public interface Filmes extends JpaRepository<Filme,Long>{}
