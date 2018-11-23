package controller;

import java.util.Map;

import model.Backlog;
import model.Defecto;
import model.EstadoTareaCompletada;
import model.HistoriaDeUsuario;
import model.PersistenciaAbstracta;
import model.Requisito;
import model.Tarea;
import model.MiembroDeEquipo;
import view.InterfazUsuarioTexto;

public class Controlador {
	

	private PersistenciaAbstracta modeloKanban;
	private InterfazUsuarioTexto vistaKanban;
	
	private static Controlador instancia = null; 

	private Controlador() {

	}
	
	public static Controlador getInstancia(){
		if(instancia == null){
			instancia = new Controlador(); 
		}
		return instancia; 
	}
	
	public void setModeloKanban(PersistenciaAbstracta modelo){
		this.modeloKanban = modelo; 
	}
	
	public void setVistaKanban(InterfazUsuarioTexto vista){
		this.vistaKanban= vista; 
	}

	public void modificarTarea(int idTarea, String nuevoTitulo, String nuevaDescripcion, String nuevoCoste, String nuevoBeneficio,String nuevoMiembro, Backlog backlog) {
		Tarea tarea = backlog.getTareas().get(idTarea); 
		if(!nuevoTitulo.isEmpty()){
			tarea.getRequisito().setTitulo(nuevoTitulo);
		}
		if(!nuevaDescripcion.isEmpty()){
			tarea.getRequisito().setDescripcion(nuevaDescripcion);
		}
		if(!nuevoCoste.isEmpty()){ 
			tarea.setCoste(Integer.parseInt(nuevoCoste));
		}
		if(!nuevoBeneficio.isEmpty()){ 
			tarea.setBeneficio(Integer.parseInt(nuevoBeneficio));
		}
		if(!nuevoMiembro.isEmpty()){ 			
			tarea.setMiembro(modeloKanban.getMiembros().get(Integer.parseInt(nuevoBeneficio)));
		}
	}

	public boolean anadirTarea(String nuevoTitulo, String nuevaDescripcion, String nuevoCoste, String nuevoBeneficio,
			String nuevoMiembro, String actor, String tarea) {
		if(nuevoTitulo.isEmpty()||nuevaDescripcion.isEmpty()||nuevoCoste.isEmpty()||nuevoBeneficio.isEmpty()||nuevoMiembro.isEmpty()){
			return false; 
		}
		try{

		Requisito nuevoRequisito; 
		if(tarea.isEmpty()){
			nuevoRequisito = new HistoriaDeUsuario(nuevoTitulo,nuevaDescripcion,actor); 
		}else{
			nuevoRequisito = new Defecto(nuevoTitulo, nuevaDescripcion, Integer.parseInt(tarea)); 
		}
		Tarea nuevaTarea = new Tarea(Integer.parseInt(nuevoCoste), Integer.parseInt(nuevoBeneficio), nuevoRequisito, modeloKanban.getMiembros().get(Integer.parseInt(nuevoMiembro))); 
		modeloKanban.anadirTarea(nuevaTarea); 
		return true; 
		}catch(Exception e){
			return false; 
		}
	}

	public void anadirTareaSprint(int idTarea) {
		Tarea t = modeloKanban.getProductBacklog().getTareas().remove(idTarea); 
		modeloKanban.getSprintBacklog().getTareas().put(t.getIdTarea(),t); 
	}

	public void moverTarea(int idTarea) {
		modeloKanban.getSprintBacklog().getTareas().get(idTarea).actualizarEstado();
	}

	public void anadirNuevoSprint(int dia, int mes, int ano, String descripcion) {
		for (Map.Entry<Integer, Tarea> par : modeloKanban.getSprintBacklog().getTareas().entrySet()) {
			if(!(par.getValue().getEstado() instanceof EstadoTareaCompletada)){
				par.getValue().reiniciarEstado(); 
				modeloKanban.getProductBacklog().getTareas().put(par.getKey(), par.getValue());
			}
		}
		modeloKanban.getSprintBacklog().reiniciarSprintBacklog(dia, mes, ano, descripcion); 
		
	}

	public void anadirNuevoMiembro(String nombre) {
		MiembroDeEquipo m = new MiembroDeEquipo(nombre); 
		modeloKanban.getMiembros().put(m.getIdMiembro(), m); 
	}
	
}
