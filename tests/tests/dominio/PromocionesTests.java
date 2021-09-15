package tests.dominio;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dominio.Atraccion;
import dominio.PromocionAbsoluta;
import dominio.Sugerible;
import dominio.TipoAtraccion;
import dominio.Usuario;

public class PromocionesTests {
	ArrayList<Atraccion> atracciones;
	Sugerible promocion1;
	Usuario u1;
	Usuario u2;

	@Before
	public void setUp() {
		atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 2, 2, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 2, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Rivendel", 14, 4.5, 2, TipoAtraccion.AVENTURA));

		u1 = new Usuario("Eowyn", 10, 8, TipoAtraccion.AVENTURA);
		u2 = new Usuario("Sauron", 10, 8, TipoAtraccion.AVENTURA);

		promocion1 = new PromocionAbsoluta("aventura", TipoAtraccion.AVENTURA, atracciones, 12);
	}

	@After
	public void tearDown() {
		atracciones = null;
		promocion1 = null;
		u1 = null;
		u2 = null;
	}

	@Test
	public void hayCupo() {
		u1.comprar(promocion1);

		assertTrue(promocion1.hayCupo());
	}

	@Test
	public void noHayCupo() {
		u1 = new Usuario("Eowyn", 10, 8, TipoAtraccion.AVENTURA);
		u2 = new Usuario("Sauron", 10, 8, TipoAtraccion.AVENTURA);

		u1.comprar(promocion1);
		u2.comprar(promocion1);

		assertFalse(promocion1.hayCupo());
	}

	@Test
	public void costoTotal() {
		assertEquals(12, promocion1.costoTotal(), 0);
	}

	@Test
	public void tiempoTotal() {
		assertEquals(9, promocion1.tiempoTotal(), 0);
	}
}
