package controller;

import controller.Controlador; 
import model.PersistenciaAbstracta; 
import model.PersistenciaCSV;
import view.InterfazUsuarioTexto;

public class Main {

	public static void main(String[] args) {
		
		InterfazUsuarioTexto vista = InterfazUsuarioTexto.getInstancia();
		PersistenciaAbstracta modelo = PersistenciaCSV.getInstancia(); 
		
		Controlador controlador = Controlador.getInstancia(); 
		


		controlador.init(vista,modelo);
		
		
		

	}

}
