package com.ozads.futsalnepal.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ozads.futsalnepal.exceptions.AlreadyExistException;
import com.ozads.futsalnepal.exceptions.NotFoundException;
import com.ozads.futsalnepal.model.Locality;
import com.ozads.futsalnepal.model.WardNumber;
import com.ozads.futsalnepal.repository.LocalityRepository;
import com.ozads.futsalnepal.repository.WardNumberRepository;
import com.ozads.futsalnepal.response.WardNumberResponse;

@Service
public class WardNumberService {
	
private static final Logger LOG = LoggerFactory.getLogger(WardNumberService.class);
	
	@Autowired
	WardNumberRepository wardNumberRepository;
	
	@Autowired
	LocalityRepository localityRepository;
	public List<WardNumberResponse> getWardNumber(String locality) {
		
		LOG.debug("Request Accepted to List a locality");
		
		Locality loc=localityRepository.findByLocality(locality);
		if(loc==null) {
			throw new AlreadyExistException("Locality not found");
		}
		
		List<WardNumber> vc=wardNumberRepository.findAllWardNumberByLocality(new Locality(loc.getId()));
		List<WardNumberResponse> wardNumberResponses=new ArrayList<>();
		vc.stream().forEach(u->{
			WardNumberResponse response=new WardNumberResponse();
			response.setWardNumber(u.getWardNumber());
			wardNumberResponses.add(response);
		});
		return wardNumberResponses;
	}

}
