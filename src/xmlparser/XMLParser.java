
package xmlparser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import parcareimpl.Parcare;

public class XMLParser {
  private Document doc;

  public XMLParser() {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse("config.xml");
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public void getCapacitateFromXml(Parcare p) {
    NodeList nList = doc.getElementsByTagName("informatiiParcare");
    for (int i = 0; i < nList.getLength(); i++) {
      Node node = nList.item(i);
      System.out.println("element curent = " + node.getNodeName());
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        String capacitateMaxima = element.getElementsByTagName("capacitate").item(0).getTextContent();
        int capacitate = Integer.parseInt(capacitateMaxima);
        System.out.println(capacitate);

        String taxaParcare = element.getElementsByTagName("taxa").item(0).getTextContent();
        Double taxa = Double.parseDouble(taxaParcare);
        System.out.println(taxa);

        //Parcare p = new Parcare();
        p.setLocuriLibere(capacitate);
        p.setTaxa(taxa);

      }
    }
    //return null;
  }

}
