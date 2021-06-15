package spreadsheet_project;

import java.io.*;
import java.util.Scanner;

import project.Image;
import project.Segment;
import project.Terminal;

public class Program {
	
	// Method to load file
	public static String[][] loadFile (String filePath) {
		String nomFichier ;
		FileInputStream fichier ;
		int c;
		
        String[][] cellArray = new String[10][6];
        String line = "";
        Grid grid = new Grid();
        int j = 0;
		
		Terminal.ecrireString ("Entrez le nom du fichier à afficher :");
		nomFichier = Terminal.lireString();
		try{
			fichier = new FileInputStream(filePath+nomFichier);
			c = fichier.read(); 
			
			System.out.println();
			System.out.println("Voici le fichier que vous voulez importer dans le tableur :");
			System.out.println();
			
			// Display the file line by line
			while ( c != -1){
				Terminal.ecrireChar((char)c);
				c = fichier.read();
			}
			fichier.close(); 
			System.out.println();
		}catch(FileNotFoundException ex){
			Terminal.ecrireStringln("Ce fichier n’existe pas"); 
		}catch(IOException exc){
			Terminal.ecrireStringln("Erreur d’entree−sortie"); 
		}
		try (	InputStream inputStream = new FileInputStream(filePath+nomFichier);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
           	
           	// Read file by line and for each line clean from special char
               while ((line = bufferedReader.readLine()) != null) {
                   line = line.replaceAll("\\R", "");
                   line = line.replaceAll("^\\s+|\\s+$", "");
                   line = line.replace("\n", "");
                   
                   // Split the line in different parts separated by a space
                   cellArray[j] = line.split("&");
                   //System.out.println("longueur ligne : " + line.length());
	   	          	for (int i = 0; i < cellArray[j].length; i++) {
		                if (cellArray[j][i] != "") {
		                	cellArray[j][i] = cellArray[j][i].replaceAll("\\s+", "");
		                    //System.out.println(i + " : "+cellArray[j][i]);
		                }
		            }
	   	          	j+=1;
               }
		}catch(FileNotFoundException ex){
			Terminal.ecrireStringln("Ce fichier n’existe pas"); 
		}catch(IOException exc){
			Terminal.ecrireStringln("Erreur d’entree−sortie"); 
		}
		return cellArray;
	}
  
	// Method to save file
	
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
	
		
	public static void main(String[] args){ 
		
		// Initialize variables
		Scanner scanner = new Scanner(System.in);
		Grid g = new Grid(); // Constructs empty 10x6 grid
	    int row,col;
	    String val;
	    String filePath = "/Users/henry/Desktop/_NFA035/_projet/";
	    
		// Ask arguments from the user and runs the operation requested
	    String menu = "*** Menu *** \n"+
	    "Pour choisir les différentes opérations, entrer la commande correspondante dans le terminal : \n" +
	    "  Afficher le tableur                        aff\n"+
	    "  Modifier la valeur d'une cellule             m\n"+
	    "  Effacer la valeur d'une cellule              e\n"+
	    "  Importer un fichier                         if\n"+
	    "  Enregistrer un fichier                      ef\n"+
	    "  Quitter le programme                         q\n";
	
		// While the user doesn't quit, run a loop displaying the menu of choices
		while(true){
			System.out.print("\n" + menu);
	  
			//Take input from user, and use input to determine which case to run
			String input = scanner.nextLine();
	  
			// Wrap in try the operations in order to manage errors
	  
			try{
				// Switch cases in order to do operations respect user request
				switch(input){
					case "aff":
				        g.display();
				        break;
			    
					case "m":
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
			
					case "e":
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
						String [][] cellArray = loadFile(filePath);
						g.importFile(cellArray);
						break;
					case "ef":  
						// Write code to insert Grid cells values in the file before to save it
						saveFile(filePath); 
						break;
					case "q":
						break;
					default:
						System.out.println("Error: Please enter one of the commands in the menu");
			    }
			  }
			  finally {}
			if(input.equals("q"))
				break;
	    }
	}
}