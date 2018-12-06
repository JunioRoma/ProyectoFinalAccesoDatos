/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal1ev;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javaCoches.Coches;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Junior
 */
public class MakeJAxbSax {

    Coches misCoches;
    List<Coches.Coche> Cochecito;
    SAXParser parser;
    File ficheroXML;
    ManejadorSAX sh;
    Document doc = null;
    JAXBContext contexto;

    public int abrir_XML_JAXB() {

        try {

            //Crea una instancia JAXB
            contexto = JAXBContext.newInstance(Coches.class);

            //Crea un objeto Unmarsheller
            Unmarshaller u = contexto.createUnmarshaller();

            //Desearializa el fichero
            misCoches = (Coches) u.unmarshal(ficheroXML);
            //Cochecito = misCoches.getCoche();

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public String[] recorrerJaxB(String cocheCampo) {
        String datos[] = new String[9]; //Declaramos un array donde vamos a guardar los datos que obtendremos al recorrer el archivo
        String auxiliar = ""; //String que vamos a utilizar para hacer comprobaciones.

        try {
            //Se crea una lista con objetos de tipo Perro.
            List<Coches.Coche> cocheche = (List<Coches.Coche>) misCoches.getCoche();

            //Recorremos la lista para sacar los valores.
            for (int i = 0; i < cocheche.size(); i++) {
                //Vamos a guardar el valor del chip en una variable string auxiliar, que usaremos para hacer una comprobación
                //Si coincide con elvalor introducido por el usuario como parámetro de entrada, entonces guardamos los
                //datos relacionados con ese chip en un array.
                auxiliar = cocheche.get(i).getModelo();

                if (auxiliar.equals(cocheCampo)) {
                    datos[0] = cocheche.get(i).getAnioSalida();
                    datos[1] = cocheche.get(i).getCombustible();
                    datos[2] = cocheche.get(i).getEstrellasSeguridad();
                    datos[3] = cocheche.get(i).getMarca();
                    datos[4] = cocheche.get(i).getPeso();
                    datos[5] = cocheche.get(i).getPotencia();
                    datos[6] = cocheche.get(i).getPrecio();
                    datos[7] = cocheche.get(i).getTipoChasis();
                    datos[8] = cocheche.get(i).getTraccion();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datos;
    }

    public int editarJaxB(String marca, String modelo, String aniosalida, String potencia, String combustible,
            String coche, String precio, String peso, String estrellasSeguridad, String traccion, String tipoChasis) {
        String auxiliar = ""; //Vamos a utilizar el String auxiliar para hacer comprobaciones.

        try {
            List<Coches.Coche> cocheche = (List<Coches.Coche>) misCoches.getCoche();

            for (int i = 0; i < cocheche.size(); i++) {
                auxiliar = cocheche.get(i).getModelo(); //Asignamos el valor del objeto chip al String auxiliar.
                //En caso que sea igual al del parámetro que le hemos pasado como entrada, ejecuta
                //Modifica todos los datos para facilitar la tarea.
                if (auxiliar.equals(modelo)) {
                    cocheche.get(i).setAnioSalida(aniosalida);
                    cocheche.get(i).setCombustible(combustible);
                    cocheche.get(i).setEstrellasSeguridad(estrellasSeguridad);
                    cocheche.get(i).setMarca(marca);
                    cocheche.get(i).setModelo(modelo);
                    cocheche.get(i).setPeso(peso);
                    cocheche.get(i).setPotencia(potencia);
                    cocheche.get(i).setPrecio(precio);
                    cocheche.get(i).setTipoChasis(tipoChasis);
                    cocheche.get(i).setTraccion(traccion);
                }
            }

            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }


    public int guardarMemoria_XML_JAXB(File fichero) {
    

        try {
            //Crea un objeto Unmarsheller
            Marshaller u = contexto.createMarshaller();

            //Desearializa el fichero
            u.setProperty(u.JAXB_FORMATTED_OUTPUT, true);
            u.marshal(misCoches, fichero);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    
    
    
    
    
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    
    
    
    
    
    
    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //Se crea un objeto SAXParser para leer el xml
            parser = factory.newSAXParser();
            //Esta declaracion nos ayudara a leer el xml
            sh = new ManejadorSAX();
            ficheroXML = fichero;

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    void annadirDom(String marca, String modelo, String anio, String potencia, String combustible, String precio, String peso, String seguridad, String traccion, String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int editarJaxB(String marca, String modelo, String anio, String potencia, String combustible, String precio, String peso, String seguridad, String traccion, String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //No consigo hacer que este metodo funcione como deberia he probado demasiadas cosas y ns pq me sigue saltando tantos errores
    class ManejadorSAX extends DefaultHandler {

        int ultimoelement;
        String cadena_resultado = "";

        public ManejadorSAX() {
            ultimoelement = 0;

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

            if (qName.equals("coche")) {
                cadena_resultado = cadena_resultado
                        + "\nLa Marca es; " + atts.getValue(atts.getQName(0))
                        + "\nEl Modelo es; " + atts.getValue(atts.getQName(1))
                        + "\nEl año de Salida es;" + atts.getValue(atts.getQName(2))
                        + "\nLa potencia ; " + atts.getValue(atts.getQName(3))
                        + "\nCombustible ; " + atts.getValue(atts.getQName(4)) + "\n";
                ultimoelement = 1;
            } else if (qName.equals("precio")) {
                ultimoelement = 2;
                cadena_resultado = cadena_resultado + "\nPrecio; ";

            } else if (qName.equals("peso")) {
                ultimoelement = 3;
                cadena_resultado = cadena_resultado + "\nPeso; ";
            } else if (qName.equals("estrellasSeguridad")) {
                ultimoelement = 3;
                cadena_resultado = cadena_resultado + "\nNivel de Seguridad; ";
            } else if (qName.equals("traccion")) {
                ultimoelement = 3;
                cadena_resultado = cadena_resultado + "\nTracción; ";
            } else if (qName.equals("tipoChasis")) {
                ultimoelement = 3;
                cadena_resultado = cadena_resultado + "\nTipo; ";
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if (qName.equals("coche")) {
                System.out.println("Final del elemneto");
                cadena_resultado = cadena_resultado + "\n --+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--++--+--+--+--+--";
            }
        }
//Cuando se detecta una cadena de texto posterior a uno de los elementos
//<titulo> o <autor> entonces guarda ese texto en la variable correspondiente.

        @Override
        public void characters(char[] ch, int start, int length) throws
                SAXException {
            if (ultimoelement == 2) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 3) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }
        }
    }

    public String recorrerSAX(ManejadorSAX sh, SAXParser parser) {
        try {
            parser.parse(ficheroXML, sh);
            return sh.cadena_resultado;
        } catch (SAXException e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }

    
    //Método para leer el XML.
    protected String[] readXml(Node n) {
        //Creamos un array que guarda los 3 nodos hijos
        String info[] = new String[3];
        Node ntemp = null;
        int contador = 1;
        //Guardamos el valor 0 en el array
        info[0] = n.getAttributes().item(0).getNodeValue();
        //Creamos los nodos texto de cada nodo elemento.
        NodeList nodoHijo = n.getChildNodes();

        for (int i = 0; i < nodoHijo.getLength(); i++) {
            ntemp = nodoHijo.item(i);
            //Guardamos el valor del nodo texto
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                info[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }

        return info;
    }
    
    
    
    
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
    
    
    

    
    public int abrirDom(File fichero){
       try{
           //Creamos un DocumentBuilderFactory que nos permitirá crear el DocumentBUilder
           DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
           //Ignoramos comentarios y espacios en blanco del documento.
           factory.setIgnoringComments(true);
           factory.setIgnoringElementContentWhitespace(true);
           //A partir del factory creamos un DocumentBuilder que nos va a permitir cargar en él
           //la estructura del árbol DOM a partir del XML que le hemos pasado.
           DocumentBuilder builder=factory.newDocumentBuilder();
           //Parseamos el fichero y generamos un DOM equivalente al fichero XML. El doc apunta al comienzo del árbol.
           doc=builder.parse(fichero);
                  
           return 0;
       
       }catch(Exception e){System.out.println("jewbfjwdb");
          e.printStackTrace();
          return -1;
       }
    }
    
    public String recorrerDom(){
       String datos_nodo[]=null; //Aquí vamos a guardar el contenido del array que nos devuelve procesarPerros
       String salida="";
       Node node;
       try{
         //Obtenemos el primer nodo del Dom.
         Node raiz=doc.getFirstChild();
         //Obtenemos una lista de nodos con todos los nodos hijo del nodo raíz. Son los nodos perro.
         NodeList listaNodos=raiz.getChildNodes();
         //Procesamos los nodos hijo con un bucle for.
         for(int i=0; i<listaNodos.getLength(); i++){
           node=listaNodos.item(i); //node adopta el valor del elemento en la lista de nodos posición i. Es un elemento perro.
                                    //Estos son los elementos que vamos a procesar con el método procesarPerros.
           if(node.getNodeType()==Node.ELEMENT_NODE){
               datos_nodo=leeListaCoches(node); //Este array es el mismo que devuelve el método procesarPerros.
               //En salida guardamos el valor del array datos_nodo ya procesado
               salida=salida + "\n" + "marca; " + datos_nodo[1];
                salida=salida + "\n" + "modelo; " + datos_nodo[0];
                 salida=salida + "\n" + "anioSalida; " + datos_nodo[2];
                  salida=salida + "\n" + "potencia; " + datos_nodo[3];
                   salida=salida + "\n" + "combustible; " + datos_nodo[4];
                    salida=salida + "\n" + "precio; " + datos_nodo[5];
                     salida=salida + "\n" + "peso; " + datos_nodo[6];
                      salida=salida + "\n" + "estrellasSeguridad; " + datos_nodo[7];
                       salida=salida + "\n" + "traccion; " + datos_nodo[8];
                        salida=salida + "\n" + "tipoChasis; " + datos_nodo[9];
                        salida=salida + "\n" + "-------------------------------------";
           }
       }
       
       }catch(Exception e){
         e.printStackTrace();
       }  
        return salida;
    }
     protected String[] leeListaCoches(Node n){
        String datos[]=new String[10]; //Declaramos un array de 10 que es lo que nos va a devolver con el contenido del árbol
        Node ntemp=null; //Declaramos un nodo temporal para poder procesar los nodos hijo.
        int contador=5; //Lo vamos a usar para movernos por las posiciones dentro del array.
      try{  
        //Dentro del array guardamos los valores de los nodos atributo.
        datos[0]=n.getAttributes().item(0).getNodeValue();
        datos[1]=n.getAttributes().item(1).getNodeValue();
        datos[2]=n.getAttributes().item(2).getNodeValue();
        datos[3]=n.getAttributes().item(3).getNodeValue();
        datos[4]=n.getAttributes().item(4).getNodeValue();
        //Dentro de una lista de nodos guardamos todos los nodos hijo 
        NodeList nodos=n.getChildNodes();
        //Procesamos a través de un bucle for.
        for(int i=0; i<nodos.getLength();i++){
            ntemp=nodos.item(i); //ntemp adopta el valor del elemento de la lista de nodos en la posición i en cada vuelta del bucle
            //En caso que el tipo de nodo de ntemp sea tipo elemento, ejecutamos el código.
            if(ntemp.getNodeType()==Node.ELEMENT_NODE){
                //Accedemos al nodo hijo de los nodos tipo elemento para poder obtener el valor texto. Los nodos hijo
                //de nombre, propietario, etc, son nodos texto donde se almacena el contenido textual del nodo.
                datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
                contador++;  //El contador nos mueve a través de la posición del array.
            }
        }
      }catch(Exception e){
         e.printStackTrace();
      }
        
        return datos;
    }
    
    
    
    

    //Metodo para añadir un nodo al DOM.
    //Declaramos el año sin la ñ por si peta
    public int annadirDom(String marca, String modelo, String aniosalida, String potencia, String combustible,
            String coche, String precio, String peso, String estrellasSeguridad, String traccion, String tipoChasis) {
        try {
            //añadimos el nodo de texto creado como hijo del elemento título.
            Node nCoche = doc.createElement("coche");
            Node nCoche_text = doc.createTextNode(coche);
            nCoche.appendChild(nCoche_text);

            Node nPrecio = doc.createElement("precio");
            Node nPrecio_text = doc.createTextNode(coche);
            nPrecio.appendChild(nPrecio_text);

            Node nPeso = doc.createElement("peso");
            Node nPeso_text = doc.createTextNode(coche);
            nPeso.appendChild(nPeso_text);

            Node nSeguridad = doc.createElement("estrellasSeguridad");
            Node nSeguridad_text = doc.createTextNode(coche);
            nSeguridad.appendChild(nSeguridad_text);

            Node nTraccion = doc.createElement("traccion");
            Node nTraccion_text = doc.createTextNode(coche);
            nTraccion.appendChild(nTraccion_text);

            Node nChasis = doc.createElement("tipoChasis");
            Node nChasis_text = doc.createTextNode(coche);
            nChasis.appendChild(nChasis_text);

            Node nCoches = doc.createElement("coche");
            ((Element) nCoches).setAttribute("marca", marca);
            ((Element) nCoches).setAttribute("modelo", modelo);
            ((Element) nCoches).setAttribute("anioSalida", aniosalida);
            ((Element) nCoches).setAttribute("potencia", potencia);
            ((Element) nCoches).setAttribute("combustible", combustible);

            nCoches.appendChild(nCoche);
            nCoches.appendChild(nPrecio);
            nCoches.appendChild(nPeso);
            nCoches.appendChild(nSeguridad);
            nCoches.appendChild(nTraccion);
            nCoches.appendChild(nChasis);

            //Obtenemos el primer nodo del documento y le añadimos como hijo el nodo Libro
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nCoche);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    //Método para guardar el archivo xml.
    public int guardarDomComoFile(String nombreArchivo) {

        try {

            File archivo_xml = new File(nombreArchivo);
            //Este es el formato.
            OutputFormat format = new OutputFormat(doc);
            //Esto ns pq es pero lo encontre en internet y funciona
            format.setIndenting(true);
            //Escribe el contenido en el file.
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);

            serializer.serialize(doc);

            System.out.println(nombreArchivo);

            return 0;

        } catch (Exception e) {
            System.out.println("No se ha guardado el archivo");
            return -1;
        }

    }

    //Método para cambiar el valor de un nodo texto ya existente.
    public void cambiarTitulo(Document doc, String entrada, String nuevoTitulo) {
        String datos_nodo[] = null;
        Node node;

        //Tenemos la raiz del nodoHijo
        Node raiz = doc.getFirstChild();
        //Metemos en una lista todos los nodos del nodoHijo
        NodeList lista = raiz.getChildNodes();
        //Procesamos los nodos hijo.

        for (int i = 0; i < lista.getLength(); i++) {
            node = lista.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String[] datos = new String[3];
                Node ntemp = null;
                int contador = 1;
                //Valor del primer atributo del nodo.
                datos[0] = node.getAttributes().item(0).getNodeValue();
                //Obtenemos los nodos hijos del nodo libro.
                NodeList nodos = node.getChildNodes();
                //Recorremos la lista de nodos hijo para obtener su valor del nodo texto.
                for (int j = 0; j < nodos.getLength(); j++) {
                    ntemp = nodos.item(i);
                    if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                        //Obtenemos los valores del nodo texto.
                        datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                        contador++;
                        //Hacemos la comprobación. Si se corresponde el dato introducido con un título, se hace el cambio.
                        if (datos[i].equals(entrada)) {
                            ntemp.setTextContent(nuevoTitulo);
                        }

                    }

                }

            }
        }

    }

}
