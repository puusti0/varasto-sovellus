package com.ro8.varastosofta.application.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;
import com.ro8.varastosofta.application.model.Validaattori;

/**
 * Testiluokka tuotteen lisäykselle.
 * @author Riina Antikainen
 * @author Janne Valle
 * @author Tuukka Mytty
 */
class ValidaattoriTest {

	@ParameterizedTest
	@CsvSource({"'', test, 10, false",
				"10, '', 12, false",
				"10, testi, '', false",
				"14, testiNimi, 54, true",
				"19, liianpitkanimitestataanjaakokiinnivaimeneeklapiheittamalla, 98, false"})
	
	/**
	 * Testataan onko lisättävä tuote validi.
	 */
	void testOnkoLisattavaTuoteValidi(String id, String nimi, String lkm, boolean tulos) {
		Validaattori validaattori = new Validaattori();
		assertEquals(validaattori.onkoLisattavaTuoteValidi(id, nimi, lkm), tulos);
		
	}

	/**
	 * Testataan onko syöte numero.
	 */
	@Test
	void testOnkoNumero() {
		Validaattori validaattori = new Validaattori();
		assertFalse(validaattori.onkoNumero("ei numero"), "Ei ole numero");
		assertTrue(validaattori.onkoNumero("12"), "On numero");
		
	}
	
	/**
	 * Testataan onko lisättävä tuoteryhmä sallittu.
	 */
	@Test
	void testOnkoTuoteryhmaValidi() {
		Validaattori validaattori = new Validaattori();
		assertTrue(validaattori.onkoTuoteryhmaValidi("vihannekset"), "On oikein");
		assertFalse(validaattori.onkoTuoteryhmaValidi(""), "Ei syötettä");
		assertFalse(validaattori.onkoTuoteryhmaValidi("rewqrewrqrqewrqqreqwrerqwqrqrqqeqiyfyfkuf"), "Liian pitkä");
		
	}

	/**
	 * Testataan on lisättävä käyttäjä validi.
	 * @param tunnus, ehdotettu käyttäjätunnus.
	 * @param salasana, ehdotettu salasana.
	 * @param tulos, testin tulos.
	 */
	@ParameterizedTest
	@CsvSource({"testi;, salasana, false",
				"testi, salasana;, false",
				"testi, salasana, true"})
	void testaaOnkoLisattavaKayttajaValidi(String tunnus, String salasana, boolean tulos) {
		Validaattori validaattori = new Validaattori();
		assertEquals(validaattori.onkoLisattavaKayttajaValidi(tunnus, salasana), tulos);
	}
}
