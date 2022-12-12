import java.io.BufferedReader;
import java.io.FileReader;

public class Day7 {
    public static String[] output = new String [1050];
    public static int height = 0;
    public static int answer = 0;
    public static int dircount = 0;
    public static int[] dirsize = new int [300];

    public static void main(String [] args) {
        String thisLine;
       try {
           BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day7input.txt"));
            while ((thisLine = br.readLine()) != null) {
                output[height] = thisLine;
                height++;
            }
            process("/", 0);
            System.out.println("The answer is to part 1 is " + answer);
            int answer2 = 100000000;
            for(int c = 0; c < dircount; c ++){
                if(dirsize[c] > (dirsize[dircount - 1] - 40000000) && (dirsize[c] < answer2)){
                    answer2 = dirsize[c];
                }
            }
           System.out.println("The answer is to part 2 is " + answer2);


       } catch (Exception e) {
        e.printStackTrace();
        }
    }
    private static int process(String a, int start){
        String[] subdir = new String[100];
        int row = 0;
        int found = 0;
        int depth = 0;
        for(int scan = start; scan < height; scan ++){
            if(found == 0) {
                String[] b = output[scan].split(" ");
                if (b[0].equals("$") && b[1].equals("cd")) {
                    if(b[2].equals("..")){
                        depth --;
                    } else {
                        depth ++;
                        if (b[2].equals(a) && depth == 1) {
                            row = 0;
                            while (row < height - scan) {
                                subdir[row] = output[row + scan + 2];
                                row++;
                                if (row >= height - scan - 2 || output[row + scan + 2].split(" ")[0].equals("$")) {
                                    start = scan + row;
                                    found = 1;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        int bytecount = 0;
        for(int b = 0; b < row; b ++){
            String[] sp = subdir[b].split(" ");
            if(sp[0].equals("dir")){
                bytecount += process(sp[1], start);
            } else {
                bytecount += Integer.parseInt(sp[0]);
            }
        }
        dirsize[dircount] = bytecount;
        dircount ++;
        if(bytecount <= 100000){
            answer += bytecount;
        }
        return bytecount;
    }
}