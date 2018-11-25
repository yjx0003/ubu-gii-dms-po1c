package controller;

import model.Tarea;

public class EstadoControladorSprintBacklog extends EstadoControlador{

	private static EstadoControladorSprintBacklog instancia = null; 
	
	private EstadoControladorSprintBacklog(){
		
	}
	
	public static EstadoControladorSprintBacklog getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorSprintBacklog(); 
		}
		return instancia; 
	}
	
	
	@Override
	public void actualizarEstado(Controlador c) {
		int opcionUsuario = c.getVistaKanban().getOpcionUsuario(); 

		
		if (opcionUsuario<OpcionesMenu.values().length) {
			c.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
		}else {
			EstadoControladorTarea estado= (EstadoControladorTarea)factoryEstadoControlador.getEstado(OpcionesMenu.TAREA);
			Tarea t=c.getModeloKanban().getSprintBacklog().getTareas().get(opcionUsuario);
			estado.setTarea(t);
			c.cambiarEstado(estado);
		
		}
		
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuSprintBacklog(c.getModeloKanban().getSprintBacklog());
		
	}

	
}
