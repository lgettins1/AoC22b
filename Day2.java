import java.io.BufferedReader;
import java.io.FileReader;

public class Day2 {
    public static void main(String [] args) {
        String thisLine;
        int oppmove;
        int mymove;
        int difference;
        int sum;
        int score = 0;
        int part2score = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day2input.txt"));
            while ((thisLine = br.readLine()) != null) {
                oppmove = thisLine.charAt(0);
                mymove = thisLine.charAt(2);

                difference = mymove - oppmove;
                switch (difference) {
                    case 23 -> score += 3;
                    case 24, 21 -> score += 6;
                }
                score += (mymove - 87);

                sum = mymove + oppmove;
                switch (sum) {
                    case 155 -> part2score += 2;
                    case 153, 156 -> part2score += 3;
                    case 154, 157 -> part2score += 1;
                }
                part2score += (mymove - 88) * 3;
            }
            System.out.println(score + " is the total score");
            System.out.println(part2score + " is the total score for part 2");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
