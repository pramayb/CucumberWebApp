package com.cucumber.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cucumber.demo.dto.NumToStringDTO;
import com.cucumber.demo.service.ConversionService;
import com.cucumber.demo.util.Validator;

@RestController
public class NumToStringController {
	@Autowired
	ConversionService conversionService;

	@RequestMapping(value = {"/demo" }, method=RequestMethod.GET)
	public ModelAndView InputDetails() {
	    return new ModelAndView("demo");
	}

	@PostMapping(path = "/api/convert-to-string")
	public ResponseEntity<NumToStringDTO> convertCurrency(@RequestBody final NumToStringDTO numToString) {
		if(Validator.threasholdCheck(numToString.getNumber()))
			return ResponseEntity.badRequest().build();
		
		numToString.setStringOfNumber(conversionService.convertInStringRepresentation(numToString.getNumber()));
		return ResponseEntity.ok(numToString);
	}
}