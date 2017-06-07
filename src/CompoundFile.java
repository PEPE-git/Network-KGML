package projet_POO;
/**
 * CompoundFile.java
 */
import java.io.*;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

/**
 * CompoundFile définit l'ensemble des données jugées pertinentes 
 * relatives aux informations contenues dans les fichiers d'entrées.
 * 
 * La classe hérite de DataFile.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class CompoundFile extends DataFile  {
	/**
     * Constructeur CompoundFile
     * 
     * @param fileName : nom du dossier correspondant aux composés.
     * @see DataFile
     */
	public CompoundFile(String fileName) {
		super.fileName = fileName;
	}
	
	/**
	 * Liste les fichiers des composés et retourne leurs noms.
	 * 
	 * @return compoundName : vecteur contenant tous les noms de fichiers
	 * correspondant aux composés.
	 */
	@Override
	public String[] listerFichier() {
		File compoundData = new File(super.fileName);
		String[] compoundName = compoundData.list();
		return compoundName;
	}
	
	/**
	 * Parser des fichiers de composé, stockage des informations et les 
	 * retour sous forme de liste.
	 * 
	 * @return compoundList : liste des composés des voies métaboliques
	 * @see List
	 * @see CompoundCaracteristic
	 */
	@Override
	public List<Data> parser() {
		String entryKey = "ENTRY";
		String nameKey = "NAME";
		String massKey = "EXACT_MASS";
		String molKey = "MOL_WEIGHT";
		String emptyKey = "";
		
		String entry = "";
		String name = "";
		String mass = "";
		String mol = "";
		
		List<Data> compoundList = new ArrayList<Data>();
		
		try {
			String [] compoundName = listerFichier();
			
			for (int i = 0; i < compoundName.length; i++) {
				
				FileReader input = new FileReader(super.fileName+compoundName[i]);
				BufferedReader bufRead = new BufferedReader(input);
				String myLine = null;
				
				while ((myLine = bufRead.readLine()) != null) {
					String[] line = myLine.split(" ");
					
					if (line[0].equals(entryKey)) {
						int j = 1;
						while (j < line.length && line[j].equals(emptyKey)) j++;
						if (j < line.length) entry = line[j];
					}
					else {
						if (line[0].equals(nameKey)) {
							int j = 1; String tmp = "";
							while (j < line.length) {
								if (!line[j].equals(emptyKey)) tmp += " "+line[j];
								j++;
							}
							if (j == line.length) name = tmp.substring(1,tmp.length()-1); // Suppression du caractère ";" en fin de nom
						}
						else {
							if (line[0].equals(massKey)) {
								int j = 1; String tmp = "";
								while (j < line.length) {
									if (!line[j].equals(emptyKey))	tmp += " "+line[j];
									j++;
								}
								if (j == line.length) mass = tmp.substring(1,tmp.length());
							}
							else {
								if (line[0].equals(molKey)) {
									int j = 1;String tmp = "";
									while (j < line.length) {
										if (!line[j].equals(emptyKey)) tmp += " "+line[j];
										j++;
									}
									if (j == line.length) mol = tmp.substring(1,tmp.length());
								}
							}
						}
					}
				}
				bufRead.close();
				Data c = new CompoundCaracteristic(entry, name, mass, mol);
				if (!compoundList.contains(c)) compoundList.add(c);
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return compoundList;
	}
}
