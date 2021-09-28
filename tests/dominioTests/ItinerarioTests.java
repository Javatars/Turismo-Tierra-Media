package dominioTests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dominio.Atraccion;
import dominio.Itinerario;
import dominio.PromocionAbsoluta;
import dominio.PromocionAxB;
import dominio.Sugerible;
import dominio.TipoAtraccion;

public class ItinerarioTests {
	Itinerario itinerario1;
	ArrayList<Atraccion> atracciones;
	ArrayList<Atraccion> atracciones2;
	ArrayList<Atraccion> atracciones3;
	Sugerible atraccion1;
	Sugerible atraccion2;
	Sugerible atraccion3;
	Sugerible atraccion4;
	Sugerible atraccion5;
	Sugerible atraccion6;
	Sugerible promocion1;
	Sugerible promocion2;
	Sugerible promocion3;

	@Before
	public void setUp(){
		itinerario1 = new Itinerario();
		atraccion1 = new Atraccion("Eary", 10, 10, 5, TipoAtraccion.PAISAJE);
		atraccion2 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atraccion3 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.AVENTURA);
		atraccion4 = new Atraccion("Rivendel", 14, 4.5, 1, TipoAtraccion.AVENTURA);
		atraccion5 = new Atraccion("Mordor", 10, 4, 6, TipoAtraccion.AVENTURA);
		atraccion6 = new Atraccion("Isengard", 7, 4, 5, TipoAtraccion.AVENTURA);

		atracciones = new ArrayList<Atraccion>();
		atracciones.add((Atraccion) atraccion4);
		atracciones.add((Atraccion) atraccion5);
		atracciones2 = new ArrayList<Atraccion>();
		atracciones2.add((Atraccion)atraccion3);
		atracciones2.add((Atraccion)atraccion4);
		atracciones3 = new ArrayList<Atraccion>();
		atracciones3.add((Atraccion)atraccion2);
		atracciones3.add((Atraccion)atraccion3);

		promocion1 = new PromocionAbsoluta("aventura", TipoAtraccion.AVENTURA, atracciones, 15);
		promocion2 = new PromocionAbsoluta("full aventura", TipoAtraccion.AVENTURA, atracciones2, 15);
		promocion3 = new PromocionAxB("aventura 2.0",TipoAtraccion.AVENTURA,atracciones3,(Atraccion)atraccion6);
	}

	@After
	public void tearDown() {
		itinerario1 = null;
		atracciones = null;
		atracciones2 = null;
		atracciones3 = null;
		atraccion1 = null;
		atraccion2 = null;
		atraccion3 = null;
		atraccion4 = null;
		atraccion5 = null;
		atraccion6 = null;
		promocion1 = null;
		promocion2 = null;
		promocion3 = null;
	}

	@Test
	public void costoTotal() {
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
	public void promocionAceptadaIncluyeAtraccion() {
		itinerario1.agregarSugerencia(promocion1);
		assertTrue(itinerario1.incluyeAtraccion(atraccion4));
	}

	@Test
	public void promocionAceptadaNoIncluyeAtraccion() {
		itinerario1.agregarSugerencia(promocion1);
		assertFalse(itinerario1.incluyeAtraccion(atraccion1));
	}

	@Test
	public void promocionAceptadaIncluyeAtraccionEnPromocionAagregar() {
		itinerario1.agregarSugerencia(promocion1);
		assertTrue(itinerario1.incluyeAtraccion(promocion2));
	}

	@Test
	public void promocionAceptadaNoIncluyeAtraccionesEnPromocionAagregar() {
		itinerario1.agregarSugerencia(promocion1);
		assertFalse(itinerario1.incluyeAtraccion(promocion3));
	}

	@Test
	public void promocionAxBaceptadaIncluyeAtraccion() {
		itinerario1.agregarSugerencia(promocion3);
		assertTrue(itinerario1.incluyeAtraccion(atraccion2));
	}

	@Test
	public void promocionAxBaceptadaNoIncluyeAtraccion() {
		itinerario1.agregarSugerencia(promocion3);
		assertFalse(itinerario1.incluyeAtraccion(atraccion1));
	}

	@Test
	public void atraccionYaIncluidaEnItinerario() {
		itinerario1.agregarSugerencia(atraccion4);
		assertTrue(itinerario1.incluyeAtraccion(promocion1));
	}

	@Test
	public void atraccionesNoIncluidaEnItinerario() {
		itinerario1.agregarSugerencia(atraccion2);
		assertFalse(itinerario1.incluyeAtraccion(promocion1));
	}

	@Test
	public void atraccionIncluidaEnItinerarioPromocionAxB() {
		itinerario1.agregarSugerencia(atraccion2);
		assertTrue(itinerario1.incluyeAtraccion(promocion3));
	}

	@Test
	public void atraccionesNoIncluidasEnItinerarioPromocionAxB() {
		itinerario1.agregarSugerencia(atraccion1);
		assertFalse(itinerario1.incluyeAtraccion(promocion3));
	}
}
