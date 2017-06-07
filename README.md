# Network-KGML
Visualisation graphique de voies métaboliques d'espèces bactériennes au format KGML
par DA SILVA OPHELIE & MERCKAERT PIERRE


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

Utilisez main_Barstar.py pour obtenir toutes les informations pertinentes quant aux changements conformationnels de votre protéine d'intérêt par rapport à sa référence.

```python
# Exécution du programme sur la barstar comme référence et 200 de ses conformations en solution, en considérant le barycentre des atomes des résidus comme le centre de masse des résidus.
	python2 main_Barstar.py start_prot_only.pdb md_prot_only_skip100.pdb all

# Parsing des fichiers .pdb en arguments sous forme de dictionnaires
	barstar_conf= __parsePDBM(barstar_conf.pdb)
	barstar_ref = __parsePDBM(barstar_ref.pdb)

# Calcul des paramètres permettant l'analyse des changements conformationnels vis-à-vis de la référence 
# et ajout de ces valeurs dans le dictionnaire avec des clés pertinentes.
	l_dict=[barstar_conf, barstar_ref]

	# Calcul du centre de masse des résidus et de chaque conformation
	centreMasseCalc(l_dict)	

	# Calcul du RMSD des residus de chaque conformation par rapport à la référence			
	RMSD(l_dict)	

	# Calcul de la distance entre les résidus et le CdM pour chaque conformation			
	distance(l_dict)

	# Calcul du Rayon de Giration pour chaque conformation				
	rayonGiration(l_dict)
					
	# Calcul de la corrélation entre le Rayon de Giration (Enfouissement) et la Flexibilité (RMSD) pour chaque conformation
	corEnfouissementFlexibilite_conf(l_dict)
	
# Ecriture des resultats dans les tableaux excel de sortie et enregistrement dans les dossiers correspondants.
	ecriture(l_dict,type_analyse)
	
# Représentation Graphique des données et enregistrement des images.
	plot(l_dict) 
```

 API
 ---
Le projet est composé de plusieurs fichiers correspondants aux fonctions du programme :
* Parsing_dico : Vérification du bon usage du programme et Parsing des fichiers pdb en argument.
* Conformation_analysis : Calcul des paramètres permettant l'analyse des fichiers pdb.
* Ecriture : Ecriture des fichiers excel de sortie et création du dossier dans lequel ils seront enregistrés.
* Graphes : Représentations graphiques pertinentes des parametres calculés.
