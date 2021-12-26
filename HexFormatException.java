//Custom Exception Class which accompanies Hex2dec.java file, simmple enough.

public class HexFormatException extends Exception{
  public HexFormatException(char ex){
    System.out.println("Illegal hex character");
  }
}
