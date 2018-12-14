package persistence;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Defecto;
import model.EstadoTareaCompletada;
import model.EstadoTareaEnProceso;
import model.EstadoTareaEnValidacion;
import model.EstadoTareaPendiente;
import model.HistoriaDeUsuario;
import model.MiembroDeEquipo;
import model.ProductBacklog;
import model.Requisito;
import model.SprintBacklog;
import model.Tarea;

public class PersistenciaXML extends PersistenciaAbstracta {

	private static PersistenciaXML instancia = null;
	private final String fileMiembroDeEquipo = "xml/MiembroDeEquipo.xml";
	private final String fileProductBacklog = "xml/ProductBacklog.xml";
	private final String fileSprintBacklog = "xml/SprintBacklog.xml";

	private PersistenciaXML() {

	}

	public static PersistenciaXML getInstancia() {
		if (instancia == null) {
			instancia = new PersistenciaXML();
		}
		return instancia;
	}

	@Override
	public void leerPersistencia() {
		leerMiembrosDeEquipo();
		leerProductBacklog();
		leerSprintBacklog();
	}

	public void leerMiembrosDeEquipo() {
		try {
			File file = new File(fileMiembroDeEquipo);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc = docBuilder.parse(file);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("miembro");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nNode;
					int id = Integer.valueOf(elemento.getAttribute("id"));
					String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
					MiembroDeEquipo m = new MiembroDeEquipo(id, nombre);
					miembros.put(id, m);
				}
			}

			nList = doc.getElementsByTagName("idSiguiente");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nNode;
				MiembroDeEquipo.setContadorIds(Integer.valueOf(elemento.getAttribute("id")));
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void leerProductBacklog() {
		try {
			productBacklog = ProductBacklog.getInstancia(); 
			
			File file = new File(fileProductBacklog);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc = docBuilder.parse(file);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("tarea");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nNode;
					int id = Integer.valueOf(elemento.getAttribute("id"));
					int coste = Integer.valueOf(elemento.getElementsByTagName("coste").item(0).getTextContent());
					int beneficio = Integer
							.valueOf(elemento.getElementsByTagName("beneficio").item(0).getTextContent());
					String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();

					int miembroAsignado = Integer
							.valueOf(elemento.getElementsByTagName("miembroAsignado").item(0).getTextContent());

					Requisito r;
					if (elemento.getElementsByTagName("tareaAsociada").getLength() != 0) {
						int tareaAsociada = Integer
								.valueOf(elemento.getElementsByTagName("tareaAsociada").item(0).getTextContent());
						r = new Defecto(titulo, descripcion, tareaAsociada);
					} else {
						String actor = elemento.getElementsByTagName("actor").item(0).getTextContent();
						r = new HistoriaDeUsuario(titulo, descripcion, actor);
					}

					Tarea t = new Tarea(id, coste, beneficio, r, miembros.get(miembroAsignado));
					productBacklog.anadirTarea(t);
				}
			}

			nList = doc.getElementsByTagName("idSiguiente");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nNode;
				Tarea.setContadorIds(Integer.valueOf(elemento.getAttribute("id")));
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void leerSprintBacklog() {
		
		try {
			
			sprintBacklog = SprintBacklog.getInstancia(); 
			
			File file = new File(fileSprintBacklog);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc = docBuilder.parse(file);

			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("descripcionSprint");
			Node nNode = nList.item(0); 
			String descripcionSprint = null; 
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elemento = (Element) nNode; 
				 descripcionSprint = elemento.getAttribute("descripcion"); 
			}
			
			nList = doc.getElementsByTagName("fechaInicio");
			nNode = nList.item(0); 
			Calendar fechaInicio = null; 
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elemento = (Element) nNode; 
				int dia = Integer.valueOf(elemento.getElementsByTagName("dia").item(0).getTextContent()); 
				int mes = Integer.valueOf(elemento.getElementsByTagName("mes").item(0).getTextContent()); 
				int anio = Integer.valueOf(elemento.getElementsByTagName("anio").item(0).getTextContent()); 
				fechaInicio = new GregorianCalendar(anio, mes, dia);
			}
			
			sprintBacklog.iniciar(descripcionSprint, fechaInicio);
			
			nList = doc.getElementsByTagName("tarea");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nNode;
					int id = Integer.valueOf(elemento.getAttribute("id"));
					int coste = Integer.valueOf(elemento.getElementsByTagName("coste").item(0).getTextContent());
					int beneficio = Integer
							.valueOf(elemento.getElementsByTagName("beneficio").item(0).getTextContent());
					String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
					String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();

					int miembroAsignado = Integer
							.valueOf(elemento.getElementsByTagName("miembroAsignado").item(0).getTextContent());

					Requisito r;
					if (elemento.getElementsByTagName("tareaAsociada").getLength() != 0) {
						int tareaAsociada = Integer
								.valueOf(elemento.getElementsByTagName("tareaAsociada").item(0).getTextContent());
						r = new Defecto(titulo, descripcion, tareaAsociada);
					} else {
						String actor = elemento.getElementsByTagName("actor").item(0).getTextContent();
						r = new HistoriaDeUsuario(titulo, descripcion, actor);
					}
					
					String estado = elemento.getElementsByTagName("estado").item(0).getTextContent();  

					Tarea t = new Tarea(id, coste, beneficio, r, miembros.get(miembroAsignado));
					
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
					
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void commit() {
		commitMiembrosDeEquipo();
		commitProductBacklog();
		commitSprintBacklog();

	}

	@Override
	public void commitMiembrosDeEquipo() {

		try {
			File file = new File(fileMiembroDeEquipo);
			file.delete();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element raiz = doc.createElement("MiembroDeEquipo");
			Element idSiguiente = doc.createElement("idSiguiente");
			idSiguiente.setAttribute("id", Integer.toString(MiembroDeEquipo.getContadorIds()));
			doc.appendChild(raiz);
			raiz.appendChild(idSiguiente); 

			for (MiembroDeEquipo miembro : miembros.values()) {
				raiz.appendChild(getElemento(doc, miembro));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource domSource = new DOMSource(doc);

			StreamResult streamResult = new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Node getElemento(Document doc, MiembroDeEquipo miembro) {
		Element m = doc.createElement("miembro");
		m.setAttribute("id", Integer.toString(miembro.getIdMiembro()));
		addAtributos(doc, m, "Nombre", miembro.getNombre());
		return m;
	}

	private Node getElemento(Document doc, Tarea tarea) {
		Element elemento = doc.createElement("tarea");
		elemento.setAttribute("id", Integer.toString(tarea.getIdTarea()));
		addAtributos(doc, elemento, "coste", Integer.toString(tarea.getCoste()));
		addAtributos(doc, elemento, "beneficio", Integer.toString(tarea.getBeneficio()));
		addAtributos(doc, elemento, "titulo", tarea.getTitulo());
		addAtributos(doc, elemento, "descripcion", tarea.getDescripcion());
		addAtributos(doc, elemento, "miembroAsignado", Integer.toString(tarea.getMiembro().getIdMiembro()));
		addAtributos(doc, elemento, "estado", tarea.getEstado().toString());

		if (tarea.getRequisito() instanceof HistoriaDeUsuario) {
			HistoriaDeUsuario hdu = (HistoriaDeUsuario) tarea.getRequisito();
			addAtributos(doc, elemento, "actor", hdu.getActor());
		} else {
			Defecto defecto = (Defecto) tarea.getRequisito();
			addAtributos(doc, elemento, "tareaAsociada", Integer.toString(defecto.getTarea()));
		}
		return elemento;
	}

	private void addAtributos(Document doc, Element elemento, String nombre, String value) {
		Element m = doc.createElement(nombre);
		m.appendChild(doc.createTextNode(value));
		elemento.appendChild(m);

	}

	@Override
	public void commitProductBacklog() {
		try {
			File file = new File(fileProductBacklog);
			file.delete();
			System.out.println(productBacklog.getTareas());
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element raiz = doc.createElement("ProductBacklog");
			doc.appendChild(raiz);
			Element element = doc.createElement("idSiguiente");
			element.setAttribute("id", Integer.toString(Tarea.getContadorIds()));
			raiz.appendChild(element);
			for (Tarea t : productBacklog.getTareas().values()) {
				raiz.appendChild(getElemento(doc, t));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource domSource = new DOMSource(doc);

			StreamResult streamResult = new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void commitSprintBacklog() {
		try {
			File file = new File(fileSprintBacklog);
			file.delete();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element raiz = doc.createElement("SprintBacklog");
			doc.appendChild(raiz);
			Element descripcionSprint = doc.createElement("descripcionSprint");
			descripcionSprint.setAttribute("descripcion", sprintBacklog.getDescripcion());
			raiz.appendChild(descripcionSprint); 
			
			Element fecha = doc.createElement("fechaInicio"); 
			raiz.appendChild(fecha); 
			Calendar fechaInicio = sprintBacklog.getFechaInicio(); 
			
			addAtributos(doc, fecha, "dia", Integer.toString(fechaInicio.get(Calendar.DAY_OF_MONTH))); 
			addAtributos(doc, fecha, "mes", Integer.toString(fechaInicio.get(Calendar.MONTH))); 
			addAtributos(doc, fecha, "anio", Integer.toString(fechaInicio.get(Calendar.YEAR))); 
			
			for (Tarea t : sprintBacklog.getTareas().values()) {
				raiz.appendChild(getElemento(doc, t));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource domSource = new DOMSource(doc);

			StreamResult streamResult = new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
