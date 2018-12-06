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
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Junior
 */
public class MakeXpath {

    Document doc = null;

    public String EjecutaXPath(String Texto, File fichero ) throws ParserConfigurationException, SAXException, IOException {
        String datos_nodo[] = null;
        String salida = "";
        Node node;


        try {
            System.out.println("tramo0");
            //Creamos un objeto XPATH.
            XPath path = XPathFactory.newInstance().newXPath();
            //Creamos un XPATHEXPRESSION con la consulta deseada. La consulta se la vamos a pasar como parámetro
            //de entrada a través de los radiobuttons.
            XPathExpression exp = path.compile(Texto);
            System.out.println("tramo0.1");
            //Ejecuta la consulta sobre el DOM y devuelve el resultado en una lista de nodos que hay que procesar.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document XMLDoc = factory.newDocumentBuilder().parse(fichero);
            Object result =(NodeList) exp.evaluate(XMLDoc, XPathConstants.NODESET);
            System.out.println("tramo0.2");
            NodeList lista = (NodeList) result;
            //Recorremos la lista con un bucle for para sacar los resultados.
            for (int i = 0; i < lista.getLength(); i++) {
                node = lista.item(i); //Analizamos el objeto en la lista en la posición i.
                //En caso de que el nodo sea de tipo elemento y el nombre del elemento sea perro, ejecutamos
                //el mismo código con el que procesábamos los perros para que nos dé todos los resultados.
//                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName() == "coche") {
                    datos_nodo = leeListaCoches(node);
                    System.out.println("tramo1");
                    //En salida guardamos el valor del array datos_nodo ya procesado
                    salida = salida + "\n" + "marca; " + datos_nodo[1];
                    salida = salida + "\n" + "modelo; " + datos_nodo[0];
                    salida = salida + "\n" + "anioSalida; " + datos_nodo[2];
                    salida = salida + "\n" + "potencia; " + datos_nodo[3];
                    salida = salida + "\n" + "combustible; " + datos_nodo[4];
                    salida = salida + "\n" + "precio; " + datos_nodo[5];
                    salida = salida + "\n" + "peso; " + datos_nodo[6];
                    salida = salida + "\n" + "estrellasSeguridad; " + datos_nodo[7];
                    salida = salida + "\n" + "traccion; " + datos_nodo[8];
                    salida = salida + "\n" + "tipoChasis; " + datos_nodo[9];
                    salida = salida + "\n" + "-------------------------------------";
//                } else if (node.getNodeType() == Node.ELEMENT_NODE && (node.getNodeName() == "raza" || node.getNodeName() == "sexo"
//                        || node.getNodeName() == "deporte" || node.getNodeName() == "grado" || node.getNodeName() == "club")) {
//                    //Procedemos a analizar los nodos tipo elemento hijos de perro.
//                    salida = salida + "\n" + lista.item(i).getChildNodes().item(0).getNodeValue();
//                } else if (node.getNodeType() == Node.ATTRIBUTE_NODE && (node.getNodeName() == "afijo" || node.getNodeName() == "nacimiento")) {
//                    //Obtenemos los atributos
//                    salida = salida + "\n" + lista.item(i).getChildNodes().item(0).getNodeValue();
//                }
                System.out.println("tramo2");
            }

        } catch (XPathExpressionException | DOMException ex) {
            salida = "Error: " + ex.getMessage();
            return salida;
        }

        return salida;
    }

    protected String[] leeListaCoches(Node n) {
        System.out.println("tramo3");
        String datos[] = new String[10]; //Declaramos un array de 10 que es lo que nos va a devolver con el contenido del árbol
        Node ntemp = null; //Declaramos un nodo temporal para poder procesar los nodos hijo.
        int contador = 5; //Lo vamos a usar para movernos por las posiciones dentro del array.
        try {
            //Dentro del array guardamos los valores de los nodos atributo.
            datos[0] = n.getAttributes().item(0).getNodeValue();
            datos[1] = n.getAttributes().item(1).getNodeValue();
            datos[2] = n.getAttributes().item(2).getNodeValue();
            datos[3] = n.getAttributes().item(3).getNodeValue();
            datos[4] = n.getAttributes().item(4).getNodeValue();
            //Dentro de una lista de nodos guardamos todos los nodos hijo 
            NodeList nodos = n.getChildNodes();
            //Procesamos a través de un bucle for.
            for (int i = 0; i < nodos.getLength(); i++) {
                ntemp = nodos.item(i); //ntemp adopta el valor del elemento de la lista de nodos en la posición i en cada vuelta del bucle
                //En caso que el tipo de nodo de ntemp sea tipo elemento, ejecutamos el código.
                if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                    //Accedemos al nodo hijo de los nodos tipo elemento para poder obtener el valor texto. Los nodos hijo
                    //de nombre, propietario, etc, son nodos texto donde se almacena el contenido textual del nodo.
                    datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                    contador++;  //El contador nos mueve a través de la posición del array.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tramo4");
        return datos;
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
