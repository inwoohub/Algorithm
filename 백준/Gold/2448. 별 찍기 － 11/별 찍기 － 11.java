import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static String[] star;

    static void start(int i){
        int bottom = (int) (3*Math.pow(2,i)) ;
        int middle = bottom/2;
        for(int j=middle; j<bottom; j++){
            star[j] = star[j-middle]+" "+star[j-middle];
        }
        String blank = " ".repeat(middle);
        for(int j=0; j<middle; j++){
            star[j] = blank+star[j]+blank;
        }
        
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        star = new String[size];
        star[0] = "  *  ";
        star[1] = " * * ";
        star[2] = "*****";

        for(int i=1; 3*Math.pow(2,i) <= size; i++){
            start(i);
        }

        for(int i=0; i<size; i++){
            sb.append(star[i]+"\n");
        }
        System.out.print(sb);
    }
}