package view;

import java.util.Map;

import controller.Controlador;
import model.Backlog;
import model.Tarea;
import model.MiembroDeEquipo;

public class EstadoVistaModificarTarea extends EstadoVista {
	
	private Tarea tarea; 
	
	private Backlog backlog; 
	
	private static EstadoVistaModificarTarea instancia = null; 
	
	private EstadoVistaModificarTarea(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaModificarTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaModificarTarea(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		ui.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.TAREA));
		
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
		
	}
	
	public void setBacklog(Backlog backlog){
		this.backlog = backlog; 
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		System.out.println("*******MODIFICAR TAREA " + tarea.getIdTarea() + "********\n");
		System.out.println("Titulo: " + tarea.getTitulo());
		System.out.println("Descripción: " + tarea.getDescripcion());
		System.out.println("Coste: " + tarea.getCoste());
		System.out.println("Beneficio: " + tarea.getBeneficio());
		System.out.println("Asignada a: " + tarea.getMiembro());
		System.out.println(tarea.getRequisito());

		System.out.println("\n**Se deben dejar los campos vacíos si no se quieren modificar. **\n");

		System.out.println("Nuevo título: ");
		String nuevoTitulo = sc.nextLine();
		System.out.println("Nueva descripción: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Nuevo coste: ");
		String nuevoCoste = sc.nextLine();
		System.out.println("Nuevo beneficio: ");
		String nuevoBeneficio = sc.nextLine();
		for (Map.Entry<Integer, MiembroDeEquipo> par : ui.getControlador().getModeloKanban().getMiembros().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		int nuevoMiembro = opcionMenu("Nuevo miembro", ui.getControlador().getModeloKanban().getMiembros(), OpcionesMenu.MANTENER_MIEMBRO);
		if (nuevoMiembro == OpcionesMenu.MANTENER_MIEMBRO.ordinal()) {
			nuevoMiembro = 0;
		}
		ui.getControlador().modificarTarea(tarea.getIdTarea(), nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio,
				nuevoMiembro, backlog);
	}

}
