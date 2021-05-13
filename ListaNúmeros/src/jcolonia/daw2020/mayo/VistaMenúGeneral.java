package jcolonia.daw2020.mayo;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
	 * Formato de salida del menú
	 * @author ivanm
	 *
	 */	

public class VistaMenúGeneral {

	
	/**
	 * titulo del programa
	 */
		private String título;
		/**
		 * Matriz de Opciones a elegir.
		 */
		private String[] opciones;
	/**
	 * Posible numero de respuestas
	 */
		private static int númRespuestas = 0;
	/**
	 * Entrada 
	 */
		private Scanner in;
		/**
		 * salida
		 */
		private PrintStream out;
	/**
	 *Devuelve el numero de respuestas
	 * @return númRespuestas
	 */
		public static int getNúmRespuestas() {
			return númRespuestas;
		}
	/**
	 * Completa con guiones lineas para separar
	 * @param longitud
	 * @param c
	 * @return
	 */
		public static String rellenar(int longitud, char c) {
			String resultado;

			char[] relleno = new char[longitud];

			Arrays.fill(relleno, c);
			resultado = new String(relleno);

			return resultado;
		}
	/**
	 * Vista del menú en pantalla.
	 * @param títuloMenúPrincipal 
	 * @param opcionesMenú
	 * @param scEntrada
	 */
		public VistaMenúGeneral(String títuloMenúPrincipal, String[] opcionesMenú,
				Scanner scEntrada) {
			título = opcionesMenú[0];
			opciones = Arrays.copyOfRange(opcionesMenú, 1,
					opcionesMenú.length);

			in = scEntrada;
			out = System.out;
		}
	/**
	 *  Salida del menú en pantalla.
	 */
		public void mostrarMenúGeneral() {
			final String FORMATO_OPCIONES = "%n         ----->        ----->       %d) %s         <-----        <-----       %n";

			out.println(rellenar(título.length()/7, '-'));
			out.println(título);
			out.println(rellenar(título.length()/7, '-'));

			for (int i = 1; i <= opciones.length; i++) {
				out.printf(FORMATO_OPCIONES, i,
						opciones[i - 1]);
			}
			out.printf(FORMATO_OPCIONES, 0, "SALIR");
		}
	/**
	 * Formato invitación.
	 */
		public void mostrarInvitación() {
			out.printf("%n  ¿Que operación desea realizar? → ");
		}
		/**
		 * Formato mensajes.
		 */
		public void mostrarMensaje(String mensaje) {
			out.printf("%n *** %s%n%n", mensaje);
		}
	/**
	 * Eleccion de opciones.
	 * @return entrada
	 */
		public int elegirOpción() {
			String s, mensaje;
			int entrada = -1;

			boolean salir = false;

			do {
				mostrarMenúGeneral();
				mostrarInvitación();

				// Recoger y validar opciones
				try {
					s = in.nextLine();
					entrada = Integer.parseInt(s);
					númRespuestas = getNúmRespuestas() + 1;
					if (entrada < 0
							|| entrada > opciones.length) {
						throw new Exception(
								"Opción no válida");
					}
					salir = true;
				} catch (NumberFormatException e) {
					mostrarMensaje(
							"¡El sistema solo admite números enteros!");
				} catch (Exception e) {
					mensaje = String.format(
							"¡Hay que introducir una opción de menú válida (1-%d, 0 para salir)!",
							opciones.length);
					mostrarMensaje(mensaje);
				}
			} while (!salir);
			return entrada;
		}
	}




