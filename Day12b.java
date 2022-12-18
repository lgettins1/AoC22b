import java.io.BufferedReader;
import java.io.FileReader;

public class Day12b {   public static char[][] map = new char[162][42];
    public static int endX = 0, endY = 0, row = 0, width;
    public static int minsteps = 8000;
    public static int [][] visited = new int[162][42];

    public static void main(String [] args) {
        String thisLine;
        int startX = 10;
        int startY = 10;

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day12input.txt"));
            while ((thisLine = br.readLine()) != null) {
                width = thisLine.length();
                for(int col = 0; col < width; col ++){
                    map[col][row] = thisLine.charAt(col);
                    if(map[col][row] == 'S'){
                        startX = col;
                        startY = row;
                    }
                    if(map[col][row] == 'E'){
                        endX = col;
                        endY = row;
                    }
                }
                row ++;
            }
            map[startX][startY] = 'a';
            map[endX][endY] = 'z';
            for(int curX = 0; curX < width; curX ++) {
                for (int curY = 0; curY < row; curY ++) {
                    for(int initX = 0; initX < width; initX ++){
                        for(int initY = 0; initY < row; initY ++){
                            visited[initX][initY] = 999;
                        }
                    }
                    int steps = 0;
                    if(map[curX][curY] == 'a') {
                        process(curX, curY, steps, "");
                    }
                }
            }
            System.out.println("minimum number of steps is " + minsteps);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void process(int x, int y, int steps, String vs){
        vs += x + "," + y + ":";

        if(x == endX && y == endY){
            if(steps < minsteps){
                minsteps = steps;
                System.out.println("Fewest steps so far is " + steps);
            }

        } else {
            steps ++;

            for(int loop = 0; loop < 4; loop ++){
                int xs = x + ((loop + 1) % 2) * (1 - loop );
                int ys = y + (loop % 2) * (loop - 2);
                if(xs >= 0 && xs < width && ys >= 0 && ys < row){
                    String curloc = xs + "," + ys;
                    int cll = curloc.length();
                    int found = 0;
                    for(int a = 0; a < vs.length() - cll; a ++){
                        if(vs.substring(a, a + cll).equals(curloc)) {
                            found = 1;
                            break;
                        }
                    }
                    if(map[xs][ys] <= map[x][y] + 1 &&
                            found == 0 && visited[xs][ys] > steps &&
                            map[xs][ys] != 'a'){
                        visited[xs][ys] = steps;
                        process(xs, ys, steps, vs);
                    }
                }
            }
        }
    }
}
