package jcolonia.daw2020.mayo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Colección de números «decimales» –valores con parte entera y parte
 * fraccionaria, almacenamiento en coma flotante–. Admite valores positivos con
 * un valor máximo un numero máximo de decimales determinado. Ver {@link #MÁX} y
 * {@link #PRECISIÓN}.
 * 
 * @versión 2021.2.1
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * 
 * 
 */

public class ListaNúmeros {
	/**
	 * Longitud máxima, tanto de la parte entera como de la parte decimal.
	 * 
	 */
	public static final int PRECISIÓN = 4;

	/** Valor máximo admitido, determinado por {@link #PRECISIÓN} */
	private static final double MÁX;

	/**
	 * Formato de salida, asociado a la {@link #PRECISIÓN}. Rellenará con ceros
	 * todas las posiciones decimales.
	 * 
	 * <pre>
	 * Ej.: 35.0e2 → 3500.0000
	 * </pre>
	 */
	private static final NumberFormat formato;

	/** Almacenamiento de los valores contenidos. */
	private ArrayList<Double> lista;

	/**
	 * Calcula {@link #MÁX} a partir de {@link #PRECISIÓN} y prepara
	 * {@link #formato}.
	 */
	static {
		MÁX = Math.pow(10.0000000000, PRECISIÓN);

		// Precisión = 4 → "0.0000"
		String txtFormato = "0." + VistaGeneral.rellenar(PRECISIÓN, '0');
		formato = new DecimalFormat(txtFormato);
	}

	/**
	 * Inicializa el almacenamiento.
	 */
	public ListaNúmeros() {
		lista = new ArrayList<Double>(6);
	}

	/**
	 * Incorpora un valor numérico.
	 * 
	 * @param valor el valor a incorporar
	 * @throws SumatorioNumberException si el valor está fuera de rango.
	 * @see #comprobarRangoPermitido(Double)
	 */
	public void add(double valor) throws SumatorioNumberException {
		comprobarRangoPermitido(valor);
		lista.add(Math.copySign(valor, +0.0));
	}

	/**
	 * Incorpora un valor a partir de una representación textual.
	 * 
	 * @param entrada el texto referido
	 * @throws SumatorioNumberException si el texto no es reconocible o el valor
	 *                                  está fuera de rango.
	 * @see java.lang.Double#parseDouble(String)
	 * @see #comprobarRangoPermitido(Double)
	 */
	public void add(String entrada) throws SumatorioNumberException {
		double valor = transformarEntradaTexto(entrada);
		lista.add(Math.copySign(valor, +0.0));
	}

	/**
	 * Transforma un texto en un valor compatible con la lista.
	 * 
	 * @param entrada el texto referido
	 * @throws SumatorioNumberException si el texto no es reconocible o el valor
	 *                                  está fuera de rango.
	 * @see java.lang.Double#parseDouble(String)
	 * @see #comprobarRangoPermitido(Double)
	 */
	public static double transformarEntradaTexto(String entrada) throws SumatorioNumberException {
		double valor;
		try {
			valor = Double.parseDouble(entrada);
		} catch (NumberFormatException e) {
			throw new SumatorioNumberException(e);
		}
		comprobarRangoPermitido(valor);
		return valor;
	}

	/**
	 * Comprueba si un valor es admisible: parte entera y parte decimal con longitud
	 * determinada por {@link #PRECISIÓN}. ATENCIÓN: hay que revisar el algoritmo:
	 * los pequeños errores en la aritmética de coma flotante causan falsas alertas…
	 * 
	 * @param valor el valor a analizar
	 * 
	 * @throws SumatorioNumberException si el valor está fuera de rango.
	 */
	private static void comprobarRangoPermitido(Double valor) throws SumatorioNumberException {
		if (valor < 0. | valor >= MÁX) {
			throw new SumatorioNumberException("Valor fuera de rango");
		} else {
			double truncado = Math.floor(valor * MÁX) / MÁX; // PRECISIÓN - 1 decimales
			if (valor - truncado > .0) {
				throw new SumatorioNumberException("Demasiados decimales");
			}
		}
	}

	/**
	 * Informa del número de sumando almacenados.
	 * 
	 * @return el calor correspondiente
	 */
	public int getNúmSumandos() {
		return lista.size();
	}

	/**
	 * Suma todos los valores almacenados.
	 * 
	 * @return la suma obtenida
	 */
	public double getSuma() {
		double suma = .0;
		for (double valor : lista) {
			suma += valor;
		}
		return suma;
	}

	/**
	 * Prepara una lista con las representaciones en modo texto de los valores
	 * contenidos empleando el {@link #formato} predeterminado.
	 * 
	 * @return ls lista correspondiente
	 */
	public ArrayList<String> toListaString() {
		ArrayList<String> listaTextos = new ArrayList<String>(lista.size());
		String texto;

		for (Double valor : lista) {
			texto = formato.format(valor);
			listaTextos.add(texto);
		}

		return listaTextos;
	}

	/**
	 * Produce una representación textual de la suma con los valores alineados.
	 * 
	 * <pre>
	 * Ej.:
	 *      9999,9999
	 *        99,0000
	 *         0,0000
	 *        33,2500
	 *       434,3600
	 *    +    0,0001
	 *   ------------
	 *     10566,6100
	 * </pre>
	 */
	@Override
	public String toString() {
		String texto, prefijo;
		Formatter sbTexto = new Formatter();

		String txtFormato = "%s%" + (2 * PRECISIÓN + 1) + "." + PRECISIÓN + "f%n";
		for (int i = 0; i < lista.size(); i++) {
			prefijo = (i + 1 == lista.size() ? " + " : "   ");
			sbTexto.format(txtFormato, prefijo, lista.get(i));
		}
		sbTexto.format("%s%n", VistaGeneral.rellenar(4 + PRECISIÓN * 2, '-'));

		txtFormato = "%" + (2 * PRECISIÓN + 4) + "." + PRECISIÓN + "f%n";
		sbTexto.format(txtFormato, getSuma());

		texto = sbTexto.toString();
		sbTexto.close();

		return texto;
	}

	/**
	 * Ensayo demostración de las funcionalidades de la clase.
	 * 
	 * @param args no se usa
	 */
	public static void main(String[] args) {
		String[] entradas = { "9999.9999", "99", "-.1", "-0.0", "10000.", "33.25", "434.36", "34,35", "1e-4", "1e-5",
				"34.2342342", "sdfgsdfgdf", "0.345", ".345" };

		ListaNúmeros grupo = new ListaNúmeros();

		System.out.println("Ensayos:");
		System.out.println(Arrays.toString(entradas));
		System.out.println();

		for (String entrada : entradas) {
			try {
				grupo.add(entrada);
			} catch (SumatorioNumberException e) {
				System.err.printf("   ** %s → %s%n", entrada, e.getLocalizedMessage());
			}
		}

		System.out.println();
		ArrayList<String> textos = grupo.toListaString();

		for (String texto : textos) {
			System.out.println(texto);
		}

		System.out.println();
		System.out.println(grupo);
	}
}
