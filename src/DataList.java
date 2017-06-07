package projet_POO;
/**
 * DataList.java
 */
import java.util.ArrayList;
import java.util.List;

/**
 * DataList contient l'ensemble des données disponibles stockées sous 
 * forme de 3 listes correspondant aux informations sur les composés, 
 * aux informations sur les réactions et aux voies métaboliques générales.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class DataList {
	private List<Data> cList;
	private List<Data> rList;
	private List<Data> gpList;
	
	/**
	 * Constructeur DataList.
	 * 
	 * @see List
	 * @see ArrayList
	 * */
	public DataList() {
		this.cList = new ArrayList<Data>();
		this.rList = new ArrayList<Data>();
		this.gpList = new ArrayList<Data>();
	}
	
	/**
	 * Extraction de l'ensemble des données depuis les fichiers et 
	 * stockage en objet de classe Data dans des listes.
	 * 
	 * @param f : fenetre dans laquelle est mise à jour la voie métabolique
	 * @param chemin : le chemin relatif des dossiers dont les fichiers sont à parser
	 */
	public void traitementData(Fenetre f, String chemin) {
		String compoundRepertoire = chemin+"/compounds/";
		String reactionRepertoire = chemin+"/reactions/";
		String bacteriaRepertoire = chemin+"/bacteria/";
		
		DataFile inFile;
		// Récuperation des réactions
		inFile = new ReactionFile(reactionRepertoire);
		rList = inFile.parser();
		// Récupération des composés
		inFile = new CompoundFile(compoundRepertoire);
		cList = inFile.parser();
		// Récupération des voies pour toutes les bactéries
		inFile = new BactFile(bacteriaRepertoire);
		gpList = inFile.parser();
		//Mise a jour des noms de voies
		f.getDataSelect().getVoieSelect().updatePathway(gpList);
	}
	
	/**
	 * Retourne la liste des GeneralPathway
	 * 
	 * @return gpList : liste des voies métaboliques générales
	 */
	public List<Data> getGpList() {
		return gpList;
	}
	
	/**
	 * Retourne la liste de l'ensemble des ReactionCaracteristic
	 * 
	 * @return rList : liste des réactions et de leurs caractéristiques
	 */
	public List<Data> getRList() {
		return rList;
	}
	
	/**
	 * Retourne la liste de l'ensemble des CompoundCaracteristic
	 * 
	 * @return cList : liste des composés et de leurs caractéristiques
	 */
	public List<Data> getCList() {
		return cList;
	}
}
