package com.shintheo.willonhair.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shintheo.willonhair.entity.CategoryDao;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryDao, Long> {

}
