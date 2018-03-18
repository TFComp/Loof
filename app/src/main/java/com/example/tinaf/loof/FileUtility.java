package com.example.tinaf.loof;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * Created by tinaf on 3/17/2018.
 */

public class FileUtility {
    public static void SaveFile(ArrayList<Anime> animeList, String type, String fileName){
        String fileContents = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        fileContents += "<" + type + ">";
        for (int i = 0; i < animeList.size(); i++){
            fileContents += animeList.get(i).toXml();
        }
        fileContents += "</" + type + ">";;
        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static FileOutputStream openFileOutput(String fileName, int modePrivate) {
    }

    public static void ReadFile(ArrayList<Anime> animeList, ArrayList<String> items, String fileName){
        try{
            //InputStream inputStream = openFileInput(fileName);
            InputStream inputStream = null;
            DocumentBuilderFactory docbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputStream);

            NodeList nodeList = doc.getElementsByTagName("anime");

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element =(Element) node;
                    String name = getValue("name",element);
                    animeList.add(new Anime(name,
                            getValue("description", element),
                            Integer.parseInt(getValue("episode", element))));
                    items.add(name);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
