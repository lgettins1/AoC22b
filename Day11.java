import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day11 {
    public static void main(String [] args) {
        String thisLine;
        int answer;
        int monkeyCount = 0;
        int[][] monkeyItems = new int [8][30];
        int[] itemCount = new int [8];
        int[] inspections = new int [8];
        String[][] operation = new String [8][3];
        int[] test = new int [8];
        int[][] target = new int [8][2];
        try {
            BufferedReader br = new BufferedReader(new FileReader("c:/users/lance/documents/AoC22Day11input.txt"));
            while (br.readLine() != null) {
                thisLine = br.readLine();
                String[] a = thisLine.split(":");
                String[] b = (a[1].split(","));
                for(int c = 0; c < b.length; c ++){
                    monkeyItems[monkeyCount][c] = Integer.parseInt(b[c].trim());
                }
                itemCount[monkeyCount] = b.length;
                inspections[monkeyCount] = 0;
                thisLine = br.readLine();
                String[] d = thisLine.split( "=");
                String[] oper = d[1].split(" ");
                operation[monkeyCount][0] = oper[2];
                operation[monkeyCount][1] = oper[3];
                thisLine = br.readLine();
                String[] e = thisLine.split(" ");
                test[monkeyCount] = Integer.parseInt(e[5]);
                thisLine=br.readLine();
                e = thisLine.split(" ");
                target[monkeyCount][0] = Integer.parseInt(e[9]);
                thisLine=br.readLine();
                e = thisLine.split(" ");
                target[monkeyCount][1] = Integer.parseInt(e[9]);
                br.readLine();
                monkeyCount ++;
            }
            for(int round = 1; round < 21; round ++){
                for(int monkey = 0; monkey < monkeyCount; monkey ++){
                    for(int item = 0; item < itemCount[monkey]; item ++){
                       int firstval = monkeyItems[monkey][item];
                       int secondval;
                       if(operation[monkey][1].equals("old")){
                           secondval = firstval;
                       } else {
                           secondval = Integer.parseInt(operation[monkey][1]);
                       }
                        switch (operation[monkey][0]) {
                            case "+" -> firstval += secondval;
                            case "-" -> firstval -= secondval;
                            case "*" -> firstval = firstval * secondval;
                            case "/" -> firstval = firstval / secondval;
                        }
                       firstval = firstval / 3;
                       if(firstval % test[monkey] == 0){
                           monkeyItems[target[monkey][0]][itemCount[target[monkey][0]]] = firstval;
                           itemCount[target[monkey][0]] ++;
                       } else {
                           monkeyItems[target[monkey][1]][itemCount[target[monkey][1]]] = firstval;
                           itemCount[target[monkey][1]] ++;
                       }
                       inspections[monkey] ++;
                    }
                    itemCount[monkey] = 0;
                }
            }
            Arrays.sort(inspections);
            answer = inspections[inspections.length - 1] * inspections[inspections.length - 2];
            System.out.println("The answer to part one is " + answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
