package projet_POO;
/**
 * MenuPrincipal.java
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * MenuPrincipal définit dans l'interface graphique la zone de menu
 * (Lancement, Sortie et Aide).
 * Elle hérite de JPanel et implémente ActionListener.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel implements ActionListener {
	
	private Fenetre f;
	private JMenuBar mainMenu;
	private JMenu carte;
	private JMenu aide;
	private JMenuItem debut;
	private JMenuItem exit;
	private JMenuItem info;
	
	/**
     * Constructeur MenuPrincipal
     * 
     * @param f : Fenetre à laquelle elle est associée
     */
	public MenuPrincipal(Fenetre f) {
		this.f = f;
		
		this.add(mainMenu = new JMenuBar());
		this.mainMenu.setLayout(new GridLayout(1,10));

		
		this.add(carte = new JMenu("Cartes Métaboliques"));
		this.add(aide = new JMenu("Aide"));
		mainMenu.add(carte);
		mainMenu.add(aide);
		
		this.add(debut = new JMenuItem("Démarrer"));
		debut.addActionListener(this);
		this.add(exit = new JMenuItem("Quitter"));
		exit.addActionListener(this);
		carte.add(debut);
		carte.add(exit);
		
		this.add(info = new JMenuItem("Infos..."));
		info.addActionListener(this);
		aide.add(info);
	}
	
	
	/**
	 * Réponse à l'action de l'utilisation sur le menu. Selon le bouton :
	 * - JFileChooser pour sélectionner les données d'entrée
	 * - Fermeture du programme
	 * - Affichage de la fenetre d'aide
	 * 
	 * @param e : clique de la souris
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.debut) {
			JFileChooser fileC = new JFileChooser();
			fileC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (fileC.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
				File dossier = fileC.getSelectedFile();
				String[] dossierList = dossier.list();
				boolean bact = false; boolean comp = false; boolean reac = false;
				for (int i = 0; i < dossierList.length; i++) {
					if(dossierList[i].equals("bacteria")) bact = true;
					if(dossierList[i].equals("compounds")) comp = true;
					if(dossierList[i].equals("reactions")) reac = true;
				}
				if(bact&&comp&&reac) {
					DataWorker dw = new DataWorker(f,dossier);
					dw.execute();
				}
			}
		}
		if (e.getSource() == this.exit) System.exit(0);
		if (e.getSource() == this.info) {
			JFrame f = new JFrame("Message Aide : Utilisation");
			f.setSize(750,710);
			f.setLocation(400,300);
			
			Container c = f.getContentPane();
			JTextArea aideMessage = new JTextArea(" Bienvenue dans NetWork Vizualisation\n "
					+ "\n Pour commencer cliquez sur Cartes Métaboliques -> Démarrer puis sélectionnez le dossier contenant \n les dossiers  /bacteria/,  /reactions/ et  /compounds/.\n "
					+ "\n Une fois le chargement terminé, sélectionnez une voie métabolique dans le menu déroulant en haut à gauche."
					+ "\n Puis sélectionnez 'All - General map' si vous souhaitez visualiser la carte générique de cette voie, ou \n sélectionnez un ensemble de bactéries "
					+ "possédants la voie si vous souhaitez visualiser la carte spécifique \n pour les bactéries sélectionnées."
					+ "\n Vous pouvez cochez la CheckBox si vous souhaitez voir les noms des composés plutôt que leur identifiant."
					+ "\n Enfin cliquez sur Lancer !\n "
					+ "\n Le graphe bipartite s'affiche dans la zone de dessin. Les composés (substrats/produits) des réactions \n sont représentés par des cercles, le nom/Id du composé est écrit en dessous du cercle. "
					+ "\n Les Enzymes catalysant les réactions sont représentées par leur identifiant dans un rectangle. "
					+ "\n Si la réaction est irréversible, le lien entre l'enzyme et la réaction sera fléché."
					+ "\n Si vous visualisez une carte spécifique, les réactions seront colorées en fonction du nombre de fois \n qu'elles sont retrouvées dans les bactéries sélectionnées pour construire la carte spécifique."
					+ "\n Vous pouvez retrouver grâce à la légende une approximation du nombre de fois que la réaction \n est retrouvée dans les bactéries sélectionnées."
					+ "\n En bas à gauche, vous trouverez la liste des réactions et la liste des composés de la voie métabolique \n sélectionnée."
					+ "\n Double cliquez sur une réaction ou un composé afin d'obtenir d'avantage d'informations pertinentes.\n "
					+ "\n Il est possible de zoomer grâce aux touches page-up et page-down. "
					+ "\n Appuyez sur Shift-R pour restaurer la vue par défaut."
					+ "\n Une fois satisfait de la vue du graphe, cliquez sur Export et rentrez le nom de l'image souhaité."
					+ "\n L'image sera enregistrée au format PNG dans le dossier de lancement du programme.\n"
					+ "\n Une fois l'analyse finie, fermez le programme ou cliquez sur Cartes Métaboliques -> Quitter.\n "
					+ "\n Merci d'utiliser NetWork Vizualisation.\n Bonne Analyse !");
			aideMessage.setBackground(Color.WHITE);
			aideMessage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
			aideMessage.setForeground(Color.BLACK);
			
			c.add(aideMessage);
			f.setContentPane(c);
			f.setVisible(true);
		}
	}
}
