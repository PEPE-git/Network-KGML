package projet_POO;
/**
 * Reaction.java
 */
import java.util.List;
import java.util.ArrayList;

/**
 * Reaction définit les réactions catalysées dans les voies
 * métaboliques.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class Reaction {
	private String id; // code de la récation Rxxxxx
	private String type; // reversible ou irreversible
	private List<String> sbt;
	private List<String> pdt;
	private List<Bacterie> bac;
	
	/**
     * Constructeur Reaction.
     * 
     * @param id : identifiant unique de la réaction
     * @param type : irréversible ou réversible
     * @param sbt : Liste des ID des substrats
     * @param pdt : Liste des ID des produits
     * @param b : bactérie possédant cette réaction
     */
	public Reaction(String id, String type, List<String> sbt, List<String> pdt, Bacterie b) {
		this.id = id;
		this.type = type;
		this.sbt = sbt;
		this.pdt = pdt;
		this.bac = new ArrayList<Bacterie>();
		this.bac.add(b);
	}
	
	/**
     * Constructeur Reaction.
     * 
     * @param id : identifiant unique de la réaction
     * @param type : irréversible ou réversible
     * @param sbt : Liste des ID des substrats
     * @param pdt : Liste des ID des produits
     * @param b : Lit des bactéries possédant cette réaction
     */
	public Reaction(String id, String type, List<String> sbt, List<String> pdt, List<Bacterie> b) {
		this.id = id;
		this.type = type;
		this.sbt = sbt;
		this.pdt = pdt;
		this.bac = b;
	}

	/**
	 * Teste l'existence de la réaction dans une liste de réactions 
	 * founie en paramètre.
	 * 
	 * @param lreaction : liste de réactions
	 * @return index : indice de la réaction dans la liste, -1 si elle 
	 * en est absente
	 * @see List;
	 */
	public int exist(List<Reaction> lreaction) {
		int index = -1;
		int i = 0;
		while (index == -1 && i < lreaction.size()) {
			if(this.id.equals(lreaction.get(i).getId())) index = i;
			else i++;
		}
		return index;
	}
	
	
	/**
	 * Retourne la liste des bactéries de la réaction.
	 * 
	 * @return bac : liste de bactéries
	 */
	public List<Bacterie> getBact() {
		return this.bac;
	}
	
	/**
	 * Retourne l'identifiant de la réaction.
	 * 
	 * @return id : identifiant unique de la réaction
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Retourne la liste des produits de la réaction.
	 * 
	 * @return pdt : liste de produits
	 */
	public List<String> getProduct() {
		return pdt;
	}
	
	/**
	 * Retourne la liste des substrats de la réaction.
	 * 
	 * @return sbt : liste de substrats
	 */
	public List<String> getSubstrate() {
		return sbt;
	}

	/**
	 * Récupération du type de la réaction.
	 * 
	 * @return type : "irreversible" ou "reversible
	 */
	public String getType() {
		return this.type;
	}
	
	
	
}
