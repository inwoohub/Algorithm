import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String InputA = br.readLine();
        String InputB = br.readLine();

        int sizeA = InputA.length();
        int sizeB = InputB.length();
        
        char[] arrA = new char[sizeA+1];
        char[] arrB = new char[sizeB+1];
        
        for(int i=1; i<=sizeA; i++){
            arrA[i] = InputA.charAt(i-1);
        }
        for(int i=1; i<=sizeB; i++){
            arrB[i] = InputB.charAt(i-1);
        }

        int[][] DP = new int[sizeB+1][sizeA+1];
        
        for(int i=1; i<=sizeA; i++){
            for(int k=1; k<=sizeB; k++){
                if(arrA[i] == arrB[k]){
                    DP[k][i] = DP[k-1][i-1]+1;
                }
                else{
                    DP[k][i] = Math.max(DP[k-1][i],DP[k][i-1]);
                }
            }
        }

        int len = DP[sizeB][sizeA];
        System.out.println(len);
        if(len==0) return;
        
        StringBuilder sb = new StringBuilder();
        int y = sizeB;
        int x = sizeA;

        while(y>0 && x>0){
            if(arrA[x] == arrB[y]){
                sb.append(arrA[x]);
                y--;
                x--;
            }
            else{
                if(DP[y-1][x]>=DP[y][x-1]){
                    y--;
                }else{
                    x--;
                }
            }
        }
        System.out.print(sb.reverse().toString());
    }
}