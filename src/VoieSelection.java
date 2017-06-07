package projet_POO;
/**
 * VoieSelection.java
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * VoieSelection correspond à la zone graphique associée à la sélection
 * des voies disponibles dans le jeu de données sélectionné.
 * La classe hérite de JPanel.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class VoieSelection extends JPanel {
	private JLabel label;
	private JComboBox<String> list;
	private JTextArea text;

	/**
	 * Constructeur VoieSelection
	 * @param f : fenetre à laquelle est rattachée la classe
	 * @param l : vecteur contenant les noms des voies
	 */
	public VoieSelection(Fenetre f, String[] l) {
		this.setBackground(Color.GRAY);
		this.setLayout(new FlowLayout());
		this.add(label = new JLabel("Sélectionner la voie"));
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		this.add(list = new JComboBox<String>(l));
		this.list.setPreferredSize(new Dimension(400,20));
		list.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		list.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				f.getDataSelect().getBactSelect().updateBact(list.getSelectedItem().toString());
			}
		});
		this.add(text = new JTextArea("Veuillez sélectionner le dossier d'étude"));
		text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		text.setEditable(false);
	}
	
	/**
	 * Mise à jour de la liste des voies.
	 * 
	 * @param generalPathList : liste des noms des voies métaboliques
	 * à mettre à jour.
	 */
	public void updatePathway(List<Data> generalPathList) {
		String pathName [] =  new String[generalPathList.size()];
		for (int i = 0; i < generalPathList.size(); i++) pathName[i] = generalPathList.get(i).getName();
		Arrays.sort(pathName);
		list.setModel(new DefaultComboBoxModel<String>(pathName));
		list.revalidate();
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Retourne le nom de la voie sélectionnée par l'utilisateur.
	 * 
	 * @return list.getSelectedValuesList() : nom de la voie sélectionnée
	 */
	public String selectedPath() {
	 	return this.list.getSelectedItem().toString();
	}
	
	/**
	 * Retourne de la zone de texte.
	 * 
	 * @return getZoneText()
	 */
	public JTextArea getZoneText() {
		return this.text;
	}
}
