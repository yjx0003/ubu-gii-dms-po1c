package controller;

/**
 * Estados posibles del controlador para mostrar los diferentes menus
 * 
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public abstract class EstadoControlador {

	protected static FactoryEstadoControlador factoryEstadoControlador = FactoryEstadoControlador.getInstancia();

	/**
	 * Actualiza al nuevo estado del controlador
	 * 
	 * @param c
	 *            el controlador que se quiere actualizar el estado
	 */
	public abstract void actualizarEstado(Controlador c);

	/**
	 * mostrar los diferentes menus segun el estado del controlador
	 * 
	 * @param c
	 *            controlador
	 */
	public abstract void mostrarMenu(Controlador c);

}
