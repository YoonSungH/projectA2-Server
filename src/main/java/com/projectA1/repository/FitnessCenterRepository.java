package com.projectA1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectA1.model.FitnessCenter;

@Repository
public interface FitnessCenterRepository extends JpaRepository<FitnessCenter, Long> {

}

