package view;

import controller.Controlador;

public abstract class InterfazUsuarioAbstracta {
	protected Controlador controladorKanban;
	
	public void init(Controlador controlador) {
		this.controladorKanban = controlador;
	}
	
	public abstract void cerrarRecursos(); 

}
