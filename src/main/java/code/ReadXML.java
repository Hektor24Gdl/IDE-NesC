/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class that read a XML file that describe the NesC Language.
 */
public class ReadXML {
    ArrayList<HashMap> config = new ArrayList<>();
    final StyleContext cont = StyleContext.getDefaultStyleContext();
    
    final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
    final AttributeSet attrDefault = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
    final AttributeSet attrBlue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
    HashMap<String, Triplet> attributes =new HashMap<>();
    
    /**
     * Function that read the XML file 
     * @throws FileNotFoundException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public  ReadXML() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException{
        HashMap<String, Triplet> localAttributes = new HashMap<>();
        try {
        InputStream file = ReadXML.class.getResourceAsStream("/configurations/nesc.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList keywords = doc.getElementsByTagName("Keywords");
        for (int s = 0; s < keywords.getLength(); s++) {

          Node keyword = keywords.item(s);
            String expr = (keyword.getTextContent()).replace(" ", "|");
             if (keyword.getNodeType() == Node.ELEMENT_NODE) {
               Element result = (Element) keyword;
                  localAttributes.put(result.getAttribute("name"), new Triplet(expr,null,null));
              //System.out.println((keyword.getTextContent()).replace(" ", "|"));
              }

        }
        NodeList lexerStyles = doc.getElementsByTagName("LexerStyle");
        for (int s = 0; s < lexerStyles.getLength(); s++) {

          Node lexerStyle = lexerStyles.item(s);

          if (lexerStyle.getNodeType() == Node.ELEMENT_NODE) {
              //System.out.println((lexerStyle.getNodeName()));
              NodeList wordsStyles = lexerStyle.getChildNodes();
              for (int r = 0; r < wordsStyles.getLength(); r++) {
                Node wordsStyle = wordsStyles.item(r);

                if (wordsStyle.getNodeType() == Node.ELEMENT_NODE) {
                    Element result = (Element) wordsStyle;
                    String[] keywordsc = result.getAttribute("keywordClass").split(" ");
                    Triplet triplet = new Triplet("",null,null);
                    //System.out.println(Arrays.toString(keywordsc));
                    //System.out.println(localAttributes.get("instre-c").get1());
                    for(String k : keywordsc){
                        if(k!=""){
                            triplet.set1(localAttributes.get(k).get1() + "|" +  triplet.get1());
                        }
                    }
                    
                    if(!"".equals((String)triplet.get1()))
                        triplet.set1(((String)triplet.get1()).substring(0,((String)triplet.get1()).length()-1));
                    //System.err.println((String)triplet.get1());
                    AttributeSet localAttr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.decode("0x" + result.getAttribute("fgColor")));
                    triplet.set2(localAttr);
                    this.attributes.put(result.getAttribute("name"), triplet);
                    //System.out.println(result.getAttribute("name"));
                    //System.out.println(result.getAttribute("bgColor"));
                    
                    
                }
            }

        }
        }
        } catch (Exception e) {
          e.printStackTrace();
        }
        //String usr = document.getElementsByTagName("user").item(0).getTextContent();
        //String pwd = document.getElementsByTagName("password").item(0).getTextContent();
    }

    public HashMap<String, Triplet> getAttributes() {
        return attributes;
    }
}
