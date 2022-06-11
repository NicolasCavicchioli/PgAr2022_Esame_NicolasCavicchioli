package it.unibs.fp.pgarnaldo.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	
	Document doc;
	Element root;
	
	public XMLParser(String fileName) throws SAXException, IOException, ParserConfigurationException {
		doc = DocumentBuilderFactory.newInstance()
			.newDocumentBuilder()
			.parse(new File(fileName));
		root = doc.getDocumentElement();
	}
	
	public int getWidth() {
		return Integer.parseInt(root.getAttribute("width"));
	}
	public int getHeight() {
		return Integer.parseInt(root.getAttribute("height"));
	}
	
	public void forBiEnumerated(TriConsumer<Integer,Integer,Element> action) {
		NodeList list = doc.getElementsByTagName("row");
		for (int i=0; i<list.getLength(); i++) {
			Node node = list.item(i);
			NodeList cells = ((Element)node).getElementsByTagName("cell");
			for (int j=0; j<cells.getLength(); j++) {
				action.accept(i, j, (Element)cells.item(j));
			}
		}
	}
	
	public interface TriConsumer<T,U,V> {
		public void accept(T t, U u, V v);
	}
	
}
