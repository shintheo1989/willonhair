package com.shintheo.willonhair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.CategoryDao;

public interface CategoryRepository extends JpaRepository<CategoryDao, Long> {

}
