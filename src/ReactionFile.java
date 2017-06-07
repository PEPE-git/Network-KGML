package projet_POO;
/**
 * ReactionFile.java
 */
import java.io.*;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

/**
 * ReactionFile définit les données relatives à un fichier réaction.
 * La classe hérite de DataFile.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class ReactionFile extends DataFile  {
	
	/**
     * Constructeur ReactionFile
     * 
     * @param fileName : nom du dossier correspondant aux réactions.
     * @see DataFile
     */
	public ReactionFile(String fileName) {
		super.fileName = fileName;
	}
	
	/**
	 * Liste les fichiers réaction et retourne leur nom.
	 * 
	 * @return reactionName : vecteur contenant tous les noms de fichiers
	 * correspondant aux réactions
	 */
	@Override
	public String[] listerFichier() {
		File reactionData = new File(super.fileName);
		String[] reactionName = reactionData.list();
		return reactionName;
	}
		
	/**
	 * Parser des fichiers de réactions : stockage des informations sur 
	 * les réactions contenues dans les fichiers correspondants.
	 * Retour sous forme de liste.
	 * 
	 * @return reactionList : liste des informations des réactions
	 * @see List
	 * @see ListInfo
	 */
	@Override
	public List<Data> parser() {
		
		String entryKey = "ENTRY";
		String nameKey = "NAME";
		String equationKey = "EQUATION";
		String enzymeKey = "ENZYME";
		String emptyKey = "";
		
		String entry = "";
		String name = "";
		String enzyme = "";
		String equation = "";
		
		List<Data> reactionList = new ArrayList<Data>();
		
		try {
			String [] reactionName = listerFichier();
			
			for (int i = 0; i < reactionName.length; i++) {
				boolean flagEnzyme = false;
				String enzymeTmp = "";
				
				FileReader input = new FileReader(super.fileName+reactionName[i]);
				BufferedReader bufRead = new BufferedReader(input);
				String myLine = null;
				
				while ( (myLine = bufRead.readLine()) != null) {
					String[] line = myLine.split(" ");
				
					if (line[0].equals(entryKey)) {
						flagEnzyme = false;
						int j = 1;
						while (j < line.length && line[j].equals(emptyKey)) j++;
						if (j < line.length) entry = line[j];
					}
					else {
						if (line[0].equals(nameKey)) {
							flagEnzyme = false;
							String completeName = "";
							for (int j = 1; j < line.length; j++) {
								if (!line[j].equals(emptyKey)) completeName += line[j]+" ";
							}
							name = completeName;
						}
						else {
							if (line[0].equals(equationKey)) {
								flagEnzyme = false;
								String completeEq = "";
								for (int j = 1; j < line.length; j++) {
									if (!line[j].equals(emptyKey)) completeEq += line[j];
								}
								equation = completeEq;
							}
							else if(line[0].equals(enzymeKey) || flagEnzyme) {
								if (!flagEnzyme) {
									String completeEnz = "";
									for (int j = 1; j < line.length; j++) {
										if (!line[j].equals(emptyKey)) completeEnz += "["+line[j]+"] ";
									}
									enzymeTmp += completeEnz;
									flagEnzyme = true;
								}
								else {
									if (line[0].equals("REMARK") || line[0].equals("REACTION") || line[0].equals("ORTHOLOGY") || line[0].equals("REFERENCE") || line[0].equals("PATHWAY") || line[0].equals("MODULE") || line[0].equals("BRITE") || line[0].equals("DBLINKS") || line[0].equals("ATOM") || line[0].equals("BOND") || line[0].equals("///")) flagEnzyme = false;
									else {
										String completeEnz = "\n";
										for (int j = 0; j < line.length; j++) {
											if (!line[j].equals(emptyKey)) completeEnz += "["+line[j]+"] ";
										}
										enzymeTmp += completeEnz;
									}
								}
							}
						}
					}
				}
				enzyme = enzymeTmp;
				bufRead.close();
				Data r = new ReactionCaracteristic(entry, name, equation, enzyme);
				if (!reactionList.contains(r)) reactionList.add(r);
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return reactionList;
	}
}



