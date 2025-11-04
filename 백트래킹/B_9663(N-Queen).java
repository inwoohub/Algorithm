A(r1,c1)
B(r2,c2)

r-c와 r+c 의 값이 겹치면 안됨.
+ 열 체크
// ----------------------------------------------------

import java.io.*;
import java.util.*;

public class Main{
    static int size, count;
    static int[][] graph;
    static int[] X;
    static int[] Y;
    static boolean[] col;

    static void backtracking(int depth){
        if(depth==size+1){
            count++;
            return;
        }
        
        for(int i=1; i<=size; i++){
            int cx = depth-i;
            int cy = depth+i;
            for(int k=1; k<depth; k++){
                if(X[k]==cx || Y[k] == cy || col[i]){
                    break;
                }
                if(k==depth-1){
                    X[depth] = cx;
                    Y[depth] = cy;
                    col[i] = true;
                    backtracking(depth+1);
                    X[depth] = Integer.MIN_VALUE;
                    Y[depth] = Integer.MIN_VALUE;
                    col[i] = false;
                }
            }
        }
    }

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        count = 0;
        int depth = 1;
        for(int i=1; i<=size; i++){
            graph = new int[size+1][size+1];
            X = new int[size+1];
            Y = new int[size+1];
            col = new boolean[size+1];
            Arrays.fill(X,Integer.MIN_VALUE);
            Arrays.fill(Y,Integer.MIN_VALUE);
            X[1] = depth-i;
            Y[1] = depth+i;
            col[i] = true;
            backtracking(depth+1);
        }
        System.out.print(count);
    }
}