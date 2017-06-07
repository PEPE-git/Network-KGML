package projet_POO;
/**
 * DataWorker.java
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 * DataWorker permet le multithreading.
 * Il est ainsi possible de lire l'aide pendant le parsing
 * des données d'entrée.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class DataWorker extends SwingWorker<Integer,Void> {
	private Fenetre f;
	private File dossier;
	
	
	/**
	 * Constructeur DataWorker
	 * 
	 * @param f : fenetre graphique
	 * @param dossier : dossier des fichiers d'entrée
	 */
	public DataWorker(Fenetre f, File dossier) {
		super();
		this.f = f;
		this.dossier = dossier;
	}
	
	/**
	 * Affichage d'un message d'attente pendant le parsing.
	 * 
	 * @return 0 : Integer nécessaire à la fonction done()
	 * pour que le programme continue après le traitement des données.
	 * @see DataWorker#done()
	 */
	@Override
	public Integer doInBackground() {
		JTextArea text = f.getDataSelect().getVoieSelect().getZoneText();
		text.setPreferredSize(new Dimension(400,50));
		text.setText("Traitement des données en cours \n Veuillez patienter s'il vous plait");
		text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		f.getDataList().traitementData(f,dossier.getPath());
		return 0;
	}
	
	/**
	 * Une fois le parsing terminé, efface le message d'attente
	 * et active les boutons de la fenetre.
	 */
	@Override
	public void done() {
		int i = -1;
		try {
			i = get();
			if(i != 0) System.exit(0);
		}
		catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		catch (ExecutionException Ee) {
			Ee.getCause().printStackTrace();
		}
		f.getDataSelect().getButtonKit().activerB();
		JTextArea text = f.getDataSelect().getVoieSelect().getZoneText();
		text.setText("");
		text.setBackground(Color.GRAY);
    }   
}


				
