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
import excepciones.AtraccionExistenteException;
import excepciones.AtraccionNoExisteException;
import excepciones.PromocionExistenteException;
import excepciones.UsuarioExistenteException;

public class AdministradorArchivo {
	public static ArrayList<Usuario> leerUsuarios() throws UsuarioExistenteException, FileNotFoundException {
		File f = new File("files/usuarios.in");
		Scanner sc = null;
		ArrayList<Usuario> usuarios = App.getUsuarios();
		String[] line;
		sc = new Scanner(f);
		while(sc.hasNextLine()) {
			line = sc.nextLine().split("-");

			boolean existeUsuario = false;
			for(Usuario unUsuario : usuarios) {
				if(line[0].equals(unUsuario.getNombre())) {
					existeUsuario = true;
					break;
				}	
			}
			if(!existeUsuario)
				usuarios.add(new Usuario(line[0], Integer.parseInt(line[1]), Double.parseDouble(line[2]),TipoAtraccion.valueOf(line[3])));
			else {
				sc.close();
				throw new UsuarioExistenteException("Ya existe un Usuario con el nombre " + line[0] + ", corrija el archivo usuarios.in e intente nuevamente");
			}
			line = null;
		}
		sc.close();
		return usuarios;
	}

	public static ArrayList<Sugerible> leerAtracciones() throws AtraccionExistenteException, FileNotFoundException {
		File f = new File("files/atracciones.in");
		Scanner sc;
		ArrayList<Sugerible> sugerencias = App.getSugerencias();
		String[] line;
		sc = new Scanner(f);
		while(sc.hasNextLine()) {
			line = sc.nextLine().split("-");

			boolean existeAtraccion = false;
			for(Sugerible unaSugerencia : sugerencias) {
				if(line[0].equals(unaSugerencia.getNombre())) {
					existeAtraccion = true;
					break;
				}	
			}
			if(!existeAtraccion)
				sugerencias.add(new Atraccion(line[0], Integer.parseInt(line[1]), Double.parseDouble(line[2]),Integer.parseInt(line[3]), TipoAtraccion.valueOf(line[4])));
			else {
				sc.close();
				throw new AtraccionExistenteException("Ya existe una Atraccion con el nombre " + line[0] + ", corrija el archivo atracciones.in e intente nuevamente");
			}
			line = null;
		}
		sc.close();
		return sugerencias;
	}

	public static ArrayList<Sugerible> leerPromociones() throws FileNotFoundException, PromocionExistenteException, AtraccionNoExisteException{
		File f = new File("files/promociones.in");
		Scanner sc;
		ArrayList<Sugerible> sugerencias = App.getSugerencias();
		String[] line;
		
		sc = new Scanner(f);
		while(sc.hasNextLine()) {
			line = sc.nextLine().split("-");
			
			boolean existePromocion = false;
			for(Sugerible unaSugerencia : sugerencias) {
				if(line[1].equals(unaSugerencia.getNombre())) {
					existePromocion = true;
					break;
				}	
			}
			if(existePromocion) {
				sc.close();
				throw new PromocionExistenteException("Ya existe una Promocion con el nombre " + line[1] + ", corrija el archivo promociones.in e intente nuevamente");
			}
			else {
				ArrayList<Atraccion> listAtracciones = new ArrayList<Atraccion>();
				for(int i=0; i < Integer.parseInt(line[4]);i++) {
					boolean existeAtraccion = false;
					for(Sugerible unaSugerencia : sugerencias) {
						if(unaSugerencia.getNombre().equals(line[5+i])) {
							existeAtraccion = true;
							listAtracciones.add((Atraccion) unaSugerencia);
							break;
						}
					}
					if(!existeAtraccion) {
						sc.close();
						throw new AtraccionNoExisteException("No existe una Atraccion con el nombre " + line[5+i] + ", revea los archivos atracciones.in y promociones.in, corrijalos e intente nuevamente");
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
					boolean existeAtraccion = false;
					for(Sugerible unaSugerencia : sugerencias) {
						if(unaSugerencia.getNombre().equals(line[3])) {
							existeAtraccion = true;
							atraccionGratis = (Atraccion) unaSugerencia;
							break;
						}
					}
					if(existeAtraccion)
						sugerencias.add(new PromocionAxB(line[1],TipoAtraccion.valueOf(line[2]),listAtracciones,atraccionGratis));
					else {
						sc.close();
						throw new AtraccionNoExisteException("No existe una Atraccion con el nombre " + line[3] + ", revea los archivos atracciones.in y promociones.in, corrijalos e intente nuevamente");
					}
				}
				line = null;
				listAtracciones = null;
			}
		}
		sc.close();
		return sugerencias;
	}

	public static void escribirCompraUsuario(Usuario usuario) {
		File f = new File("files/" + usuario.getNombre() + ".out");
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(f);
			 
			pw.write(usuario.getItinerario().resumen());
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
}
