package spreadsheet_project;

import java.awt.*;
import javax.swing.*;

// ====================================================================
public class CalcGUI{
	// String filePath = "/Users/henry/Desktop/_NFA035/_projet/";
	// String [][] cellArray = Program.loadFile(filePath);
    JTextField[][] tft = new JTextField[10][6];
    JPanel jpane;
    JFrame fenetre;
    /** Constructeur cr�ant une fen�tre graphique avec une feuille de calcul vierge de 10 lignes et 6 colonnes */
    public CalcGUI(){
    	fenetre = new JFrame();
	fenetre.setTitle("Tableur"); 
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jpane = new JPanel();
	fenetre.setContentPane(jpane);
	jpane.setLayout(new GridLayout(11,7));
	jpane.setPreferredSize(new Dimension(600, 400));
	//jpane.setBackground(new Color(255,246,140));
	jpane.setBackground(new Color(205,26,26));
	for (int i=0; i<10; i++){
	    for (int j=0; j<6; j++){  
		tft[i][j]=new JTextField();
		tft[i][j].setText(" " + i + " " + j);
		// System.out.println(cellArray[i][j]);
		//tft[i][j].setText(cellArray[i][j]); 
		tft[i][j].setHorizontalAlignment(JTextField.CENTER); 
		tft[i][j].setEditable(false);
		tft[i][j].setBackground(new Color(255,228,225));
	    }
	}
	jpane.setForeground(new Color(255,255,0));
	jpane.add(new Label(" "));
	for (int i=0; i<6; i++){
	    jpane.add(new MyJLabel("" + (i+1),JLabel.CENTER));
	}
	for (int i=0; i<10; i++){
	    jpane.add(new MyJLabel(""+ (i+1),JLabel.CENTER));
	    for (int j=0; j<6; j++){
		jpane.add(tft[i][j]);
	    }
	}
	fenetre.pack();
	fenetre.setVisible(true);
    }
    /** m�thode qui affiche la chaine s sur la case en ligne x et colonne y */
    public void afficheString(String s, int x, int y){
	tft[x-1][y-1].setText(s);
    }
   /** m�thode qui affiche l'entier val sur la case en ligne x et colonne y */
    public  void afficheValeur(float val, int x, int y){
	tft[x-1][y-1].setText(""+val);
    }
   /** m�thode qui efface la case en ligne x et colonne y */
    public void effaceCase(int x, int y){
	tft[x-1][y-1].setText("");   
    }
}
class MyJLabel extends JTextField{
    MyJLabel(String s, int i){
	super(s,i);
	this.setForeground(new Color(255,255,0));
	this.setBackground(new Color(205,26,26));
	this.setHorizontalAlignment(JTextField.CENTER);
	this.setEditable(false);
    }
} 

