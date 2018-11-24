package controller;

import java.util.Map;

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
		if(opcionUsuario == 0){
			c.cambiarEstado(EstadoControladorPrincipal.getInstancia());
		}
		if(opcionUsuario == 1){
			c.cambiarEstado(EstadoControladorAnadirTareaSprint.getInstancia());
		}
		if(opcionUsuario == 3){
			c.cambiarEstado(EstadoControladorCrearNuevoSprint.getInstancia());
		}
		for (Map.Entry<Integer, Tarea> par : c.getModeloKanban().getSprintBacklog().getTareas().entrySet()) {
			if(opcionUsuario == par.getKey()){
				EstadoControladorTarea estado = EstadoControladorTarea.getInstancia(); 
				estado.setTarea(par.getValue()); 
				c.cambiarEstado(estado);
			}
		}
		
		
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuSprintBacklog(c.getModeloKanban().getSprintBacklog());
		
	}

	
}
