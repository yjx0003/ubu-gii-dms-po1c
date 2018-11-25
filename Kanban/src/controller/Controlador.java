package controller;

import java.util.Map;

import model.Backlog;
import model.Defecto;
import model.EstadoTareaCompletada;
import model.EstadoTareaEnValidacion;
import model.HistoriaDeUsuario;
import model.MiembroDeEquipo;
import model.PersistenciaAbstracta;
import model.Requisito;
import model.Tarea;
import view.InterfazUsuarioTexto;

/**
 * Clase controlador encargado de enlazar la vista y el modelo.
 * 
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class Controlador {

	private PersistenciaAbstracta modeloKanban;
	private InterfazUsuarioTexto vistaKanban;
	private EstadoControlador estado;

	private static Controlador instancia = null;

	private Controlador() {
		estado = EstadoControladorPrincipal.getInstancia();
	}

	/**
	 * Devuelve una instancia de Controlador.
	 * 
	 * @return instancia única de Controlador
	 */
	public static Controlador getInstancia() {
		if (instancia == null) {
			instancia = new Controlador();
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
	public void cambiarEstado(EstadoControlador e) {
		this.estado = e;
	}

	/**
	 * Inicia la vista y el modelo, tambien imprime el menu de la vista.
	 * 
	 * @param vista
	 *            instancia de la interfaz de usuario de texto.
	 * @param modelo
	 *            persistencia donde se accederan a los datos.
	 */
	public void init(InterfazUsuarioTexto vista, PersistenciaAbstracta modelo) {
		this.vistaKanban = vista;
		this.modeloKanban = modelo;

		modeloKanban.leerPersistencia();
		vistaKanban.init(this);

		while (true) {
			estado.mostrarMenu(this);
			estado.actualizarEstado(this);
		}
	}

	/**
	 * Devuelve el modelo donde se guardan los datos.
	 * 
	 * @return modeloKanban.
	 */
	public PersistenciaAbstracta getModeloKanban() {
		return modeloKanban;
	}

	/**
	 * Devuelve la vista donde se muestra la interfaz de los datos.
	 * 
	 * @return vistaKanban.
	 */
	public InterfazUsuarioTexto getVistaKanban() {
		return vistaKanban;
	}

	/**
	 * Devuelve el estado actual del controlador.
	 * 
	 * @return estado actual del controlador.
	 */
	public EstadoControlador getEstado() {
		return estado;
	}

	/**
	 * Modifica la tarea a partir de los parametros recibidos, los parametros vacios
	 * no se cambian.
	 * 
	 * @param idTarea
	 *            id de la tarea que se quiere modificar.
	 * @param nuevoTitulo
	 *            titulo nuevo o un string vacio si no se quiere modificar.
	 * @param nuevaDescripcion
	 *            nueva descripcion o un string vacio si no se quiere modificar.
	 * @param nuevoCoste
	 *            nuevo coste o un string vacio si no se quiere modificar.
	 * @param nuevoBeneficio
	 *            nuevo beneficio o un string vacio si no se quiere modificar.
	 * @param nuevoMiembro
	 *            nuevo miembro o un 0 si no se quiere modificar.
	 * @param backlog
	 *            el backlog donde se encuentra la tarea.
	 */
	public void modificarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, String nuevoCoste,
			String nuevoBeneficio, int nuevoMiembro, Backlog backlog) {
		Tarea tarea = backlog.getTareas().get(idTarea);
		boolean modificado = false;
		if (!nuevoTitulo.isEmpty()) {
			tarea.getRequisito().setTitulo(nuevoTitulo);
			modificado = true;
		}
		if (!nuevaDescripcion.isEmpty()) {
			tarea.getRequisito().setDescripcion(nuevaDescripcion);
			modificado = true;
		}
		if (!nuevoCoste.isEmpty()) {
			tarea.setCoste(Integer.parseInt(nuevoCoste));
			modificado = true;
		}
		if (!nuevoBeneficio.isEmpty()) {
			tarea.setBeneficio(Integer.parseInt(nuevoBeneficio));
			modificado = true;
		}
		if (nuevoMiembro != 0) { // mantener el miembro si es 0
			tarea.setMiembro(modeloKanban.getMiembros().get(nuevoMiembro));
			modificado = true;
		}

		if (modificado) {
			this.modeloKanban.commitProductBacklog();
			this.modeloKanban.commitSprintBacklog();
		}
	}

	/**
	 * Añade una nueva tarea al product backlog.
	 * 
	 * @param nuevoTitulo
	 *            el tilulo de la tarea.
	 * @param nuevaDescripcion
	 *            descripcion de la tarea.
	 * @param nuevoCoste
	 *            coste de la tarea.
	 * @param nuevoBeneficio
	 *            beneficio de la tarea.
	 * @param nuevoMiembro
	 *            miembro asociado a la tarea.
	 * @param actor
	 *            quien es el actor en caso de Historia de Usuario.
	 * @param tarea
	 *            que tarea es si es un Defecto.
	 * @return true si se ha podido añadir al product backlog, falso en caso
	 *         contrario.
	 */
	public boolean anadirTarea(String nuevoTitulo, String nuevaDescripcion, String nuevoCoste, String nuevoBeneficio,
			int nuevoMiembro, String actor, String tarea) {
		if (nuevoTitulo.isEmpty() || nuevaDescripcion.isEmpty() || nuevoCoste.isEmpty() || nuevoBeneficio.isEmpty()
				|| nuevoMiembro == 0) {
			return false;
		}
		try {

			Requisito nuevoRequisito;
			if (tarea.isEmpty()) {
				nuevoRequisito = new HistoriaDeUsuario(nuevoTitulo, nuevaDescripcion, actor);
			} else {
				nuevoRequisito = new Defecto(nuevoTitulo, nuevaDescripcion, Integer.parseInt(tarea));
			}
			Tarea nuevaTarea = new Tarea(Integer.parseInt(nuevoCoste), Integer.parseInt(nuevoBeneficio), nuevoRequisito,
					modeloKanban.getMiembros().get(nuevoMiembro));
			modeloKanban.anadirTarea(nuevaTarea);
			modeloKanban.commitProductBacklog();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Añade una tarea del product backlog al sprint backlog.
	 * 
	 * @param idTarea
	 *            id de la tarea que se quiere mover al sprint backlog.
	 */
	public void anadirTareaSprint(int idTarea) {
		Tarea t = modeloKanban.getProductBacklog().getTareas().remove(idTarea);
		modeloKanban.getSprintBacklog().getTareas().put(t.getIdTarea(), t);
		modeloKanban.commitProductBacklog();
		modeloKanban.commitSprintBacklog();
	}

	/**
	 * Actualiza el estado de la tarea.
	 * 
	 * @param idTarea
	 *            id de la tarea que se quiere actualizar.
	 */
	public void moverTarea(int idTarea) {
		if (this.modeloKanban.getSprintBacklog().getTareas().get(idTarea)
				.getEstado() instanceof EstadoTareaEnValidacion) {
			EstadoTareaEnValidacion.getInstancia().setSiguienteEstado(this.getVistaKanban().pedirSiguienteEstado());
		}
		modeloKanban.getSprintBacklog().getTareas().get(idTarea).actualizarEstado();
		modeloKanban.commitSprintBacklog();
	}

	/**
	 * Cambia el sprint backlog actual por uno nuevo.
	 * 
	 * @param dia
	 *            dia de inicio del nuevo sprint.
	 * @param mes
	 *            mes de inicio del nuevo sprint.
	 * @param ano
	 *            ano de inicio del nuevo sprint.
	 * @param descripcion
	 *            descripcion del nuevo sprint.
	 */
	public void anadirNuevoSprint(int dia, int mes, int ano, String descripcion) {
		for (Map.Entry<Integer, Tarea> par : modeloKanban.getSprintBacklog().getTareas().entrySet()) {
			if (!(par.getValue().getEstado() instanceof EstadoTareaCompletada)) {
				par.getValue().reiniciarEstado();
				modeloKanban.getProductBacklog().getTareas().put(par.getKey(), par.getValue());
			}
		}
		modeloKanban.getSprintBacklog().reiniciarSprintBacklog(dia, mes, ano, descripcion);
		modeloKanban.commitProductBacklog();

	}

	/**
	 * Añade un nuevo miembro a miembro de equipo
	 * 
	 * @param nombre
	 *            del nuevo miembro
	 */
	public void anadirNuevoMiembro(String nombre) {
		MiembroDeEquipo m = new MiembroDeEquipo(nombre);
		modeloKanban.getMiembros().put(m.getIdMiembro(), m);
		modeloKanban.commitMiembrosDeEquipo();
	}

	/**
	 * Cierra el scanner de teclado y guarda todos los ficheros csv y apaga la
	 * maquina virtual de java.
	 * 
	 */
	public void close() {
		vistaKanban.cerrarRecursos();
		modeloKanban.commit();
		System.exit(0);

	}

}
