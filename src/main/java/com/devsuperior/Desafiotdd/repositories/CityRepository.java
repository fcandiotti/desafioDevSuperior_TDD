package com.devsuperior.Desafiotdd.repositories;

import com.devsuperior.Desafiotdd.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
