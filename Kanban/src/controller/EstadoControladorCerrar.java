package controller;

public class EstadoControladorCerrar extends EstadoControlador {

	private static EstadoControladorCerrar instancia=null;
	
	private EstadoControladorCerrar() {
		
	}
	
	public static EstadoControladorCerrar getInstancia() {
		if(instancia==null) {
			instancia=new EstadoControladorCerrar();
		}
		return instancia;
	}
	@Override
	public void actualizarEstado(Controlador c) {
		//NO hay mas estados

	}

	@Override
	public void mostrarMenu(Controlador c) {
		System.out.println("cerrando...");
		c.close();

	}



}
