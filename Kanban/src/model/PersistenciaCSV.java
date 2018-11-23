package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import com.opencsv.CSVReader;
import java.util.Calendar; 
import java.util.GregorianCalendar; 

public class PersistenciaCSV extends PersistenciaAbstracta {

	private static PersistenciaCSV instancia = null;

	private PersistenciaCSV() {
		miembros = new HashMap<Integer, MiembroDeEquipo>();
	}

	public static PersistenciaCSV getInstancia() {
		if (instancia == null) {
			instancia = new PersistenciaCSV();
		}
		return instancia;
	}

	@Override
	public void leerPersistencia() {
		leerMiembros(); 
		leerProductBacklog(); 
		leerSprintBacklog(); 
	}

	public void leerMiembros() {
		try {

			String archCSV = "csv/MiembroDeEquipo.csv";
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(archCSV));

			String[] fila = null;

			csvReader.readNext(); // saltar titulos

			fila = csvReader.readNext();
			int contador = Integer.parseInt(fila[0]);

			MiembroDeEquipo.setContadorIds(contador);
			fila = csvReader.readNext();
			while (fila != null) {
				int id = Integer.parseInt(fila[0]);
				String nombre = fila[1];
				MiembroDeEquipo m = new MiembroDeEquipo(id, nombre);
				miembros.put(id, m);
				
				fila = csvReader.readNext(); 
			}
			csvReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void leerProductBacklog() {
		try {
			
			productBacklog = ProductBacklog.getInstancia();  

			String archCSV = "csv/ProductBacklog.csv";
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(archCSV));

			String[] fila = null;

			csvReader.readNext(); // saltar titulos

			fila = csvReader.readNext();
			int contador = Integer.parseInt(fila[0]);

			Tarea.setContadorIds(contador);
			
			fila = csvReader.readNext();
			
			while (fila != null) {
				
				int id = Integer.parseInt(fila[0]); 
				int coste = Integer.parseInt(fila[1]); 
				int beneficio = Integer.parseInt(fila[2]); 
				String titulo = fila[3]; 
				String descripcion = fila[4]; 
				int idMiembro = Integer.parseInt(fila[5]); 
				int tipoDeRequisito = Integer.parseInt(fila[6]); 
				Requisito r = null; 
				switch(tipoDeRequisito){
				case 0: 
					String actor = fila[7]; 
					r = new HistoriaDeUsuario(titulo, descripcion, actor); 
					break; 
				case 1: 
					int tareaAsociada = Integer.parseInt(fila[7]); 
					r = new Defecto(titulo, descripcion, tareaAsociada); 
					break; 
				}
				Tarea t = new Tarea(id, coste, beneficio, r, miembros.get(idMiembro)); 
				productBacklog.anadirTarea(t); 
				
				fila = csvReader.readNext(); 
			}
			
			csvReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void leerSprintBacklog() {
		try {
			
			sprintBacklog = SprintBacklog.getInstancia(); 

			String archCSV = "csv/SprintBacklog.csv";
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(archCSV));

			String[] fila = null;

			csvReader.readNext(); // saltar titulos
			
			fila = csvReader.readNext(); 
			
			String descripcionSprint = fila[0]; 
			int dia = Integer.parseInt(fila[1]); 
			int mes = Integer.parseInt(fila[2]); 
			int ano = Integer.parseInt(fila[3]); 
			Calendar fechaInicio =new  GregorianCalendar(ano, mes, dia); 
			sprintBacklog.iniciar(descripcionSprint, fechaInicio);

			fila = csvReader.readNext(); 
			
			while (fila != null) {
				
				int id = Integer.parseInt(fila[0]); 
				int coste = Integer.parseInt(fila[1]); 
				int beneficio = Integer.parseInt(fila[2]); 
				String titulo = fila[3]; 
				String descripcion = fila[4]; 
				int idMiembro = Integer.parseInt(fila[5]); 
				String estado = fila[6]; 
				int tipoDeRequisito = Integer.parseInt(fila[7]); 
				Requisito r = null; 
				switch(tipoDeRequisito){
				case 0: 
					String actor = fila[8]; 
					r = new HistoriaDeUsuario(titulo, descripcion, actor); 
					break; 
				case 1: 
					int tareaAsociada = Integer.parseInt(fila[8]); 
					r = new Defecto(titulo, descripcion, tareaAsociada); 
					break; 
				}
				Tarea t = new Tarea(id, coste, beneficio, r, miembros.get(idMiembro)); 
				switch(estado){
				case "Pendiente": 
					t.cambiarEstado(EstadoTareaPendiente.getInstancia()); break; 
				case "En Proceso": 
					t.cambiarEstado(EstadoTareaEnProceso.getInstancia());break; 
				case "En Validacion": 
					t.cambiarEstado(EstadoTareaEnValidacion.getInstancia());break; 
				case "Completada": 
					t.cambiarEstado(EstadoTareaCompletada.getInstancia());break; 
				}
				
				sprintBacklog.anadirTarea(t); 
				
				fila = csvReader.readNext(); 
			}
			
			csvReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
	}

}
