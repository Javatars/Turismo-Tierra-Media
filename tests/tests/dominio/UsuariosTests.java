package tests.dominio;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dominio.Atraccion;
import dominio.PromocionAbsoluta;
import dominio.PromocionAxB;
import dominio.PromocionPorcentual;
import dominio.Sugerible;
import dominio.TipoAtraccion;
import dominio.Usuario;

public class UsuariosTests {
	ArrayList<Atraccion> atracciones1;
	ArrayList<Atraccion> atracciones2;
	ArrayList<Atraccion> atracciones3;
	ArrayList<Atraccion> atracciones4;
	Sugerible atraccion1;
	Sugerible atraccion2;
	Sugerible atraccion3;
	Sugerible atraccion4;
	Sugerible promocion1;
	Sugerible promocion2;
	Sugerible promocion3;
	Sugerible promocion4;
	Usuario usuario1;
	Usuario usuario2;

	@Before
	public void setUp() {
		atracciones1 = new ArrayList<Atraccion>();
		atracciones2 = new ArrayList<Atraccion>();
		atracciones3 = new ArrayList<Atraccion>();
		atracciones4 = new ArrayList<Atraccion>();
		atraccion1 = new Atraccion("Moria", 5, 2, 5, TipoAtraccion.AVENTURA);
		atraccion2 = new Atraccion("Minas Tirith", 5, 2.5, 5, TipoAtraccion.AVENTURA);
		atraccion3 = new Atraccion("Mordor",7,5,5,TipoAtraccion.AVENTURA);
		atraccion4 = new Atraccion("La comarca",2,9,5,TipoAtraccion.DEGUSTACION);
		atracciones1.add((Atraccion)atraccion1);
		atracciones1.add((Atraccion)atraccion2);
		atracciones2.add((Atraccion)atraccion1);
		atracciones2.add((Atraccion)atraccion3);
		atracciones3.add((Atraccion)atraccion1);
		atracciones3.add((Atraccion)atraccion3);
		atracciones4.add((Atraccion)atraccion2);
		atracciones4.add((Atraccion)atraccion4);
		promocion1 = new PromocionAbsoluta("aventura", TipoAtraccion.AVENTURA, atracciones1, 10);
		promocion2 = new PromocionPorcentual("aventura2", TipoAtraccion.AVENTURA, atracciones2, 0.3);
		promocion3 = new PromocionAxB("aventura3", TipoAtraccion.AVENTURA, atracciones3,(Atraccion) atraccion2);
		promocion4 = new PromocionAbsoluta("aventura4", TipoAtraccion.AVENTURA, atracciones1, 5);
		usuario1 = new Usuario("Eowyn", 100, 100, TipoAtraccion.AVENTURA);
		usuario2 = new Usuario("Sauron", 10, 10, TipoAtraccion.AVENTURA);
	}

	@After
	public void tearDown() {
		atraccion1 = null;
		atraccion2 = null;
		atraccion3 = null;
		atraccion4 = null;
		atracciones1 = null;
		atracciones2 = null;
		atracciones3 = null;
		atracciones4 = null;
		promocion1 = null;
		promocion2 = null;
		promocion3 = null;
		promocion4 = null;
		usuario1 = null;
		usuario2 = null;
	}

	@Test
	public void presupuestoDisponible() {
		assertEquals(10, usuario2.getPresupuesto(), 0);
		usuario2.comprar(atraccion1);
		assertEquals(5, usuario2.getPresupuesto(), 0);
	}

	@Test
	public void tiempoDisponible() {
		assertEquals(10, usuario2.getTiempoDisponible(), 0);
		usuario2.comprar(atraccion1);
		assertEquals(8, usuario2.getTiempoDisponible(), 0);
	}

	@Test
	public void puedeComprarAtraccion() {
		usuario1.comprar(atraccion1);
		assertTrue(usuario1.puedeComprar(atraccion2));
	}

	@Test
	public void puedeComprarPromocion() {
		usuario1.comprar(atraccion2);
		assertTrue(usuario1.puedeComprar(promocion2));
	}

	@Test
	public void atraccionIncluidaEnPromocion() {
		usuario1.comprar(promocion1);
		assertFalse(usuario1.puedeComprar(atraccion2));
	}

	@Test
	public void atraccionYaComprada() {
		usuario1.comprar(atraccion1);
		assertFalse(usuario1.puedeComprar(promocion2));
	}

	@Test
	public void noPuedeComprarPromocionAtraccionIncluida() {
		usuario1.comprar(promocion1);
		assertFalse(usuario1.puedeComprar(promocion2));
	}

	@Test
	public void noPuedeComprarPromocionAtraccionIncluidaComoGratis() {
		usuario1.comprar(promocion3);
		assertFalse(usuario1.puedeComprar(promocion4));
	}
	
	@Test
	public void noPuedeComprarPresupuestoInsuficiente() {
		usuario2.comprar(atraccion1);
		assertFalse(usuario2.puedeComprar(atraccion3));
	}

	@Test
	public void noPuedeComprarTiempoInsuficiente() {
		usuario2.comprar(atraccion1);
		assertFalse(usuario2.puedeComprar(atraccion4));
	}
}
