// TSP
import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[][] arr;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 초기 입력값 세팅
        init();

        // 2. 외판원 순회 (TSP)
        int answer = tsp();

        // 3. 정답 출력
        System.out.println(answer);
    }

    static int tsp() {
        int answer = Integer.MAX_VALUE;
        int[][] dp = new int[N][1<<N];
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][(1<<0)] = 0; // 처음 시작점

        for(int mask=0; mask<(1<<N); mask++){

            for(int i=0; i<N; i++) {
                if( (mask & (1<<i)) == 0 ) continue; // 현재 경로에 포함되지 않은 경우
                if(dp[i][mask] == Integer.MAX_VALUE) continue; // 오버플로 방지

                for(int j=0; j<N; j++) {
                    if( (mask & (1<<j)) > 0 ) continue; // 이미 지나온 길
                    if(arr[i][j]==0) continue; // 길없음

                    int nextMask = (mask | (1<<j));
                    dp[j][nextMask] = Math.min(dp[j][nextMask], dp[i][mask]+arr[i][j]);
                }
            }
        }
        for(int i=1; i<N; i++){
            if(dp[i][(1<<N)-1] == Integer.MAX_VALUE) continue; // 오버플로 방지
            if(arr[i][0] == 0) continue; // 돌아가는 길 없음
            answer = Math.min(answer, dp[i][(1<<N)-1]+arr[i][0]);
        }
        return answer;
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}