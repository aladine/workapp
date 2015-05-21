import java.io.*;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream =  new FileInputStream("D:\\Gdrive\\IdeaProjects\\WorkApp\\src\\input_03.txt");//System.in;//new File("input_01.txt");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        long now = System.currentTimeMillis();
//        SherlockAndMiniMax solver = new SherlockAndMiniMax();
//        solver.solve(1, in, out);
//        Boxes solver = new Boxes();
//        solver.solve(1, in, out);

        ConnectCampus solver = new ConnectCampus();
        int T = in.readInt();
        solver.solve(T, in, out);



//        SendingEmail solver = new SendingEmail();
//        int T = in.readInt();
//        for (int i = 0; i < T; i++) {
//            solver.solve(i+1, in, out);
//        }

//        LiftHopping solver = new LiftHopping();
//        int n = in.readInt();
//        int k = in.readInt();
//        solver.solve(n, k ,in, out);

//        zumata solver = new zumata();
//        int T = in.readInt();
//        for (int i = 0; i < T; i++) {
//            solver.solve( i ,in, out);
//        }

        System.out.printf("Total processing time(ms): %d ", (System.currentTimeMillis() - now));
        out.close();
    }
}