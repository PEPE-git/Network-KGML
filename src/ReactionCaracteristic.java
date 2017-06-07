package projet_POO;
/**
 * ReactionCaracteristic.java
 */

/**
 * ReactionCaracteristic correspond à la description d'une réaction
 * métabolique obtenue à partir des fichiers d'entrée.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class ReactionCaracteristic extends Data {
	private String equation;
	private String enzyme;

	/**
	 * Constructeur ReactionCaracteristic.
	 * 
	 * @param id : identifiant unique associé à une réaction
	 * @param name : nom complet du composé
	 * @param equation : équation de la réaction
	 * @param enzyme : enzyme catalysant la réaction
	 */
	public ReactionCaracteristic(String id, String name, String equation, String enzyme) {
		super.id = id;
		super.name = name;
		this.equation = equation;
		this.enzyme = enzyme;
	}
	
	/**
	 * Retourne un texte contenant la description de la réaction.
	 * 
	 * @return message : description de la réaction
	 */
	public String afficher() {
		String message = "";
		message += "ID :\t"+this.id+"\n";
		message += "NAME :\t"+this.name+"\n";
		message += "EQUATION :\t"+this.equation+"\n";
		message += "ENZYME(S) :\t"+this.enzyme+"\n";
		return message;
	}
	
	/**
	 * Retourne l'identifiant de la réaction.
	 * 
	 * @return id : identifiant
	 */
	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * Retourne le nom complet de la réaction.
	 * 
	 * @return name : nom complet
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
