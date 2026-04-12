/**
알고리즘 :
    TSP (Traveling Salesman promblem)

문제 요약 :
    어느 한 도시에서 시작해서 N개의 도시를 거쳐 다시 원래의 도시로 돌아오는 문제
    * 좌표로 주어짐
    * double 쓰면 됨
    * N : 1 ~ 16 로 주어짐 (외판원 문제에서는 최대 20개 정도까지만 가능함 -> N 크기만 보고 외판원 알고리즘을 유추하자.)

전략 :
    1. 좌표 사이에 거리 구하기 
    2. 비트 마스킹을 통해서 지나온 경로 표기하기
*/

import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 

        double[][] dist = new double[N][N]; // 거리 배열 [N]: 시작노드    [N][N]: 도착 노드
        double[][] dp = new double[N][1<<N];// dp  배열 [N]: 현재 위치 , [1<<N]: 지나온 경로 비트로 마스킹

        for(int i=0; i<N; i++){
            Arrays.fill(dist[i], -1); // 초기값 -1 로 세팅 : 거리기 때문에 도달 하지 못한 다는 의미로
        }

        // idx : 노드 번호
        // [0] : x 좌표
        // [1] : y 좌표
        ArrayList<int[]> list = new ArrayList<>(); // 모든 좌표가 들어 있는 리스트
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list.add(new int[]{A, B}); // 리스트에 좌표 추가 
        }

        for(int i=0; i<list.size(); i++){
            int[] cur = list.get(i);
            int cx = cur[0]; // 현재 x 좌표
            int cy = cur[1]; // 현재 y 좌표
            
            for(int j=0; j<list.size(); j++){
                if(i==j) continue; // A -> A 로 이동은 불가능
                int[] next = list.get(j);
                int nx = next[0]; // 다음 x좌표
                int ny = next[1]; // 다음 y좌표
                
                // 거리 계산 및 거리 배열에 넣어주기
                double distance = Math.sqrt( (cx-nx)*(cx-nx) + (cy-ny)*(cy-ny)  ); 
                dist[i][j] = distance; // i -> j 로 가는데 거리 값
            }
        }

        // dp 배열 세팅 및 초기값 지정하기
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE); // dp 배열 최대값으로 채워넣기 (최소값을 구하기 위함)
        }
        dp[0][1<<0] = 0; // dp 초기값 세팅 (시작은 0점으로 지정, 1<<0 비트 연산으로 통해 0번 노드 방문 처리)

        // 출발했던 도시로 돌아오는 것이기 때문에 순환 값 중 가장 작은 것을 고르면 됨
        for(int mask=0; mask<(1<<N); mask++){

            for(int cur=0; cur<N; cur++){
                if( (mask&(1<<cur)) ==0 ) continue; // 현재 cur 위치에서 cur 이 방문처리가 안되어있음 (즉, cur 방문 안하면 패스)
                if( dp[cur][mask] == Integer.MAX_VALUE ) continue; // 최소값이 없는 상태 즉, 도달하지 못하는 곳

                for(int next=0; next<N; next++){
                    if( ( mask&(1<<N) ) != 0 ) continue;  // 다음 방문 경로가 이미 방문처리가 되어있는 경우 (재방문 x)
                    if( dist[cur][next] == -1 ) continue; // 경로가 없다면 패스
                    dp[next][mask|(1<<next)] = Math.min( dp[next][mask|(1<<next)], dp[cur][mask] + dist[cur][next] ); // dp 갱신
                }
            }
        }

        double answer = Integer.MAX_VALUE;

        for(int end=0; end<N; end++){
            if( end == 0) continue; // 0으로 시작했기 때문에 0은 제외
            if( dp[end][ (1<<N) -1 ] == Integer.MAX_VALUE ) continue; // 오버플로우 방지
            if( dist[end][0] == -1 ) continue; // 돌아오는 길 없음
            answer = Math.min( answer , dp[end][ (1<<N) -1 ] +dist[end][0] );
        }
        System.out.print(answer);
    }
}