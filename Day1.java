import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day1 {
    public static void main(String [] args) {
        String thisLine;
        int currVal = 0;
        int elfCount = 0;
        int[] elves = new int [250];

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day1input.txt"));
            while ((thisLine = br.readLine()) != null) {
                thisLine = thisLine.trim();
                if(thisLine.length() > 0 ) {
                    currVal += Integer.parseInt(thisLine);
                } else {
                    elves[elfCount] = currVal;
                    currVal = 0;
                    elfCount ++;
                    System.out.println(elfCount + " " + elves[elfCount - 1]);
                }
            }
            Arrays.sort(elves);
            int answer = elves[247] + elves[248] + elves[249];
        System.out.println(elves[249] + " calories is the maximum amount carried");
        System.out.println(answer + " is the answer to part b");
    }catch (Exception e) {
        e.printStackTrace();
    }
}

}
