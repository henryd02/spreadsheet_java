# spreadsheet_java

Dans le cadre d'un projet, je suis entrain de réaliser un petit tableur Java.

Le but de ce projet est de réaliser un petit tableur. 
Un tableau est un programme permettant de manipuler des feuilles de calcul. 
Une feuille de calcul est composée de cellules organisées en ligne et en colonne dans lesquelles on peut placer des données et effectuer des calculs. 

Le programme demandé ne générera qu'une seule feuille de calcul, composée de 10 lignes et de 6 colonnes. Les cellules sont les éléments de la feuille. Elles sont référencées par leur numéro de ligne et de colonne. $
Par exemple (6,2) désigne la cellule au croisement de la sixuème ligne et de la deuxième colonne. 
Une cellule est soit vide, soit contient une expression qui peut être une valeur entitère ou une formule (cf schéma dans le fichier pdf pour voir un exemple de feuille de calcul). 

Une formule est : 
* soit un opérateur arithmétique (+, -, *, /) appliqué à 2 opérandes
* soit les opérateurs Somme ou Moy appliqué à un bloc

Les opérandes des opérateurs arithmétiques sont soit des valeurs entières, soit des références de cellules. Par exemple, le contenu de la cellule (1,2) peut être la formule (1,1) + 5 c'est à dire la somme contenu de la cellule (1,1) auquel on ajoute 5.

Les blocs, qui sont les arguments des opérations Somme et Moy sont définis par la donnée de 2 références de cellules C1 et C2, désignant le bloc de cellules dont le coin supérieur gauche est la cellule C1 et le coin inférieur droit est la cellule C2.

Lorsqu'on définit le contenu d'une cellule par une formule, plusieurs cas de figure sont à envisager : 
* la formule est syntaxiquement incorrecte. Par exemple Moy(32), le programme doit rejeter de telles formules 
* la formule est syntaxiquement correcte mais non évaluable : cela se produit lorsqu'on divise par zéro, lorsque l'on référence une cellule qui n'a pas de valeur (vide ou non évaluable) ou lorsque l'on référence une cellule inexistante. Le programme doit savoir que le statut de la cellule est non évaluable (afin de ne pas chercher à calculer la formule)
* la formule est correcte et l'expression évaluable. Le programme doit connaitre la valeur correspondant à l'évaluation de la formule

Pour plus de détailles sur les attentes du projet, je vous invite à consulter le fichier 1_consigne.pdf dans le repository...

Les fichiers (classes) du programme : 
* Program : affiche le menu du programme et éxécute les actions demandées par l'utilisateur en appelant les autres classes et leurs méthodes 
* Grid.java : 2d-array qui représente le tableur en mémoire et qui applique des différentes méthodes aux cellules (nodes)
* Node : défini une cellule avec ses coordonnées (right, down) et sa valeur (classe value)
* Value : défini la valeur qui est attribuée à la cellule : valeur décimal, valeur texte, le tag (défini le type de valeur)
* Tests : une série de test avec junit permettant de tester le programme en fonction de l'ajout de certaines valeurs
* UseCalcGUI et CalcGUI permette d'afficher une interface graphique à l'aide de JFrame afin d'afficher le tableur


Notions à utiliser dans le projet : 
* test unitaire
* objet
* entrées-sorties
* interface graphique
