import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day16 {
    public static String[][]tunnelList = new String[61][5];
    public static String[][]scanInfo = new String[61][3];
    public static int bestFlow = 0;
    public static int valves = 0;


    public static void main(String[] args) throws Exception{
        String filename = "c:/users/lance/documents/AoC22Day16inputb.txt";
        String thisLine;
        int maxTunnels = 0;
        List<String> scan = new ArrayList<>();
        List<Integer> openValves = new ArrayList<>();
        int minutes = 0;
        int firstValve = 0;
        int currentFlow = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while((thisLine = br.readLine()) != null) {
                scan.add(thisLine);
                String[] a = thisLine.split(",");
                if(maxTunnels < a.length) maxTunnels = a.length;
                valves ++;
            }
            for(int a = 0; a < valves; a ++){
                String[] cd = scan.get(a).split(",");
                int tc = cd.length;
                String[] sd = cd[0].split(" ");
                String[] ed = sd[4].split("[=;]");
                scanInfo[a][0] = sd[1];
                scanInfo[a][1] = ed[1];
                scanInfo[a][2] = Integer.toString(tc);
                tunnelList[a][0] = sd[9];
                if(tc > 1){
                    for(int b = 1; b < tc; b ++){
                        tunnelList[a][b] = cd[b].trim();
                    }
                }
            }
            decide(firstValve, minutes, openValves, currentFlow);
            System.out.println("The answer is " + bestFlow);


        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    public static void decide(int valveNo, int minutes, List<Integer> openValves, int currentFlow){
        minutes ++;
        for (Integer vl : openValves) {
            currentFlow += Integer.parseInt(scanInfo[vl][1]);
        }
        System.out.println(minutes + " " + scanInfo[valveNo][0] + " " + openValves + " " + bestFlow);
        if (minutes >= 30) {
            if(currentFlow > bestFlow) bestFlow = currentFlow;

        } else {
            boolean open = openValves.contains(valveNo);
            if(open){
                for(int tunnels = 0; tunnels < Integer.parseInt(scanInfo[valveNo][2]); tunnels ++){
                    for(int ft = 0; ft < valves; ft ++){
                        if(tunnelList[valveNo][tunnels].equals(scanInfo[ft][0])){
                            decide(ft, minutes, openValves, currentFlow);
                        }
                    }
                }
            } else {
                for (int tunnels = 0; tunnels <= Integer.parseInt(scanInfo[valveNo][2]); tunnels++) {
                    if (tunnels == 0) {
                        openValves.add(valveNo);
                        decide(valveNo, minutes, openValves, currentFlow);
                    } else {
                        for (int ft = 0; ft < valves; ft++) {
                            if (tunnelList[valveNo][tunnels - 1].equals(scanInfo[ft][0])) {
                                decide(ft, minutes, openValves, currentFlow);
                            }
                        }
                    }
                }
            }
        }
    }
}

