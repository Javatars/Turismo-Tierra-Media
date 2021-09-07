package administradores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import app.App;
import dominio.Atraccion;
import dominio.PromocionAbsoluta;
import dominio.PromocionAxB;
import dominio.PromocionPorcentual;
import dominio.Sugerible;
import dominio.TipoAtraccion;
import dominio.Usuario;

public class AdministradorArchivo {
	public static ArrayList<Usuario> leerUsuarios() {
		File f = new File("files/usuarios.in");
		Scanner sc;
		ArrayList<Usuario> usuarios = App.getUsuarios();
		String[] line;
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine()) {
				line = sc.nextLine().split("-");
				usuarios.add(new Usuario(line[0], Integer.parseInt(line[1]), Double.parseDouble(line[2]),TipoAtraccion.valueOf(line[3])));
				line = null;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	public static ArrayList<Sugerible> leerAtracciones() {
		File f = new File("files/atracciones.in");
		Scanner sc;
		ArrayList<Sugerible> sugerencias = App.getSugerencias();
		String[] line;
		
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine()) {
				line = sc.nextLine().split("-");
				sugerencias.add(new Atraccion(line[0], Integer.parseInt(line[1]), Double.parseDouble(line[2]),Integer.parseInt(line[3]), TipoAtraccion.valueOf(line[4])));
				line = null;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		return sugerencias;
	}
	
	public static ArrayList<Sugerible> leerPromociones(){
		File f = new File("files/promociones.in");
		Scanner sc;
		ArrayList<Sugerible> sugerencias = App.getSugerencias();
		String[] line;
		
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine()) {
				line = sc.nextLine().split("-");
				ArrayList<Atraccion> listAtracciones = new ArrayList<Atraccion>();
				for(int i=0; i < Integer.parseInt(line[4]);i++) {
					for(Sugerible unaSugerencia : sugerencias) {
						if(unaSugerencia.getNombre().equals(line[5+i])) {
							listAtracciones.add((Atraccion) unaSugerencia);
							break;
						}
					}
				}
				if(Integer.parseInt(line[0]) == 1) {//PROMOCION PORCENTUAL
					sugerencias.add(new PromocionPorcentual(line[1],TipoAtraccion.valueOf(line[2]),listAtracciones,Double.parseDouble(line[3])));
				}
				else if(Integer.parseInt(line[0]) == 2) {//PROMOCION ABSOLUTA
					sugerencias.add(new PromocionAbsoluta(line[1],TipoAtraccion.valueOf(line[2]),listAtracciones,Integer.parseInt(line[3])));
				}
				else { //PROMOCION AxB
					Atraccion atraccionGratis = null;
					for(Sugerible unaSugerencia : sugerencias) {
						if(unaSugerencia.getNombre().equals(line[3])) {
							atraccionGratis = (Atraccion) unaSugerencia;
							break;
						}
					}
					sugerencias.add(new PromocionAxB(line[1],TipoAtraccion.valueOf(line[2]),listAtracciones,atraccionGratis));
				}
				line = null;
				listAtracciones = null;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
		}
		return sugerencias;
	}
	
	public static void escribirCompraUsuario(Usuario usuario) {
		File f = new File("files/" + usuario.getNombre() + ".out");
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(f);
			 
			pw.write(usuario.toString());
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
}
