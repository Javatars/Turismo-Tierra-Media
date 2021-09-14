package testsTierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.App;
import dominio.Atraccion;
import dominio.Promocion;
import dominio.PromocionAbsoluta;
import dominio.TipoAtraccion;
import dominio.Usuario;

public class UsuariosTests {

	String nombre;
	TipoAtraccion tipo;
	ArrayList<Atraccion> atracciones;
	Promocion promo1;
	Usuario usuario1;

	@Before
	public void setUp() {
		nombre = "aventura";
		tipo = null;
		atracciones = new ArrayList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 2, 6, tipo.AVENTURA));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, tipo.AVENTURA));
		atracciones.add(new Atraccion("Rivendel", 14, 4.5, 1, tipo.AVENTURA));

		promo1 = new PromocionAbsoluta(nombre, tipo.AVENTURA, atracciones, 6);
		usuario1 = new Usuario("Eowyn", 10, 10, tipo.AVENTURA);
	}

	@Test
	public void monedasGastadasTests() {

		assertEquals(10, usuario1.getPresupuesto(), 0);
		App.sugerir(promo1, usuario1);
		assertEquals(4, usuario1.getPresupuesto(), 0);

	}

	@Test
	public void tiempoTest() {

		assertEquals(10, usuario1.getTiempoDisponible(), 0);
		App.sugerir(promo1, usuario1);
		assertEquals(1, usuario1.getTiempoDisponible(), 0);

	}

	@Test
	public void puedeComprarTest() {

		Promocion promo2 = new PromocionAbsoluta(nombre, tipo.AVENTURA, atracciones, 6);
		assertTrue(usuario1.puedeComprar(promo1));
		App.sugerir(promo2, usuario1);
		assertFalse(usuario1.puedeComprar(promo2));

	}

}
