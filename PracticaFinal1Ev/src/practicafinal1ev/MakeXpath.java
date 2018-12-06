//Funciona correctamente pero antes de todas las consultas me sale algo q nose solucionarlo aun asi es funcional.
package practicafinal1ev;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Junior
 */
public class MakeXpath {

   
    Document doc = null;

    public String EjecutaXPath(String Texto) {
        Node node;
         String salida = "";
        
        try {

            //Crea el objeto Xpath
            XPath xpath = XPathFactory.newInstance().newXPath();

            //Crea un XpathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(Texto);

            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que 
            //devolvera el resultado como una lista de nodos
            Object result = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;

            //Ahora recorre la lista para sacar los resultados
            for (int i = 0; i < nodeList.getLength(); i++) {
                
                node = nodeList.item(i);
                //Con el getAtributtes y el content consigo leer el resto de nodos
                salida = salida + "\n" + "Marca; " + nodeList.item(i).getAttributes().getNamedItem("marca").getNodeValue();
                salida = salida + "\n" + "Modelo; " + nodeList.item(i).getAttributes().getNamedItem("modelo").getNodeValue();
                salida = salida + "\n" + "Año de salida; " + nodeList.item(i).getAttributes().getNamedItem("anioSalida").getNodeValue();
                salida = salida + "\n" + "Potencia; " + nodeList.item(i).getAttributes().getNamedItem("potencia").getNodeValue();
                salida = salida + "\n" + "Combustible; " + nodeList.item(i).getAttributes().getNamedItem("combustible").getNodeValue();
                salida = salida + "\n" + nodeList.item(i).getNodeName() +  node.getTextContent().equals("precio");
               
                
                
                salida = salida +"\n" + "---------------------------------------------";
            }
            return salida;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return Texto;
    }

    public int abrirDOM(File fichero) {

        try {
            //Creamos un objeto DocumentBuilderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Ignoramos los comentarios que pueda contener el XML
            factory.setIgnoringComments(true);

            //Ignoramos los espacios en blanco que tenga el documento.
            factory.setIgnoringElementContentWhitespace(true);

            //Creamos un objeto DocumentBUilder para cargar en él la estructura de árbol del DOM.
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Interpretamos (parseamos) el documento XML y generamos el DOM equivalente.
            doc = builder.parse(fichero);

            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    
    
    
}