package xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mapeditor.MapEditorMap;
import mapeditor.MapEditorTile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLReader {

	public static void saveMapInXML(String xml, MapEditorMap map){
		Document dom;
		Element e;
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    try {
		        // use factory to get an instance of document builder
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        // create instance of DOM
		        dom = db.newDocument();

		        // create the root element
		        
		        //Set up map dimensions
		        Element rootEle = dom.createElement("mapsize");
		        e = dom.createElement("row");
		        e.appendChild(dom.createTextNode(""+map.getRow()));
		        rootEle.appendChild(e);
		        
		        e = dom.createElement("col");
		        e.appendChild(dom.createTextNode(""+map.getCol()));
		        rootEle.appendChild(e);

		        e = dom.createElement("layer");
		        e.appendChild(dom.createTextNode(""+map.getNumLayers()));
		        rootEle.appendChild(e);
		        
		        e = dom.createElement("tilesize");
		        e.appendChild(dom.createTextNode(""+32));
		        rootEle.appendChild(e);

		        //Add in tiles to layers
		        Element subEle = dom.createElement("Layers");
		        for(int i = 0; i < map.getNumLayers(); i++){
		        	Element layer = dom.createElement("one");
		        	for(int j = 0; j < map.getRow(); j++){
		        		for(int k = 0; k < map.getCol(); k++){
		        			MapEditorTile t = map.getLayers()[i].getTiles()[j][k];
		        			Element tile = dom.createElement("tile");
		        			
		        			e = dom.createElement("x");
		        			if(t != null)
		        				e.appendChild(dom.createTextNode(""+t.getX()));
		        			tile.appendChild(e);
		        			
		        			e = dom.createElement("y");
		        			if(t != null)
		        				e.appendChild(dom.createTextNode(""+t.getY()));
		        			tile.appendChild(e);
		        			
		        			e = dom.createElement("width");
		        			if(t != null)
		        				e.appendChild(dom.createTextNode(""+t.getWidth()));
		        			tile.appendChild(e);
		        			
		        			e = dom.createElement("height");
		        			if(t != null)
		        				e.appendChild(dom.createTextNode(""+t.getHeight()));
		        			tile.appendChild(e);
		        			
		        			e = dom.createElement("frame");
		        			if(t != null)
		        				e.appendChild(dom.createTextNode(""+t.getFrame()));
		        			tile.appendChild(e);
		        			
		        			e = dom.createElement("passable");
		        			if(t != null)
		        				e.appendChild(dom.createTextNode(""+t.isPassable()));
		        			tile.appendChild(e);
		        			
		        			layer.appendChild(tile);
		        		}
		        	}
		        
		        	subEle.appendChild(layer);
		        }
		        
		        rootEle.appendChild(subEle);
		        
		        dom.appendChild(rootEle);


		        try {
		            Transformer tr = TransformerFactory.newInstance().newTransformer();
		            tr.setOutputProperty(OutputKeys.INDENT, "yes");
		            tr.setOutputProperty(OutputKeys.METHOD, "xml");
		            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "MAP.dtd");
		            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		            // send DOM to file
		            tr.transform(new DOMSource(dom), 
		                                 new StreamResult(new FileOutputStream(xml)));

		        } catch (TransformerException te) {
		            System.out.println(te.getMessage());
		        } catch (IOException ioe) {
		            System.out.println(ioe.getMessage());
		        }
		    } catch (ParserConfigurationException pce) {
		        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
		    }
	}
	
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}
	
	
}
