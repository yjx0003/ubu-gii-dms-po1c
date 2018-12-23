package view;

import java.util.Map;
import model.MiembroDeEquipo;

public class EstadoVistaMiembros extends EstadoVista {

	private static EstadoVistaMiembros instancia = null;

	private EstadoVistaMiembros() {

	}

	/**
	 * Devuelve la instancia unica.
	 * 
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaMiembros getInstancia() {
		if (instancia == null) {
			instancia = new EstadoVistaMiembros();
		}
		return instancia;
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		ui.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		Map<Integer, MiembroDeEquipo> miembros = ui.getControlador().getModeloKanban().getMiembros(); 
		System.out.println("*******MIEMBROS DE EQUIPO********\n");
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("-" + par.getValue().getNombre());
		}
		opcionUsuario = opcionMenu("Escoja una opcion: ", OpcionesMenu.ANADIR_MIEMBRO, OpcionesMenu.ATRAS);


	}

}
