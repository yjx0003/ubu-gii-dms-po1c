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
		c.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuMiembros(c.getModeloKanban().getMiembros());
		
	}

}
