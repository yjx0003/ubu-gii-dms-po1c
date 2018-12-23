package view;

import java.util.Map;
import java.util.Scanner;

/**
 * Estados posibles del controlador para mostrar los diferentes menus
 * 
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public abstract class EstadoVista {
	
	protected static Scanner sc;
	protected int opcionUsuario;

	protected static FactoryEstadoControlador factoryEstadoControlador = FactoryEstadoControlador.getInstancia();

	/**
	 * Actualiza al nuevo estado del controlador
	 * 
	 * @param c
	 *            el controlador que se quiere actualizar el estado
	 */
	public abstract void actualizarEstado(InterfazUsuarioTexto ui);

	/**
	 * mostrar los diferentes menus segun el estado del controlador
	 * 
	 * @param c
	 *            controlador
	 */
	public abstract void mostrarMenu(InterfazUsuarioTexto ui);
	
	/**
	 * Devuelve la opcion de usuario en el menu actual.
	 * @return opcion de usuario.
	 */
	public int getOpcionUsuario() {
		return this.opcionUsuario;
	}
	/**
	 * Bucle infinito hasta que se devuelva una opcion valida por el usuario
	 * @param preguntaOpcion texto que se muestra
	 * @param mapa mapa de miembros, product back log y sprint backlog o null 
	 * @param opcionesValidos OpcionesMenu validos
	 * @return
	 */
	protected int opcionMenu(String preguntaOpcion, Map<Integer, ?> mapa, OpcionesMenu... opcionesValidos) {
		System.out.println("");
		for (int i = 0; i < opcionesValidos.length; i++) {
			System.out.println("[" + i + "] " + opcionesValidos[i].getNombre());
		}

		while (true) {
			try {

				System.out.println(preguntaOpcion);

				int opcion = Integer.valueOf(sc.nextLine());

				if (opcion >= 0 && opcion < opcionesValidos.length) {
					return opcionesValidos[opcion].ordinal();
				} else if (mapa != null && mapa.containsKey(opcion)) {
					return opcion;
				}
				System.out.println(opcion + " no es un número válido");

			} catch (NumberFormatException e) {
				System.out.println("Tiene que ser un número");

			}

		}
	}
	/**
	 * Bucle infinito hasta que se devuelva una opcion valida por el usuario
	 * @param preguntaOpcion texto que se muestra
	 * @param opcionesValidos OpcionesMenu validos
	 * @return
	 */
	protected int opcionMenu(String preguntaOpcion, OpcionesMenu... opcionesValidos) {
		return opcionMenu(preguntaOpcion, null, opcionesValidos);

	}

}
