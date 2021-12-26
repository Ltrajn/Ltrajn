//Task: Create a program which asks user for the length and number of DNA strands and randomly generates those strands 
//while adhering to certain criteria characteristic to actual DNA.
//Criteria 1: No repeating a nucleotide (A,C,G,or T) more than twice
//Criteria 2: C and G nucleotides must make up between 40%-60% of all nucleotides in any strand.
//Criteria 3: Certain sequences are not allowed, which are specified on line 81.


import java.util.*;
public class DNABarcodeGenerator{
  public static void main(String[]args){
    //Landen Messner, lem611
      System.out.println(generateBarcode());
  }
  //Main method right above, pretty straight forward.
  public static String generateBarcode(){
    Scanner input = new Scanner(System.in);
    //need to use scanner in generateBarcode method to get number of barcodes and their respective length
    System.out.println("How many sequences of DNA barcodes you would like to generate?");
    int N = input.nextInt();
    System.out.println("How long would you like the DNA Barcode to be?: ");
    int length = input.nextInt();
    //declare String object called totalstring which will be comprised of individual barcodes
    String totalstring = "";
    int counter = 0;
    //counter to count how many barcodes have actually been created and while loop ends once it has reach N barcodes
    while (counter<N){
      //if user inputs N less than 3 or length less than 4 just set them to defautl 3,4 values respectively
    if (length<4)
      length=4;
    if (N<3)
      N=3;
    //declare barcode String object which will be the inidivudal barcodes comprised of nucleotides
    String barcode = "";
    //declare index variable for randomly selecting a char from nucleo array
    int index;
    char [] nucleo = {'G','T','C','A'};
    //loop through the user inputted length and select a random char index from nucleo array, add it to single barcode string
    for (int j =0;j<length;j++){
      index = (int)(Math.random()*4);
        barcode += nucleo[index];
      }
    //Call isRedundantBarcode
    if (isRedundantBarcode(barcode)==true){
      continue;
    }
    //call method isRestricted List, if its true don't add a count and skip
    if (isRestrictedList(barcode)==true){
      continue;
    }
    //same for validateGCCount
    if (validateGCCount(barcode)==true){
      continue;
    }
    //if it passed both of those checks then add it to total string which correct formatting and
    //add the counter to while loop condition
    else{
    totalstring += "barcode"+(counter+1)+"= "+barcode+"\n";
    counter++;
   }
  }
  //close the input and return total string
  input.close();
      return totalstring;
 }
 //isRedundantBarcode checks to see if last two characters were the same as the current one, if so, returns true and this string is discarded.
 public static boolean isRedundantBarcode(String barcode){
   boolean flag = false;
   for(int i = 2;i<barcode.length();i++){
     if (barcode.charAt(i-2) == barcode.charAt(i-1) && barcode.charAt(i-1) == barcode.charAt(i)){
     flag = true;
     return flag;
   }
   else
   continue;
   }
   return flag;
 }
//isrestrictedList method, declare flag and String array of restricted sequences
  public static boolean isRestrictedList(String barcode){
    boolean flag=false;
    String [] restrict = {"ACCGGT","GGCGCGCC","GGATCC","CCTGCAGG"};
    //if the barcode is shorter than the shortest sequence, it passes the test
    if (barcode.length()<restrict[0].length())
      return flag;
      //else, loop through all of the restricted sequences, and for each one, fill a temporary String object
      //s with the barcode substring of the same length of the restricted sequence
      //shift the substring to the right one step until the you've checked the entire string and don't run out of range
      //if any s equals the restricted value, print Restricted and change the flag to true.
    else{
    for (int i = 0;i<restrict.length;i++){
      String s="";
      for (int j=0;j<(barcode.length()-restrict[i].length());j++){
        s = barcode.substring(j,(j+restrict[i].length()));
        if (s.equals(restrict[i])){
          flag = true;
          System.out.println("Restricted");
          return flag;
        }
        else
        continue;
    }
   }
   return flag;
 }
  }
  //validate GCcount method counts the number of each nucleotide and adds them up
  public static boolean validateGCCount(String barcode){
    boolean flag = false;
    int Gcount=0;
    int Ccount=0;
    int Tcount=0;
    int Acount=0;
    for (int i = 0; i<barcode.length();i++){
      if (barcode.charAt(i)=='G')
      Gcount++;
      else if (barcode.charAt(i)=='C')
      Ccount++;
      else if (barcode.charAt(i)=='T')
      Tcount++;
      else if (barcode.charAt(i)=='A')
      Acount++;
      else
      continue;
    }
    //if the G-C sum/the total is less than 40 or greater than 60, it passes the check, otherwise it fails
    double GCcount = Gcount + Ccount;
    double totalcount = Gcount+Acount+Ccount+Tcount;
    if ((GCcount/totalcount)*100>40 && (GCcount/totalcount)*100<60){
      flag = false;
      return flag;
    }
    else{
      flag = true;
    }
  return flag;
  }

//end of code
}
