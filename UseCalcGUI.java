package spreadsheet_project;

class UseCalcGUI{
	
    public static void main(String[] args){
    	String filePath = "/Users/henry/Desktop/_NFA035/_projet/";
    	String [][] cellArray = Program.loadFile(filePath);
		CalcGUI fenetre = new CalcGUI(cellArray);
		Terminal.lireString();
		fenetre.afficheString("toto",2,3);
		fenetre.afficheValeur(145,4,3);
		fenetre.effaceCase(3,7);
	
    }
}
