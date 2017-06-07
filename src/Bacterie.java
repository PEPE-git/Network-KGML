package projet_POO;
/**
 * Bacterie.java
 */
import java.util.List;

/**
 * La classe Bacterie définit les organismes d'étude.
 * Chaque bactérie est définie par son ID et son nom latin.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class Bacterie {
	private String id;
	private String name;
	
	/**
     * Constructeur Bactérie.
     * 
     * @param id : identifiant unique de la bactérie
     * @param name : nom latin de la bactérie
     */
	public Bacterie(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Récupération de l'identifiant de la bactérie.
	 * 
	 * @return id : identifiant unique de la bactérie
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Récupération du nom de la bactérie.
	 * 
	 * @return name : nom complet de la bactérie
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Teste l'existence de la bactérie dans une liste de bactéries 
	 * fournie en paramètre.
	 * 
	 * @param bList : liste de bactérie
	 * @return index : indice de la bactérie dans la liste, -1 si elle 
	 * en est absente
	 * @see List;
	 */
	public int exist(List<Bacterie> bList) {
		int index = -1;
		int i = 0;
		while (index == -1 && i < bList.size()) {
			if(this.id.equals(bList.get(i).getId())) index =  i;
			else i++;
		}
		return index;
	}
}
