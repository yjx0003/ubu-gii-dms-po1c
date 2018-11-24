package controller;

public class EstadoControladorPrincipal extends EstadoControlador{
	
	private static EstadoControladorPrincipal instancia = null; 
	
	private EstadoControladorPrincipal(){
		
	}
	
	public static EstadoControladorPrincipal getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorPrincipal(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		int opcionUsuario = c.getVistaKanban().getOpcionUsuario(); 
		
		switch(opcionUsuario){
		case 1: 
			c.cambiarEstado(EstadoControladorProductBacklog.getInstancia());break; 
		case 2: 
			c.cambiarEstado(EstadoControladorSprintBacklog.getInstancia());break; 
		case 3: 
			c.cambiarEstado(EstadoControladorMiembros.getInstancia());break; 
		case 4:
			c.cambiarEstado(EstadoControladorCerrar.getInstancia());break;
		}
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuPrincipal();
		
	}	

}
