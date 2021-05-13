package jcolonia.daw2020.mayo;

/**
 * Excepción usada generalmente en la aplicación «Sumatorio MVC» cuando se
 * intenta añadir con formato incorrecto o fuera del rango soportado.
 * 
 * @versión 2021.2.0
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 */
public class SumatorioNumberException extends Exception {

	/**
	 * Número de serie, asociado a la versión de la clase.
	 */
	private static final long serialVersionUID = 20210511001L;

	/**
	 * Crea una excepción sin ninguna información adicional.
	 */
	public SumatorioNumberException() {
		super();
	}

	/**
	 * Crea una excepción con un texto descriptivo.
	 * 
	 * @param mensaje el texto correspondiente
	 */
	public SumatorioNumberException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Crea una excepción secundaria almacenando otra excepción de referencia.
	 * 
	 * @param causa la excepción –o {@link Throwable}– correspondiente
	 */
	public SumatorioNumberException(Throwable causa) {
		super(causa);
	}

	/**
	 * Crea una excepción secundaria almacenando otra excepción de referencia y un
	 * texto descriptivo.
	 * 
	 * @param mensaje el texto correspondiente
	 * @param causa   la excepción –o {@link Throwable}– correspondiente
	 */
	public SumatorioNumberException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}