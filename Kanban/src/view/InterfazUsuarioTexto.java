package view;

import java.util.Map;
import java.util.Scanner;

import controller.Controlador;
import model.Backlog;
import model.MiembroDeEquipo;
import model.ProductBacklog;
import model.SprintBacklog;
import model.Tarea;

public class InterfazUsuarioTexto {

	private Controlador controladorKanban;

	private int opcionUsuario;
	
	private static InterfazUsuarioTexto instancia = null; 

	private Scanner sc;
	
	private InterfazUsuarioTexto(){
		
	}
	
	public static InterfazUsuarioTexto getInstancia(){
		if(instancia == null){
			instancia = new InterfazUsuarioTexto(); 
		}
		return instancia; 
	}

	public void menuPrincipal() {
		System.out.println("********MEN� SCRUM********\n");
		System.out.println("[1] Product Backlog");
		System.out.println("[2] Sprint Backlog");
		System.out.println("[3] Gesti�n de Miembros ");

		System.out.println("Escoja una opci�n: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario < 1 || opcionUsuario > 3) {
				System.out.println("Opci�n incorrecta. Escoja una opci�n: ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario < 1 || opcionUsuario > 3);
		sc.close();

	}

	public void menuProductBacklog(ProductBacklog productBacklog) {
		System.out.println("*******PRODUCT BACKLOG********\n");
		System.out.println("Tareas: ");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		System.out.println("[-1] A�adir tarea");
		System.out.println("[0] Atr�s");

		System.out.println("Escoja una opci�n: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario != 0 && opcionUsuario != -1 && !productBacklog.getTareas().containsKey(opcionUsuario)) {
				System.out.println("Opci�n incorrecta. Escoja una opci�n: ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario != 0 && opcionUsuario != -1 && !productBacklog.getTareas().containsKey(opcionUsuario));
		sc.close();
	}

	public void menuTarea(Tarea t) {
		System.out.println("*******TAREA " + t.getIdTarea() + "********\n");
		System.out.println("Titulo: " + t.getTitulo());
		System.out.println("Descripci�n: " + t.getDescripcion());
		System.out.println("Coste: " + t.getCoste());
		System.out.println("Beneficio: " + t.getBeneficio());
		System.out.println("Asignada a: " + t.getMiembro());
		System.out.println(t.getRequisito());

		System.out.println("[1] Modificar tarea");
		System.out.println("[0] Atr�s");

	}

	public void menuModificarTarea(Tarea t, Map<Integer,MiembroDeEquipo> miembros, Backlog backlog) {
		System.out.println("*******MODIFICAR TAREA " + t.getIdTarea() + "********\n");
		System.out.println("Titulo: " + t.getTitulo());
		System.out.println("Descripci�n: " + t.getDescripcion());
		System.out.println("Coste: " + t.getCoste());
		System.out.println("Beneficio: " + t.getBeneficio());
		System.out.println("Asignada a: " + t.getMiembro());
		System.out.println(t.getRequisito());

		sc = new Scanner(System.in);
		System.out.println("**Se deben dejar los campos vac�os si no se quieren modificar. **");

		System.out.println("Nuevo t�tulo: ");
		String nuevoTitulo = sc.nextLine();
		System.out.println("Nueva descripci�n: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Nuevo coste: ");
		String nuevoCoste = sc.nextLine();
		System.out.println("Nuevo beneficio: ");
		String nuevoBeneficio = sc.nextLine();
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		System.out.println("Nuevo miembro: ");
		String nuevoMiembro = sc.nextLine();
		if (!nuevoMiembro.isEmpty()) {
			do {
				if (!miembros.containsKey(nuevoMiembro)) {
					System.out.println("Opci�n incorrecta. Nuevo Miembro: ");
					opcionUsuario = sc.nextInt();
				}
			} while (!miembros.containsKey(nuevoMiembro));
		}
		sc.close();
		
		controladorKanban.modificarTarea(t.getIdTarea(),nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio, nuevoMiembro, backlog);

	}
	
	public void menuAnadirTarea(Map<Integer,MiembroDeEquipo> miembros){
		sc = new Scanner(System.in);

		System.out.println("Nuevo t�tulo: ");
		String nuevoTitulo = sc.nextLine();
		System.out.println("Nueva descripci�n: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Nuevo coste: ");
		String nuevoCoste = sc.nextLine();
		System.out.println("Nuevo beneficio: ");
		String nuevoBeneficio = sc.nextLine();
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		System.out.println("Nuevo miembro: ");
		String nuevoMiembro = sc.nextLine();
		if (!nuevoMiembro.isEmpty()) {
			do {
				if (!miembros.containsKey(nuevoMiembro)) {
					System.out.println("Opci�n incorrecta. Nuevo Miembro: ");
					nuevoMiembro = sc.nextLine();
				}
			} while (!miembros.containsKey(nuevoMiembro));
		}
		System.out.println("Tipo de requisito asociado: ");
		System.out.println("[1] Historia de Usuario");
		System.out.println("[2] Defecto");
		String opcion = sc.nextLine(); 
		int intopcion = Integer.parseInt(opcion); 
		String actor = ""; 
		String tarea = ""; 
		switch(intopcion){
		case 1: 
			System.out.println("Actor: ");
			actor = sc.nextLine(); 
			break; 
		case 2: 
			System.out.println("Defecto asociado a la tarea: ");
			tarea = sc.nextLine(); 
			break; 
		}
		
		sc.close();
		
		boolean anadida = controladorKanban.anadirTarea(nuevoTitulo, nuevaDescripcion, nuevoCoste, nuevoBeneficio, nuevoMiembro, actor, tarea); 
		if(!anadida){
			System.out.println("La tarea no se ha podido a�adir. Alguno de los campos era incorrecto. ");
		}
		
	}
	
	public void menuSprintBacklog(SprintBacklog sprintBacklog) {
		System.out.println("*******SPRINT BACKLOG********\n");
		System.out.println(sprintBacklog.getDescripcion());
		for (Map.Entry<Integer, Tarea> par : sprintBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo()+ " Estado: "+par.getValue().getEstado());
		}
		System.out.println("");
		System.out.println("[1] A�adir tarea al Sprint.");
		System.out.println("[2] Mover tarea."); 
		System.out.println("[3] Crear nuevo Sprint."); 
		System.out.println("[0] Atr�s."); 
		
		System.out.println("Escoja una opci�n o el ID de la tarea que quiere visualizar: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario < 0 || opcionUsuario > 3) {
				System.out.println("Opci�n incorrecta. Escoja una opci�n: ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario < 1 || opcionUsuario > 3);
		sc.close();

	}
	
	public void menuAnadirTareaSprint(ProductBacklog productBacklog){
		System.out.println("*******A�ADIR TAREA A SPRINT********\n");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}
		System.out.println("Escoja la tarea que se desea a�adir o 0 para salir: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario !=0 && !productBacklog.getTareas().containsKey(opcionUsuario)) {
				System.out.println("Opci�n incorrecta. Escoja la tarea que se desea a�adir o 0 para salir: ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario !=0 && !productBacklog.getTareas().containsKey(opcionUsuario));
		sc.close();
		
		if(opcionUsuario!=0){
			controladorKanban.anadirTareaSprint(opcionUsuario); 
		}
	}
	
	public void menuMoverTarea(SprintBacklog sprintBacklog){
		System.out.println("*******MOVER TAREA********\n");
		for (Map.Entry<Integer, Tarea> par : sprintBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo()+ "Estado: "+par.getValue().getEstado());
		}
		
		System.out.println("Escoja la tarea que se desea mover o 0 para salir: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario !=0 && !sprintBacklog.getTareas().containsKey(opcionUsuario)) {
				System.out.println("Opci�n incorrecta. Escoja la tarea que se desea mover o 0 para salir: ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario !=0 && !sprintBacklog.getTareas().containsKey(opcionUsuario));
		sc.close();
		
		if(opcionUsuario!=0){
			
			controladorKanban.moverTarea(opcionUsuario); 

		}
		
	}
	
	public void menuCrearNuevoSprint(){
		System.out.println("*******CREAR NUEVO SPRINT********\n");
		sc = new Scanner(System.in);
		System.out.println("D�a del mes: ");
		int dia = sc.nextInt(); 
		System.out.println("Mes: ");
		int mes = sc.nextInt();
		System.out.println("A�o: ");
		int ano = sc.nextInt(); 
		System.out.println("Descripcion: "); 
		String descripcion = sc.nextLine(); 
		
		System.out.print("�Desea crear el nuevo sprint? (no se tendr� acceso al sprint anterior)");
		System.out.println("[1] Crear nuevo Sprint."); 
		System.out.println("[0] Cancelar."); 
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario !=0 && opcionUsuario!=1) {
				System.out.println("Opci�n incorrecta. �Desea crear el nuevo sprint? (no se tendr� acceso al sprint anterior) ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario !=0 && opcionUsuario!=1);
		sc.close();
		
		if(opcionUsuario==1){
			controladorKanban.anadirNuevoSprint(dia, mes, ano, descripcion); 
		}
		
	}
	
	public void menuMiembros(Map<Integer,MiembroDeEquipo> miembros) {
		System.out.println("*******MIEMBROS DE EQUIPO********\n");
		for (Map.Entry<Integer, MiembroDeEquipo> par : miembros.entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getNombre());
		}
		System.out.println("[1] A�adir Miembro."); 
		System.out.println("[0] Atr�s."); 
		
		System.out.println("Escoja una opci�n: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario !=0 && opcionUsuario!=1) {
				System.out.println("Opci�n incorrecta. Escoja una opci�n:  ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario !=0 && opcionUsuario!=1);
		sc.close();
		
	}
	
	public void menuAnadirMiembro(){
		System.out.println("*******CREAR NUEVO MIEMBRO*******\n");
		sc = new Scanner(System.in);
		System.out.println("Nombre: ");
		String nombre = sc.nextLine(); 
		
		System.out.print("�Desea crear un miembro con el nobmre "+nombre+"?");
		System.out.println("[1] Crear miembro."); 
		System.out.println("[0] Cancelar."); 
		
		System.out.println("Escoja una opci�n: ");
		sc = new Scanner(System.in);
		opcionUsuario = sc.nextInt();
		do {
			if (opcionUsuario !=0 && opcionUsuario!=1) {
				System.out.println("Opci�n incorrecta. Escoja una opci�n:  ");
				opcionUsuario = sc.nextInt();
			}
		} while (opcionUsuario !=0 && opcionUsuario!=1);
		sc.close();
		
		if(opcionUsuario == 1){
			controladorKanban.anadirNuevoMiembro(nombre); 
		}
		
	}

	public void setControladorKanban(Controlador controlador) {
		this.controladorKanban = controlador;
	}

}
