import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Day15attempt2 {

        public static void main(String[] args) throws Exception{
            String filename = "c:/users/lance/documents/AoC22Day15input.txt";
            String thisLine;
            int[][] coords = new int[5][35];
            int lc = 0;
            int targetrow = 2000000;

            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                Set<Integer> positions = new HashSet<>();
                while ((thisLine = br.readLine()) != null) {
                    String [] lineParts = thisLine.split("[=,:]");
                    coords[0][lc] = Integer.parseInt(lineParts[1]);
                    coords[1][lc] = Integer.parseInt(lineParts[3]);
                    coords[2][lc] = Integer.parseInt(lineParts[5]);
                    coords[3][lc] = Integer.parseInt(lineParts[7]);
                    coords[4][lc] = Math.abs(coords[0][lc] - coords[2][lc]) + Math.abs(coords[1][lc] - coords[3][lc]);

                    if(Math.abs(coords[1][lc] - targetrow) <= coords[4][lc]) {
                        int smallx = coords[0][lc] - (coords[4][lc] - (Math.abs(coords[1][lc] - targetrow)));
                        int bigx = coords[0][lc] + (coords[4][lc] - (Math.abs(coords[1][lc] - targetrow)));
                        for(int a = smallx; a <= bigx; a ++){
                            positions.add(a);
                        }
                   }
                    lc ++;
                }
                for(int loop = 0; loop < lc; loop ++){
                    if(coords[1][loop] == targetrow) positions.remove(coords[0][loop]);
                    if(coords[3][loop] == targetrow) positions.remove(coords[2][loop]);
                }
                System.out.println();
                System.out.println("There are "  + positions.size() +  " positions");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
