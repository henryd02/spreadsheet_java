package spreadsheet_project;

public class Value {
	private double dval;
	private String sval;
	private String tag ;
	  
	public Value() {
		dval = 0.0;
		sval = null;
		tag = "STRING";
	}
	  
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	  
	//Constructor
  
	public Value(String s){
		//System.out.println(s);
		// Check if s is null
		if (s == null) {
			sval=null;
			tag = "STRING";
		}
	
		// Check if s starts with a quote, and if so remove it
	
		// else if(isQuote(s) == true){  
			//System.out.println("string : "+s);
			//s = s.substring(1);          
			//this.sval = s;
			//tag = "STRING";
		//}
		else if (isNumeric(s)){
			// System.out.println("num : "+s);
			dval = Double.parseDouble(s);
			tag = "DBL";
		}
		else {
			// System.out.println("string : "+s);
			this.sval = s;
			tag = "STRING";
		}
	}
  
	  // Check if the first character of input is a quote -> returns true or false
	  
	  public boolean isQuote(String s){
	    return s.substring(0,1).equals("\"");
	  }
	  
	  // Method to add two Values together
	  
	  public Value plus(Value v){
	    Value newValue = new Value();
	    //Check to ensure both Values have tag == DBL
	    if(this.tag.equals("DBL") && v.tag.equals("DBL")){     
	      newValue.dval = this.dval + v.dval;
	      newValue.tag = "DBL";                                
	    }
	    
	    // If both Values don't have DBL tags, don't do the addition and set the new Value's tag to INVALID
	    else
	      newValue.tag = "INVALID";                            
	    return newValue;                                       
	  }
	  
	  // Method to subtract one Value from another, same logic as above
	  
	  public Value minus(Value v){
	    Value newValue = new Value();
	    if(this.tag.equals("DBL") && v.tag.equals("DBL")){
	      newValue.dval = this.dval - v.dval;
	      newValue.tag = "DBL";
	    }
	    else
	      newValue.tag = "INVALID";
	    return newValue;
	  }
	  
	  // Method to multiply two Values together, same logic as above
	  
	  public Value star(Value v){
	    Value newValue = new Value();
	    if(this.tag.equals("DBL") && v.tag.equals("DBL")){
	      newValue.dval = this.dval * v.dval;
	      newValue.tag = "DBL";
	    }
	    else
	      newValue.tag = "INVALID";
	    return newValue;
	  }
	  
	  // Method to divide one Value from another, same logic as above
	  
	  public Value slash(Value v){
	    Value newValue = new Value();
	    //Check both tags are DBL and divisor is not zero
	    if(this.tag.equals("DBL") && v.tag.equals("DBL") && v.dval != 0){  
	      newValue.dval = this.dval / v.dval;
	      newValue.tag = "DBL";
	    }  
	    //If divisor is zero, print error statement, and don't attempt divison
	    else if(v.dval == 0){                                 
	      System.out.println("Attempted division by zero");
	      newValue.tag = "INVALID";
	    }
	    else
	      newValue.tag = "INVALID";
	    return newValue;
	  }
	  
	  // Method toString checks tag, then truncates strings to 10 characters, and doubles to 10 digits with 4 decimal places
	  
	  public String toString(){
	    if(this.tag.equals("STRING"))
	      return String.format("%10s", this.getSVal());
	    else
	      return String.format("%10.4f", this.getDVal());
	  }

	  // Write method to calculate sum of a bloc, ex : Somme((3,1),(5,2))
	  
	  // Write method to calculate mean of a bloc, ex : Moy(3,2),(5,2)
	  
	  // Getter methods
	  
	  public String getSVal(){
	    if(this.sval == null)
	      return "";
	    return this.sval;
	  }
	  
	  public Double getDVal(){
	    return this.dval;
	  }
	  
	  public String getTag(){
	    return this.tag;
	  }
	}
