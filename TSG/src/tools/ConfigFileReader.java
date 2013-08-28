package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.lwjgl.input.Keyboard;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigFileReader {

	public static void writeKeyBindings(Map<String, Integer>keys) throws ParserConfigurationException, TransformerException, IOException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		ArrayList<String>allKeyCommands = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader(new File("AllKeys")));
		String line = in.readLine();
		while(line!=null){
			allKeyCommands.add(line);
			line = in.readLine();
		}
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Keybindings");
		doc.appendChild(rootElement);

		// staff elements

		for(String commandName : allKeyCommands){
			Element key = doc.createElement("Key");
			rootElement.appendChild(key);
			
			
			System.out.println(commandName);
			key.setAttribute("Name", commandName);

			Element keyCode = doc.createElement("KeyCode");
			keyCode.appendChild(doc.createTextNode(Integer.toString(0)));
			key.appendChild(keyCode);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("keybindigs.xml"));

		transformer.transform(source, result);
	}

	public static void main(String[]args){
		try {
			writeKeyBindings(null);
		} catch (ParserConfigurationException | TransformerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
