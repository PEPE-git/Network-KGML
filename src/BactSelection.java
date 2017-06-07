package projet_POO;
/**
 * BactSelection.java
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/**
 * BactSelection dans la fenetre graphique correspond à l'ensembe de la sélection de
 * bactéries possibles associées à la voie métabolique sélectionnée.
 * 
 * La classe hérite de JPanel et possède 4 attributs de spécialisation
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class BactSelection extends JPanel {

	private Fenetre f;
	private JLabel label;
	private JList<String> list;
	private JScrollPane scroll;
	
	/**
	 * Constructeur BactSelection
	 * 
	 * @param f : fenetre à laquelle est rattachée la classe
	 * @param l : vecteur contenant les noms des bactéries
	 */
	public BactSelection(Fenetre f,String[] l) {
		this.f=f;
		this.setBackground(Color.GRAY);
		this.setLayout(new FlowLayout());
		this.add(label = new JLabel("Sélectionner les organismes"));
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		this.add(list = new JList<String>(l));
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		this.add(scroll = new JScrollPane(list));
		this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
	}
	
	/**
	 * Mise à jour de la liste de bactérie
	 * 
	 * @param pathName : voie métabolique pour laquelle la nouvelle liste
	 * de bactéries doit être affichée.
	 */
	public void updateBact(String pathName) {
		List<Data> gp = f.getDataList().getGpList();
		this.remove(list);
		this.remove(scroll);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for (int i = 0; i < gp.size(); i++) {
			if(gp.get(i).getName().equals(pathName)) {
				model.addElement("- All - General map");
				for (int j = 0; j < ((GeneralPathway)gp.get(i)).getBacterieList().size(); j++) {
					model.addElement(((GeneralPathway)gp.get(i)).getBacterieList().get(j).getName());
				}
			}
		}
		String[] strings = new String[model.getSize()];
		for(int i=0;i<strings.length;i++){
			strings[i]=model.getElementAt(i).toString();
		}
		Arrays.sort(strings);
		list.setListData(strings);    
		
		this.add(scroll = new JScrollPane(list));
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Récupération de la liste des bactéries
	 * sélectionnées.
	 * 
	 * @return list.getSelectedValuesList() : liste des bactéries sélectionnées
	 */
	public List<String> selectedBact() {
		return list.getSelectedValuesList();
	}

}

