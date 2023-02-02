package com.shintheo.willonhair.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shintheo.willonhair.entity.ExtrasDao;

@Repository
public interface ExtrasRepository extends CrudRepository<ExtrasDao, Long> {

}
