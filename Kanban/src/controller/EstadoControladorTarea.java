package controller;

import model.Tarea;

public class EstadoControladorTarea extends EstadoControlador{
	
	private Tarea tarea;
	private static EstadoControladorTarea instancia = null; 
	
	private EstadoControladorTarea(){
	
	}
	
	public static EstadoControladorTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorTarea(); 
		}
		return instancia; 
	}
	
	@Override
	public void actualizarEstado(Controlador c) {
		int opcionUsuario = c.getVistaKanban().getOpcionUsuario(); 
		
		EstadoControlador e=factoryEstadoControlador.getEstado(opcionUsuario);
		if (!(e instanceof EstadoControladorModificarTarea)) {
			c.cambiarEstado(e);
		}else {
			EstadoControladorModificarTarea estado=(EstadoControladorModificarTarea) e;
			estado.setTarea(tarea);
			c.cambiarEstado(estado);
		}
		

	}
	


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	@Override
	public void mostrarMenu(Controlador c) {
		
		c.getVistaKanban().menuTarea(this.tarea);
	}
	
	

}
