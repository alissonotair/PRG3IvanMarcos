package jcolonia.daw2020.mayo;

import java.util.Scanner;

public class VistaAgregarValores extends VistaGeneral {

	public VistaAgregarValores(Scanner in) {
		super(null, in);
	}

	public boolean agregarValor(ListaNúmeros conjunto) {
		boolean datosCorrectos = false;
		do {
			try {
				mostrarMensaje("Introduzca el nuevo valor → ");
				try {
					conjunto.add(recogerTexto());
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}

				datosCorrectos = true;
			} catch (Exception e) {

				mostrarMensaje("Codigo incorrecto");

			}
		} while (!datosCorrectos);
		return datosCorrectos;

	}

	protected String recogerTexto() throws Exception {
		String resultado = "";
		boolean salir = false;

		do {
			try {
				resultado = in.nextLine();

				salir = true;
			} catch (Exception e) {
				mostrarMensaje("Texto no válido");
			}
		} while (!salir);
		return resultado;
	}
}
