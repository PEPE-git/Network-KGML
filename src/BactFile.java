package projet_POO;
/**
 * BactFile.java
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * BactFile définit l'ensemble des données jugées pertinentes 
 * relatives aux informations contenues dans les fichiers kgml des bactéries.
 * 
 * La classe hérite de DataFile.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class BactFile extends DataFile {
	
	/**
     * Constructeur BactFile
     * 
     * @param fileName : nom du dossier correspondant aux bactéries,
     * hérité de la classe mère
     * @see DataFile
     */
	public BactFile(String fileName) {
        super.fileName = fileName;
    }
	
	/**
	 * Lister les dossiers des bactéries et retourner leurs noms
	 * 
	 * @return bactName : vecteur contenant tous les noms de dossiers
	 * correspondant aux bactéries
	 */
	public String[] listerFichier() {
		File bactData = new File(super.fileName);
		String[] bactName = bactData.list();
		return bactName;
	}
	
	/**
	 * Parser des fichiers de bactéries : l'ensemble des informations
	 * contenues dans les fichiers permettent de créer les voies métaboliques
	 * générales.
	 * Retourne les voies métaboliques générales construites sous forme
	 * de liste.
	 * 
	 * @return pathwayList : liste des voies métaboliques générales
	 * @see List
	 * @see GeneralPathway
	 */
	@Override
	public List<Data> parser() {
		
		List<Data> pathwayList = new ArrayList<Data>(); // Liste stockant toutes les voies métaboliques
		
		try {
			String [] bactName = listerFichier(); // Liste de toutes les bactéries
			
			// 1. POUR CHAQUE BACTERIE
			for (int i = 0; i < bactName.length; i++) {
				
				// Récupération de l'id et du nom complet de la bactérie à partir du nom du dossier
				String [] bact = bactName[i].split("/");
				bact = bact[bact.length-1].split("_");
				String id = bact[0];
				String completeName = bact[1]+" "+bact[2];
				
				// Nouvelle instance de bactérie
				Bacterie b = new Bacterie(id, completeName); // Bactérie considérée stockée en i ème position du tableau
				List<Bacterie> bactList = new ArrayList<Bacterie>();
				bactList.add(b);
				
				File bactData = new File(fileName+bactName[i]); // Chemin pour le dossier d'une bacterie
				String[] bactPath = bactData.list(); // Liste des voies pour une bactérie


				
				// 2. POUR CHAQUE VOIE DE BACTERIE 
				for (int j = 0; j < bactPath.length; j++) {
				
					String path = bactPath[j].split("path_"+id)[1];
					path = path.split(".kgml")[0];
					// path est de la forme 00000
					

					List<Reaction> reactionList = new ArrayList<Reaction>();
					String name="";
					
					File inFile = new File(fileName+bactName[i]+"/"+bactPath[j]);
					
					
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					dbFactory.setValidating(false);
					dbFactory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(inFile);
					doc.getDocumentElement().normalize();
					Node root = doc.getDocumentElement();
					if (root.getNodeType() == Node.ELEMENT_NODE) {
						Element eRoot = (Element) root;
						name = eRoot.getAttribute("title");
					}
					NodeList nReaction = doc.getElementsByTagName("reaction");
					
					
					// REACTION
					for (int l = 0; l < nReaction.getLength(); l++) {
						Node reaction = nReaction.item(l);

						if (reaction.getNodeType() == Node.ELEMENT_NODE) {
							Element eReaction = (Element) reaction;
				
							String rCode = eReaction.getAttribute("name").split(":")[1];
							rCode = rCode.split(" ")[0];
							String rType = eReaction.getAttribute("type");
							
							NodeList nSubstrate = eReaction.getElementsByTagName("substrate");
							NodeList nProduct = eReaction.getElementsByTagName("product");
							
							List<String> sbtList = new ArrayList<String>();
							List<String> pdtList = new ArrayList<String>();
				
							for (int m = 0; m < nSubstrate.getLength(); m++) {
								Element subst = (Element) nSubstrate.item(m);
								if (subst.getNodeType() == Node.ELEMENT_NODE) sbtList.add(subst.getAttribute("name").split(":")[1]);
							}
							for (int m = 0; m < nProduct.getLength(); m++) {
								Element prod = (Element) nProduct.item(m);
								if (prod.getNodeType() == Node.ELEMENT_NODE) pdtList.add(prod.getAttribute("name").split(":")[1]);
							}
							
							
							
							
							Reaction reac = new Reaction(rCode,rType,sbtList,pdtList,b);
							reactionList.add(reac);
						}
					} // Pour une bactérie, pour une voie, on a toutes les réactions

					// 3. AJOUT DE LA VOIE OU FUSION
					GeneralPathway g = new GeneralPathway(path,name,reactionList,bactList);
					int index = g.exist(pathwayList);
					if(index == -1) pathwayList.add(g);
					else {
						
						for(int k = 0; k < reactionList.size(); k++) {
							int index2 = reactionList.get(k).exist(((GeneralPathway)pathwayList.get(index)).getReactionList());
							if(index2 == -1) ((GeneralPathway)pathwayList.get(index)).getReactionList().add(reactionList.get(k));
							else ((GeneralPathway)pathwayList.get(index)).getReactionList().get(index2).getBact().add(b);
						}
						if(b.exist(((GeneralPathway)pathwayList.get(index)).getBacterieList())==-1) {
							((GeneralPathway)pathwayList.get(index)).getBacterieList().add(b);
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pathwayList;
	}
}
