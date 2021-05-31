package com.urbancompany.offersservice.contoller;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_EMAIL;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_ROLE;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbancompany.commonservice.enums.UserRole;
import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.commonservice.response.validators.IsAuthorized;
import com.urbancompany.offersservice.entity.Offer;
import com.urbancompany.offersservice.request.AddOfferRequest;
import com.urbancompany.offersservice.service.OfferService;

@Validated
@RestController
@RequestMapping("offer")
public class OffersController {
	
	@Autowired
	private OfferService offerService;

	@PostMapping
	public ResponseEntity<BaseResponse> addOffer(@RequestBody @Valid AddOfferRequest req,
			@IsAuthorized(authorizedRoles = UserRole.ADMIN) @RequestAttribute(USER_ROLE) String role) {

		Long offerId = offerService.addOffer(req);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(offerId).toUri();

		return ResponseEntity.created(location).body(new BaseResponse("success", "offer added"));
	}

	@GetMapping("{id}")
	public ResponseEntity<Offer> getOfferById(
			@PathVariable @Pattern(regexp = NUMBER, message = "offer id is invalid") String id,
			@RequestParam(required = false) String forEmail) {
		Offer offer = offerService.getOfferById(id, forEmail);		
		return Objects.nonNull(offer) ? ResponseEntity.ok(offer) 
				: ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOfferById(
			@PathVariable @Pattern(regexp = NUMBER, message = "offer id is invalid") String id) {
		boolean deleted = offerService.deleteOfferById(id);		
		return Objects.nonNull(deleted) ? ResponseEntity.ok().build() 
				: ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<Offer>> getOffers(@RequestAttribute(USER_EMAIL) String email,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_RECIEVER) @RequestAttribute(USER_ROLE) String role) {
		List<Offer> offers = offerService.getOffersForEmail(email);
		return Objects.nonNull(offers) ? ResponseEntity.ok(offers) : ResponseEntity.notFound().build();
	}
	
	
}
