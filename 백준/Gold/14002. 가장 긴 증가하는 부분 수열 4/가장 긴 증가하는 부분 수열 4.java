/**
 * ==================
 * 알고리즘
 * 가장 긴 수열 찾기 + 경로 추적
 * DP
 * ==================
 * 6
 * 10 20 10 30 20 50
 * ==================
 * 4
 * 10 20 30 50
 * ==================
 */


import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];  // 숫자 배열
        int[] dp = new int[N];   // DP 배열 (최대값 저장 용도)
        int[] parent = new int[N]; // 경로 추적용 부모인덱스
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            parent[i] = i; // 초기값은 자기 자신을 가르킴
        }                

        // 1) dp 배열 '1'로 초기화
        Arrays.fill(dp,1);

        // 2) 1 번째 부터 탐색 시작
        for(int i=1; i<N; i++){
            int MAX = 0; // 자기보다 작은 것중 가장 큰 값
            int MAXidx = 0; // 자기보다 작은 것중 가잔 큰 값의 인덱스
            
            // 3) 이중 for문을 통해 자기보다 작은거 중 가장 큰 값 탐색
            for(int j=0; j<i; j++){
                // 4) arr[i] 보다 작은 것
                if( arr[i] > arr[j] ){
                    // 5) 가장큰 dp 값 탐색
                    if( dp[i] < dp[j] + 1 ){
                        parent[i] = j;     // 부모 저장
                        dp[i] = dp[j] + 1; // dp값 갱신
                    }
                }
            } // End of 2중 for
        } // End of for

        // 5) 가장 큰 dp의 인덱스 찾기
        int MAXidx = 0;
        for(int i=0; i<N; i++){
            if( dp[i] > dp[MAXidx] ){
                MAXidx = i;
            }
        }

        // 6) 최대 값
        StringBuilder sb = new StringBuilder();
        sb.append(dp[MAXidx]+"\n");

        // 7) 경로 추적
        int curIdx = MAXidx;
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            list.add(arr[curIdx]);
            if( parent[curIdx] == curIdx ) break; // root 경우 종료
            curIdx = parent[curIdx];
        }
        Collections.reverse(list); // 리스트 뒤집기
        for(int i=0; i<list.size(); i++){
            sb.append(list.get(i)+" ");
        }

        // 데이터 출력
        System.out.println(sb);
        
    } // End of main

} // End of Main