package Funciones;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Archivos {

    /**
     * Lee de un archivo binario un objeto de tipo ArrayList de la clase persona
     *
     * @param archivo String con el nombre del archivo a editar
     * @return ArrayList con los datos obtenidos
     */
    public static ArrayList<Object> leerObjetoBinario(String archivo) {
        ObjectInputStream flujoEntrada = null;
        ArrayList<Object> personas = null;
        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream(archivo));
            personas = (ArrayList<Object>) flujoEntrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (flujoEntrada != null) {
                try {
                    flujoEntrada.close();
                } catch (IOException e3) {
                    System.out.println(e3.getMessage());
                }
            }
        }
        return personas;
    }

    /**
     * Guarda en un archivo binario un objeto de tipo ArrayList de la clase
     * Persona
     *
     * @param personas ArrayList a Guardar
     * @param archivo String con el nombre del archivo
     */
    public static void guardarObjectoBinario(ArrayList<Object> personas, String archivo) {
        ObjectOutputStream flujoSalida = null;
        try {
            flujoSalida = new ObjectOutputStream(new FileOutputStream(archivo));
            flujoSalida.writeObject(personas);
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        } finally {
            if (flujoSalida != null) {
                try {
                    flujoSalida.close();
                } catch (IOException e3) {
                    System.out.println(e3.getMessage());
                }
            }
        }
    }

    public static ArrayList<String> leerTexto(String archivo) {
        BufferedReader flujoEntrada = null;
        ArrayList<String> datos = new ArrayList<>();

        try {
            flujoEntrada = new BufferedReader(new FileReader(archivo));
            if (flujoEntrada.ready()) {
                int indice = Integer.parseInt(flujoEntrada.readLine());
                for (int i = 0; i < indice; i++) {
                    datos.add(flujoEntrada.readLine());
                }
            } else {
                System.out.println("El fichero está vacio");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (flujoEntrada != null) {
                try {
                    flujoEntrada.close();
                } catch (IOException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return datos;
    }

    public static boolean guardarTexto(ArrayList<String> lista, String archivo) {
        BufferedWriter flujoSalida = null;
        boolean guardado = false;
        try {
            flujoSalida = new BufferedWriter(new FileWriter(archivo));
            flujoSalida.write(lista.size());
            flujoSalida.newLine();
            for (int i = 0; i < lista.size(); i++) {
                flujoSalida.write(lista.get(i));
                flujoSalida.newLine();
            }
            guardado = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (flujoSalida != null) {
                try {
                    flujoSalida.close();
                } catch (IOException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return guardado;
    }

    public static boolean guardarXML(ArrayList<String> lista, String ruta, String nombre) {
        boolean guardado = false;
        if (!nombre.endsWith(".xspf")) {
            nombre += ".xspf";
        }

        try {
            //Document builders y documento
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.newDocument();

            //Archivo en el que transformaremos el documento xml
            File aGuardar = new File(ruta, nombre);

            //Transformador que transformara el DOM al fichero
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            //Se cambian las propiedades del transformador
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            //Creamos un stream 
            StreamResult resultado = new StreamResult(aGuardar);

            //Obtenemos la raiz del documento
            DOMSource source = new DOMSource(documento);

            //Creamos el elemento raíz PLAYLIST y atributos
            Element raiz = documento.createElement("playlist");
            raiz.setAttribute("xmlns", "http://xspf.org/ns/0/");
            raiz.setAttribute("Version", "1");
            //Creamos el primer hijo de playlist TRACKS
            Element tracks = documento.createElement("trackList");
            //Creamos el primer hijo de tracks LOCATION, insertamos
            //la ruta de la playlist y lo añadimos al elemento tracks
            Element location1 = documento.createElement("location");
            location1.setTextContent("File:///" + ruta);
            tracks.appendChild(location1);
            /*
            Recorremos todo el array de canciones extrayendo la información
            y añadiendolo todo a sus respectivos nodos
            
            for (int i = 0; i < lista.size(); i++) {
                Element track = documento.createElement("track");
                Element title = documento.createElement("title");
                Element location2 = documento.createElement("location");
                title.setTextContent(lista.get(i).getNombre());
                location2.setTextContent("File:///" + lista.get(i).getRuta());
                track.appendChild(title);
                track.appendChild(location2);
                tracks.appendChild(track);
            }
            */
            raiz.appendChild(tracks);
            documento.appendChild(raiz);

            transformador.transform(source, resultado);
            guardado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return guardado;
    }

     public static ArrayList<String> leerPlaylistXSPF(String rutaPlaylist){
        ArrayList<String> canciones = new ArrayList<>();

        try{
            File aCambiar = new File(rutaPlaylist);
            //Document builders y documento
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(aCambiar);
            
            NodeList nodes = documento.getElementsByTagName("track");

            //Recorremos cada Track que tiene el archivo XPFS
            for(int i=0; i < nodes.getLength(); i++){
                //Sacamos el Track correspondiente con el índice del bucle
                //Creamos También las variables 
                Node actual = nodes.item(i);
                String title = "";
                String location = "";
                //Switch con el tipo de nodo para evitar errores
                switch(actual.getNodeType()){
                    //En caso de que sea un elemento nodo, obtenemos sus hijos 
                    case Node.ELEMENT_NODE -> {
                        NodeList interna = actual.getChildNodes();
                        //Recorremos todos los nodos internos
                        for(int j=0; j < interna.getLength(); j++){
                            Node actualInterno = interna.item(j);
                            switch(actualInterno.getNodeType()){
                                //Elementos Title y Location
                                case Node.ELEMENT_NODE -> {
                                    if(actualInterno.getNodeName().equals("title")){
                                        title = actualInterno.getTextContent();
                                    }else{
                                        location = actualInterno.getTextContent();
                                        location = location.substring(8);
                                    }
                                }
                            }
                        }
                    }
                }
                canciones.add(title);
                canciones.add(location);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return canciones;
    }
}
