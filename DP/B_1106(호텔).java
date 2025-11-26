// C : 목표 고객,  N : 도시 개수, result : 총 비용
// 배낭 문제 (DP사용)


import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int result = Integer.MAX_VALUE;

        int[] cost = new int[N];
        int[] person = new int[N];
        int[] DP = new int[C+100+1];
        Arrays.fill(DP,1000000000);
        DP[0] = 0;
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            cost[i] = A;
            person[i] = B;
        }
        
        for(int i=0; i<N; i++){
            for(int p=person[i]; p<=C+100; p++){
                DP[p] = Math.min(DP[p], DP[p-person[i]]+cost[i]);
            }
        }

        for(int p=C; p<=C+100; p++){
            result = Math.min(result,DP[p]);    
        }
        
        System.out.print(result);
    }
}