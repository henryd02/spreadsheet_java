package spreadsheet_project;

import java.io.*;
import java.util.Scanner;

public class Program {
	
	// Method to load image
	public static Grid loadFile (String filePath) {
		String nomFichier ;
		FileInputStream fichier ;
		int c;
		Terminal.ecrireString ("Entrez le nom du fichier à afficher :");
		nomFichier = Terminal.lireString();
		try{
			fichier = new FileInputStream(filePath+nomFichier);
			c = fichier.read(); 
			while ( c != -1){
				Terminal.ecrireChar((char)c);
				c = fichier.read();
			}
			fichier.close(); 
		}catch(FileNotFoundException ex){
			Terminal.ecrireStringln("Ce fichier n’existe pas"); 
		}catch(IOException exc){
			Terminal.ecrireStringln("Erreur d’ ́eentre−sortie"); 
		}
		return null;
	}
  
	// Method to save image
	
	public static void saveFile (String filePath) {
		String nomFichier;
		FileOutputStream fichier ;
		String aEcrire;
		Terminal.ecrireString("Entrez le nom du fichier"); 
		nomFichier = Terminal.lireString(); 
		Terminal.ecrireStringln("Entrez des lignes à enregistrer."); 
		Terminal.ecrireStringln("Tapez FIN lorsque vous avez fini."); 
		try{
			fichier = new FileOutputStream(filePath+nomFichier ); 
			aEcrire = Terminal.lireString();
			while (!aEcrire.equals("FIN")){
				for (int i = 0; i<aEcrire.length();i++){ 
					fichier.write(aEcrire.charAt(i));
				}
				fichier.write('\r'); fichier.write('\n'); 
				aEcrire = Terminal . lireString ();
			}
			fichier.close(); 
		}catch(IOException exc){
			Terminal.ecrireStringln("Erreur d'entree−sortie"); 
		}
		
	}
	// Constructs an empty 10x6 grid, and runs in a loop displaying the menu of choices to the user
	// Ask arguments from the user and runs the operation requested
		
	public static void main(String[] args){ 
		Scanner scanner = new Scanner(System.in);
		Grid g = new Grid();
	    int row,col;
	    String val;
	    String filePath = "/Users/henry/Desktop/_NFA035/_projet/";
	    String menu = "Operations\n" +
	    "  Display           dis\n"+
	    "  Assign cell       a\n"+
	    "  Delete cell       d\n"+
	    "  Import file       if\n"+
	    "  Save file         sf\n"+
	    "  Quit              q\n";
	
	while(true){
	  System.out.print("\n" + menu);
	  
	  //Take input from user, and use input to determine which case to run
	  
	  String input = scanner.nextLine();
	  
	  // Wrap in try+catch for the possibility that the user enters a non-digit for a row or column number
	  // Other validation for ensuring digits are actually in the grid is done using if-statements
	  
	  try{
	    switch(input){
	      case "dis":
	        g.display();
	        break;
	        
	      case "a":
	        System.out.println("row: ");
	        row = Integer.parseInt(scanner.nextLine());
	        System.out.println("column: ");
	        col = Integer.parseInt(scanner.nextLine());
	        if(row >= g.getRows() || col >= g.getColumns()){
	          System.out.println("Error: Cell out of bounds");
	          break;
	        }
	        System.out.println("with value: ");
	        val = scanner.nextLine(); 
	        g.assignCell(row,col,val);
	        break;
	
	      case "d":
	          System.out.println("row: ");
	          row = Integer.parseInt(scanner.nextLine());
	          System.out.println("column: ");
	          col = Integer.parseInt(scanner.nextLine());
	          if(row >= g.getRows() || col >= g.getColumns()){
	            System.out.println("Error: Cell out of bounds");
	            break;
	          }
	          g.deleteCell(row,col);
	          break;
	       
	      case "if":
	          loadFile(filePath);
	          // Write code to parse imported file and insert values from file to Grid cells
	          break;
	      case "sf":  
	    	  // Write code to insert Grid cells values in the file before to save it
	          saveFile(filePath); 
	          break;
	      case "q":
	        break;
	        
	      default:
	        System.out.println("Error: Please enter one of the commands in the menu");
	    }
	  }
	  catch(NumberFormatException e){
	    System.out.println("Error: Not a number");
	  }
	  if(input.equals("q"))
	        break;
	    }
	  }
}