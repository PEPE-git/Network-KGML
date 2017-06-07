package projet_POO;
/**
 * DataSelection.java
 */
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * DataSelection représente dans l'interface graphique la zone de sélection
 * des données et choix de l'utilisateur.
 * 
 * La classe hérite de JPanel
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class DataSelection extends JPanel {
	private VoieSelection voie;
	private BactSelection bact;
	private ButtonKit b;
	private ListInfo li;
	private BarreCaracteristique bc;
	
	/**
	 * Constructeur DataSelection
	 * 
	 * @param f : fenetre
	 * @param l1 : liste des voies
	 * @param l2 : liste des bactéries
	 */
	public DataSelection(Fenetre f, String[] l1, String[] l2) {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(500, 150));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(new GridLayout(5,1,10,10));
		this.add(voie = new VoieSelection(f,l1));
		this.add(bact = new BactSelection(f,l2));
		this.add(b = new ButtonKit(f));
		this.add(li = new ListInfo(f));
		this.add(bc = new BarreCaracteristique(f));
	}
	
	/**
	 * Retourne l'ensemble des voies disponibles.
	 * 
	 * @return voie : liste des voies
	 */
	public VoieSelection getVoieSelect() {
		return this.voie;
	}

	/**
	 * Retourne la liste de bactéries sélectionnées.
	 * 
	 * @return bact : liste des noms de bactéries sélectionnées
	 */
	public BactSelection getBactSelect() {
		return this.bact;
	}
	
	
	/**
	 * Retourne la voie sélectionnée par l'utilisateur.
	 * 
	 * @return voie.selectedPath() : voie sélectionnée
	 */
	public String selectedPath() {
		return this.voie.selectedPath();
	}
	
	/**
	 * Retourne la zone de boutons.
	 * 
	 * @return b
	 * @see ButtonKit
	 */
	public ButtonKit getButtonKit() {
		return this.b;
	}
	
	/**
	 * Retourne la zone de listes d'information.
	 * 
	 * @return li
	 * @see ListInfo
	 */
	public ListInfo getListInfo() {
		return this.li;
	}
	
	/**
	 * Retourne la zone de texte où s'incrivent les informations demandées.
	 * 
	 * @return bc
	 * @see BarreCaracteristic
	 */
	public BarreCaracteristique getBarre() {
		return bc;
	}
}
