package dominioTests;

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

public class PromocionesTests {
	ArrayList<Atraccion> atracciones;
	ArrayList<Atraccion> atracciones2;
	ArrayList<Atraccion> atracciones3;
	Sugerible promocion1;
	Sugerible promocion2;
	Sugerible promocion3;
	Usuario u1;
	Usuario u2;

	@Before
	public void setUp() {
		atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 2, 2, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 2, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Rivendel", 14, 4.5, 2, TipoAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Mordor",25,3,3,TipoAtraccion.AVENTURA));

		atracciones2 = new ArrayList<Atraccion>();
		atracciones2.add(new Atraccion("Moria", 10, 2, 2, TipoAtraccion.AVENTURA));
		atracciones2.add(new Atraccion("Minas Tirith", 5, 2.5, 2, TipoAtraccion.AVENTURA));
		atracciones2.add(new Atraccion("Mordor",25,3,3,TipoAtraccion.AVENTURA));

		atracciones3 = new ArrayList<Atraccion>();
		atracciones3.add(new Atraccion("Bosque Negro", 10, 2, 2, TipoAtraccion.AVENTURA));
		atracciones3.add(new Atraccion("Minas Tirith", 5, 2.5, 2, TipoAtraccion.AVENTURA));
		atracciones3.add(new Atraccion("Mordor",25,3,3,TipoAtraccion.AVENTURA));

		u1 = new Usuario("Eowyn", 10, 8, TipoAtraccion.AVENTURA);
		u2 = new Usuario("Sauron", 10, 8, TipoAtraccion.AVENTURA);

		promocion1 = new PromocionAbsoluta("aventura", TipoAtraccion.AVENTURA, atracciones, 20);
		promocion2 = new PromocionPorcentual("aventura 2", TipoAtraccion.AVENTURA, atracciones2, 0.3);
		promocion3 = new PromocionAxB("aventura 3", TipoAtraccion.AVENTURA,atracciones3, new Atraccion("Bosque de Fangorn",25,3,3,TipoAtraccion.AVENTURA));
	}

	@After
	public void tearDown() {
		atracciones = null;
		atracciones2 = null;
		atracciones3 = null;
		promocion1 = null;
		promocion2 = null;
		promocion3 = null;
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
	public void costoTotalPromocionAxB() {
		assertEquals(40, promocion3.costoTotal(), 0);
	}

	@Test
	public void costoTotalPromocionPorcentual() {
		assertEquals(28, promocion2.costoTotal(), 0);
	}

	@Test
	public void costoTotalPromocionAbsoluta() {
		assertEquals(20, promocion1.costoTotal(), 0);
	}

	@Test
	public void tiempoTotalLas3Promociones() {
		assertEquals(12, promocion1.tiempoTotal(), 0);
		assertEquals(7.5, promocion2.tiempoTotal(), 0);
		assertEquals(10.5, promocion3.tiempoTotal(), 0);
	}
}
