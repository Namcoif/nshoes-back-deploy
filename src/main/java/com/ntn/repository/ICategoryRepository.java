package com.ntn.repository;

import com.ntn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICategoryRepository extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {
}
