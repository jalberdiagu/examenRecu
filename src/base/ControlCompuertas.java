package base;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlCompuertas {
	private static final Logger LOGGER = Logger.getLogger(ControlCompuertas.class.getName());

	private static Scanner teclado = new Scanner(System.in);
	
	private static boolean permiso = false;
	
	private static boolean compuertasVerificadas = false;

	public static void main(String[] args) {
		
		try {
			Handler fileHandler = new FileHandler("./logs/" + ControlCompuertas.leerNivelAgua() , true);
		    fileHandler.setLevel(Level.ALL);
		    LOGGER.addHandler(fileHandler);
		}catch(SecurityException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(
				"Este programa lee el nivel de agua de una presa y permite abrir compuertas si tenemos permiso (el nivel es superior a 50) y las compuertas están verificadas.");

		int nivel = leerNivelAgua();

		mostrarMenu(nivel);

	}

	/**
	 *  Este metodo muestra un menu que contiene varias opciones de eleccion y no delvuelve nada
	 * @param nivel lee el nivel del agua y lo muestra por consola
	 * @author jalberdiagu
	 */
	public static void mostrarMenu(int nivel) {
		int opcion = 0;
		do {
			System.out.println();
			System.out.println("Nivel del agua: " + nivel);
			System.out.println();
			System.out.println("ACCIONES: ");
			System.out.println();
			System.out.println("1. Nueva lectura del nivel de agua.");
			System.out.println("2. Abrir compuertas. Requiere:");
			System.out.println("	3. Solicitar permiso, estado: " + (permiso ? "CONCEDIDO" : "NO CONCEDIDO"));
			System.out.println("	4. Verificar compuertas, estado: " + (compuertasVerificadas ? "VERIFICADAS" : "NO VERIFICADAS"));
			System.out.println("5. Salir");
			System.out.println();
			System.out.print("Introduce opción: ");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				nivel = leerNivelAgua();
				permiso = false;
				compuertasVerificadas = false;
				break;
			case 2:
				if(abrirCompuertas()) {
					System.out.println();
					System.out.print("¡Compuertas abiertas!");
				}else {
					System.out.println();
					System.out.print("No se cumplen las condiciones para abrir compuertas.");
				}
				break;
			case 3:
				permiso = solicitarPermiso(nivel);
				if(!permiso) {
					System.out.println();
					System.out.print("El permiso solamente se concede si el nivel del agua es superior a 60.");
				}
				break;	
			case 4:
				compuertasVerificadas = verificarCompuertas();
				if(compuertasVerificadas) {
					System.out.println();
					System.out.print("¡Compuertas verificadas!");
				}
				break;
			default:
				break;
			}
		} while (opcion != 5);
	}
	
	

	static int leerNivelAgua() {
		permiso = false;
		int nivel = (int) Math.round(Math.random() * 100);
		return  nivel;
	}

	static boolean abrirCompuertas() {
		if (permiso && compuertasVerificadas) {
			return true;
		}else {
			return false;
		}
	}
	
	static boolean solicitarPermiso(int nivel) {
		if (nivel > 60) {
			return true; 
		}else {
			return false;
		}
	}
	static boolean verificarCompuertas() {		
		return true;
	}

}
