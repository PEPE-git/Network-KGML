package projet_POO;
	/**
 * Data.java
 */

/**
 * Data est une classe abstraite et définit l'ensemble des données 
 * disponibles récupérées des fichiers.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public abstract class Data {
	protected String id;
	protected String name;
	
	public abstract String getId();
	public abstract String getName();
}
