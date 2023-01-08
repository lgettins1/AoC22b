import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day18 {
    public static char[][][] solid = new char [21][21][21];
    public static List<String> tried = new ArrayList<>();
    public static List<Integer> triedDist = new ArrayList<>();
    public static List<String> trapped = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        String filename = "c:/users/lance/documents/AoC22Day18input.txt";
        String thisLine;
        List<String> sides = new ArrayList<>();
        for(int x = 0; x < 21; x ++){
            for(int y = 0; y < 21; y ++){
                for(int z = 0; z < 21; z++){
                    solid[x][y][z] = '.';
                }
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while((thisLine = br.readLine()) != null) {
                String[] coordinates = thisLine.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                int z = Integer.parseInt(coordinates[2]);
                solid[x][y][z] = '#';
                String[] faces = new String[6];
                faces[0] = ((double)x - 0.5) + "," + coordinates[1] + "," + coordinates[2];
                faces[1] = ((double)x + 0.5) + "," + coordinates[1] + "," + coordinates[2];
                faces[2] = coordinates[0] + "," + ((double)y - 0.5) + "," + coordinates[2];
                faces[3] = coordinates[0] + "," + ((double)y + 0.5) + "," + coordinates[2];
                faces[4] = coordinates[0] + "," + coordinates[1] + "," + ((double)z - 0.5);
                faces[5] = coordinates[0] + "," + coordinates[1] + "," + ((double)z + 0.5);
                for(int l = 0; l < 6; l ++){
                    if(sides.contains(faces[l])){
                        sides.remove(faces[l]);
                    } else {
                        sides.add(faces[l]);
                    }
                }
            }
            System.out.println("total lava surface area is " + sides.size());
            System.out.print("     ");
            for(int z = 0; z < 21; z++){
                System.out.print("\b\b\b\b\b" + (z * 5) + "%");
                for(int x = 0; x < 21; x ++){
                    for(int y = 0; y < 21; y ++){
                        if(solid[x][y][z] == '.'){
                            String start = x + "," + y + "," + z;
                            tried.clear();
                            triedDist.clear();
                            String escape = tryToEscape(x, y, z, "", 0);
                            if(escape.equals("bad") || escape.equals("blocked")) {
                                trapped.add(start);
                                sides.remove(((double)x - 0.5) + "," + y + "," + z);
                                sides.remove(((double)x + 0.5) + "," + y + "," + z);
                                sides.remove(x + "," + ((double)y - 0.5) + "," + z);
                                sides.remove(x + "," + ((double)y + 0.5) + "," + z);
                                sides.remove(x + "," + y + "," + ((double)z - 0.5));
                                sides.remove(x + "," + y + "," + ((double)z + 0.5));
                            }
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("exposed lava surface area is " + sides.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String tryToEscape(int x, int y, int z, String visited, int mc){
        String status = "bad";
        if(x == 0 && y == 0 && z == 0){
            return "good";
        }
        String v1 = x + "," + y + "," + z;
        visited += "->" + v1 + "-";
        if(tried.contains(v1)) {
            triedDist.set(tried.indexOf(v1), mc);
        }else{
            tried.add(v1);
            triedDist.add(mc);
        }
        if(trapped.contains(v1)){
            return "blocked";
        }
        mc ++;
        int xx = x; int yy = y; int zz = z;

        for(int dir = 0; dir < 6; dir ++){
            switch (dir) {
                case 0 -> xx = x - 1;
                case 1 -> {
                    xx = x;
                    yy = y - 1;
                    zz = z;
                }
                case 2 -> {
                    xx = x;
                    yy = y;
                    zz = z - 1;
                }
                case 3 -> {
                    xx = x + 1;
                    yy = y;
                    zz = z;
                }
                case 4 -> {
                    xx = x;
                    yy = y + 1;
                    zz = z;
                }
                case 5 -> {
                    xx = x;
                    yy = y;
                    zz = z + 1;
                }
            }
            if(status.equals("blocked")) return "blocked";
            String h = xx + "," + yy + "," + zz;
            if(xx >= 0 && xx < 21 && yy >= 0 && yy < 21 && zz >=0 &&  zz < 21) {
                if (status.equals("bad") && solid[xx][yy][zz] != '#' && !visited.contains(">" + h + "-")) {
                    boolean fnd = tried.contains(h) && triedDist.get(tried.indexOf(h)) <= mc;
                    if (!fnd) status = tryToEscape(xx, yy, zz, visited, mc);
                }
            }
        }
        return status;
    }
}
