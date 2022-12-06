import java.io.BufferedReader;
import java.io.FileReader;

public class Day5 {
    public static void main(String [] args) {
        String thisLine;
        String[] header = new String [15];
        int height = 0;
            try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day5input.txt"));
            while ((thisLine = br.readLine()).length() > 0) {
                header[height] = thisLine;
                height++;
            }
            int stackCount = (header[height - 1].length() + 2) / 4;
            String[] stacks = new String[stackCount + 1];
            for(int a = 1; a <= stackCount; a ++){
                stacks[a] = "";
            }
            for(int ol = height - 1; ol >= 0; ol --){
                for(int il = 1; il <= stackCount; il ++){
                    if(header[ol].charAt((4 * il) - 2) != ' '){
                        stacks[il] += header[ol].substring((4 * il) - 3,(4 * il) - 2);
                    }
                }
            }
            int count;
            int stack1;
            int stack2;
            while((thisLine = br.readLine()) != null){
                String[] a = thisLine.split(" ");
                count = Integer.parseInt(a[1]);
                stack1 = Integer.parseInt(a[3]);
                stack2 = Integer.parseInt(a[5]);
                for(int cl = 0; cl < count; cl ++){
                    stacks[stack2] += stacks[stack1].substring(stacks[stack1].length() - 1);
                    stacks[stack1] = stacks[stack1].substring(0, stacks[stack1].length() - 1);
                }
            }
            StringBuilder answer = new StringBuilder();
            for(int pl = 1; pl <= stackCount; pl ++) {
                answer.append(stacks[pl].substring(stacks[pl].length() - 1));
            }
            System.out.println("Give the elves the message " + answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}