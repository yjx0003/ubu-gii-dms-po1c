package controller;

import java.util.Map;

import model.Tarea;

public class EstadoControladorProductBacklog extends EstadoControlador{
	
	private static EstadoControladorProductBacklog instancia = null; 
	
	private EstadoControladorProductBacklog(){
		
	}
	
	public static EstadoControladorProductBacklog getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorProductBacklog(); 
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
			c.cambiarEstado(EstadoControladorAnadirTarea.getInstancia());
		}
		for (Map.Entry<Integer, Tarea> par : c.getModeloKanban().getProductBacklog().getTareas().entrySet()) {
			if(opcionUsuario == par.getKey()){
				EstadoControladorTarea estado = EstadoControladorTarea.getInstancia(); 
				estado.setTarea(par.getValue()); 
				c.cambiarEstado(estado);
			}
		}
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuProductBacklog(c.getModeloKanban().getProductBacklog());
		
	}

}
