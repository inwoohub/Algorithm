import java.io.*;
import java.util.*;

public class Main{

    static int INF = Integer.MAX_VALUE;

    static int cost(int from, int to){
        if (from == to) return 1;
        if (from == 0) return 2;
        if(Math.abs(from-to) == 2) return 4;
        return 3;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            int A = Integer.parseInt(st.nextToken());
            if(A==0)break;
            list.add(A);
        }
        
        int[][] dp = new int[5][5];
        int[][] ndp = new int[5][5];

        for(int i=0; i<5; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int x:list){
            for(int i=0; i<5; i++){
                Arrays.fill(ndp[i], INF);
            }

            for(int l=0; l<5; l++){
                for(int r=0; r<5; r++){
                    int cur = dp[l][r];
                    if(cur>=INF) continue;

                    // 왼발로 x 누르기
                    if(x != r){
                        ndp[x][r] = Math.min(ndp[x][r], cur+cost(l,x));
                    }

                    // 오른발로 x 누르기
                    if( x != l){
                        ndp[l][x] = Math.min(ndp[l][x], cur+cost(r,x));
                    }
                }    
            }
            int[][] tmp = dp;
            dp = ndp;
            ndp = tmp;
        }

        int ans = INF;
        for (int l=0; l<5; l++){
            for(int r=0; r<5; r++){
                ans = Math.min(ans, dp[l][r]);
            }
        }
        System.out.print(ans);
    }
    
}