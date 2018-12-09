package persistence;

import java.io.File;
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

import model.Defecto;
import model.HistoriaDeUsuario;
import model.MiembroDeEquipo;
import model.ProductBacklog;
import model.SprintBacklog;
import model.Tarea;

public class PersistenciaXML extends PersistenciaAbstracta {

	private static PersistenciaXML instancia = null;
	private final String fileMiembroDeEquipo = "xml/MiembroDeEquipo.xml";
	private final String fileProductBacklog = "xml/ProductBacklog.xml";
	private final String fileSprintBacklog = "xml/SprintBacklog.xml";

	
	private PersistenciaXML() {
		
	}
	
	public void init(SprintBacklog sprintBacklog, ProductBacklog productBacklog, Map<Integer, MiembroDeEquipo> map) {
		this.sprintBacklog=sprintBacklog;
		this.productBacklog=productBacklog;
		this.miembros=map;
	}
	
	public static PersistenciaXML getInstancia() {
		if (instancia==null) {
			instancia=new PersistenciaXML();
		}
		return instancia;
	}
	
	@Override
	public void leerPersistencia() {
		// TODO Auto-generated method stub

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
			File file=new File(fileMiembroDeEquipo);
			file.delete();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element raiz = doc.createElement("MiembroDeEquipo");
			doc.appendChild(raiz);

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
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
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
			File file=new File(fileProductBacklog);
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
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void commitSprintBacklog() {
		try {
			File file=new File(fileSprintBacklog);
			file.delete();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element raiz = doc.createElement("SprintBacklog");
			doc.appendChild(raiz);
			Element descripcionSprint = doc.createElement("descripcionSprint");
			descripcionSprint.setAttribute("descripcion", sprintBacklog.getDescripcion());
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
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
