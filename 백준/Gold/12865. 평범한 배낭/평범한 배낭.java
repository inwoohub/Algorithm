import java.io.*;
import java.util.*;

public class Main{
    static int N, K;
    static int[][] DP;
    static int[][] bags;

    static void find(){
        for(int i=1; i<=N; i++){
            for(int w=1; w<=K; w++){
                DP[i][w] = DP[i-1][w];
                if( w>=bags[i][0]){
                    DP[i][w] = Math.max(DP[i][w] , DP[i-1][w-bags[i][0]]+bags[i][1]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        DP = new int[N+1][K+1];
        bags = new int[N+1][2];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            bags[i][0] = Integer.parseInt(st.nextToken());
            bags[i][1] = Integer.parseInt(st.nextToken());
        }
        find();
        System.out.print(DP[N][K]);
    }
}