import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day6b {
    public static void main(String [] args) {
        try {
            String test = "xxxxxxxxxxxxxx";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
                    ("c:/users/lance/documents/AoC22Day6input.txt"), StandardCharsets.UTF_8));
            int c;
            int charnum = 0;
            int success;
            while ((c = br.read()) != -1) {
                success = 1;
                charnum ++;
                String a = Character.toString((char) c);
                test = test.substring(1) + a;
                for(int ol = 0; ol < 13; ol ++){
                    for(int il = ol + 1; il <= 13; il ++){
                        if(test.substring(ol, ol + 1).equals(test.substring(il, il + 1)) ){
                            success = 0;
                            break;
                        }
                    }
                }
                if(success == 1 && charnum > 13){
                    System.out.println("The first fourteen char string of unique characters" +
                            " is " + test + " at position " + charnum);
                break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
