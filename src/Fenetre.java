package projet_POO;
/**
 * Fenetre.java
 */
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Fenetre Graphique, classe lien entre éléments graphiques et donnéees.
 * 
 * La classe hérite de JFrame.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	private DataList data;
	
	private Container c;
	private MenuPrincipal menuBar;
	private DataSelection s;
	private DessinReseau reseau;
	
	/**
     * Constructeur Fenetre
     * 
     * Positionnement des éléments graphiques
     */
	public Fenetre() {
		JFrame f = new JFrame("Réseau Métabolique");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(2000,1600);

		this.c = f.getContentPane();
		c.setLayout(new BorderLayout());
		
		this.data = new DataList();
		
		// Nord : le menu
		this.menuBar = new MenuPrincipal(this);
		c.add(menuBar,"North");
		
		// Dans la partie haute on veut les 2 listes
		String[] listeTest1 = {}; 
		String[] listeTest2 = {}; 
		this.s = new DataSelection(this,listeTest1,listeTest2);
		c.add(s,"West");
		
		// Dans la partie centrale le dessin du réseau métabolique
		this.reseau = new DessinReseau(this);
		c.add(reseau,"Center");
		
		f.setContentPane(c);
		f.setVisible(true);
	}
	
	/**
	 * Retourne la zone liste de l'élément souhaité
	 * (composés, réactions, voies).
	 * 
	 * @return data
	 */
	public DataList getDataList() {
		return this.data;
	}
	
	/**
	 * Retourne la zone graphique contenant l'ensemble des données.
	 * 
	 * @return s
	 */	
	public DataSelection getDataSelect() {
		return this.s;
	}
	
	/**
	 * Retourne la zone graphique correspondant à la sélection de voie.
	 * par l'utilisateur 
	 * 
	 * @return s.selectedPath()
	 */
	public String selectedPath() {
		return this.s.selectedPath();
	}
	
	/**
	 * Retourne la zone de dessin des voies.
	 * 
	 * @return reseau
	 */
	public DessinReseau getDessin() {
		return this.reseau;
	}
}

