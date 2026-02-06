// 알고리즘
//
// ===============
// 5 17
// ===============
// 4
// 5 10 9 18 17
// ===============

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int[] parent = new int[1000001]; // 부모
    static int[] dp = new int[1000001];
    
    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 1) 우선순위 큐 생성
        // [0] -> count, [1] -> preNode
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a[0], b[0]));

        // 2) root 설정 (수빈) & dp 값 초기화
        pq.offer(new int[]{0,A});
        parent[A] = A;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[A] = 0;

        // 3) 우선순위큐 이용 bfs 탐색
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curNode = cur[1];

            // 종료 조건
            if(curNode == B){
                sb.append(curCost+"\n");
                break;
            }

            // curNode x 2 | 100000 보다 작고, 현재 cost 더 작은 경우
            if( curNode * 2 <= 100000 && dp[curNode*2] > curCost + 1){
                dp[curNode*2] = curCost + 1;
                parent[curNode*2] = curNode;
                pq.offer(new int[]{ curCost+1, curNode*2 });
            }

            // curNode + 1
            if( curNode + 1 <= 100000 && dp[curNode+1] > curCost + 1){
                dp[curNode+1] = curCost + 1;
                parent[curNode+1] = curNode;
                pq.offer(new int[]{ curCost+1, curNode+1 });
            }

            // curNode -1
            if( curNode - 1 >= 0 && dp[curNode-1] > curCost + 1){
                dp[curNode-1] = curCost + 1;
                parent[curNode-1] = curNode;
                pq.offer(new int[]{ curCost+1, curNode-1 });
            }
        }

        // 4) 경로 추적 ArrayList 담기
        ArrayList<Integer> list = new ArrayList<>();
        int curNode = B;
        list.add(B);
        while(true){
            if(curNode == A) break;
            list.add(parent[curNode]);
            curNode = parent[curNode];
        }

        // 데이터 출력
        for(int i = list.size()-1; i>-1; i--){
            sb.append(list.get(i)+" ");
        }
        
        System.out.print(sb);
        
    } // End of main
    
} // END of Main