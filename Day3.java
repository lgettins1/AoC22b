import java.io.BufferedReader;
import java.io.FileReader;

public class Day3 {
    public static void main(String [] args) {
        String thisLine;
        String comp1;
        String comp2;
        int compSize;
        int score = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day3input.txt"));
            while ((thisLine = br.readLine()) != null) {
                compSize = thisLine.length() / 2;
                comp1 = thisLine.substring(0, compSize);
                comp2 = thisLine.substring(compSize);
                int found = 0;
                for(int ol = 0; ol < compSize; ol ++){
                    for(int il = 0; il < compSize; il ++){
                        if(comp1.charAt(ol) == comp2.charAt(il) && found == 0){
                            if(comp1.charAt(ol) < 96 ){
                                score += comp1.charAt(ol) - 38;
                            } else {
                                score += comp1.charAt(ol) - 96;
                            }
                            found = 1;
                        }
                    }
                }
            }
            System.out.println(score + " is the total score");
       }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
