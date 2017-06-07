package projet_POO;
/**
 * CompoundCaracteristic.java
 */

/**
 * CompoundCaracteristic correspond à la description d'un composé
 * métabolique obtenu à partir des fichiers d'entrée.
 * La classe hérite de Data.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class CompoundCaracteristic extends Data {
	private String mass;
	private String mol;

	/**
	 * Constructeur CompoundCaracteristic.
	 * 
	 * @param mass : masse exacte
	 * @param : poids moléculaire
	 */
	public CompoundCaracteristic(String id, String name, String mass, String mol) {
		super.id = id;
		super.name = name;
		this.mass = mass;
		this.mol = mol;
	}
	
	/**
	 * Retourne un texte contenant la description du composé.
	 * 
	 * @return message : description composé
	 */
	public String afficher() {
		String message = "";
		message += "ID :\t"+this.id+"\n";
		message += "NAME :\t"+this.name+"\n";
		message += "MASS :\t"+this.mass+"\n";
		message += "MOL :\t"+this.mol+"\n";
		return message;
	}
	
	/**
	 * Retourne l'identifiant du composé.
	 * 
	 * @return id : identifiant
	 */
	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * Retourne le nom complet du composé.
	 * 
	 * @return name : nom complet
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
