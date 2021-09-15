package testsTierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import dominio.Atraccion;
import dominio.ComparadorDeSugerencias;
import dominio.Promocion;
import dominio.PromocionAbsoluta;
import dominio.Sugerible;
import dominio.TipoAtraccion;
import dominio.Usuario;

public class ComparadorTest {

	ArrayList<Atraccion> atracciones;
	Promocion promo1;
	Usuario usuario1;
	Atraccion atraccion1;
	Atraccion atraccion2;
	Atraccion atraccion3;
	Atraccion atraccion4;
	Atraccion atraccion5;
	ComparadorDeSugerencias comparador;

	@Before
	public void setUp() {
		atraccion1 = new Atraccion("Eary", 10, 10, 5, TipoAtraccion.PAISAJE);
		atraccion2 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		atraccion3 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.AVENTURA);
		atraccion4 = new Atraccion("Rivendel", 14, 4.5, 1, TipoAtraccion.AVENTURA);
		atraccion5 = new Atraccion("Maria", 10, 4, 6, TipoAtraccion.AVENTURA);

		atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
		atracciones.add(atraccion4);

		promo1 = new PromocionAbsoluta("aventura", TipoAtraccion.AVENTURA, atracciones, 6);
		usuario1 = new Usuario("Eowyn", 10, 10, TipoAtraccion.AVENTURA);

		comparador = new ComparadorDeSugerencias(TipoAtraccion.AVENTURA);

	}

	@Test
	public void comparadorDeDistintasSugerenciasDelMismoTipo() {

		ArrayList<Sugerible> lista = new ArrayList<Sugerible>();
		lista.add(atraccion2);
		lista.add(promo1);

		lista.sort(comparador);

		assertEquals(promo1, lista.get(0));
		assertEquals(atraccion2, lista.get(1));

	}

	@Test
	public void comparadorDeSugerenciaDeMismoTipoYDistintoCosto() {

		ArrayList<Sugerible> lista = new ArrayList<Sugerible>();
		lista.add(atraccion3);
		lista.add(atraccion4);
		lista.add(atraccion2);

		lista.sort(comparador);

		assertEquals(atraccion4, lista.get(0));
		assertEquals(atraccion2, lista.get(1));
		assertEquals(atraccion3, lista.get(2));

	}

	@Test
	public void comparadorDeSugerenciaDeMismoTipoMismoCostoDistintoTiempo() {

		ArrayList<Sugerible> lista = new ArrayList<Sugerible>();
		lista.add(atraccion2);
		lista.add(atraccion5);

		lista.sort(comparador);

		assertEquals(atraccion5, lista.get(0));
		assertEquals(atraccion2, lista.get(1));

	}

	@Test
	public void comparadorDeSugerenciasDeDistintoTipoDeAtraccion() {

		ArrayList<Sugerible> lista = new ArrayList<Sugerible>();
		lista.add(atraccion1);
		lista.add(atraccion2);

		lista.sort(comparador);

		assertEquals(atraccion2, lista.get(0));
		assertEquals(atraccion1, lista.get(1));

	}

}
