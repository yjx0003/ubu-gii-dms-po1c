package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class PersistenciaCSV extends PersistenciaAbstracta {

	public PersistenciaCSV() {
		// TODO Auto-generated constructor stub
		miembros = new ArrayList<MiembroDeEquipo>();
		sprints = new ArrayList<SprintBacklog>();
		productBacklog = new ProductBacklog();
	}

	@Override
	public void leerPersistencia() {

		try {

			String archCSV = "src/model/persistencia.csv";
			CSVReader csvReader;

			csvReader = new CSVReader(new FileReader(archCSV));

			String[] fila = null;

			int nMiembros = Integer.parseInt(csvReader.readNext()[0]);

			csvReader.readNext(); // saltar titulos

			for (int i = 0; i < nMiembros; i++) {
				fila = csvReader.readNext();
				MiembroDeEquipo m = new MiembroDeEquipo(Integer.parseInt(fila[0]), fila[1]);
				miembros.add(m);
			}

			int nTareas = Integer.parseInt(csvReader.readNext()[0]);

			csvReader.readNext(); // saltar titulos

			for (int i = 0; i < nTareas; i++) {
				fila = csvReader.readNext();
				Requisito r = new Requisito();
				Tarea t = new Tarea(Integer.parseInt(fila[0]), r);
				t.asignarMiembro(miembros.get(Integer.parseInt(fila[5])));
			}

			csvReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( IOException e){
			e.printStackTrace();
		}

	}

	public void mostrarPersistencia() {
		for (MiembroDeEquipo m : miembros) {
			System.out.println(m.getNombre());
			System.out.println(m.getId());
		}
	}

}
