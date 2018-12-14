package view;

import java.util.Map;
import java.util.Scanner;

import controller.Controlador;
import model.Backlog;
import model.EstadoTarea;
import model.EstadoTareaCompletada;
import model.EstadoTareaEnProceso;
import model.MiembroDeEquipo;
import model.ProductBacklog;
import model.SprintBacklog;
import model.Tarea;

/**
 * Clase de la interfaz de usuario en texto, imprime todos los menus.
 * 
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class InterfazUsuarioTexto extends InterfazUsuarioAbstracta {


	private EstadoVista estado;



	private static InterfazUsuarioTexto instancia = null;

	private InterfazUsuarioTexto() {
		this.estado = EstadoVistaPrincipal.getInstancia(); 
	}

	public static InterfazUsuarioTexto getInstancia() {
		if (instancia == null) {
			instancia = new InterfazUsuarioTexto();
		}
		return instancia;
	}
	
	/**
	 * Cambia el estado del controlador.
	 * 
	 * @param e
	 *            nuevo estado del controlador.
	 * 
	 */
	public void cambiarEstado(EstadoVista e) {
		this.estado = e;
	}

	/**
	 * Cierra el scanner de teclado
	 */
	public void cerrarRecursos() {
		EstadoVista.sc.close();
	}
	
	public Controlador getControlador(){
		return this.controladorKanban; 
	}


	/**
	 * Inicia la vista instanciando el scanner y asignando el controlador
	 * @param controlador controlador
	 */
	public void init(Controlador controlador) {
		super.init(controlador);
		EstadoVista.sc = new Scanner(System.in); 
		
		while (true) {
			estado.mostrarMenu(this);
			estado.actualizarEstado(this);
		}
	}

	/**
	 * Devuelve el estado actual del controlador.
	 * 
	 * @return estado actual del controlador.
	 */
	public EstadoVista getEstado() {
		return estado;
	}

}