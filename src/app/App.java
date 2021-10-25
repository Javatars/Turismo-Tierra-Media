package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import administradores.AdministradorArchivo;
import dominio.Atraccion;
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
		DecimalFormat formato = new DecimalFormat();
		formato.setMaximumFractionDigits(2);
		for(Usuario unUsuario : usuarios) {
			sugerencias.sort(new ComparadorDeSugerencias(unUsuario.getTipoAtraccionPreferida()));
			System.out.println("-----------------------");
			System.out.println("Usuario " + unUsuario.getNombre() + " - tiene " + unUsuario.getPresupuesto() + " monedas y " + 
					formato.format(unUsuario.getTiempoDisponible()) + " horas disponibles.");
			for(Sugerible unaSugerencia : sugerencias) {
				if(unUsuario.puedeComprar(unaSugerencia)) {
					System.out.println("Le quedan " + unUsuario.getPresupuesto() + " monedas y " + formato.format(unUsuario.getTiempoDisponible()) + " horas disponibles.");
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
		System.out.print("¿Desea comprarlo?(si/no): ");
		String decisionUsuario = "";
		try {
			decisionUsuario = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(decisionUsuario.equals("si"))
			unUsuario.comprar(sugerencia);
		else if(!decisionUsuario.equals("si") && !decisionUsuario.equals("no")) {
			while(!decisionUsuario.equals("si") && !decisionUsuario.equals("no")) {
				System.out.print("Debe ingresar si o no: ");
				try {
					decisionUsuario = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(decisionUsuario.equals("si"))
				unUsuario.comprar(sugerencia);
		}
	}

	public static boolean existeUsuario(String nombreUsuario) {
		boolean existeUsuario = false;
		for(Usuario unUsuario : usuarios) {
			if(unUsuario.getNombre().equals(nombreUsuario)) {
				existeUsuario = true;
				break;
			}	
		}
		return existeUsuario;
	}

	public static boolean existeSugerencia(String nombreSugerencia) {
		boolean existeSugerencia = false;
		for(Sugerible unaSugerencia : sugerencias) {
			if(unaSugerencia.getNombre().equals(nombreSugerencia)) {
				existeSugerencia = true;
				break;
			}
		}
		return existeSugerencia;
	}

	public static Atraccion getAtraccionPorNombre(String nombreSugerencia) {
		for(Sugerible unaSugerencia : sugerencias) {
			if(unaSugerencia.getNombre().equals(nombreSugerencia)) return (Atraccion) unaSugerencia;	
		}
		return null;//devuelve null si no existe la atraccion
	}

	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static ArrayList<Sugerible> getSugerencias() {
		return sugerencias;
	}
}
