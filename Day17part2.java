import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day17part2 {
    public static char[][] column = new char[24500][7];

    public static void main(String[] args) throws IOException {
        long target = 1000000000000L;
        String filename = "c:/users/lance/documents/AoC22Day17input.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String thisLine = br.readLine();
        int[] towerRow = new int[20000];
        int[] blockHeight = new int [20000];

        ArrayList<String>[] shapes = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            shapes[i] = new ArrayList<>();
        }
        shapes[0].add("####");
        shapes[1].add(".#.");
        shapes[1].add("###");
        shapes[1].add(".#.");
        shapes[2].add("..#");
        shapes[2].add("..#");
        shapes[2].add("###");
        for (int a = 0; a < 4; a++) {
            shapes[3].add("#");
        }
        shapes[4].add("##");
        shapes[4].add("##");
        int towerHeight = 0;
        for (int a = 0; a < 24500; a++) {
            for (int b = 0; b < 7; b++) {
                column[a][b] = '.';
            }
        }
        int xPos;
        int windPos = 0;
        int sh = 0;

        for (int block = 1; block <= 13000; block ++) {
            xPos = 2;
            int yPos = towerHeight + 3;
            boolean landed = false;

            while (!landed) {
                if (thisLine.charAt(windPos) == '>') {
                    if (xPos + shapes[sh].get(0).length() < 7){
                        if (!check(shapes[sh], yPos, xPos + 1)) xPos ++;
                    }
                } else {
                    if (xPos > 0) {
                        if (!check(shapes[sh], yPos, xPos - 1)) xPos --;
                    }
                }
                windPos ++;
                if(windPos == thisLine.length()) windPos = 0;

                if (check(shapes[sh], yPos - 1, xPos)){
                    landed = true;
                    for(int rh = 0; rh < shapes[sh].size(); rh ++){
                        for(int rw = 0; rw < shapes[sh].get(rh).length(); rw ++){
                            if(column[yPos + rh][xPos + rw] == '.') {
                                column[yPos + rh][xPos + rw] = shapes[sh].get(shapes[sh].size() - 1 - rh).charAt(rw);
                            }
                        }
                    }
                    if(yPos + shapes[sh].size() > towerHeight) towerHeight = yPos + shapes[sh].size();

                } else {
                    yPos --;
                }
            }
            blockHeight[block] = towerHeight;
            sh ++;
            if(sh == 5) sh = 0;
        }
        for(int a = 0; a < 20000; a ++){
            int value = 0;
            for(int aa = 0; aa < 7; aa ++){
                if(column[a][aa] == '#') value += Math.pow(2, aa);
            }
            towerRow[a] = value;
        }
        int repeatInterval = 0;
        int[] baseSeries = new int[40];
        System.arraycopy(towerRow, 300, baseSeries, 0, 40);

        for(int scan = 400; scan < 15000; scan ++){
            int[] compareSeries = new int[40];
            System.arraycopy(towerRow, scan, compareSeries, 0, 40);
            if(Arrays.equals(baseSeries, compareSeries)){
                repeatInterval = scan - 300;
                break;
            }
        }
        int firstBlock = 200;
        int blockInterval = 0;
        int a = 0;
        while(blockInterval == 0){
            if(blockHeight[firstBlock] + repeatInterval == blockHeight[firstBlock + a] &&
                    blockHeight[firstBlock + 3] + repeatInterval == blockHeight[firstBlock + a + 3]){
                blockInterval = a;
            }
            a ++;
        }
        System.out.println();
        System.out.println("The pattern repeats every " + repeatInterval + " rows, which is every "
                + blockInterval + " blocks.");
        double loops = Math.floor(target / blockInterval);
        int extraBit = (int)(target - (long)(loops * blockInterval));
        target = (long)(loops * repeatInterval) + (long) blockHeight[extraBit];
        System.out.println("Therefore the loops account for " + (long)(loops * repeatInterval) + " and then we need to add ");
        System.out.println(extraBit + " for the blocks before the loops, and the blocks up to 1000000000000");
        System.out.println();
        System.out.println("The total height will be " + target);


    }
    public static boolean check(ArrayList<String> shapes, int yP, int xP){
        if(yP < 0)   return true;
        for(int a = 0; a < shapes.size(); a++){
            for(int b = 0; b < shapes.get(a).length(); b ++){
                if(shapes.get(shapes.size() - a - 1).charAt(b) == '#' && column[yP + a][xP + b] == '#') return true;
            }
        }
        return false;
    }
}
