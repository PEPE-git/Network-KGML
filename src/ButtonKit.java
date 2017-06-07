package projet_POO;
/**
 * ButtonKit.java
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * ButtonKit définit dans l'interface graphique la zone des bouttons
 * permettant la visualisation des données choisies par l'utilisateur.
 * 
 * Elle hérite de JPanel et implémente les interfaces ActionListener et
 * KeyListener.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class ButtonKit extends JPanel implements ActionListener, KeyListener {
	private Fenetre f;
	private JButton b1;
	private JButton b2;
	private JCheckBox b3;
	
	/**
	 * Constructeur ButtonKit
	 * 
	 * @see JButton
	 * @see KeyListener
	 * @see ActionListener
	 */
	public ButtonKit(Fenetre f) {
		this.f = f;
		this.setBackground(Color.GRAY);
		
		this.add(b1 = new JButton("Lancer"));
		this.b1.addActionListener(this);
		this.b1.addKeyListener(this);
		this.b1.setEnabled(false);
		
		this.add(b2 = new JButton("Exporter"));
		this.b2.addActionListener(this);
		this.b2.addKeyListener(this);
		this.b2.setEnabled(false);
		
		String titleCheckBox = "Affichage du nom complet des composés";
		this.add(b3 = new JCheckBox(titleCheckBox, false));
		b3.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
		b3.setBackground(Color.GRAY);
		this.b3.addActionListener(this);
		this.b3.addKeyListener(this);
		this.b3.setEnabled(false);
	}
	
	/**
	 * Capture et comportement à adopter suite à une interaction clavier
	 * avec le bouton (touche ENTER uniquement).
	 * 
	 * @param e : événement
	 * @see KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (e.getSource() == b1) drawGraph();
			else if (e.getSource() == b2) new Export(f);
			else if (e.getSource() == b3) b3.setSelected(true);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Capture et comportement à adopter suite à un clic souris sur un
	 * des boutons.
	 * 
	 * @param e : événement
	 * @see ActionEvent
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) drawGraph();
		else if (e.getSource() == b2) new Export(f);
	}
	
	
	public void drawGraph() {
		if (!f.getDataSelect().getBactSelect().selectedBact().isEmpty()) {
			String ask = f.getDataSelect().getBactSelect().selectedBact().get(0);
			int i = 0; int k = -1;
			while(i < f.getDataList().getGpList().size() && k == -1) {
				if(f.getDataList().getGpList().get(i).getName().equals(f.getDataSelect().getVoieSelect().selectedPath())) k=i;
				else i++;
			}
			
			f.getDataSelect().getListInfo().setListInfo(((GeneralPathway)f.getDataList().getGpList().get(k)).getReactionList());
				if(ask.equals("- All - General map")) {
				// Reseau Global
				if(k==-1) System.out.println("PROBLEME");
				else f.getDessin().setDessin(((GeneralPathway)f.getDataList().getGpList().get(k)).draw(f));
			}
			else {
				// Réseau spécifique
				List<String> bactName = new ArrayList<String>();
				bactName = f.getDataSelect().getBactSelect().selectedBact();
				
				List<Bacterie> bactList = new ArrayList<Bacterie>();
				for(int l = 0; l < bactName.size(); l++) {
					
					for (int j = 0; j < ((GeneralPathway)f.getDataList().getGpList().get(k)).getBacterieList().size(); j++) {
						
						if(bactName.get(l).equals(((GeneralPathway)f.getDataList().getGpList().get(k)).getBacterieList().get(j).getName())) {
							bactList.add(((GeneralPathway)f.getDataList().getGpList().get(k)).getBacterieList().get(j));
						}
					}
				}
				SpecificPathway sp = ((GeneralPathway)f.getDataList().getGpList().get(k)).specificPathwayConstruction(bactList);
				f.getDessin().setDessin(sp.draw(f));
				f.getDessin().setLegend(sp.draw(bactName,f));
			}
		}
	}		
	
	/**
	 * Retourne le bouton de lancement de graphique.
	 * 
	 * @return b1 
	 */
	public JButton getB1() {
		return this.b1;
	}
	
	/**
	 * Activation du bouton de lancement de graphique.
	 */
	public void activerB1() {
		this.b1.setEnabled(true);
	}

	/**
	 * Activation de l'ensemble des boutons.
	 */
	public void activerB() {
		activerB1();
		activerB2();
		activerB3();
		this.revalidate();
		this.repaint();
	}

	/**
	 * Retourne le bouton d'export.
	 * 
	 * @return b2 
	 */
	public JButton getB2() {
		return this.b2;
	}

	/**
	 * Activation du bouton d'export.
	 */
	public void activerB2() {
		this.b2.setEnabled(true);
	}
	
	/**
	 * Retourne la checkbox pour afficher les noms complets des
	 * composés.
	 *  
	 * @return b2 
	 */
	public JCheckBox getB3() {
		return this.b3;
	}
	
	/**
	 * Activation de la checkbox.
	 */
	public void activerB3() {
		this.b3.setEnabled(true);
	}	
}
