package com.ro8.otp1.varasto_softa.application.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;

import application.model.Validaattori;

class ValidaattoriTest {

	@ParameterizedTest
	@CsvSource({"'', test, 10, false",
				"10, '', 12, false",
				"10, testi, '', false",
				"14, testiNimi, 54, true",
				"19, liianpitkanimitestataanjaakokiinnivaimeneeklapiheittamalla, 98, false"})
	void testOnkoLisattavaTuoteValidi(String id, String nimi, String lkm, boolean tulos) {
		
		assertEquals(Validaattori.onkoLisattavaTuoteValidi(id, nimi, lkm), tulos);
		
	}

	@Test
	void testOnkoNumero() {
		
		assertFalse(Validaattori.onkoNumero("ei numero"), "Ei ole numero");
		assertTrue(Validaattori.onkoNumero("12"), "On numero");
		
	}

}
