package controller;

public class EstadoControladorMiembros extends EstadoControlador{
	
	private static EstadoControladorMiembros instancia = null; 
	
	private EstadoControladorMiembros(){
		
	}
	
	public static EstadoControladorMiembros getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorMiembros(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		int opcionUsuario = c.getVistaKanban().getOpcionUsuario(); 
		switch(opcionUsuario){
		case 0: 
			c.cambiarEstado(EstadoControladorPrincipal.getInstancia());break; 
		case 1: 
			c.cambiarEstado(EstadoControladorAnadirMiembro.getInstancia());break; 
		}
		
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuMiembros(c.getModeloKanban().getMiembros());
		
	}

}
