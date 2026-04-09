/**
알고리즘 :
    TSP (Traveling Salesman problem)
    -> 영업사원이 여러 도시를 한 번씩만 방문하고 출발지로 돌아오는 최단 경로를 찾는 조합 최적화 문제

문제 요약 :
    출발지를 제외하고 모든경로를 딱 1번만 순회하며 여행했을 때 최소 비용

전략 :
    1. dist[][] 거리를 담아주는 배열 만들기
    2. dp[][] 현재 노드에 있을 때 이전 경로를 지나온 경로중 최소 비용 담는 배열 만들기
    3. 모든 노드를 순회하며 출발지로 설정
    4. dp[][] 배열의 크기 즉, 비트마스크 만큼 for문 돌리기
    5. 현재 위치해 있는 노드가 비트마스크로 비교하였을 때 이전 경로에 포함되어있는지 확인하기
        - 포함 되어있지 않다면 4번으로
    6. 다음 방문할 노드를 아직 방문 안했는지 확인하기
        - 포함 되어있다면 이미 방문했으므로 넘어가기
    7. 다음 노드 방문 할 때 더 적은 비용으로 접근 가능 하다면 업데이트
    8. 4 ~7 이 끝난 후 최소 비용 갱신 (작은 경우만)
*/

import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException{

        int answer = Integer.MAX_VALUE; // 결과 초기값 세팅
        
        // 데이터 입력 및 매핑
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드 개수

        // 1, 2 배열 생성
        int[][] dist = new int[N][N];  // 각 노드끼리의 길이 배열 생성
        int[][] dp = new int[N][1<<N]; // dp 배열 생성 [N]: 현재 위치, [1<<N] : 비트마스크 -> 0001 은 1번 노드 방문, 0011 은 1번,2번 노드 방문 비트로 마스킹하기

        // dist 배열에 거리 넣어주기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3. 모든 노드가 출발지가 가능하기 때문에 for문 사용
        for(int i=0; i<1; i++){
            
            // dp 배열 초기 세팅 (최소를 찾기 위해 최대로 설정)
            for(int j=0; j<N; j++){
                Arrays.fill(dp[j], Integer.MAX_VALUE);
            }
            // dp 첫번째 세팅
            dp[i][1<<i] = 0; // i에 있고, i만 방문했으니까 0 넣어주기 (이동 안한 상태)

            // 4. 비트 마스크 만큼 for 문 돌리기 (모든 경로 중 최소 값 찾기 위해서)
            for(int mask=0; mask<(1<<N); mask++){

                // 5. 현재 위치 (모든 노드가 현재 위치가 가능)
                for(int cur=0; cur<N; cur++){
                    // 현재 위치가 이전 방문 경로에 포함되어있는지 확인, 아니라면 패스
                    if( (mask&(1<<cur)) == 0 ) continue; // 포함되어있지 않음
                    if( dp[cur][mask] == Integer.MAX_VALUE ) continue; // 갱신 못하고 오버플로우 방지

                    // 6. 다음 방문노드 탐색
                    for(int next=0; next<N; next++){
                        // 다음 위치가 방문 안한 위치인지 확인하기
                        if( (mask & (1<<next)) != 0 ) continue; // 마스크에 포함되어있음

                        // 길이 없어도 통과
                        if( dist[cur][next] == 0 ) continue;
                        
                        
                        // 7. 더 적은 비용으로 업데이트 하기 ( 이전 최소 비용 값 vs 현재->다음 비용 값 )
                        dp[next][ (mask|(1<<next)) ] = Math.min( dp[next][(mask|(1<<next))], dp[cur][mask]+dist[cur][next] );

                        // System.out.println("["+ next + "]["+(mask|(1<<next))+"] : "+dp[next][ (mask|(1<<next)) ] + "  , mask : "+mask);
                    }
                }
            }
            
            // 4 ~ 7 종료 후 마지막 노드 -> 출발지로 돌아가는 길 작은걸로 업데이트
            for(int last=0; last<N; last++){
                if(last == i) continue; // 마지막 == 출발지 라면 성립 불가능
                if(dist[last][i] == 0) continue; // 길이 없는 경우도 못돌아감
                if(dp[last][(1<<N)-1] == Integer.MAX_VALUE) continue; // 오버 플로우 방지
                answer = Math.min( answer, dp[last][(1<<N)-1] + dist[last][i] ); // 이전 최소 비용 vs 새로운 최소 비용 비교 (전부 방문 + 출발지)
            }
        }
        // 결과 출력
        System.out.println(answer);
    }
}

