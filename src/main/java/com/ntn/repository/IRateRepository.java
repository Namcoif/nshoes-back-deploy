package com.ntn.repository;

import com.ntn.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRateRepository extends JpaRepository<Rate, Integer> {
}
