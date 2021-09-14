package testsTierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.App;
import dominio.Atraccion;
import dominio.Promocion;
import dominio.PromocionAbsoluta;
import dominio.TipoAtraccion;
import dominio.Usuario;

public class PromocionesTests {

	String nombre;
	TipoAtraccion tipo;
	ArrayList<Atraccion> atracciones;
	Promocion promo1;

	@Before
	public void setUp() {
		nombre = "aventura";
		tipo = null;
		atracciones = new ArrayList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 2, 6, tipo.AVENTURA));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, tipo.AVENTURA));
		atracciones.add(new Atraccion("Rivendel", 14, 4.5, 1, tipo.AVENTURA));

		promo1 = new PromocionAbsoluta(nombre, tipo.AVENTURA, atracciones, 6);
	}

	@Test
	public void hayCupoTest() {
		Usuario usuario1 = new Usuario("Eowyn", 10, 8, tipo.AVENTURA);

		assertTrue(promo1.hayCupo());
		App.sugerir(promo1, usuario1);
		assertFalse(promo1.hayCupo());

	}

	@Test
	public void getCostoTotalTest() {
		assertEquals(6, promo1.costoTotal(), 0);
	}
	
	@Test
	public void getTiempoTotalTest() {
		assertEquals(9, promo1.tiempoTotal(), 0);
	}

}
