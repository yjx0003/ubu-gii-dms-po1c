package controller;




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
		
		if (opcionUsuario<OpcionesMenu.values().length) {
			c.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
		}else {
			c.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.TAREA));
		
			
		}
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuProductBacklog(c.getModeloKanban().getProductBacklog());
		
	}

}
