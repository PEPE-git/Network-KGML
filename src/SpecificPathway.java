package projet_POO;
/**
 * SpecificPathway.java
 */
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.graph.implementations.MultiGraph;


/**
 * SpecificPathway définit une voie métabolique spécifique à des bactéries sélectionnées.
 * La classe hérite de GeneralPathway et possède en plus une liste "nb" 
 * correspondant pour chaque réaction de la voie, au nombre de bactéries 
 * sélectionnées qui la possède.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class SpecificPathway extends GeneralPathway {
	private List<Float> nb;
	
	/**
     * Constructeur SpecificPathway.
     * 
     * @param gp : la voie générale dont on veut la voie spécifique.
     * @param nb : une liste de float correspondant pour chaque réaction de la voie,
 	 * le nombre de fois qu'elle apparaît en tant que gène dans les réactions des
	 * bactéries sélectionnées.
     * 
     * @see SpecificPathway#nb
     */
	public SpecificPathway(GeneralPathway gp, List<Float> nb) {
		super(gp.id, gp.name, gp.reactionList, gp.bacterieList);
		this.nb = nb;
	}
	
	/**
     * Construction du graphique réseau correspondant la voie spécifique.
     * 
     * @param bactList : la liste de bactéries à partir de laquelle il faut construire la voie
     * @param f : fenetre graphique
     * 
     * @see GeneralPathway#viewerGraph()
     */
	public ViewPanel draw(List<String> bactList, Fenetre f) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		float max = bactList.size();
		Graph graph = new MultiGraph(name);
		Viewer viewer = this.viewerGraph(graph,max,f);
		ViewPanel viewpanel = viewer.addDefaultView(false);
		return viewpanel;
	}
	
	/**
     * Construction du viewer du graphique réseau de la voie métabolique
     * spécifique.
     * 
     * @param graph : le graphe de la voie spécifique
	 * @return Le Viewer du graphe pour créer ensuite le ViewPanel
     * @see GeneralPathway#draw()
     */
	private Viewer viewerGraph (Graph graph, float max, Fenetre f) {
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet",
			"edge {fill-color: black; }");
		graph.addAttribute("ui.stylesheet",
			"graph { padding: 40px; }");
		graph.addAttribute("ui.stylesheet",
			"node {text-alignment: under;" 
			+"padding: 3px, 1px;" 
			+"text-background-mode: plain;" 
			+"text-size : 11;"
			+"text-background-color: white;"
			+"fill-color: white;"
			+"stroke-mode: plain;}");
		graph.addAttribute("ui.stylesheet",
			"node.reac {text-alignment: center;"
			+"text-background-mode: plain;" 
			// +"text-background-color: white;"
			+"text-size: 11;" 
			+"shape: rounded-box;" 
			+"padding: 3px, 1px;" 
			+"text-padding: 2px, 0px;"
			+"text-style: bold;"
			+"fill-color: white;" 
			+"stroke-mode: plain; "
			+"size-mode: fit;}");

		for (int i = 0; i < reactionList.size(); i++ ) {
			Node nodereac;
			String reacID = reactionList.get(i).getId();
			if (graph.getNode(reacID)==null) {
				graph.addNode(reacID);
				nodereac = graph.getNode(reacID);
				nodereac.setAttribute("ui.label", reacID);
			}
			else nodereac = graph.getNode(reacID);
			nodereac.addAttribute("ui.class", "reac");
			
			int x = (int)(nb.get(i)/max*100);
            if (x==0.0) nodereac.addAttribute("ui.style", "text-background-color : #FFFFFF; text-padding: 3px, 1px;");
            else if (x <= 10) nodereac.addAttribute("ui.style", "text-background-color : #C0C0C0; text-padding: 3px, 1px;");
            else if (x <= 20) nodereac.addAttribute("ui.style", "text-background-color : #CD853F; text-padding: 3px, 1px;");
            else if (x <= 30) nodereac.addAttribute("ui.style", "text-background-color : #FFFF00; text-padding: 3px, 1px;");
            else if (x <= 40) nodereac.addAttribute("ui.style", "text-background-color : #FF4500; text-padding: 3px, 1px;");
            else if (x <= 50) nodereac.addAttribute("ui.style", "text-background-color : #FF0000; text-padding: 3px, 1px;");
            else if (x <= 60) nodereac.addAttribute("ui.style", "text-background-color : #EE82EE; text-padding: 3px, 1px;");
            else if (x <= 70) nodereac.addAttribute("ui.style", "text-background-color : #4169E1; text-padding: 3px, 1px;");
            else if (x <= 80) nodereac.addAttribute("ui.style", "text-background-color : #00FFFF; text-padding: 3px, 1px;");
            else if (x <= 90) nodereac.addAttribute("ui.style", "text-background-color : #00FF00; text-padding: 3px, 1px;");
            else if (x <= 100) nodereac.addAttribute("ui.style", "text-background-color : #FF1493; text-padding: 3px, 1px;");
			
			
			
			
			List<Node> nodeprodL = new ArrayList<Node>();
			if (!f.getDataSelect().getButtonKit().getB3().isSelected()){
				for (int j = 0; j < reactionList.get(i).getProduct().size(); j++ ) {
					if (graph.getNode(reactionList.get(i).getProduct().get(j))==null) {
						graph.addNode(reactionList.get(i).getProduct().get(j));
						nodeprodL.add(graph.getNode(reactionList.get(i).getProduct().get(j)));
						Node lastnode = nodeprodL.get(nodeprodL.size()-1);
						lastnode.setAttribute("ui.label", reactionList.get(i).getProduct().get(j));
					}
					else nodeprodL.add(graph.getNode(reactionList.get(i).getProduct().get(j)));
				}
			}
			else{
				for (int j = 0; j < reactionList.get(i).getProduct().size(); j++ ) {
					if (graph.getNode(reactionList.get(i).getProduct().get(j))==null) {
						graph.addNode(reactionList.get(i).getProduct().get(j));
						nodeprodL.add(graph.getNode(reactionList.get(i).getProduct().get(j)));
						Node lastnode = nodeprodL.get(nodeprodL.size()-1);
						String productID=reactionList.get(i).getProduct().get(j);
						int k=-1; int l=0;
						while (k==-1 && l < f.getDataList().getCList().size()) {
							if(productID.equals(f.getDataList().getCList().get(l).getId())){
								k=l;
								lastnode.setAttribute("ui.label", f.getDataList().getCList().get(k).getName());
							}
							else l++;
						}
						
					}
					else nodeprodL.add(graph.getNode(reactionList.get(i).getProduct().get(j)));
				}
				
			}
			
			List<Node> nodesubL = new ArrayList<Node>();
			if (!f.getDataSelect().getButtonKit().getB3().isSelected()){
				for (int j = 0; j < reactionList.get(i).getSubstrate().size(); j++ ) {
					if (graph.getNode(reactionList.get(i).getSubstrate().get(j))==null) {
						graph.addNode(reactionList.get(i).getSubstrate().get(j));
						nodesubL.add(graph.getNode(reactionList.get(i).getSubstrate().get(j)));
						Node lastnode = nodesubL.get(nodesubL.size()-1);
						lastnode.setAttribute("ui.label", reactionList.get(i).getSubstrate().get(j));
					}
					else nodesubL.add(graph.getNode(reactionList.get(i).getSubstrate().get(j)));
				}
			}
			else{
				for (int j = 0; j < reactionList.get(i).getSubstrate().size(); j++ ) {
					if (graph.getNode(reactionList.get(i).getSubstrate().get(j))==null) {
						graph.addNode(reactionList.get(i).getSubstrate().get(j));
						nodesubL.add(graph.getNode(reactionList.get(i).getSubstrate().get(j)));
						Node lastnode = nodesubL.get(nodesubL.size()-1);
						String substrateID=reactionList.get(i).getSubstrate().get(j);
						int k=-1; int l=0;
						while (k==-1 && l < f.getDataList().getCList().size()) {
							if(substrateID.equals(f.getDataList().getCList().get(l).getId())){
								k=l;
								lastnode.setAttribute("ui.label", f.getDataList().getCList().get(k).getName());
							}
							else l++;
						}		
					}
					else nodesubL.add(graph.getNode(reactionList.get(i).getSubstrate().get(j)));
				}
				
			}

						
			
			for (int j = 0; j < nodeprodL.size(); j++ ) {
				if (reactionList.get(i).getType().equals("irreversible")) {
					if (graph.getEdge(reactionList.get(i).getId()+nodeprodL.get(j).toString()) == null) graph.addEdge(reactionList.get(i).getId()+nodeprodL.get(j).toString(), nodereac, nodeprodL.get(j), true);
				}
				else {
					if (graph.getEdge(reactionList.get(i).getId()+nodeprodL.get(j).toString()) == null) graph.addEdge(reactionList.get(i).getId()+nodeprodL.get(j).toString(), nodereac, nodeprodL.get(j));
				}
			}

			for (int j = 0; j < nodesubL.size(); j++ ) {
				if (graph.getEdge(nodesubL.get(j).toString()+reactionList.get(i).getId()) == null) graph.addEdge(nodesubL.get(j).toString()+reactionList.get(i).getId(), nodesubL.get(j), nodereac);
			}
		}

		viewer.enableAutoLayout();
		return viewer;
	}
	
}
