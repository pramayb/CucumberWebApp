package com.cucumber.demo.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cucumber.demo.dto.NumToStringDTO;

/**
 * 
 * @author Pramay
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NumToStringControllerTest {
	MockMvc mockMvc;

	@Mock
	private NumToStringController ntsController;

	@Autowired
	private TestRestTemplate template;

	private String convertAPI="/api/convert-to-string";

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(ntsController).build();
	}

	@Test
	public void convertCurrencyTest() throws Exception {
		Double currencyToTest = 0.45;
		String nameToTest = "John Smith";

		NumToStringDTO ntsDTO = new NumToStringDTO();
		ntsDTO.setName(nameToTest);
		ntsDTO.setNumber(currencyToTest);
		
		ResponseEntity<NumToStringDTO> response = template.postForEntity(convertAPI, getHttpEntity(ntsDTO), NumToStringDTO.class);
		Assert.assertEquals(nameToTest, response.getBody().getName());
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	private HttpEntity<Object> getHttpEntity(final Object body) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
}