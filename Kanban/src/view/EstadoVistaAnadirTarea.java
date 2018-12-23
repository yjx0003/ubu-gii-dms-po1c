package view;

import java.util.Map;
import model.MiembroDeEquipo;

public class EstadoVistaAnadirTarea extends EstadoVista{
	
	
	private static EstadoVistaAnadirTarea instancia = null; 
	
	private EstadoVistaAnadirTarea(){
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaAnadirTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaAnadirTarea(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		
		ui.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.PRODUCT_BACKLOG));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		Map<Integer, MiembroDeEquipo> miembros = ui.getControlador().getModeloKanban().getMiembros(); 
		System.out.println("Nuevo título: ");
		String nuevoTitulo = sc.nextLine();
		System.out.println("Nueva descripción: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Nuevo coste: ");
		String nuevoCoste = sc.nextLine();
		System.out.println("Nuevo beneficio: ");
		String nuevoBeneficio = sc.nextLine();
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		int nuevoMiembro = opcionMenu("Nuevo miembro", miembros);
		System.out.println("Tipo de requisito asociado: ");

		int intopcion = opcionMenu("Elige el tipo de requisito", OpcionesMenu.HISTORIA_DE_USUARIO,
				OpcionesMenu.DEFECTO);
		String actor = "";
		String tarea = "";

		switch (OpcionesMenu.values()[intopcion]) {
		case HISTORIA_DE_USUARIO:
			System.out.println("Actor: ");
			actor = sc.nextLine();
			break;
		case DEFECTO:
			System.out.println("Defecto asociado a la tarea: ");
			tarea = sc.nextLine();
			break;
		default:
			System.err.println("Error");

		}

		boolean anadida = ui.getControlador().anadirTarea(nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio,
				nuevoMiembro, actor, tarea);
		if (!anadida) {
			System.out.println("La tarea no se ha podido añadir. Alguno de los campos era incorrecto. ");
		}
		
	}



}
