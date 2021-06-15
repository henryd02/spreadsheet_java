package spreadsheet_project;

public class Grid {
	private int rows = 10;
	private int columns = 6;
	private Node head;
  
	//Constructor: Makes a 10x6 grid of nodes (setting attributes right and down pointers)

	public Grid(){
		// Create 2d-array and fill with Nodes
		Node[][] gridArray = new Node[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				gridArray[i][j] = new Node();
		head = gridArray[0][0];
    
		// Set each Node's right and down pointers
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++){
				gridArray[i][j].setRight(gridArray[i][(j+1) % columns]);
				gridArray[i][j].setDown(gridArray[(i+1) % rows][j]);
			}
	}
  
	//Displays grid with row and column labels.
	public void display(){
		System.out.print("     ");
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
  
	//Traverses to a given node from the head pointer.
	public Node traverse(int row, int col){
		Node iterator = head;
		for(int i = 0; i < col; i++)
			iterator = iterator.getRight();
		for(int i = 0; i < row; i++)
			iterator = iterator.getDown();
		return iterator;
	}
  
	//Creates a Value from given string and uses Traverse method to get to given cell, then setValue
	public void assignCell(int row, int col, String s){
		Value newValue = new Value(s);
		Node iterator = traverse(row,col);
		iterator.setValue(newValue);
  }
  
	// Same as above deleting a value
	public void deleteCell(int row, int col){
		Value newValue = new Value(null);
		Node iterator = traverse(row,col);
		iterator.setValue(newValue);
	}
  
	// Import the file and store cell in the Grid
  	public void importFile(String [] [] cellArray){
    for(int i = 0; i < rows; i++)
        for(int j = 0; j < columns; j++){
            Value newValue = new Value(cellArray[i][j]);
            Node iterator = traverse(i,j);
            iterator.setValue(newValue);
      }
    System.out.println("Le fichier a bien été importé dans le tableur");
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


