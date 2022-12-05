import java.io.BufferedReader;
import java.io.FileReader;

public class Day4 {
    public static void main(String [] args) {
        String thisLine;
        int pairs = 0;
        int pairs2 = 0;
        int[] areas = new int[4];

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day4input.txt"));
            while ((thisLine = br.readLine()) != null) {
                String[] parts = thisLine.split("[,-]");
                for(int a = 0; a < 4; a ++){
                    areas [a] = Integer.parseInt(parts [a]);
                }
                if((areas [0] >= areas [2] && areas [1] <= areas [3]) ||
                        (areas [2] >= areas [0] && areas [3] <= areas [1])){
                    pairs ++;
                }
                if(areas [1] >= areas [2] && areas [0] <= areas [3]){
                    pairs2 ++;
                }
            }
            System.out.println(pairs + " is the number of assignment pairs for part 1");
            System.out.println(pairs2 + " is the number of assignment pairs for part 2");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
