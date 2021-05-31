package com.urbancompany.servicesservice.controller;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_PINCODE;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urbancompany.servicesservice.response.ServiceSearchReponse;
import com.urbancompany.servicesservice.service.SearchService;

@Validated
@RestController
@RequestMapping("search")
public class ServiceSearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping
	public ResponseEntity<ServiceSearchReponse> searchService( @Pattern(regexp = NUMBER, message = "service type id must be a number") 
		@RequestParam String serviceTypeId,	@RequestParam(required = false) String pincode,
			@RequestAttribute(USER_PINCODE) String userPincode) {
		
		return ResponseEntity.ok(searchService.searchServices(serviceTypeId,
				ObjectUtils.isEmpty(pincode) ? userPincode : pincode));
	}
	
}
