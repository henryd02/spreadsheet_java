class UseCalcGUI{

    public static void main(String[] args){
	CalcGUI fenetre = new CalcGUI();
	Terminal.lireString();
	fenetre.afficheString("toto",2,3);
	fenetre.afficheValeur(145,4,3);
	fenetre.effaceCase(3,7);
    }
}
