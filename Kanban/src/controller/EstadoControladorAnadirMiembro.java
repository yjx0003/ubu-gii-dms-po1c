package controller;

public class EstadoControladorAnadirMiembro extends EstadoControlador{
	
	private static EstadoControladorAnadirMiembro instancia = null; 
	
	private EstadoControladorAnadirMiembro(){
		
	}
	
	public static EstadoControladorAnadirMiembro getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorAnadirMiembro(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		c.cambiarEstado(EstadoControladorMiembros.getInstancia());
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuAnadirMiembro();
	}

}
