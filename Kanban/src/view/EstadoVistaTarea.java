package view;

import controller.Controlador;
import model.Tarea;

public class EstadoVistaTarea extends EstadoVista{
	
	private Tarea tarea;
	private static EstadoVistaTarea instancia = null; 
	
	private EstadoVistaTarea(){
	
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaTarea(); 
		}
		return instancia; 
	}
	
	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		
		EstadoVista e=factoryEstadoControlador.getEstado(opcionUsuario);
		if (!(e instanceof EstadoVistaModificarTarea)) {
			ui.cambiarEstado(e);
		}else {
			EstadoVistaModificarTarea estado=(EstadoVistaModificarTarea) e;
			estado.setTarea(tarea);
			ui.cambiarEstado(estado);
		}
		

	}
	


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		
		System.out.println("*******TAREA " + tarea.getIdTarea() + "********\n");
		System.out.println("Titulo: " + tarea.getTitulo());
		System.out.println("Descripción: " + tarea.getDescripcion());
		System.out.println("Coste: " + tarea.getCoste());
		System.out.println("Beneficio: " + tarea.getBeneficio());
		System.out.println("Asignada a: " + tarea.getMiembro());
		System.out.println(tarea.getRequisito());

		this.opcionUsuario = opcionMenu("Elige una opción", OpcionesMenu.MODIFICAR_TAREA, OpcionesMenu.ATRAS);
	}
	
	

}
