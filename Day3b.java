import java.io.BufferedReader;
import java.io.FileReader;

public class Day3b {  public static void main(String [] args) {
    String [] thisLine = new String [3];
    int [] compSize = new int [3];
    int score = 0;

    try {
        BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day3input.txt"));
        while ((thisLine[0] = br.readLine()) != null) {
            thisLine[1] = br.readLine();
            thisLine[2] = br.readLine();
            compSize[0] = thisLine[0].length();
            compSize[1] = thisLine[1].length();
            compSize[2] = thisLine[2].length();
            int found= 0;
            for(int ol = 0; ol < compSize[0]; ol ++){
                for(int ml =0; ml < compSize[1]; ml ++){
                    for(int il = 0; il < compSize[2]; il ++){
                    if(thisLine[0].charAt(ol) == thisLine[1].charAt(ml) && found == 0){
                        if(thisLine[0].charAt(ol) == thisLine[2].charAt(il)){
                            if(thisLine[0].charAt(ol) < 96 ){
                                score += thisLine[0].charAt(ol) - 38;
                            } else {
                                score += thisLine[0].charAt(ol) - 96;
                            }
                            found = 1;
                        }
                    }
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
