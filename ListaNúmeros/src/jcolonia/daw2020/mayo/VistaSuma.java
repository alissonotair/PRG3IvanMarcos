package jcolonia.daw2020.mayo;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaSuma extends VistaGeneral {

	public VistaSuma(String nombre, Scanner scEntrada) {
		super(nombre, scEntrada);

	}

	public void mostrarSuma(String valor) {
		int linea = 0;
		mostrarMensaje("\nlistado\n");
		out.println(VistaMenúGeneral.rellenar(30, '-'));
		out.println(valor);
		linea++;

	}
}
