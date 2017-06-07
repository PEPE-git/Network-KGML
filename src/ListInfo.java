package projet_POO;
/**
 * ListInfo.java
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * ListInfo correspond à la zone graphique d'affichage des listes des 
 * composés et des réactions présents dans la voie en cours d'affichage.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
@SuppressWarnings("serial")
public class ListInfo extends JPanel implements MouseListener {
	private Fenetre f;
	private JList<String> infoR;
	private JList<String> infoC;
	
	/**
	 * Constructeur ListInfo.
	 * 
	 * @param f : Fenetre d'affichage
	 */
	public ListInfo(Fenetre f) {
		this.f = f;
		this.setLayout(new GridLayout(1,2));
		this.add(infoR = new JList<String>());
		this.add(infoC = new JList<String>());
		
		infoR.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		infoC.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		
		JScrollPane scrollR = new JScrollPane(infoR);
		scrollR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane scrollC = new JScrollPane(infoC);
		scrollC.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollR);
		this.add(scrollC);
		
		
		MouseListener mouseListenerR = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int k = -1; int i =0;
					while (k==-1 && i < f.getDataList().getRList().size()) {
						if(infoR.getSelectedValue().toString().equals(f.getDataList().getRList().get(i).getId())) k=i;
						else i++;
					} // k : indice de la réaction dans la liste info
					int j = 0; int l = -1;
					while(j < f.getDataList().getGpList().size() && l == -1) {
						if(f.getDataList().getGpList().get(j).getName().equals(f.getDataSelect().getVoieSelect().selectedPath())) l=j;
						else j++;
					} // l : indice de la voie métabolique dans la liste générale
					int m = 0; int n = -1;
					while(m < ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().size() && n == -1) {
						if(((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(m).getId().equals(f.getDataList().getRList().get(k).getId())) n=m;
						else m++;
					} // n : indice de la réaction dans la liste de réaction de la voie
					String bactReac = new String("BACTERIE(S) :\n");
					for (i = 0; i < ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().size(); i++){
						bactReac += "\t"+((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().get(i).getName()+"\n";				
					}
					String bactSelec = new String("BACTERIE(S) SELECTIONNEES :\n");
					for (i = 0; i < f.getDataSelect().getBactSelect().selectedBact().size(); i++) {
						j =0;
						while(j < ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().size()) {
							if (f.getDataSelect().getBactSelect().selectedBact().get(i).equals(((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().get(j).getName())) bactSelec += "\t"+((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().get(j).getName()+"\n";
							j++;
						}
					}
					f.getDataSelect().getBarre().setTextBarre(((ReactionCaracteristic)f.getDataList().getRList().get(k)).afficher()+bactSelec+bactReac);
				}
			}
		};
		infoR.addMouseListener(mouseListenerR);
		
		KeyListener keyListenerR = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (!infoR.isSelectionEmpty()) {
						int k = -1; int i =0;
						while (k==-1 && i < f.getDataList().getRList().size()) {
							if(infoR.getSelectedValue().toString().equals(f.getDataList().getRList().get(i).getId())) k=i;
							else i++;
						}
						int j = 0; int l = -1;
						while(j < f.getDataList().getGpList().size() && l == -1) {
							if(f.getDataList().getGpList().get(j).getName().equals(f.getDataSelect().getVoieSelect().selectedPath())) l=j;
							else j++;
						}
						int m = 0; int n = -1;
						while(m < ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().size() && n == -1) {
							if(((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(m).getId().equals(f.getDataList().getRList().get(i).getId())) n=m;
							else m++;
						}
						String bactReac = new String("BACTERIE(S) :\t");
						for (int i2 = 0; i2 < ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().size(); i2++){
							bactReac += ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().get(i2).getName()+"\n\t";				
						}
						
						String bactSelec = new String("BACTERIE(S) SELECTIONNEES :\n");
                        if(!f.getDataSelect().getBactSelect().selectedBact().isEmpty()) {
	                        for (i = 0; i < f.getDataSelect().getBactSelect().selectedBact().size(); i++) {
	                            j =0;
	                            while(j < ((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().size()) {
	                                if (f.getDataSelect().getBactSelect().selectedBact().get(i).equals(((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().get(j).getName())) bactSelec += "\t"+((GeneralPathway)f.getDataList().getGpList().get(l)).getReactionList().get(n).getBact().get(j).getName()+"\n";
	                                j++;
	                            }
	                        }
                        }
                        f.getDataSelect().getBarre().setTextBarre(((ReactionCaracteristic)f.getDataList().getRList().get(k)).afficher()+bactSelec+bactReac);
					}
				}
			}
		};
		infoR.addKeyListener(keyListenerR);
		
		MouseListener mouseListenerC = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (f.getDataSelect().getButtonKit().getB3().isSelected()){
						int k = -1; int i =0;
						while (k==-1 && i < f.getDataList().getCList().size()) {
							if(infoC.getSelectedValue().toString().equals(f.getDataList().getCList().get(i).getName())) k=i;
							else i++;
						}
						f.getDataSelect().getBarre().setTextBarre(((CompoundCaracteristic)f.getDataList().getCList().get(k)).afficher());
					}
					else {
						int k = -1; int i =0;
						while (k==-1 && i < f.getDataList().getCList().size()) {
							if(infoC.getSelectedValue().toString().equals(f.getDataList().getCList().get(i).getId())) k=i;
								else i++;
							}
							f.getDataSelect().getBarre().setTextBarre(((CompoundCaracteristic)f.getDataList().getCList().get(k)).afficher());
						}
				}
			}
		};
		infoC.addMouseListener(mouseListenerC);		
		
		KeyListener keyListenerC = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (f.getDataSelect().getButtonKit().getB3().isSelected()){
						int k = -1; int i =0;
						while (k==-1 && i < f.getDataList().getCList().size()) {
							if(infoC.getSelectedValue().toString().equals(f.getDataList().getCList().get(i).getName())) k=i;
							else i++;
						}
						f.getDataSelect().getBarre().setTextBarre(((CompoundCaracteristic)f.getDataList().getCList().get(k)).afficher());
					}
					else {
						int k = -1; int i =0;
						while (k==-1 && i < f.getDataList().getCList().size()) {
							if(infoC.getSelectedValue().toString().equals(f.getDataList().getCList().get(i).getId())) k=i;
							else i++;
						}
						f.getDataSelect().getBarre().setTextBarre(((CompoundCaracteristic)f.getDataList().getCList().get(k)).afficher());
					}
				}
			}
		};
		infoC.addKeyListener(keyListenerC);
	}
	
	/**
	 * Définition des listes de réactions et composés à afficher dans la
	 * ListInfo de la fenetre graphique à partir d'une liste de réactions
	 * fournie en paramètre.
	 * Affichage des composés par leur nom ou leur identifiant selon l'état
	 * d'activation du CheckButton.
	 * 
	 * @param reactList : liste de réactions associées à la voie en 
	 * cours d'affichage
	 */
	public void setListInfo(List<Reaction> reactList) {

		DefaultListModel<Object> modelC = new DefaultListModel<Object>();
		DefaultListModel<Object> modelR = new DefaultListModel<Object>();

		if (!f.getDataSelect().getButtonKit().getB3().isSelected()){
			for (int i = 0; i < reactList.size(); i++) {
				modelR.addElement(reactList.get(i).getId());
				for (int j = 0; j < reactList.get(i).getSubstrate().size();j++) {
					if(!modelC.contains(reactList.get(i).getSubstrate().get(j))) modelC.addElement(reactList.get(i).getSubstrate().get(j));
				}
				for (int j = 0; j < reactList.get(i).getProduct().size();j++) {
					if(!modelC.contains(reactList.get(i).getProduct().get(j))) modelC.addElement(reactList.get(i).getProduct().get(j));
				}
			}			
		}
		else {
			for (int i = 0; i < reactList.size(); i++) {
				modelR.addElement(reactList.get(i).getId());
				for (int j = 0; j < reactList.get(i).getSubstrate().size();j++) {
					String substrateID=reactList.get(i).getSubstrate().get(j);
					int k=-1; int l=0;
					while (k==-1 && l < f.getDataList().getCList().size()) {
						if(substrateID.equals(f.getDataList().getCList().get(l).getId())) k=l;
						else l++;
					}
					if (!modelC.contains(f.getDataList().getCList().get(k).getName())){
						modelC.addElement(f.getDataList().getCList().get(k).getName());
					}
				}
				for (int j = 0; j < reactList.get(i).getProduct().size();j++) {
					String productID=reactList.get(i).getProduct().get(j);
					int k=-1; int l=0;
					while (k==-1 && l < f.getDataList().getCList().size()) {
						if(productID.equals(f.getDataList().getCList().get(l).getId())) k=l;
						else l++;
					}
					if (!modelC.contains(f.getDataList().getCList().get(k).getName())){
						modelC.addElement(f.getDataList().getCList().get(k).getName());
					}
				}
			}
		}
		
		String[] stringsC = new String[modelC.getSize()];
		for(int i=0;i<stringsC.length;i++){
			stringsC[i]=modelC.getElementAt(i).toString();
		}
		Arrays.sort(stringsC);
		infoC.setListData(stringsC);    
		
		String[] stringsR = new String[modelR.getSize()];
		for(int i=0;i<stringsR.length;i++){
			stringsR[i]=modelR.getElementAt(i).toString();
		}
		Arrays.sort(stringsR);
		infoR.setListData(stringsR);    

		
		
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
