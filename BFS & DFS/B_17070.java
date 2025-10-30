import java.io.*;
import java.util.*;

public class Main{
    static int size;
    static int[][] graph;
    static int[][] visited;
    static int result = 0;

    static void backtracking(int A, int B, int C, int D){
        if( C==size && D==size){
            result++;
            return;
        }
        // 1. 가로
        if((A==C) && ((B+1)==D)){
            // 우측 이동
            if(D+1 <= size){
               if(graph[C][D+1] == 0){
                   backtracking(C,D,C,D+1);
               }
            }
            // 대각선 이동
            if((C+1<=size) &&(D+1<=size)){
                if(graph[C][D+1] == 0 && graph[C+1][D] == 0 && graph[C+1][D+1] == 0){
                    backtracking(C,D,C+1,D+1);
                }
            }
        }

        // 2. 세로
        else if((A+1==C) && (B==D)){
            // 세로 이동
            if(C+1<=size){
                if(graph[C+1][D]==0){
                    backtracking(C,D,C+1,D);
                }
            }
            // 대각선 이동
            if((C+1<=size) && (D+1<=size)){
                if(graph[C][D+1] == 0 && graph[C+1][D] == 0 && graph[C+1][D+1] == 0){
                    backtracking(C,D,C+1,D+1);
                }
            }
        }

        // 3. 대각선
        else if( (A+1==C) && (B+1==D) ){
            // 가로이동
            if(D+1 <= size){
               if(graph[C][D+1] == 0){
                   backtracking(C,D,C,D+1);
               }
            }
            // 세로 이동
            if(C+1<=size){
                if(graph[C+1][D]==0){
                    backtracking(C,D,C+1,D);
                }
            }
            // 대각선 이동
            if((C+1<=size) && (D+1<=size)){
                if(graph[C][D+1] == 0 && graph[C+1][D] == 0 && graph[C+1][D+1] == 0){
                    backtracking(C,D,C+1,D+1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        graph = new int[size+1][size+1];
        visited = new int[size+1][size+1];
        for(int i=1; i<=size; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=1; k<=size; k++){
                graph[i][k] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(1,1,1,2);
        System.out.print(result);
    }
}