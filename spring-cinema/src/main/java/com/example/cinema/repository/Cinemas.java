package com.example.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema.models.Cinema;

public interface Cinemas extends JpaRepository<Cinema,Long>{}