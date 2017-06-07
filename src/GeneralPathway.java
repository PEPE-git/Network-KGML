package projet_POO;
/**
 * GeneralPathway.java
 */
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

/**
 * GeneralPathway définit une voie métabolique générale construite à partir
 * des informations pour l'ensemble des bactéries ayant cette voie.
 * 
 * La classe hérite de Data.
 * 
 * @author      Ophélie Da Silva
 * @author      Pierre Merckaert
 * @since       1.0
 */
public class GeneralPathway extends Data {
	protected List<Reaction> reactionList; // Ensemble des réactions répertoriées dans les bactéries sélectionnées pour la voie
	protected List<Bacterie> bacterieList; // Ensemble des bactéries qui ont cette voie
	
	/**
     * Constructeur GeneralPathway.
     * 
     * @param reactionList : liste des réactions de la voie
     * @param bacterieList : liste des bactéries ayant la voie
     */
	public GeneralPathway(String id, String name, List<Reaction> reactionList, List<Bacterie> bacterieList) {
		super.id = id;
		super.name = name;
		this.reactionList = reactionList;
		this.bacterieList = bacterieList;
	}
	
	/**
     * Constructeur GeneralPathway.
     * 
     * @param g : voie générale
     */
	public GeneralPathway(GeneralPathway g) {
		this.id = g.id;
		this.name = g.name;
		this.reactionList = g.reactionList;
		this.bacterieList = g.bacterieList;
	}
	
	/**
     * Retourne un ViewPanel correspondant au graphique réseau.
     * 
     * @param f : fenetre graphique.
     * @see GeneralPathway#viewerGraph()
     */
	public ViewPanel draw(Fenetre f) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new MultiGraph(name);
		Viewer viewer = this.viewerGraph(graph,f);
		ViewPanel viewpanel = viewer.addDefaultView(false);
		return viewpanel;
	}
	
	/**
	 * Teste si la voie existe dans la liste des voies fournie en paramètre.
	 * Retourne l'indice dans la liste de voie, ou -1 si elle en est absente.
	 * 
	 * @param gpList : liste des voies
	 * @return index : indice de la voie si elle existe, -1 sinon.
	 */
	public int exist(List<Data> gpList) {
		int index = -1;
		int i = 0;
		while (index == -1 && i < gpList.size()) {
			if(this.id.equals(gpList.get(i).getId())) index =  i;
			else i++;
		}
		return index;
	}
	
	/**
	 * Retourne l'id de la voie.
	 * 
	 * @return id
	 */
	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * Retourne le nom de la voie.
	 * 
	 * @return name
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Retourne la liste des réactions de la voie.
	 * 
	 * @return reactionList
	 */
	public List<Reaction> getReactionList() {
		return this.reactionList;
	}
	
	/**
	 * Retourne la liste des bactéries de la voie.
	 * 
	 * @return bacterieList
	 */
	public List<Bacterie> getBacterieList() {
		return this.bacterieList;
	}
	/**
	 * Construction d'une voie spécifique à certaines bactéries,
	 * fournies en argument sous forme de liste.
	 * 
	 * @param bactList : liste des bactéries sélectionnées
	 * @return new SpecificPathway(this, nbList) : une nouvelle
	 * voie spécifique caractérisée par la voie générale et le
	 * nombre de bactéries parmis les sélectionnées se retrouvant pour
	 * chaque réaction de la voie.
	 * @see SpecificPathway#SpecificPathway(GeneralPathway, List)
	 */
	public SpecificPathway specificPathwayConstruction(List<Bacterie> bactList) {
		List <Float> nbList = new ArrayList<Float>();
		
		for (int i = 0; i < this.reactionList.size(); i ++) { // Pour toutes les réactions
			float nb = 0;
			for (int j = 0; j < bactList.size(); j++) { // Est ce que la bactérie est dans la liste
				if (bactList.get(j).exist(reactionList.get(i).getBact()) != -1) nb++;
			}
			nbList.add(nb);
		}
		return new SpecificPathway(this, nbList);
	}
		
	/**
     * Construction du viewer du graphique réseau général.
     * 
     * @param graph : le graphe de la voie générale.
	 * @return Le Viewer du graphe pour créer ensuite le ViewPanel.
     * @see GeneralPathway#draw()
     */
	private Viewer viewerGraph (Graph graph, Fenetre f) {
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
					+"text-background-color: white;"
					+"text-size : 11;"
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
