public class Exercise_1{
  public static void main(String[]args){
    try{

    System.out.println(hex2Dec("A5"));
    System.out.println(hex2Dec("FAA"));
    System.out.println(hex2Dec("T10"));
    System.out.println(hex2Dec("ABC"));
    System.out.println(hex2Dec("10A"));
  }
  catch (HexFormatException ex){
    }
  }
  public static int hex2Dec(String hex) throws HexFormatException{
    for (int i =0;i<hex.length();i++){
      if (hex.charAt(i) > 'F'){
      throw new HexFormatException(hex.charAt(i));
     }
    }
    int returnint = 0;
    int g =0;
    int [] hexkey = {10,11,12,13,14,15};
    for (int i =0;i<hex.length();i++){
      if ((int)hex.charAt(i)>=65){
        g = ((int)hex.charAt(i)) - 65;
        returnint += (hexkey[g] * Math.pow(16,(hex.length()-(1+i))));
      }
      else{
        returnint += (((int)hex.charAt(i) - 48) * Math.pow(16,(hex.length()-(1+i))));
      }
    }
    return returnint;
    }
  }
