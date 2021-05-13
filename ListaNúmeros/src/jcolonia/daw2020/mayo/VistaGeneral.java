package jcolonia.daw2020.mayo;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Utilidades comunes para vistas.
 * 
 * @versión 2021.2.1
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * 
 */
public abstract class VistaGeneral {

	/**
	 * Título asignado a la vista
	 */
	protected String título;

	/**
	 * Gestor único asociado a la entrada estándar sobre el que recoger todas las
	 * entradas por consola de texto realizadas.
	 */
	protected Scanner in;

	/**
	 * Salida general, salida estándar, a la que se enviarán todos los mensajes de
	 * salida.
	 */
	protected PrintStream out;

	/**
	 * Crea una cadena de texto por repetición de un carácter. Útil para prepara
	 * subrayados o rellenos.
	 * 
	 * @param longitud el número de caracteres
	 * @param c        el carácter deseado
	 * @return la cadena producida
	 */
	public static String rellenar(int longitud, char c) {
		String resultado;

		char[] relleno = new char[longitud];

		Arrays.fill(relleno, c);
		resultado = new String(relleno);

		return resultado;
	}

	/**
	 * Recoge el título, la entrada y determina la salida.
	 * 
	 * @param nombre    el título o descripción principal
	 * @param scEntrada el gestor conectado a la entrada estándar
	 */
	public VistaGeneral(String nombre, Scanner scEntrada) {
		título = nombre;
		in = scEntrada;
		out = System.out;
	}

	/**
	 * Muestra el título con borde simple.
	 * 
	 * <pre>
	 * Ej.:
	 *    Título
	 *    ------
	 * </pre>
	 */
	public void mostrarTítuloSimple() {
		out.printf("%n%s%n%s%n", título, rellenar(título.length(), '-'));
	}

	/**
	 * Muestra el título con doble borde.
	 * 
	 * <pre>
	 * Ej.:
	 *    ========
	 *     Título
	 *    ========
	 * </pre>
	 */
	public void mostrarTítuloDoble() {
		out.printf("%2$s%n %1$s%n%2$s%n", título, rellenar(título.length() + 2, '='));
	}

	/**
	 * Muestra un texto previo, invitación a introducir datos.
	 * 
	 * <pre>
	 * Ej.:
	 *     Escriba la opción elegida →
	 * </pre>
	 */
	public void mostrarInvitación() {
		out.printf("%n  Escriba la opción elegida → ");
	}

	/**
	 * Solicita confirmación al usuario
	 * 
	 * @param texto una invitación descriptiva
	 * @return si se ha aceptado o no
	 */
	public boolean pedirConfirmación(String texto) {
		String línea;
		boolean respuesta = false, respuestaVálida = false;

		do {
			out.printf("  %s (S/N): ", texto);
			línea = in.nextLine().toUpperCase();
			switch (línea) {
			case "S":
				respuesta = true;
				respuestaVálida = true;
				break;
			case "N":
				respuesta = false;
				respuestaVálida = true;
				break;
			default:
				out.printf("  ** ¡Debe contestar solo «S» o «N»! **%n", texto);
				break;
			}
		} while (!respuestaVálida);
		return respuesta;
	}

	/**
	 * Muestra un texto o aviso informativo.
	 * 
	 * <pre>
	 * Ej.:
	 *     *** Texto a aviso informativo
	 * </pre>
	 * 
	 * @param mensaje el texto deseado
	 */
	public void mostrarMensaje(String mensaje) {
		out.printf(" *** %s%n%n", mensaje);
	}

	/**
	 * Muestra un texto y espera a recoger una entrada. Sin más objetivo que pausar
	 * la ejecución, la entrada se desecha.
	 * 
	 * <pre>
	 * Ej.:
	 *     Pulse «Intro» para continuar
	 * </pre>
	 * 
	 * @param mensaje el texto deseado
	 */
	public void pausar() {
		mostrarMensaje("Pulse «Intro» para continuar");
		in.nextLine();
	}
}
