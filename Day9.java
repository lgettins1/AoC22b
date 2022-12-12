import java.io.BufferedReader;
import java.io.FileReader;

public class Day9 {
    public static int[] posX = new int [10];
    public static int[] posY = new int [10];
    public static boolean[][][] tail = new boolean[500][500][10];

    public static void main(String [] args) {
        String thisLine;
       for(int a = 0; a < 10; a ++) {
            posX[a] = 150;
            posY[a] = 150;
        }
        int answer = 0;
        int answer2 = 0;
        for(int a = 0; a < 500; a ++){
            for(int b = 0; b < 500; b ++) {
                for (int c = 0; c < 10; c++) {
                    tail[a][b][c] = false;
                }
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day9input.txt"));
            while ((thisLine = br.readLine()) != null) {
                String[] command = thisLine.split(" ");
                int c = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "R" -> {
                        for (int a = 0; a < c; a++) {
                            posX[0]++;
                            updatetail();
                        }
                   }
                    case "L" -> {
                        for (int a = 0; a < c; a++) {
                            posX[0]--;
                            updatetail();
                         }
                    }
                    case "U" -> {
                        for (int a = 0; a < c; a++) {
                            posY[0]--;
                            updatetail();
                          }
                    }
                    case "D" -> {
                        for (int a = 0; a < c; a++) {
                            posY[0]++;
                            updatetail();
                        }
                    }
                }
            }
            for(int a = 0; a < 500; a ++){
                for(int b = 0; b < 500; b ++) {
                    if (tail[a][b][1]) answer ++;
                    if (tail[a][b][9]) answer2 ++;
                }
            }
            System.out.println("The answer to part one is " + answer);
            System.out.println("The answer to part two is " + answer2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updatetail(){
        for(int loop = 1; loop < 10; loop ++){
            if(Math.abs(posX[loop - 1] - posX[loop]) > 1){
                if(Math.abs(posY[loop - 1] - posY[loop]) > 1) {
                   posY[loop] += (posY[loop - 1] - posY[loop]) / 2;
                } else {
                    posY[loop] = posY[loop - 1];
                }
                posX[loop] += (posX[loop - 1] - posX[loop]) / 2;
            }
            if(Math.abs(posY[loop - 1] - posY[loop]) > 1){
                if(Math.abs(posX[loop] - posX[loop - 1]) > 1) {
                    posX[loop] = (posX[loop - 1] - posX[loop])/ 2;
                } else {
                    posX[loop] = posX[loop - 1];
                }
                posY[loop] += (posY[loop - 1] - posY[loop]) / 2;
            }
            tail[posX[loop]][posY[loop]][loop] = true;
        }
    }
}
