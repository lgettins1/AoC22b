import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Day17 {
    public static char[][] column = new char[3500][7];

    public static void main(String[] args) throws IOException {
        String filename = "c:/users/lance/documents/AoC22Day17input.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String thisLine = br.readLine();

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
        for (int a = 0; a < 3500; a++) {
            for (int b = 0; b < 7; b++) {
                column[a][b] = '.';
            }
        }
        int xPos;
        int windPos = 0;
        int sh = 0;

        for (int block = 1; block <= 2022; block ++) {
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
            System.out.println(block  + " blocks - tower height: " + towerHeight);
           sh ++;
            if(sh == 5) sh = 0;
        }
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
