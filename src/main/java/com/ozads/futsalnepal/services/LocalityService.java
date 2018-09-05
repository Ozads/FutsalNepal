package com.ozads.futsalnepal.services;


import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ozads.futsalnepal.exceptions.AlreadyExistException;

import com.ozads.futsalnepal.model.District;
import com.ozads.futsalnepal.model.Locality;
import com.ozads.futsalnepal.repository.DistrictRepository;
import com.ozads.futsalnepal.repository.LocalityRepository;
import com.ozads.futsalnepal.response.LocalityResponse;


@Service
public class LocalityService {

private static final Logger LOG = LoggerFactory.getLogger(DistrictService.class);
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	LocalityRepository localityRepository;

	public List<LocalityResponse> getLocality(String district) {
		LOG.debug("Request Accepted to List a Locality");
		
		District dist=districtRepository.findByDistrict(district);
		if(dist==null) {
			throw new AlreadyExistException("Locality not found");
		}
		System.out.println(dist.getId());
		
		List<Locality> locality=localityRepository.findAllLocalityByDistrict(new District(dist.getId()));
		List<LocalityResponse> localityResponses=new ArrayList<>();
		locality.stream().forEach(u->{
			LocalityResponse response=new LocalityResponse();
			response.setLocality(u.getLocality());
			localityResponses.add(response);
		});
		return localityResponses;
	}
	
}
