import java.io.BufferedReader;
import java.io.FileReader;

public class Day15 {


    public static void main(String[] args) throws Exception{
        String filename = "c:/users/lance/documents/AoC22Day15inputb.txt";
        String thisLine;
        int minx = 1000; int miny = 1000; int maxx = 0; int maxy = 0;
        int[][] coords = new int[5][35];
        int linecount = 0;
        int targetrow = 10;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((thisLine = br.readLine()) != null) {
                String [] lineParts = thisLine.split("[=,:]");
                coords[0][linecount] = Integer.parseInt(lineParts[1]);
                coords[1][linecount] = Integer.parseInt(lineParts[3]);
                coords[2][linecount] = Integer.parseInt(lineParts[5]);
                coords[3][linecount] = Integer.parseInt(lineParts[7]);
                coords[4][linecount] = Math.abs(coords[0][linecount] - coords[2][linecount]) +
                        Math.abs(coords[1][linecount] - coords[3][linecount]);
                if(coords[0][linecount] - coords[4][linecount] < minx) minx = coords[0][linecount] - coords[4][linecount];
                if(coords[0][linecount] + coords[4][linecount] > maxx) maxx = coords[0][linecount] + coords[4][linecount];
                if(coords[1][linecount] - coords[4][linecount] < miny) miny = coords[1][linecount] - coords[4][linecount];
                if(coords[1][linecount] + coords[4][linecount] > maxy) maxy = coords[1][linecount] + coords[4][linecount];
                if(coords[2][linecount] - coords[4][linecount] < minx) minx = coords[2][linecount] - coords[4][linecount];
                if(coords[2][linecount] + coords[4][linecount] > maxx) maxx = coords[2][linecount] + coords[4][linecount];
                if(coords[3][linecount] - coords[4][linecount] < miny) miny = coords[3][linecount] - coords[4][linecount];
                if(coords[3][linecount] + coords[4][linecount] > maxy) maxy = coords[3][linecount] + coords[4][linecount];
                linecount ++;
            }
            System.out.println("X " + minx + " -> " + maxx + " Y " + miny + " -> " + maxy + " " + linecount + " lines." );
           int xdim = maxx - minx + 1;
            int ydim = maxy - miny + 1;
            char[][]grid = new char[xdim][ydim];
            for(int b = 0; b < ydim; b ++){
                for(int a = 0; a < xdim; a ++){
                    grid[a][b]= '.';
                }
            }
            for(int row = 0; row < linecount; row ++){
                grid[coords[0][row] - minx][coords[1][row] - miny] = 'S';
                grid[coords[2][row] - minx][coords[3][row] - miny] = 'B';
                for(int yl = -coords[4][row]; yl <= coords[4][row]; yl ++){
                    for(int xl = 0; xl <= coords[4][row]; xl ++){
                        if(xl + Math.abs(yl) <= coords[4][row]){
                            if(grid[coords[0][row] - minx + xl][coords[1][row] - miny + yl] == '.') {
                                grid[coords[0][row] - minx + xl][coords[1][row] - miny + yl] = '#';
                            }
                            if(grid[coords[0][row] - minx - xl][coords[1][row] - miny + yl] == '.') {
                                grid[coords[0][row] - minx - xl][coords[1][row] - miny + yl] = '#';
                            }
                        }
                    }
                }
            }


            for(int b = 0; b < ydim; b ++){
                for(int a = 0; a < xdim; a ++){
                    System.out.print(grid[a][b]);
                }
                System.out.println();
            }
            int posCount = 0;
            System.out.println();
            for(int a = 0; a < xdim; a ++){
                System.out.print(grid[a][targetrow - miny]);
                if(grid[a][targetrow - miny] == '#') posCount ++;
            }
            System.out.println();
            System.out.println("There are " + posCount + " positions");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
