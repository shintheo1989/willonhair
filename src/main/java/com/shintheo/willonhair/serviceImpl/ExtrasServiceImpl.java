package com.shintheo.willonhair.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.ExtrasDao;
import com.shintheo.willonhair.repository.ExtrasRepository;
import com.shintheo.willonhair.service.ExtrasService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExtrasServiceImpl implements ExtrasService {

	@Autowired
	private ExtrasRepository extrasRepository;

	@Override
	public ExtrasDao submitExtra(ExtrasDao extras) {
		log.info("======>Service: submit ({}) <========", extras);
		return extrasRepository.save(extras);
	}

}
