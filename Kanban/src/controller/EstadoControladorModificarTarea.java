package controller;

import model.Tarea;

public class EstadoControladorModificarTarea extends EstadoControlador {
	

	
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
		c.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.TAREA));
		
	}



	@Override
	public void mostrarMenu(Controlador c) {
		int opcionUsuario=c.getVistaKanban().getOpcionUsuario();
		Tarea t=c.getModeloKanban().getProductBacklog().getTareas().get(opcionUsuario);
		if (t==null) {
			c.getModeloKanban().getSprintBacklog().getTareas().get(opcionUsuario);
			
		}
		c.getVistaKanban().menuModificarTarea(t, c.getModeloKanban().getMiembros(), c.getModeloKanban().getProductBacklog());
		
	}

}
