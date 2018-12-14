package controller;

import controller.Controlador;
import persistence.PersistenciaAbstracta;
import persistence.PersistenciaCSV;
import persistence.PersistenciaXML;
import view.InterfazUsuarioAbstracta;
import view.InterfazUsuarioTexto;

public class Main {

	public static void main(String[] args) {
		
		InterfazUsuarioAbstracta vista = InterfazUsuarioTexto.getInstancia();
		PersistenciaAbstracta modelo = PersistenciaXML.getInstancia(); 
		
		Controlador controlador = Controlador.getInstancia(); 
		


		controlador.init(vista,modelo);
		
		
		

	}

}
