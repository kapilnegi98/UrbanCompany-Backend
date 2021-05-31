package com.urbancompany.servicesservice.controller;

import static com.urbancompany.commonservice.Constants.SUCCESS;
import static com.urbancompany.commonservice.Constants.Regex.NUMBER;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_EMAIL;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_ID;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_PINCODE;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_ROLE;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbancompany.commonservice.enums.UserRole;
import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.commonservice.response.validators.IsAuthorized;
import com.urbancompany.servicesservice.entity.ServiceProvider;
import com.urbancompany.servicesservice.request.ProvideServiceRequest;
import com.urbancompany.servicesservice.service.ServiceProviderService;

@Validated
@RestController
@RequestMapping("provider")
public class ServiceProviderController {

	@Autowired
	private ServiceProviderService provideService;
	
	@GetMapping
	public ResponseEntity<List<ServiceProvider>> getAllProvidedService(@RequestAttribute(USER_ID) String providerId) {
		System.out.println("all aservice provided"+ providerId);
		return ResponseEntity.ok(provideService.getAllProvidedService(providerId));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ServiceProvider> getProvidedService(@PathVariable String id) {
		
		ServiceProvider serviceProvider = provideService.getProvidedService(id);
		return Objects.nonNull(serviceProvider) ? ResponseEntity.ok().body(serviceProvider)
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<BaseResponse> provideService(@RequestBody @Valid ProvideServiceRequest req,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_PROVIDER) @RequestAttribute(USER_ROLE) String userRole,
			@RequestAttribute(USER_ID) String userId, @RequestAttribute(USER_PINCODE) String pincode,
			@RequestAttribute(USER_EMAIL) String email) {

		Long id = provideService.provideService(req, userId, pincode, email);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).body(new BaseResponse(SUCCESS, "user now providing this service"));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<BaseResponse> stopProvidedService(@PathVariable String id,
			@IsAuthorized(authorizedRoles = { UserRole.SERVICE_PROVIDER, UserRole.ADMIN }) 
			@RequestAttribute(USER_ROLE) String userRole, @RequestAttribute(USER_ID) String userId) {
		boolean status = provideService.stopProvidedService(id, userId);
		return status ? ResponseEntity.ok().body(new BaseResponse(SUCCESS, "user not providing this service now"))
				: ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<BaseResponse> updateProvidedService(@PathVariable String id,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_PROVIDER) @RequestAttribute(USER_ROLE) String userRole,
			@RequestAttribute(USER_ID) String userId, 
			@Pattern(regexp = NUMBER, message = "fees is invalid") @RequestParam String fees, 
			@NotBlank(message = "comment is mandatory") @RequestParam String comment) {
		boolean status = provideService.updateProvidedService(id, userId, fees, comment);
		return status ? ResponseEntity.ok().body(new BaseResponse(SUCCESS, "updated"))
				: ResponseEntity.notFound().build();
	}

}
