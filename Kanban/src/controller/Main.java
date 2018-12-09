package controller;

import controller.Controlador;
import persistence.PersistenciaAbstracta;
import persistence.PersistenciaCSV;
import view.InterfazUsuarioTexto;

public class Main {

	public static void main(String[] args) {
		
		InterfazUsuarioTexto vista = InterfazUsuarioTexto.getInstancia();
		PersistenciaAbstracta modelo = PersistenciaCSV.getInstancia(); 
		
		Controlador controlador = Controlador.getInstancia(); 
		


		controlador.init(vista,modelo);
		
		
		

	}

}
