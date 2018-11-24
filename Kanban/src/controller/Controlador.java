package controller;

import java.util.Map;


import model.Backlog;
import model.Defecto;
import model.EstadoTareaCompletada;
import model.HistoriaDeUsuario;
import model.MiembroDeEquipo;
import model.PersistenciaAbstracta;
import model.Requisito;
import model.Tarea;
import view.InterfazUsuarioTexto;

public class Controlador {
	
	private PersistenciaAbstracta modeloKanban;
	private InterfazUsuarioTexto vistaKanban;
	private EstadoControlador estado;
	
	private static Controlador instancia = null; 

	private Controlador() {
		estado = EstadoControladorPrincipal.getInstancia(); 
	}
	
	public static Controlador getInstancia(){
		if(instancia == null){
			instancia = new Controlador(); 
		}
		return instancia; 
	}
	
	public void cambiarEstado(EstadoControlador e){
		this.estado =e; 
	}
	
	public void init(InterfazUsuarioTexto vista, PersistenciaAbstracta modelo){
		this.vistaKanban=vista;
		this.modeloKanban=modelo;
		
		modeloKanban.leerPersistencia();
		vistaKanban.init(this);
		
		while(true){
			estado.mostrarMenu(this);
			estado.actualizarEstado(this);
		}
	}
	
	public PersistenciaAbstracta getModeloKanban() {
		return modeloKanban;
	}

	public InterfazUsuarioTexto getVistaKanban() {
		return vistaKanban;
	}

	public EstadoControlador getEstado() {
		return estado;
	}

	public void modificarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, String nuevoCoste, String nuevoBeneficio,int nuevoMiembro, Backlog backlog) {
		Tarea tarea = backlog.getTareas().get(idTarea); 
		boolean modificado = false; 
		if(!nuevoTitulo.isEmpty()){
			tarea.getRequisito().setTitulo(nuevoTitulo);
			modificado = true; 
		}
		if(!nuevaDescripcion.isEmpty()){
			tarea.getRequisito().setDescripcion(nuevaDescripcion);
			modificado = true; 
		}
		if(!nuevoCoste.isEmpty()){ 
			tarea.setCoste(Integer.parseInt(nuevoCoste));
			modificado = true; 
		}
		if(!nuevoBeneficio.isEmpty()){ 
			tarea.setBeneficio(Integer.parseInt(nuevoBeneficio));
			modificado = true; 
		}
		if(nuevoMiembro!=0){ //mantener el miembro si es 0			
			tarea.setMiembro(modeloKanban.getMiembros().get(nuevoMiembro));
			modificado = true; 
		}
		
		if(modificado){
			this.modeloKanban.commitProductBacklog();
			this.modeloKanban.commitSprintBacklog();
		}
	}

	public boolean anadirTarea(String nuevoTitulo, String nuevaDescripcion, String nuevoCoste, String nuevoBeneficio,
			int nuevoMiembro, String actor, String tarea) {
		if(nuevoTitulo.isEmpty()||nuevaDescripcion.isEmpty()||nuevoCoste.isEmpty()||nuevoBeneficio.isEmpty()||nuevoMiembro==0){
			return false; 
		}
		try{

		Requisito nuevoRequisito; 
		if(tarea.isEmpty()){
			nuevoRequisito = new HistoriaDeUsuario(nuevoTitulo,nuevaDescripcion,actor); 
		}else{
			nuevoRequisito = new Defecto(nuevoTitulo, nuevaDescripcion, Integer.parseInt(tarea)); 
		}
		Tarea nuevaTarea = new Tarea(Integer.parseInt(nuevoCoste), Integer.parseInt(nuevoBeneficio), nuevoRequisito, modeloKanban.getMiembros().get(nuevoMiembro)); 
		modeloKanban.anadirTarea(nuevaTarea); 
		modeloKanban.commitProductBacklog(); 
		return true; 
		}catch(Exception e){
			return false; 
		}
	}

	public void anadirTareaSprint(int idTarea) {
		Tarea t = modeloKanban.getProductBacklog().getTareas().remove(idTarea); 
		modeloKanban.getSprintBacklog().getTareas().put(t.getIdTarea(),t); 
		modeloKanban.commitProductBacklog();
		modeloKanban.commitSprintBacklog();
	}

	public void moverTarea(int idTarea) {
		modeloKanban.getSprintBacklog().getTareas().get(idTarea).actualizarEstado();
		modeloKanban.commitSprintBacklog();
	}

	public void anadirNuevoSprint(int dia, int mes, int ano, String descripcion) {
		for (Map.Entry<Integer, Tarea> par : modeloKanban.getSprintBacklog().getTareas().entrySet()) {
			if(!(par.getValue().getEstado() instanceof EstadoTareaCompletada)){
				par.getValue().reiniciarEstado(); 
				modeloKanban.getProductBacklog().getTareas().put(par.getKey(), par.getValue()); 
			}
		}
		modeloKanban.getSprintBacklog().reiniciarSprintBacklog(dia, mes, ano, descripcion); 
		modeloKanban.commitProductBacklog();
		
	}

	public void anadirNuevoMiembro(String nombre) {
		MiembroDeEquipo m = new MiembroDeEquipo(nombre); 
		modeloKanban.getMiembros().put(m.getIdMiembro(), m); 
		modeloKanban.commitMiembrosDeEquipo();
	}

	public void close() {
		vistaKanban.cerrarRecursos();
		modeloKanban.commit();
		System.exit(0);
		
	}
	
}
