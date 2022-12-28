import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day16part2 {
    public static String[][] tunnelList = new String[61][5];
    public static String[][] scanInfo = new String[61][3];
    public static String[][] routes = new String[225][3];
    public static int routeCount = 0;
    public static int bestFlow = 0;
    public static int valves = 0;


    public static void main(String[] args) throws Exception {
        String filename = "c:/users/lance/documents/AoC22Day16input.txt";
        String thisLine;
        int maxTunnels = 0;
        List<String> scan = new ArrayList<>();
        String[] ends = new String[20];
        ends[0]="AA";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((thisLine = br.readLine()) != null) {
                scan.add(thisLine);
                String[] a = thisLine.split(",");
                if (maxTunnels < a.length) maxTunnels = a.length;
                valves++;
            }
            int usefulValves = 0;
            for (int a = 0; a < valves; a++) {
                String[] cd = scan.get(a).split(",");
                int tc = cd.length;
                String[] sd = cd[0].split(" ");
                String[] ed = sd[4].split("[=;]");
                scanInfo[a][0] = sd[1];
                scanInfo[a][1] = ed[1];
                int flow = Integer.parseInt(ed[1]);
                if(flow > 0){
                    usefulValves ++;
                    ends[usefulValves] = scanInfo[a][0];
                }
                scanInfo[a][2] = Integer.toString(tc);
                tunnelList[a][0] = sd[9];
                if (tc > 1) {
                    for (int b = 1; b < tc; b++) {
                        tunnelList[a][b] = cd[b].trim();
                    }
                }
            }

            for(int a = 0; a <= usefulValves; a ++) {
                for (int b = a; b <= usefulValves; b++) {
                    if (a != b) {
                        int md = minDist(ends[a], ends[b], 0, "");
                        routes[routeCount][0] = ends[a];
                        routes[routeCount][1] = ends[b];
                        routes[routeCount][2] = Integer.toString(md);
                        routeCount ++;
                    }
                }
            }

            StringBuilder remaining = new StringBuilder();
            for(int a = 1; a <=usefulValves; a ++) {
                remaining.append(ends[a]).append(",");
            }
            remaining = new StringBuilder(remaining.substring(0, remaining.length() - 1));
            for(int a = 1; a <= usefulValves; a ++){
                calcFlow("AA", ends[a], 0, "AA", remaining.toString(), 0, 0);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public int getPos(String node){
        for(int a = 0; a < valves; a++){
            if(scanInfo[a][0].equals(node)){
                return a;
            }
        }
        return 0;
    }
    static public int minDist(String a, String b, int steps, String vis) {
        vis += a + ",";
        int index = getPos(a);
        int minValve = valves;
        int cv = 0;
        for (int t = 0; t < Integer.parseInt(scanInfo[index][2]); t++) {
            if (tunnelList[index][t].equals(b)) {
                return cv + 1;
            }
        }
        if (steps < 30) {
            for (int t = 0; t < Integer.parseInt(scanInfo[index][2]); t++) {
                String[] pv = vis.split(",");
                int visited = 0;
                for (String s : pv) {
                    if (s.equals(tunnelList[index][t])) {
                        visited = 1;
                        break;
                    }
                }
                if (visited == 0) {
                    cv = 1 + minDist(tunnelList[index][t], b, steps + 1, vis);
                    if (cv < minValve) minValve = cv;
                }
            }
            return minValve;
        } else {
            return 999;
        }
    }
    public static void calcFlow(String a, String b, int time, String route, String remaining, int flowRate, int totalFlow){
        route += "->" + b;
        int bIndex = getPos(b);
        for(int aa = 0; aa < routeCount; aa ++){
            if((routes[aa][0].equals(a) && routes[aa][1].equals(b)) ||
                    (routes[aa][1].equals(a) && routes[aa][0].equals(b))){
                int elapsed = Integer.parseInt(routes[aa][2]) + 1;
                time += elapsed;
                totalFlow += flowRate * elapsed;
                break;
            }
        }
        int oldFlowRate = flowRate;
        flowRate += Integer.parseInt(scanInfo[bIndex][1]);
        List<String> ends = new ArrayList<>();
        StringBuilder newRemaining = new StringBuilder();
        String[] rValves = remaining.split(",");
        for (String rVal : rValves) {
            if (!rVal.equals(b)) {
                newRemaining.append(rVal).append(",");
                ends.add(rVal);
            }
        }

        remaining = newRemaining.substring(0, newRemaining.length() - 1);

            if(time < 26) {
                for (String thisValve : ends) {
                    calcFlow(b, thisValve, time, route, remaining, flowRate, totalFlow);
                }
            } else {
            totalFlow += (26 - time ) * oldFlowRate;
            for(String thisValve : ends) {
                elephantFlow("AA", thisValve, totalFlow, route, 0, "AA", remaining, 0, 0);
            }
        }
    }

    public static void elephantFlow(String a, String b, int meFlow, String meRoute, int time, String route, String remaining, int flowRate, int totalFlow){
        route += "->" + b;
        int bIndex = getPos(b);
        for(int aa = 0; aa < routeCount; aa ++){
            if((routes[aa][0].equals(a) && routes[aa][1].equals(b)) ||
                    (routes[aa][1].equals(a) && routes[aa][0].equals(b))){
                int elapsed = Integer.parseInt(routes[aa][2]) + 1;
                time += elapsed;
                totalFlow += flowRate * elapsed;
                break;
            }
        }
        int oldFlowRate = flowRate;
        flowRate += Integer.parseInt(scanInfo[bIndex][1]);
        List<String> ends = new ArrayList<>();
        StringBuilder newRemaining = new StringBuilder();
        String[] rValves = remaining.split(",");
        for (String rVal : rValves) {
            if (!rVal.equals(b)) {
                newRemaining.append(rVal).append(",");
                ends.add(rVal);
            }
        }
        if(ends.size() > 0 && time < 26) {
            remaining = newRemaining.substring(0, newRemaining.length() - 1);
            for (String thisValve : ends) {
                elephantFlow(b, thisValve, meFlow, meRoute, time, route, remaining, flowRate, totalFlow);
            }
        } else {
            totalFlow += (26 - time) * oldFlowRate;
            if(totalFlow + meFlow > bestFlow) {
                bestFlow = totalFlow + meFlow;
                System.out.println("Best route so far " + meRoute + " " + meFlow +
                        " and elephant " + route + " " + totalFlow + " = " + bestFlow);
            }
        }
    }
}
