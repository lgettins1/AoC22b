import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day15part2 {

    public static void main(String[] args) throws Exception{
        String filename = "c:/users/lance/documents/AoC22Day15input.txt";
        String thisLine;
        int dimension = 4000000;
        int[][] coords = new int[5][35];
        int lc = 0;
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((thisLine = br.readLine()) != null) {
                String[] lineParts = thisLine.split("[=,:]");
                coords[0][lc] = Integer.parseInt(lineParts[1]);
                coords[1][lc] = Integer.parseInt(lineParts[3]);
                coords[2][lc] = Integer.parseInt(lineParts[5]);
                coords[3][lc] = Integer.parseInt(lineParts[7]);
                coords[4][lc] = Math.abs(coords[0][lc] - coords[2][lc]) + Math.abs(coords[1][lc] - coords[3][lc]);
                lc++;
            }

        int targetX = 0;
        int targetY = 0;
        for(int targetRow = 0; targetRow <= dimension; targetRow ++){
                int[][] pairs = new int [35][2];
                int pc = 0;
                for(int lc2 = 0; lc2 < lc; lc2 ++) {
                    int offset = Math.abs(coords[1][lc2] - targetRow);
                    if (offset <= coords[4][lc2]) {
                        int smallX = coords[0][lc2] - (coords[4][lc2] - offset);
                        int bigX = coords[0][lc2] + (coords[4][lc2] - offset);
                        pairs[pc][0] = smallX;
                        pairs[pc][1] = bigX;
                        pc ++;
                    }
                }
                Arrays.sort(pairs, Comparator.comparingInt(arr -> arr[0]));

                int minX = 500;
                int maxX = 0;
                for(int a = 0; a < 35; a ++){
                    if(pairs[a][0] < minX){
                        minX = pairs[a][0];
                    }

                    if(pairs[a][0] <= maxX + 1){
                        if(maxX < pairs[a][1]) maxX = pairs[a][1];
                    }else{
                        targetX = maxX + 1;
                        targetY = targetRow;
                   }
                }
            }
        long answer = (long) 4000000 * targetX + targetY;
        System.out.println("The target coordinates are (" + targetX +"," + targetY+")");
        System.out.println("The answer is " + answer);
        }
    }

