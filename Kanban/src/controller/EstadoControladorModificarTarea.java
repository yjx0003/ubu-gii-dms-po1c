package controller;

import model.Tarea;

public class EstadoControladorModificarTarea extends EstadoControlador {
	
	private Tarea tarea; 
	
	private static EstadoControladorModificarTarea instancia = null; 
	
	private EstadoControladorModificarTarea(){
		
	}
	
	public static EstadoControladorModificarTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorModificarTarea(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		c.cambiarEstado(EstadoControladorTarea.getInstancia());
		
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
		
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuModificarTarea(this.tarea, c.getModeloKanban().getMiembros(), c.getModeloKanban().getProductBacklog());
		
	}

}
