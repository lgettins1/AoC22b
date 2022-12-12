import java.io.BufferedReader;
import java.io.FileReader;

public class Day10 {

    public static int cycle = 0;
    public static int register = 1;
    public static int answer = 0;

    public static void main(String [] args) {
        String thisLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day10input.txt"));
            while ((thisLine = br.readLine()) != null) {
                String[] command = thisLine.split(" ");
                if(command[0].equals("noop")){
                    cycle ++;
                    sigcheck();
                } else {
                    int c = Integer.parseInt(command[1]);
                    cycle ++;
                    sigcheck();
                    cycle ++;
                    sigcheck();
                    register += c;
                }
           }
            System.out.println();
            System.out.println("The answer to part one is " + answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sigcheck(){
        if((cycle - 20) % 40 == 0 ){
            answer += (cycle * register);
       }
        if(cycle % 40  >= register &&  cycle % 40 < register + 3){
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if(cycle % 40 == 0) System.out.println();
    }
}
