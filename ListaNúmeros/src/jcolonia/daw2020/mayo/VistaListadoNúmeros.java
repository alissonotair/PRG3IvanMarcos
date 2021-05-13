package jcolonia.daw2020.mayo;

import java.util.ArrayList;
import java.util.Scanner;



public class VistaListadoNúmeros extends VistaGeneral{

	public VistaListadoNúmeros(String nombre, Scanner scEntrada) {
		super(nombre, scEntrada);
	}

	public void mostrarListado(ArrayList<String> conjunto) {
		int linea = 0;
		mostrarMensaje("\nlistado\n");
		for (int i = 0; i < conjunto.size(); i++) {
			out.println(VistaMenúGeneral.rellenar(30, '-'));
			out.println(conjunto.get(i));
			
			linea++;
			if (linea % 10 == 0) {

				if (!pedirConfirmación("¿desea ver todo el listado?")) {
					break;
				}
			}

		}
	}
}
