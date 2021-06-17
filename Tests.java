package spreadsheet_project;

import junit.framework.TestCase;

/**
 * A JUnit test pour écrire des tests fonctionnels
 * Toutes les méthodes qui commencent par le mot "test" vont être exécuté lorsqu'elles seront appelées
 */

public class Tests extends TestCase {
  

  public void test1() {
    Value v = new Value();
    assertEquals(v.getTag(), "STRING");
    assertEquals(v.getDVal(), 0.0);
    assertEquals(v.getSVal(), "");
  }
  
  public void test2(){
    Value v = new Value("\"henry");
    assertEquals(v.getSVal(), "henry");
    assertEquals(v.getTag(), "STRING");
  }
  
  public void test3(){
    Value v = new Value("10");
    assertEquals(v.getTag(), "DBL");
    assertEquals(v.getDVal(), 10.0);
  }
  
  public void testIsQuoteTrue(){
    Value v = new Value();
    String s = "\"henry";
    assertEquals(true, v.isQuote(s));
  }
  
  public void testIsQuoteFalse(){
    Value v = new Value();
    String s = "henry";
    assertEquals(false, v.isQuote(s));
  }
  
  public void testPlus1(){
    Value v1 = new Value("1");
    Value v2 = new Value("2");
    assertEquals(v1.plus(v2).getDVal(), 3.0);
    assertEquals(v1.plus(v2).getSVal(), "");
    assertEquals(v1.plus(v2).getTag(), "DBL");
  }
  
  public void testPlus2(){
    Value v1 = new Value("1");
    Value v2 = new Value("\"2");
    assertEquals(v1.plus(v2).getDVal(), 0.0);
    assertEquals(v1.plus(v2).getTag(), "INVALID");
  }
    
  public void testSlash1(){
    Value v1 = new Value("1");
    Value v2 = new Value("2");
    assertEquals(v1.slash(v2).getDVal(), 0.5);
    assertEquals(v1.slash(v2).getSVal(), "");
    assertEquals(v1.slash(v2).getTag(), "DBL");
  }
  
  public void testSlash2(){
    Value v1 = new Value("1");
    Value v2 = new Value("0");
    assertEquals(v1.slash(v2).getDVal(), 0.0);
    assertEquals(v1.slash(v2).getSVal(), "");
    assertEquals(v1.slash(v2).getTag(), "INVALID");
  }
    
  public void testToStringSTR(){
    Value v = new Value("\"henry");
    assertEquals(v.toString(), "       henry");
  }
    
  public void testToStringDBL(){
    Value v = new Value("10");
    assertEquals(v.toString(), "   10.0000");
  }
  
  public void testTraverse(){
    Grid g = new Grid();
    assertEquals(g.getHead().getDown().getRight() == g.traverse(1,1), true);
  }
  
  public void testTraverse2(){
    Grid g = new Grid();
    assertTrue(g.traverse(1,1) != null);
  }
  
  public void testAssignCell(){
    Grid g = new Grid();
    g.assignCell(1,1,"24");
    assertEquals(g.getHead().getDown().getRight().getValue().getDVal(), 24.0);
  }
  
}