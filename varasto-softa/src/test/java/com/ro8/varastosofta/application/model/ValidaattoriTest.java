package com.ro8.varastosofta.application.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;

import com.ro8.varastosofta.application.model.Validaattori;

class ValidaattoriTest {

	@ParameterizedTest
	@CsvSource({"'', test, 10, false",
				"10, '', 12, false",
				"10, testi, '', false",
				"14, testiNimi, 54, true",
				"19, liianpitkanimitestataanjaakokiinnivaimeneeklapiheittamalla, 98, false"})
	private void testOnkoLisattavaTuoteValidi(String id, String nimi, String lkm, boolean tulos) {
		
		assertEquals(Validaattori.onkoLisattavaTuoteValidi(id, nimi, lkm), tulos);
		
	}

	@Test
	private void testOnkoNumero() {
		
		assertFalse(Validaattori.onkoNumero("ei numero"), "Ei ole numero");
		assertTrue(Validaattori.onkoNumero("12"), "On numero");
		
	}
	
	@Test
	private void testOnkoTuoterymavValidi() {
		
		assertTrue(Validaattori.onkoTuoteryhmaValidi("vihannekset"), "On oikein");
		assertFalse(Validaattori.onkoTuoteryhmaValidi(""), "Ei syötettä");
		assertFalse(Validaattori.onkoTuoteryhmaValidi("rewqrewrqrqewrqqreqwrerqwqrqrqqeq"), "Liian pitkä");
		
	}

}
