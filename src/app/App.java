package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import administradores.AdministradorArchivo;
import dominio.ComparadorDeSugerencias;
import dominio.Sugerible;
import dominio.Usuario;
import excepciones.AtraccionExistenteException;
import excepciones.AtraccionNoExisteException;
import excepciones.PromocionExistenteException;
import excepciones.UsuarioExistenteException;

public class App {
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static ArrayList<Sugerible> sugerencias = new ArrayList<Sugerible>();

	public static void main(String[] args) {
		System.out.println("Sistema de Turismo en la Tierra Media");

		try {
			usuarios = AdministradorArchivo.leerUsuarios();
			sugerencias = AdministradorArchivo.leerAtracciones();
			sugerencias = AdministradorArchivo.leerPromociones();
			ejecutar();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}catch (UsuarioExistenteException e) {
			System.err.println(e.getMessage());
		}catch (AtraccionExistenteException e) {
			System.err.println(e.getMessage());
		} catch (PromocionExistenteException e) {
			System.err.println(e.getMessage());
		} catch (AtraccionNoExisteException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void ejecutar() {
		for(Usuario unUsuario : usuarios) {
			sugerencias.sort(new ComparadorDeSugerencias(unUsuario.getTipoAtraccionPreferida()));
			System.out.println("-----------------------");
			System.out.println("Usuario " + unUsuario.getNombre() + " - tiene " + unUsuario.getPresupuesto() + " monedas y " + 
					unUsuario.getTiempoDisponible() + " horas disponibles.");
			for(Sugerible unaSugerencia : sugerencias) {
				if(unUsuario.puedeComprar(unaSugerencia) && unaSugerencia.hayCupo() && !unUsuario.getItinerario().incluyeAtraccion(unaSugerencia)) {
					System.out.println("Le quedan " + unUsuario.getPresupuesto() + " monedas y " + unUsuario.getTiempoDisponible() + " horas disponibles.");
					sugerir(unaSugerencia, unUsuario);
				}		
			}
			System.out.println("-----------------------");
		}
		System.out.println();
		System.out.println("Acontinuacion se generan los archivos de salida para cada usuario");
		for(Usuario unUsuario : usuarios) {
			AdministradorArchivo.escribirCompraUsuario(unUsuario);
		}
		System.out.println("Fin del programa");
	}

	public static void sugerir(Sugerible sugerencia, Usuario unUsuario) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(sugerencia.toString());
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

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static ArrayList<Sugerible> getSugerencias() {
		return sugerencias;
	}
}
