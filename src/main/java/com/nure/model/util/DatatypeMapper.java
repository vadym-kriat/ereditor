package com.nure.model.util;

import com.nure.model.sql.Dialect;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class DatatypeMapper {
    private static final Properties map;
    private static List<String> datatypes;

    static {
        map = new Properties();
        try {
            map.load(DatatypeMapper.class.
                    getClassLoader().getResourceAsStream("datatype_map.map"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        datatypes = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(DatatypeMapper.class.
                    getClassLoader().getResourceAsStream("conf/datatypes.xml"));
            NodeList list = doc.getElementsByTagName("datatype");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                datatypes.add(node.getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DatatypeMapper() {

    }

    public static boolean datatypeIsExist(String datatype) {
        return datatypes.contains(datatype);
    }

    public static String getSpecificDatatypeFor(Dialect dialect, String datatype) {
        String dialectText = dialect.toString().toLowerCase();
        return map.getProperty(String.format("%s.%s", dialectText, datatype));
    }

    public static List<String> listOfDatatypes() {
        return datatypes;
    }
}
