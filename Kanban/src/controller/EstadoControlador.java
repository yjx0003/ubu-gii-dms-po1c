package controller;


public abstract class EstadoControlador {
	
	protected static FactoryEstadoControlador factoryEstadoControlador=FactoryEstadoControlador.getInstancia();
	public abstract void actualizarEstado(Controlador c); 
	
	public abstract void mostrarMenu(Controlador c); 
	

}
