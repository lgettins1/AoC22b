import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.Math.abs;

public class Day14 {
    public static int maxx = 546;
    public static int minx = 474;
    public static int maxy = 168;
    public static int miny = 0;
    public static char[][]cave = new char[maxx + 1][maxy + 1];


    public static void main(String[] args) throws Exception{
        String filename = "c:/users/lance/documents/AoC22Day14input.txt";
        String thisLine;
        int sandcount = 0;
        for(int a= 0; a <= maxx; a ++){
            for(int b = 0; b <= maxy; b ++){
                cave[a][b] = '.';
            }
        }


        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((thisLine = br.readLine()) != null) {
                String[] coords = thisLine.split(" -> ");
                String[] start = coords[0].split(",");
                int x = Integer.parseInt(start[0]);
                int y = Integer.parseInt(start[1]);
                int x2; int y2;
                for (int z = 1; z < coords.length; z ++) {
                    String coord = coords[z];
                    String[] pair = coord.split(",");
                     x2 = Integer.parseInt(pair[0]);
                     y2 = Integer.parseInt(pair[1]);
                    if(x2 != x) {
                        int a = x;
                        while(a != x2){
                            cave[a][y] = '#';
                            a += abs(x2 - x) / (x2 - x);
                        }
                    } else {
                        int b = y;
                         while(b != y2) {
                             cave[x][b] = '#';
                             b += abs(y2 - y) / (y2 - y);
                        }
                    }
                    cave[x2][y2] = '#';
                    x = x2;
                    y = y2;
                }
            }

            int[] sandloc = new int[2];
            int[] newloc = new int[2];
            int maxed = 0;
            while(maxed == 0) {
                sandloc[0] = 500;
                sandloc[1] = 0;
                int falling = 1;
                while (falling == 1 && maxed == 0) {
                    newloc = testcave(sandloc);
                    if(newloc[1] == maxy - 1){
                         maxed = 1;
                    }
                    if (newloc[0] == sandloc[0] && newloc[1] == sandloc[1]) {
                        falling = 0;
                    } else {
                        sandloc = newloc;
                    }
                }

                cave[newloc[0]][newloc[1]] = 'o';
                sandcount++;
            }

            for(int b = miny; b <= maxy; b ++){
                for(int a = minx; a <= maxx; a ++){
                    System.out.print(cave[a][b]);
                }
                System.out.println();
            }
            System.out.println("number of grains of sand is " + (sandcount - 1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int[] testcave(int[] sandloc){
        int sandx = sandloc[0];
        int sandy = sandloc[1];
        int[] newloc = new int[2];
        newloc[0] = sandx;
        newloc[1] = sandy;
        if(cave[sandx][sandy + 1] == '.'){
            newloc[1] = sandy + 1;
        } else {
            if(cave[sandx - 1][sandy + 1] == '.'){
                newloc[1] = sandy + 1;
                newloc[0] = sandx - 1;
            } else {
                if (cave[sandx + 1][sandy + 1] == '.') {
                    newloc[1] = sandy + 1;
                    newloc[0] = sandx + 1;

                }
            }
        }
        return newloc;
    }
}
