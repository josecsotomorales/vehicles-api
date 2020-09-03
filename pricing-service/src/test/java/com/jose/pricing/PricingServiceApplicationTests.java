package com.jose.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private MockMvc mvc;

	/**
	 * Tests if the read operation appropriately returns a list of prices.
	 * @throws Exception if the read operation of the prices list fails
	 */
	@Test
	public void listPrices() throws Exception {
		mvc.perform(
				get(new URI("/prices"))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("_embedded.prices", hasSize(12)))
				.andExpect(jsonPath("_embedded.prices[0].currency", is("USD")))
				.andExpect(jsonPath("_embedded.prices[0].price", is(25000.00)))
				.andExpect(jsonPath("_embedded.prices[0].vehicleId", is(1)));
	}


}
