package src.model;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;

/**
 * Classe responsable de la gestion de la persistance des dessins sous forme de fichiers XML.
 * Cette classe permet de sauvegarder et de charger des dessins dans des fichiers XML.
 */
public class DessinXMLDAO{

    public void sauvegarder(Dessin dessin, File fichier) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("dessin");
            doc.appendChild(rootElement);

            for (Forme forme : dessin.getFormesListe()) {
                Element formeElement;

                if (forme instanceof Cercle) {
                    Cercle c = (Cercle) forme;
                    formeElement = doc.createElement("cercle");
                    formeElement.setAttribute("x", String.valueOf(c.getX()));
                    formeElement.setAttribute("y", String.valueOf(c.getY()));
                    formeElement.setAttribute("rayon", String.valueOf(c.getRayon()));
                } else if (forme instanceof Rectangle) {
                    Rectangle r = (Rectangle) forme;
                    formeElement = doc.createElement("rectangle");
                    formeElement.setAttribute("x", String.valueOf(r.getX()));
                    formeElement.setAttribute("y", String.valueOf(r.getY()));
                    formeElement.setAttribute("largeur", String.valueOf(r.getLargeur()));
                    formeElement.setAttribute("hauteur", String.valueOf(r.getHauteur()));
                } else {
                    continue;
                }

                rootElement.appendChild(formeElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fichier);

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge un dessin depuis un fichier XML.
     * 
     * @param fichier le fichier XML à charger
     * @return le dessin chargé depuis le fichier XML
     */
    public Dessin charger(File fichier) {
        Dessin dessin = new Dessin();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(fichier);
            doc.getDocumentElement().normalize();

            NodeList formes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < formes.getLength(); i++) {
                Node node = formes.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) continue;

                Element element = (Element) node;
                String tag = element.getTagName();

                switch (tag) {
                    case "cercle":
                        int cx = Integer.parseInt(element.getAttribute("x"));
                        int cy = Integer.parseInt(element.getAttribute("y"));
                        int rayon = Integer.parseInt(element.getAttribute("rayon"));
                        Cercle cercle = new Cercle(new Point(cx, cy), rayon);
                        dessin.ajouterForme(cercle);
                        break;

                    case "rectangle":
                        int rx = Integer.parseInt(element.getAttribute("x"));
                        int ry = Integer.parseInt(element.getAttribute("y"));
                        int largeur = Integer.parseInt(element.getAttribute("largeur"));
                        int hauteur = Integer.parseInt(element.getAttribute("hauteur"));
                        Rectangle rect = new Rectangle(new Point(rx, ry), largeur, hauteur);
                        dessin.ajouterForme(rect);
                        break;

                    default:
                        System.out.println("Forme non reconnue: " + tag);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dessin;
    }
}
