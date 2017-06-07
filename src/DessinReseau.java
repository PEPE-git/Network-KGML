package projet_POO;
/**
 * DessinReseau.java
 */
import java.awt.*;
import javax.swing.*;
import org.graphstream.ui.swingViewer.ViewPanel;

/**
 * DessinReseau correspond à la partie
 * de la fenetre dans laquelle est affiché
 * la représentation graphique de la voie.
 * 
 * La classe hérite de JPanel
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class DessinReseau extends JPanel {
	private Fenetre f;
	private JPanel panel;
	private JPanel legend;
	
	/**
	 * Constructeur DessinReseau
	 * 
	 * @param f : fenetre
	 */
	public DessinReseau(Fenetre f) {
		this.f=f;
		this.setBackground(Color.WHITE);
		this.add(panel = new JPanel());
	}
	
	/**
	 * Ajout du viewpanel du graph de la voie
	 * dans le JPanel de DessinReseau.
	 * 
	 * @param vp :viewpanel
	 */
	public void setDessin(ViewPanel vp) {
		int l = this.getWidth();
		int h = this.getHeight();
		
		vp.setPreferredSize(new Dimension(l,h));
		
		this.remove(panel);
		this.add(panel = new JPanel());
		this.panel.add(vp);
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Dans le cas de la représentation d'une voie spécifique,
	 * ajoute la légende dynamique à droite du graphe.
	 * 
	 * @param vp : viewpanel du graph
	 */
	public void setLegend(ViewPanel vp) {
		int nb = f.getDataSelect().getBactSelect().selectedBact().size();
		
		int l = this.getWidth()-this.getWidth()/4;
		int h = this.getHeight();
		
		vp.setPreferredSize(new Dimension(l,h));
		
		this.remove(panel);
		this.add(panel = new JPanel());
		panel.setBackground(Color.WHITE);
		this.panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 4;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.fill = GridBagConstraints.BOTH;
		
		this.panel.add(vp);
		
		
		legend = new JPanel();
		GridLayout legendLayout = new GridLayout(0,1);
		legend.setLayout(legendLayout);

		Font font = new Font("SANS_SERIF", Font.BOLD, 18);

		Label title1 = new Label("Nombre de Bactérie(s)");
        legend.add(title1); title1.setBackground(Color.black); title1.setForeground(Color.white); title1.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        Label title2 = new Label("sélectionnée(s) ayant");
        legend.add(title2); title2.setBackground(Color.black); title2.setForeground(Color.white); title2.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        Label title3 = new Label("cette réaction.");
        legend.add(title3); title3.setBackground(Color.black); title3.setForeground(Color.white); title3.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        
		Label zero = new Label(Integer.toString(0));
		legend.add(zero); zero.setBackground(Color.decode("#FFFFFF")); zero.setFont(font);
		if(((int) (0.1*nb - 0)) >= 1) {
			Label ten = new Label("]"+Integer.toString(0)+"-"+Integer.toString((int) (0.1*nb))+"]");
			legend.add(ten); ten.setBackground(Color.decode("#C0C0C0")); ten.setFont(font);
		}
		if(((int) (0.2*nb) - (int) (0.1*nb)) >= 1) {
			Label twenty = new Label("]"+Integer.toString((int)(0.1*nb))+"-"+Integer.toString((int)(0.2*nb))+"]");
			legend.add(twenty); twenty.setBackground(Color.decode("#CD853F")); twenty.setFont(font);
		}
		if(((int) (0.3*nb) - (int) (0.2*nb)) >= 1) {
			Label thirty = new Label("]"+Integer.toString((int)(0.2*nb))+"-"+Integer.toString((int)(0.3*nb))+"]");
			legend.add(thirty); thirty.setBackground(Color.decode("#FFFF00")); thirty.setFont(font);
		}
		if(((int) (0.4*nb) - (int) (0.3*nb)) >= 1) {
			Label fourty = new Label("]"+Integer.toString((int)(0.3*nb))+"-"+Integer.toString((int)(0.4*nb))+"]");
			legend.add(fourty); fourty.setBackground(Color.decode("#FF4500")); fourty.setFont(font);
		}
		if(((int) (0.5*nb) - (int) (0.4*nb)) >= 1) {
			Label fifty = new Label("]"+Integer.toString((int)(0.4*nb))+"-"+Integer.toString((int)(0.5*nb))+"]");
			legend.add(fifty); fifty.setBackground(Color.decode("#FF0000")); fifty.setFont(font);
		}
		if(((int) (0.6*nb) - (int) (0.5*nb)) >= 1) {
			Label sixty = new Label("]"+Integer.toString((int)(0.5*nb))+"-"+Integer.toString((int)(0.6*nb))+"]");
			legend.add(sixty); sixty.setBackground(Color.decode("#EE82EE")); sixty.setFont(font);
		}	
		if(((int) (0.7*nb) - (int) (0.6*nb)) >= 1) {
			Label seventy = new Label("]"+Integer.toString((int)(0.6*nb))+"-"+Integer.toString((int)(0.7*nb))+"]");
			legend.add(seventy); seventy.setBackground(Color.decode("#4169E1")); seventy.setFont(font);
		}
		if(((int) (0.8*nb) - (int) (0.7*nb)) >= 1) {
			Label eighty = new Label("]"+Integer.toString((int)(0.7*nb))+"-"+Integer.toString((int)(0.8*nb))+"]");
			legend.add(eighty); eighty.setBackground(Color.decode("#00FFFF")); eighty.setFont(font);
		}
		if(((int) (0.9*nb) - (int) (0.8*nb)) >= 1) {
			Label ninety = new Label("]"+Integer.toString((int)(0.8*nb))+"-"+Integer.toString((int)(0.9*nb))+"]");
			legend.add(ninety); ninety.setBackground(Color.decode("#00FF00")); ninety.setFont(font);
		}
		if((nb - (int) (0.9*nb)) >= 1) {
			Label ninetynine = new Label("]"+Integer.toString((int)(0.9*nb))+"-"+Integer.toString(nb)+"]");
			legend.add(ninetynine); ninetynine.setBackground(Color.decode("#FF1493")); ninetynine.setFont(font);
        }
		
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.panel.add(legend);
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Retourne le panel.
	 * 
	 * @return panel
	 * @see Jpanel
	 */
	public JPanel getPanel() {
		return panel;
	}
}
