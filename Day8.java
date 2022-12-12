import java.io.BufferedReader;
import java.io.FileReader;

public class Day8 {
    public static void main(String [] args) {
        String thisLine;
        char[][] tree = new char [100][100];
        int row = 0;
        int width = 0;
        int answer = 0;
        int answer2 = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day8input.txt"));
            while ((thisLine = br.readLine()) != null) {
                for(int col = 0; col < thisLine.length(); col ++){
                    width = thisLine.length();
                    tree[col][row] = thisLine.charAt(col);
                }
                row ++;
            }
        for(int x = 0; x < width; x ++){
            for(int y = 0; y < row; y ++){
                int leftcount = 0;
                int rightcount = 0;
                int upcount = 0;
                int downcount = 0;
                int obscure = 0;
                int obscure2 = 0;

                if(x > 0){
                    for(int scx = x - 1; scx >= 0; scx --){
                        leftcount ++;
                        if((int)tree[scx][y] >= (int)tree[x][y]){
                            obscure2 ++;
                            break;
                        }
                    }
                    if(obscure2 > 0) obscure ++;
                }
                obscure2 = 0;
                if(x < width - 1){
                    for(int scx = x + 1; scx < width; scx ++){
                        rightcount ++;
                        if((int)tree[scx][y] >= (int)tree[x][y]){
                            obscure2 ++;
                            break;
                        }
                    }
                    if(obscure2 > 0) obscure ++;
                }
                obscure2 = 0;
                if(y > 0){
                    for(int scy = y - 1; scy >= 0 ; scy --){
                        upcount ++;
                        if((int)tree[x][scy] >= (int)tree[x][y]){
                            obscure2 ++;
                            break;
                        }
                    }
                    if(obscure2 > 0) obscure ++;
                }
                obscure2 = 0;
                if(y < row - 1) {
                    for (int scy = y + 1; scy < row; scy ++) {
                        downcount ++;
                        if ((int) tree[x][scy] >= (int) tree[x][y]) {
                            obscure2++;
                            break;
                        }
                    }
                    if (obscure2 > 0) obscure++;

                }
                if(obscure < 4) answer ++;
                int b = leftcount * rightcount * upcount * downcount;
                if(b > answer2) answer2 = b;

            }
        }
        System.out.println("The answer to part one is " + answer);
        System.out.println("the answer to part two is " + answer2);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
