package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import administradores.AdministradorArchivo;
import dominio.Atraccion;
import dominio.ComparadorDeSugerencias;
import dominio.Sugerible;
import dominio.Usuario;

public class App {
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static ArrayList<Sugerible> sugerencias = new ArrayList<Sugerible>();

	public static void main(String[] args) {
		System.out.println("Sistema de Turismo en la Tierra Media");
		usuarios = AdministradorArchivo.leerUsuarios();
		sugerencias = AdministradorArchivo.leerAtracciones();
		sugerencias = AdministradorArchivo.leerPromociones();

		for(Usuario unUsuario : usuarios) {
			sugerencias.sort(new ComparadorDeSugerencias(unUsuario.getTipoAtraccionPreferida()));
			System.out.println("Soy el usuario " + unUsuario.getNombre());
			System.out.println("-----------------------");
			for(Sugerible unaSugerencia : sugerencias) {
				if(unUsuario.puedeComprar(unaSugerencia) && unaSugerencia.hayCupo()) {
					if(unaSugerencia.esPromocion()) sugerir(unaSugerencia, unUsuario);
					else if(!unUsuario.getItinerario().hayPromocionAceptadaQueIncluyeAtraccion((Atraccion)unaSugerencia)) sugerir(unaSugerencia, unUsuario);
				}			
			}
		}
		System.out.println();
		System.out.println("Acontinuacion se generan los archivos de salida para cada usuario");
		for(Usuario unUsuario : usuarios) {
			AdministradorArchivo.escribirCompraUsuario(unUsuario);
		}
	}

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static ArrayList<Sugerible> getSugerencias() {
		return sugerencias;
	}

	public static void sugerir(Sugerible sugerencia, Usuario unUsuario) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		sugerencia.toString();
		System.out.println("¿Desea comprarlo?(si/no): ");
		String decisionUsuario = "";
		try {
			decisionUsuario = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(decisionUsuario.equals("si")) {
			unUsuario.getItinerario().agregarSugerencia(sugerencia);
			unUsuario.disminuirPresupuesto(sugerencia.costoTotal());
			unUsuario.disminuirTiempoDisponible(sugerencia.tiempoTotal());
			sugerencia.disminuirCupo();
		}
	}
}
