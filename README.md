# Network-KGML
Visualisation graphique de voies métaboliques d'espèces bactériennes au format KGML
par DA SILVA OPHELIE & MERCKAERT PIERRE

## To Do:
-Synopsis
-English translation

 Synopsis
 --------

...

 Exécution
 ---------
 Dans le dossier /src/ :
```java
javac *.java
java NetworkVizualisation
 ```
 

 Exemple de Code
 ---------------

Constructeur de la classe Fenetre.
```java
/**
 * Constructeur Fenetre
 * Positionnement des éléments graphiques
 */
public Fenetre() {
	JFrame f = new JFrame("Réseau Métabolique");
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	f.setSize(2000,1600);
	this.c = f.getContentPane();
	c.setLayout(new BorderLayout());
	this.data = new DataList(); // Les données d'entrée triées
	
	// Nord : le Menu Principal
	this.menuBar = new MenuPrincipal(this);
	c.add(menuBar,"North");
	
	// Dans la partie haute on veut les 2 listes de sélection des voies et des bactéries
	String[] listeTest1 = {}; 
		String[] listeTest2 = {}; 
	this.s = new DataSelection(this,listeTest1,listeTest2);
	c.add(s,"West");
	
	// Dans la partie centrale le graohe du réseau métabolique
	this.reseau = new DessinReseau(this);
	c.add(reseau,"Center");
		
	f.setContentPane(c);
	f.setVisible(true);
}
```


Fonction draw() de la calsse GeneralPathway.
Création du graphe, récupération du viewer et du viewpanel.
(Puis ajout du viewpanel dans un JPanel de la fenêtre, voir classe DessinReseau)
```java
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
```

 API
 ---
Le projet utilise la librairie Graphstream. Deux fichiers JAR sont à inclure dans le classpath pour pouvoir exécuter le programme, gs-core et gs-ui que vous pouvez récupérer [ici](http://graphstream-project.org/download/).
Toutes les instructions quant à l'utilisation du programme se trouvent dans l'onglet Aide du Menu Principal du programme.

La Javadoc et l'UML (image UML.png) du projet se trouvent dans le dossier /doc/.

Un jeu de données test des voies métaboliques de 50 espèces bactériennes se trouve dans le fichier Data_test.zip
