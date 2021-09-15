package testsTierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dominio.Atraccion;
import dominio.Itinerario;
import dominio.PromocionAbsoluta;
import dominio.PromocionAxB;
import dominio.Sugerible;
import dominio.TipoAtraccion;

public class ItinerarioTest {

	Itinerario itinerario1;
	Sugerible atraccion1;
	Sugerible atraccion2;
	Sugerible atraccion3;
	Sugerible atraccion4;
	Sugerible atraccion5;
	ArrayList<Atraccion> atracciones;

	Sugerible promocion1;

	@Before
	public void setUp() throws Exception {
		itinerario1 = new Itinerario();
		atraccion1 = new Atraccion("Eary", 10, 10, 5, TipoAtraccion.PAISAJE);
		atraccion2 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atraccion3 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.AVENTURA);
		atraccion4 = new Atraccion("Rivendel", 14, 4.5, 1, TipoAtraccion.AVENTURA);
		atraccion5 = new Atraccion("Maria", 10, 4, 6, TipoAtraccion.AVENTURA);

		atracciones = new ArrayList<Atraccion>();
		atracciones.add((Atraccion) atraccion4);
		atracciones.add((Atraccion) atraccion5);

		promocion1 = new PromocionAbsoluta("aventura", TipoAtraccion.AVENTURA, atracciones, 15);
	}

	@Test
	public void agregarSugerenciastest() {

		itinerario1.agregarSugerencia(atraccion1);
		itinerario1.agregarSugerencia(atraccion2);
		itinerario1.agregarSugerencia(atraccion3);
		itinerario1.agregarSugerencia(promocion1);

		assertEquals(4, itinerario1.getSugerenciasAceptadas().size());

	}

	@Test
	public void costoTotaltest() {

		itinerario1.agregarSugerencia(atraccion1);
		itinerario1.agregarSugerencia(atraccion2);
		itinerario1.agregarSugerencia(atraccion3);
		itinerario1.agregarSugerencia(promocion1);

		assertEquals(40, itinerario1.costoTotal());

	}

	@Test
	public void horasNecesarias() {

		itinerario1.agregarSugerencia(atraccion1);
		itinerario1.agregarSugerencia(atraccion2);
		itinerario1.agregarSugerencia(atraccion3);
		itinerario1.agregarSugerencia(promocion1);

		assertEquals(23, itinerario1.horasNecesarias(), 0);

	}

	@Test
	public void promocionIncluyeAtraccion() {

		itinerario1.agregarSugerencia(promocion1);
		assertTrue(itinerario1.incluyeAtraccion(atraccion4));

	}

	@Test
	public void promocionNoIncluyeAtraccion() {

		itinerario1.agregarSugerencia(promocion1);
		assertFalse(itinerario1.incluyeAtraccion(atraccion1));

	}

	@Test
	public void atraccionesYaIncluidaEnItinerario() {

		itinerario1.agregarSugerencia(atraccion4);

		assertTrue(itinerario1.incluyeAtraccion(promocion1));

	}

	@Test
	public void atraccionesNoIncluidaEnItinerario() {

		itinerario1.agregarSugerencia(atraccion2);

		assertFalse(itinerario1.incluyeAtraccion(promocion1));

	}

	@Test
	public void atraccionesIncluidaEnItinerarioTest2() {

		Sugerible promocion2 = new PromocionAxB("Full aventura", TipoAtraccion.AVENTURA, atracciones,
				(Atraccion) atraccion2);

		itinerario1.agregarSugerencia(atraccion2);

		assertTrue(itinerario1.incluyeAtraccion(promocion2));

	}

	@Test
	public void atraccionesNoIncluidaEnItinerarioTest2() {

		Sugerible promocion2 = new PromocionAxB("Full aventura", TipoAtraccion.AVENTURA, atracciones,
				(Atraccion) atraccion2);

		itinerario1.agregarSugerencia(atraccion1);

		assertFalse(itinerario1.incluyeAtraccion(promocion2));

	}

}
