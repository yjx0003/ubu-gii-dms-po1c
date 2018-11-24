package controller;

import controller.Controlador; 
import model.PersistenciaAbstracta; 
import model.PersistenciaCSV;
import view.InterfazUsuarioTexto;

public class Main {

	public static void main(String[] args) {
		
		InterfazUsuarioTexto vista = InterfazUsuarioTexto.getInstancia();
		PersistenciaCSV modelo = PersistenciaCSV.getInstancia(); 
		
		Controlador controlador = Controlador.getInstancia(); 
		
		controlador.setModeloKanban(modelo);
		controlador.setVistaKanban(vista);
		
		vista.setControladorKanban(controlador);
		
		modelo.leerPersistencia();

		//vista.menuPrincipal();
		
		modelo.commit();
		

	}

}
