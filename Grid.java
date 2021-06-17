package spreadsheet_project;

import java.util.stream.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
	private int rows = 10;
	private int columns = 6;
	private Node head;
  
	// Déclaration du constructeur de la classe Grid, configuration des attributs (setRight, setDown)

	public Grid(){
		// Créer un array à 2 dimensions pour représenter le tableur
		Node[][] gridArray = new Node[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				gridArray[i][j] = new Node();
		head = gridArray[0][0];
    
		// Pour chaque Node (noeud), enregistrer les valeurs des attributs right et down
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++){
				gridArray[i][j].setRight(gridArray[i][(j+1) % columns]);
				gridArray[i][j].setDown(gridArray[(i+1) % rows][j]);
			}
	}
  
	// Méthode pour afficher le tableur 
	public void display(){
		System.out.println();
		//Print column labels
		for(int i = 0; i < columns; i++){
			System.out.printf("     col " + i);
		}
		System.out.println();
		Node temp = head;
		for(int i = 0; i < rows; i++){
			//Print row labels
			System.out.print("row " + i);
			for(int j = 0; j < columns; j++){
				//Print value of each Node in row
				System.out.print(temp.getValue());
				temp = temp.getRight();
			}
		temp = temp.getDown();
		System.out.println();
		}
	}
  
	// Fonction pour se déplacer dans le tableur et accéder aux cellules avec les coordonnées
	public Node traverse(int row, int col){
		Node iterator = head;
		for(int i = 0; i < col; i++)
			iterator = iterator.getRight();
		for(int i = 0; i < row; i++)
			iterator = iterator.getDown();
		return iterator;
	}
  
	// Méthode pour ajouter une valeur dans une cellule
	public void assignCell(int row, int col, String s){
		Value newValue = new Value(s);
		Node iterator = traverse(row,col);
		iterator.setValue(newValue);
  }
  
	// Méthode pour effacer une valeur dans une cellule
	public void deleteCell(int row, int col){
		Value newValue = new Value(null);
		Node iterator = traverse(row,col);
		iterator.setValue(newValue);
	}
  
	// Méthode pour mettre les valeurs du tableau dans le tableur
  	public void arrayToGrid(String [] [] cellArray){
    for(int i = 0; i < rows; i++)
        for(int j = 0; j < columns; j++){
            Value newValue = new Value(cellArray[i][j]);
            Node iterator = traverse(i,j);
            iterator.setValue(newValue);
      }
    System.out.println("Le fichier a bien été importé dans le tableur");
  	}
  	
  	// Fonction pour détecter s'il y a une opération à faire dans la cellule
    public static boolean regex_tester(String regex, String cell) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cell);
        boolean matchFound = matcher.find();
        if(matchFound) {
          //System.out.println("Match found");
        	return true;
        } else {
          //System.out.println("Match not found");
        	return false;
        }
      }

    // Fonction pour détecter les références ou nombres dans les cellules 
    // Détecter l'opération à faire et faire le calcul, renvoyer un résultat
    public static int regex_matcher(String cell, String [] [] cellArray) {
        int count = 0;
        int[] values = new int[2];
        int result = 0;
        //String cell = "5+2";
        Pattern pattern = Pattern.compile("\\d+,+\\d+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cell);
        while(matcher.find() && count < 2){
        	int coord1 = 0;
            int coord2 = 0;
            //System.out.println("Match found");
            String expression = matcher.group();
            //System.out.println(expression);
            String [] array = expression.split(",");
            coord1 = Integer.parseInt(array[0]);
            coord2 = Integer.parseInt(array[0]);
            //System.out.println(coord1);
            //System.out.println(coord2);
            if(cellArray[coord1-1][coord2-1] != null & cellArray[coord1-1][coord2-1] != "") {
            	values[count] = Integer.parseInt(cellArray[coord1-1][coord2-1]);
            }
            else {
            	values[count] = 0;
            }
            count+=1;
        	}
        Pattern pattern_2 = Pattern.compile("^\\d|\\d$", Pattern.CASE_INSENSITIVE);
        Matcher matcher_2 = pattern_2.matcher(cell);
        while(matcher_2.find() && count < 2){
        	  int num = 0;
              //System.out.println("Match found");
              String expression_2 = matcher_2.group();
              num = Integer.parseInt(expression_2);  
              //System.out.println(num);
              values[count] = num;
              count+=1;
        	}
        Pattern pattern_3 = Pattern.compile("\\+", Pattern.CASE_INSENSITIVE);
        Matcher matcher_3 = pattern_3.matcher(cell);
        if(matcher_3.find()) {
        	result = values[0]+values[1];
        }
        Pattern pattern_4 = Pattern.compile("\\-", Pattern.CASE_INSENSITIVE);
        Matcher matcher_4 = pattern_4.matcher(cell);
        if(matcher_4.find()) {
        	result = values[0]-values[1];
        }
        Pattern pattern_5 = Pattern.compile("\\*", Pattern.CASE_INSENSITIVE);
        Matcher matcher_5 = pattern_5.matcher(cell);
        if(matcher_5.find()) {
        	result = values[0]*values[1];
        }
        Pattern pattern_6 = Pattern.compile("\\/", Pattern.CASE_INSENSITIVE);
        Matcher matcher_6 = pattern_6.matcher(cell);
        if(matcher_6.find()) {
        	result = values[0]/values[1];
        }
        Pattern pattern_7 = Pattern.compile("Somme", Pattern.CASE_INSENSITIVE);
        Matcher matcher_7 = pattern_7.matcher(cell);
        if(matcher_7.find()) {
        	result = values[0]+values[1];
        }
        Pattern pattern_8 = Pattern.compile("Moy", Pattern.CASE_INSENSITIVE);
        Matcher matcher_8 = pattern_8.matcher(cell);
        if(matcher_8.find()) {
        	result = (values[0]+values[1])/2;
        }
        //System.out.println(values);
        return result;
    }
    
    // Calcul de l'opération dans le cellule
  	public String[][] calculate(String [] [] cellArray){
  		int[] values = new int[2];
  		int result = 0;
  	    for(int i = 0; i < rows; i++) {
  	        for(int j = 0; j < columns; j++){
  	        	if (cellArray[i][j]!= null) {
  	        		if (regex_tester("\\+|-|\\*|/|Somme|Moy", cellArray[i][j])) {
  	  	            	//System.out.println(cellArray[i][j]);
  	  	            	if(regex_tester("\\d*,\\d*", cellArray[i][j])) {
  	  	            		result = regex_matcher(cellArray[i][j], cellArray);
  	  	            		//System.out.println(result);
  	  	            		cellArray[i][j] = Integer.toString(result);
  	  	            		//values = regex_matcher(cellArray[i][j], cellArray);
  	  	            	}
  	  	            }
  	        	}
  	        }
  		}
  	    return cellArray;
  	}
  
  	public Node getHead(){
  		return this.head;
  	}
  
  	public int getRows(){
  		return this.rows;
  	}
  
  	public int getColumns(){
  		return this.columns;
  	}
}


