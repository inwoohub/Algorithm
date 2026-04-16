/**
알고리즘 :
    ArrayList + Bitmasking
    
문제 요약 :
    2진 트리 존재
    0 : 양 , 1 : 늑대
    양 or 늑대를 주우면서 다님
    지나 왔던 길 다시 갈 수 있음
    (양 수 <= 늑대 수) 양 전부 잡아먹힘 -> 끝
    최대한 많은 수의 양 줍기
    
전략 :
    1. ArrayList<Node>[] 만들기 : 길 만들기 Node -> n: 다음 노드, b: T-양, F-늑대
    2. dp[][][] 만들기
        []: 현재 위치, [][]: 양, 늑대 수 ,[][][]: 지나온 경로
    3. 0번 노드부터 탐색시작
        - 0번 노드 방문
        - 0번 -> 1 or 8 노드 방문
            - 양이라면 줍기
            - 늑대라면 주울 수 있는지 판단 후 줍거나 or 안 줍거나
        - 양 최대치 갱신
        - 위 반복 해서 양 최대로 모으는 경우 찾기
*/

import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 1; // 정답값 (default : 0)
        
        // 1. ArrayList 만들고 길 매핑하기
        ArrayList<Node>[] list = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++){
            int A = edges[i][0]; // A 노드
            int B = edges[i][1]; // B 노드
            boolean boolA = (info[A] == 0) ? true : false;
            boolean boolB = (info[B] == 0) ? true : false; // 양이라면 T 늑대라면 F
            list[A].add(new Node(B, boolB));
            list[B].add(new Node(A, boolA)); // 양방향 매핑
        }
        
        // 2. dp 배열 만들기
        int[][][] dp = new int[info.length][2][(1<<info.length)];
        
        if(info[0] == 1){ // 시작부터 꼬인 경우
            return answer;
        }
        
        /**
            현재 위치 : 0
            양의 수   : 1
            지나온 경로: (1<<0) 0번 노드 로 dp 초기값 주고 시작
        */
        dp[0][0][(1<<0)] = 1;
        
        // 모든 mask 검사
        for(int mask=0; mask<(1<<info.length); mask++){
            for(int repeat = 0; repeat < info.length; repeat++) {
                for(int cur=0; cur<info.length; cur++){ // 현재 위치
                    if( (mask & (1<<cur)) == 0 ) continue; // 현재 경로가 mask 포함 안 되어있음

                    if( dp[cur][0][mask] == 0 ) continue;

                    for(Node next : list[cur]){ // 다음으로 이동 가능한 경로 (물론 지나온길도 가능)

                        // 근데 지나온 길이라면 양을 줍거나, 늑대를 더이상 더해주면 안됨
                        boolean check = false; // 지나온 길이 아닌 경우 false
                        if( (mask & (1<<next.n)) != 0 ) check = true; // 지나온 경우 true

                        int nextMask = (mask | (1<<next.n)); // 다음 이동할 경로 mask 포함하기
                        if(next.b){ // 다음 경로가 양이라면,

                            if(!check){
                                dp[next.n][0][nextMask] = dp[cur][0][mask] + 1 ; // 양 갱신    
                            } else {
                                dp[next.n][0][nextMask] = Math.max(dp[next.n][0][mask] ,dp[cur][0][mask]); // 양더 많이 주우면 갱신
                            }
                            dp[next.n][1][nextMask] = Math.max(dp[next.n][1][mask], dp[cur][1][mask]);

                            answer = Math.max( answer, dp[next.n][0][nextMask] ); // 양 최대치 갱신
                        }

                        else { // 다음 경로가 늑대라면
                            if(!check){
                                // 현재 양 vs 현재 늑대 + 1 비교하기
                                if(dp[cur][0][mask] <= dp[cur][1][mask] + 1) continue; // 이동 불가능

                                dp[next.n][1][nextMask] = dp[cur][1][mask] + 1; // 늑대 갱신
                            } else {
                                dp[next.n][1][nextMask] = Math.max(dp[next.n][1][mask], dp[cur][1][mask]); // 늑대 그대로 
                            }
                            dp[next.n][0][nextMask] = Math.max(dp[next.n][0][mask], dp[cur][0][mask]);
                        }
                    }
                } // End nextNode
            }
        }
        return answer;
    }
    
    // list 에 들어갈 Class 정의
    class Node{
        int n;
        boolean b;
        Node(int n, boolean b){
            this.n = n;
            this.b = b;
        }
    }
    
}









