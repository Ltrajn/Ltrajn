//Task: Create a method which converts Hexadecimal numbers to decimal and throws a custom exception if the input is not a hexidecimal number.


public class Hex2Dec{
  //main method tries five different inputs
  public static void main(String[]args){
    try{

    System.out.println(hex2Dec("A5"));
    System.out.println(hex2Dec("FAA"));
    System.out.println(hex2Dec("T10"));
    System.out.println(hex2Dec("ABC"));
    System.out.println(hex2Dec("10A"));
  }
    //catches custom HexFormatException exception
  catch (HexFormatException ex){
    }
  }
  //hex2dec method which throws HexFormatException and returns an integer.
  public static int hex2Dec(String hex) throws HexFormatException{
    //checks the passed string to see if any character in it is greater than F, throws exception if true.
    for (int i =0;i<hex.length();i++){
      if (hex.charAt(i) > 'F'){
      throw new HexFormatException(hex.charAt(i));
     }
    }
    //if no exception is thrown, initialize return variable
    int returnint = 0;
    int g =0;
    // create array which assigns numbers to letters A,B,C,D,E,F 
    int [] hexkey = {10,11,12,13,14,15};
    //if character is not a decimal number, do the following
    for (int i =0;i<hex.length();i++){
      if ((int)hex.charAt(i)>=65){
        //variable g access correct element of hexkey array
        g = ((int)hex.charAt(i)) - 65;
        //returnint is added by the number itself times 16 ^ n where n is the position of that digit in the original strand
        returnint += (hexkey[g] * Math.pow(16,(hex.length()-(1+i))));
      }
      else{
        //if character is decimal number, repeat the above formula for converting to decimal numbers from hex.
        returnint += (((int)hex.charAt(i) - 48) * Math.pow(16,(hex.length()-(1+i))));
      }
    }
    return returnint;
    }
  }
