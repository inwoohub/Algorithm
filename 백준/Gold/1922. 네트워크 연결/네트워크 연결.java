// ================
// 알고리즘
// 우선순위큐 + 그래프
// ================

import java.io.*;
import java.util.*;

public class Main{

    static ArrayList<Integer>[] list; // 연결 리스트
    static int[][] arr; // 가중치
    static boolean[] visited; // 방문 완료
    
    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 컴퓨터 수
        arr = new int[N+1][N+1]; 
        visited = new boolean[N+1]; 
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 연결선 수
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 컴퓨터 A
            int B = Integer.parseInt(st.nextToken()); // 컴퓨터 B
            int C = Integer.parseInt(st.nextToken()); // 선의 가중치
            list[A].add(B);
            list[B].add(A);
            arr[A][B] = C;
            arr[B][A] = C;
        }

        // 1) 우선순위 큐 생성 [0]: 가중치, [1]: 가르키는 컴퓨터 번호
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a[0], b[0]) );

        // 2) 시작 노드 1번
        pq.offer(new int[]{0,1});
        int ans = 0;
        
        // 3) 탐색 시작
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curWeight = cur[0];
            int curNode = cur[1];

            // 4) 방문하지 않았을 경우에 가중치 더해주기 및 방문체크
            if(!visited[curNode]){
                visited[curNode] = true;
                ans += curWeight;
            }
            
            // 4) 다음 노드 확인
            for(int nextNode : list[curNode]){
                // 5) 방문하지 않은 노드라면 우선순위 큐에 추가
                if(!visited[nextNode]){
                    pq.offer(new int[]{ arr[curNode][nextNode], nextNode });
                }
            }
        } // End of while

        // 데이터 출력
        System.out.println(ans);
        
    }
}
