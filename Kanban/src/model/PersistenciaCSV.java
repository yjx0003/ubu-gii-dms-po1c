package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class PersistenciaCSV extends PersistenciaAbstracta {

	private static PersistenciaCSV instancia = null;
	private final String fileMiembroDeEquipo= "csv/MiembroDeEquipo.csv";
	private final String fileProductBacklog="csv/ProductBacklog.csv";
	private final String fileSprintBacklog="csv/SprintBacklog.csv";

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

	private void leerMiembros() {
		try {
			
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(this.fileMiembroDeEquipo));

			String[] fila = null;

			csvReader.readNext(); // saltar titulos

			fila = csvReader.readNext();
			int contador = Integer.parseInt(fila[0]);

			MiembroDeEquipo.setContadorIds(contador);
			fila = csvReader.readNext();
			while (fila != null&& !fila[0].isEmpty()) {
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

	private void leerProductBacklog() {
		try {

			productBacklog = ProductBacklog.getInstancia();

			
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(this.fileProductBacklog));

			String[] fila = null;

			csvReader.readNext(); // saltar titulos

			fila = csvReader.readNext();
			int contador = Integer.parseInt(fila[0]);

			Tarea.setContadorIds(contador);

			fila = csvReader.readNext();

			while (fila != null && !fila[0].isEmpty()) {

				int id = Integer.parseInt(fila[0]);
				int coste = Integer.parseInt(fila[1]);
				int beneficio = Integer.parseInt(fila[2]);
				String titulo = fila[3];
				String descripcion = fila[4];
				int idMiembro = Integer.parseInt(fila[5]);
				int tipoDeRequisito = Integer.parseInt(fila[6]);
				Requisito r = null;
				switch (tipoDeRequisito) {
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

	private void leerSprintBacklog() {
		try {

			sprintBacklog = SprintBacklog.getInstancia();

			
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(this.fileSprintBacklog));

			String[] fila = null;

			csvReader.readNext(); // saltar titulos

			fila = csvReader.readNext();

			String descripcionSprint = fila[0];
			int dia = Integer.parseInt(fila[1]);
			int mes = Integer.parseInt(fila[2]);
			int ano = Integer.parseInt(fila[3]);
			Calendar fechaInicio = new GregorianCalendar(ano, mes, dia);
			sprintBacklog.iniciar(descripcionSprint, fechaInicio);

			fila = csvReader.readNext();

			while (fila != null && !fila[0].isEmpty()) {

				int id = Integer.parseInt(fila[0]);
				int coste = Integer.parseInt(fila[1]);
				int beneficio = Integer.parseInt(fila[2]);
				String titulo = fila[3];
				String descripcion = fila[4];
				int idMiembro = Integer.parseInt(fila[5]);
				String estado = fila[6];
				int tipoDeRequisito = Integer.parseInt(fila[7]);
				Requisito r = null;
				switch (tipoDeRequisito) {
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
				
				switch (estado) {
				case "Pendiente":
					t.cambiarEstado(EstadoTareaPendiente.getInstancia());
					break;
				case "En Proceso":
					t.cambiarEstado(EstadoTareaEnProceso.getInstancia());
					break;
				case "En Validacion":
					t.cambiarEstado(EstadoTareaEnValidacion.getInstancia());
					break;
				case "Completada":
					t.cambiarEstado(EstadoTareaCompletada.getInstancia());
					break;
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
		commitMiembrosDeEquipo();
		commitProductBacklog();
		commitSprintBacklog();
	}

	private void commitMiembrosDeEquipo() {
		try {
			
			CSVWriter writer = new CSVWriter(new FileWriter(this.fileMiembroDeEquipo), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			
			String[] header = { "#ID", "#nombre" };
			writer.writeNext(header);
			
			// Valor actual de contador id
			String contadorId=Integer.toString(MiembroDeEquipo.getContadorIds());
			writer.writeNext(new String[] { contadorId});

			for (MiembroDeEquipo m : miembros.values()) {
				String id = Integer.toString(m.getIdMiembro());
				String nombre = m.getNombre();
				writer.writeNext(new String[] { id, nombre });
			}
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void commitProductBacklog() {
		try {
			
			CSVWriter writer = new CSVWriter(new FileWriter(this.fileProductBacklog), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			
			String[] header = { "#ID","#Coste","#Beneficio","#Titulo","#Descripcion","#Miembro asignado","#Tipo","#Actor/Tarea Anterior" };
			writer.writeNext(header);
			
			// Valor actual de contador id
			String contadorId=Integer.toString(Tarea.getContadorIds());
			writer.writeNext(new String[] { contadorId});

			for (Tarea t : productBacklog.getTareas().values()) {
				String id = Integer.toString(t.getIdTarea());
				String coste= Integer.toString(t.getCoste());
				String beneficio= Integer.toString(t.getBeneficio());
				String titulo=t.getTitulo();
				String descripcion=t.getDescripcion();
				String miembroAsignado=Integer.toString(t.getMiembro().getIdMiembro());
				String tipo=null;
				String actorOTarea=null;
				if (t.getRequisito() instanceof HistoriaDeUsuario) {
					tipo="0";
					HistoriaDeUsuario h=(HistoriaDeUsuario)t.getRequisito();
					actorOTarea =h.getActor();
				}else {
					tipo="1";
					Defecto d=(Defecto)t.getRequisito();
					actorOTarea=Integer.toString(d.getTarea());
				}
				
				writer.writeNext(new String[] { id,coste,beneficio,titulo,descripcion,miembroAsignado,tipo,actorOTarea });
			}
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void commitSprintBacklog() {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(this.fileSprintBacklog), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			
			String[] header = { "#ID","#Coste","#Beneficio","#Titulo","#Descripcion","#Miembro asignado","#Estado","#Tipo","#Actor/Tarea Anterior" };
			writer.writeNext(header);
			Calendar fechaInicio=sprintBacklog.getFechaInicio();
			String ano=Integer.toString(fechaInicio.get(Calendar.YEAR));
			String mes=Integer.toString(fechaInicio.get(Calendar.MONTH));
			String dia=Integer.toString(fechaInicio.get(Calendar.DAY_OF_MONTH));
			writer.writeNext(new String[] {sprintBacklog.getDescripcion(),dia,mes,ano});

			for (Tarea t : sprintBacklog.getTareas().values()) {
				String id = Integer.toString(t.getIdTarea());
				String coste= Integer.toString(t.getCoste());
				String beneficio= Integer.toString(t.getBeneficio());
				String titulo=t.getTitulo();
				String descripcion=t.getDescripcion();
				String miembroAsignado=Integer.toString(t.getMiembro().getIdMiembro());
				String tipo=null;
				String actorOTarea=null;
				String estado=t.getEstado().toString();
				if (t.getRequisito() instanceof HistoriaDeUsuario) {
					tipo="0";
					HistoriaDeUsuario h=(HistoriaDeUsuario)t.getRequisito();
					actorOTarea =h.getActor();
				}else {
					tipo="1";
					Defecto d=(Defecto)t.getRequisito();
					actorOTarea=Integer.toString(d.getTarea());
				}
				
				writer.writeNext(new String[] { id,coste,beneficio,titulo,descripcion,miembroAsignado,estado,tipo,actorOTarea });
			}
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
