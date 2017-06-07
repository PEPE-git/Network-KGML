package projet_POO;
/**
 * Export.java
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Classe d'export de la representation
 * graphique de la voie sous forme d'image, dont
 * l'utilisateur choisit le nom, enregistrée dans 
 * le dossier à partir duquel à été lancé le programme. 
 *  
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class Export extends JFrame implements ActionListener, KeyListener {
	private Fenetre f;
	private JLabel label;
	private JTextField text;
	private JButton b;
	
	/**
     * Constructeur Export associé à une Fenetre passée en argument.
     * 
     * @param f : la fenetre graphique
     */
	public Export (Fenetre f) {
		this.f = f;
		this.setTitle("Nom fichier");
		this.setSize(200,100);
		this.setLocation(500,300);
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(3,1));
		
		this.add(label = new JLabel("Entrez un nom de fichier"));
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		c.add(label);
		
		this.add(text = new JTextField());
		text.setBackground(Color.WHITE);
		text.setPreferredSize(new Dimension(150,100));
		c.add(text);
		
		this.add(b = new JButton("Valider"));
		b.addActionListener(this);
		b.addKeyListener(this);
		c.add(b);
		this.setContentPane(c);
		this.setVisible(true);
	}
	
	
	/**
	 * Enregistrement de l'image de la voie (format .png).
	 * 
	 * @param fileName : nom de l'image choisi par l'utilisateur
	 */
	private void save(String fileName) {
		BufferedImage image;
		if (f.getDataSelect().getBactSelect().selectedBact().get(0).equals("- All - General map")){
			image = new BufferedImage(f.getDessin().getWidth(), f.getDessin().getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			f.getDessin().getPanel().printAll(g);
			g.dispose();
		}
		else {image = new BufferedImage(f.getDessin().getWidth()-f.getDessin().getWidth()/4, f.getDessin().getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			f.getDessin().getPanel().printAll(g);
			g.dispose();
		}
		try {
			ImageIO.write(image, "png", new File(fileName+".png"));
		}
		catch (IOException exp) {
			exp.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(this.text.getText());
		save(this.text.getText());
		this.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			save(this.text.getText());
			this.dispose();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}

