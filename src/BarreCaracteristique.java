package projet_POO;
/**
 * BarreCaracteristique.java
 */
import java.awt.*;
import javax.swing.*;

/**
 * BarreCaracteristique définit dans l'interface graphique la zone 
 * d'affichage des caractéristiques des éléments de la voie métabolique
 * en cours de visualisation.
 * 
 * Elle hérite de JPanel.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class BarreCaracteristique extends JPanel {
	private Fenetre f;
	private JTextArea caract;
	private JScrollPane scroll;
	
	/**
     * Constructeur BarreCaracteristique
     * 
     * @param f : Fenetre à laquelle la Barre Caractéristique est associée.
     */
	public BarreCaracteristique(Fenetre f) {
		this.setF(f);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout());
		
		this.caract = new JTextArea("");
		this.caract.setEditable(false);
		
		scroll = new JScrollPane(caract);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scroll);
	}
	
	/**
	 * Retourne le texte contenu dans la zone de texte.
	 * 
	 * @return caract : caracteristiques des éléments
	 */
	public JTextArea getTextBarre() {
		return caract;
	}
	
	/**
	 * Modification du contenu de la zone de texte.
	 * 
	 * @param s : texte à inclure dans la zone de texte
	 */
	public void setTextBarre(String s) {
		caract.setText(s);
		caract.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
	}

	/**
	 * Retourne la fenetre dans laquelle est la Barre.
	 * 
	 * @return f : fenetre
	 */
	public Fenetre getF() {
		return f;
	}

	/**
	 * Fait de la Fenetre f la fenetre dans laquelle sera la Barre
	 * 
	 * @param f : fenetre à définir
	 */
	public void setF(Fenetre f) {
		this.f = f;
	}
}
