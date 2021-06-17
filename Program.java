package spreadsheet_project;

import java.io.*;
import java.util.Scanner;

public class Program {
	
	// Chargement du fichier
	public static String[][] loadFile (String filePath) {
		String nomFichier ;
		FileInputStream fichier ;
		int c;
		
        String[][] cellArray = new String[10][6];
        String line = "";
        Grid grid = new Grid();
        int j = 0;
		
		Terminal.ecrireString ("Entrez le chemin absolu vers le répertoire + le nom du fichier à afficher \n"
				+ "exemple /Users/XXX/Desktop/_NFA035/_projet/table.txt :");
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
  
	// Sauvegarde du fichier
	public static void saveFile (String filePath, String[][] cellArray) {
		String nomFichier;
		FileOutputStream fichier ;
		String aEcrire="";
		Terminal.ecrireString("Entrez le nom du chemin absolue vers le répertoire + le nom du fichier à enregister \n"
				+ "exemple /Users/XXX/Desktop/_NFA035/_projet/toto.txt :"); 
		nomFichier = Terminal.lireString(); 
		try{
			fichier = new FileOutputStream(filePath+nomFichier ); 
		    for(int i = 0; i < cellArray[i].length; i++) {
		        for(int j = 0; j < cellArray[0].length; j++){
		            aEcrire += cellArray[i][j]+" &";
		        }
		    	aEcrire += "\n";
		    }
		    System.out.println("Le tableur a bien été enregistré dans le fichier "+nomFichier);
			//aEcrire = Terminal.lireString();
			for (int i = 0; i<aEcrire.length();i++){ 
				fichier.write(aEcrire.charAt(i));
			}
			fichier.write('\r'); fichier.write('\n'); 
			aEcrire = Terminal . lireString ();
			fichier.close(); 
		}catch(IOException exc){
			Terminal.ecrireStringln("Erreur d'entree−sortie"); 
		}
		
	}
	
		
	public static void main(String[] args){ 
		
		// Initialisation des variables
		Scanner scanner = new Scanner(System.in);
		Grid g = new Grid(); 
	    int row,col;
	    String val;
	    String filePath = "/";
	    String[][] cellArray = new String[10][6];
	    
	    
		// Demander à l'utilisateur les actions à réalisés
	    String menu = "*** Menu *** \n"+
	    "Pour choisir les différentes opérations, entrer la commande correspondante dans le terminal : \n" +
	    "  Afficher le tableur                                  a\n"+
	    "  Modifier la valeur d'une cellule                     m\n"+
	    "  Effacer la valeur d'une cellule                      e\n"+
	    "  Importer un fichier                                 if\n"+
	    "  Afficher le tableur avec les valeurs numériques     an\n"+
	    "  Enregistrer un fichier                              ef\n"+
	    "  Afficher l'interface graphique                      ig\n"+
	    "  Quitter le programme                                 q\n";
	
		// Tant que l'utilisateur n'a pas quitté, lui afficher le menu des choix
		while(true){
			System.out.print("\n" + menu);
	  
			// Enregistrer la commande de l'utilisateur et réaliser l'action correspondante
			String input = scanner.nextLine();
	  
			// Encapsuler l'opération dans un try afin de gérer les erreurs
	  
			try{
				// Tester les différents cas en fonction de la demande de l'utilisateur
				switch(input){
					case "a":
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
						cellArray = loadFile(filePath);
						g.arrayToGrid(cellArray);
						break;
					    
					case "an":
						cellArray = g.calculate(cellArray);
						g.arrayToGrid(cellArray);
						g.display();
						break;
						
					case "ig":
						if(cellArray!=null) {
							CalcGUI fenetre = new CalcGUI(cellArray);
							Terminal.lireString();
							fenetre.afficheString("toto",2,3);
							fenetre.afficheValeur(145,4,3);
							fenetre.effaceCase(3,7);
						}
						break;
						
					case "ef":  
						saveFile(filePath, cellArray); 
						break;
						
					case "q":
						break;
					default:
						System.out.println("Error: Please enter one of the commands in the menu");
				}
			}
			finally {
				  
			}
			if(input.equals("q"))
				break;
	    }
	}
}