import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][1<<N];
        int[][] dist = new int[N][N];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken()); // 거리 매핑
            }
        }

        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE); // 도착못하는 곳
        }

        // 순회이므로 어디서 시작해도 상관없음 따라서 그냥 0번 노드부터 시작함
        int start = 0;
        dp[start][1<<start] = 0; // dp 배열 초기값 세팅

        for(int mask=0; mask<(1<<N); mask++){
            for(int cur=0; cur<N; cur++){
                if( (mask&(1<<cur)) == 0) continue; //포함되지 않은 경우 되돌리기
                if(dp[cur][mask]==Integer.MAX_VALUE) continue; // 오버플로우 방지
                for(int next=0; next<N; next++){
                    if( (mask&(1<<next)) != 0) continue; // 다음 경로가 이미 방문하면
                    if(dist[cur][next] == 0 ) continue; // 경로 없음
                    int nextMask = (mask|(1<<next)); // 다음 경로 포함한 마스킹
                    dp[next][nextMask] = Math.min(dp[next][nextMask],
                                     dp[cur][mask] + dist[cur][next]);
                }
            }
        }
        int answer = Integer.MAX_VALUE; 
        for(int i=0; i<N; i++){
            if(i==start) continue; // 종착점 == 출발점은 불가능
            if( dp[i][( (1<<N) - 1 )] == Integer.MAX_VALUE ) continue; // 접근 못함
            if(dist[i][start] == 0 ) continue; // 길없음
            answer = Math.min( answer, dp[i][((1<<N) - 1)] + dist[i][start] );
        }
        // 답 출력
        System.out.print(answer);
    }
}