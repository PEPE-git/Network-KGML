package projet_POO;
/**
 * DataFile.java
 */
import java.util.List;

/**
 * DataFile est une classe de données abstraite qui représente
 * un fichier de données
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public abstract class DataFile {
	protected String fileName;
	
	public abstract List<Data> parser();
	public abstract String[] listerFichier();
}
