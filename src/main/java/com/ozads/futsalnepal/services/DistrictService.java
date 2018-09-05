package com.ozads.futsalnepal.services;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozads.futsalnepal.model.District;
import com.ozads.futsalnepal.repository.DistrictRepository;
import com.ozads.futsalnepal.response.DistrictResponse;

@Service
public class DistrictService {

	@Autowired
	DistrictRepository districtRepository;
	private static final Logger LOG = LoggerFactory.getLogger(DistrictService.class);
	
	
	
	public List<DistrictResponse> listAllDistrict() {
		List<District> district=districtRepository.findAll();
		List<DistrictResponse> districtResponses=new ArrayList<>();
		district.stream().forEach(u->{
			DistrictResponse response=new DistrictResponse();
			response.setId(u.getId());
			response.setDistrict(u.getDistrict());
			districtResponses.add(response);
		});
		return districtResponses;
	}
}
