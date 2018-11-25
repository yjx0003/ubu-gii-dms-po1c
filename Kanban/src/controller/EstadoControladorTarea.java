package controller;



import model.Tarea;

public class EstadoControladorTarea extends EstadoControlador{
	

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
		c.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));

	}


	@Override
	public void mostrarMenu(Controlador c) {
		int opcionUsuario=c.getVistaKanban().getOpcionUsuario();
		Tarea t=c.getModeloKanban().getProductBacklog().getTareas().get(opcionUsuario);
		if (t==null) {
			c.getModeloKanban().getSprintBacklog().getTareas().get(opcionUsuario);
			
		}
		c.getVistaKanban().menuTarea(t);
		
	}
	
	

}
