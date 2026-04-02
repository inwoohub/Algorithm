/** 
알고리즘 : 
    위상 정렬

전략 : 
    1. 차수 담긴 배열 생성
    2. LinkedList 만들어서 연결 해두기
    3. 큐에 노드 전부 담기
    4. 해당 차수가 0이라면, 바라보는 리스트 차수 -1 하기
    5. 해당 차수가 0이 아니라면, 다시 큐에 담기
*/

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] list ; // 연결 리스트
    static int[] degree; // 각 노드의 차수
    static boolean[] visited; // 사용 처리 배열

    public static void main(String[] args) throws IOException {
        // 데이터 매핑 (1 ~ 2 과정)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 학생 비교 수
        list = new ArrayList[N+1];
        degree = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++) { list[i] = new ArrayList<>(); }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B); // 단방향 매핑
            degree[B]++;
        }

        // 3. 우선순위 큐 생성 및 큐에 노드 전부 담기 -> 우선순위 큐로 반경 [1]: degree 순으로 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        for(int i=1; i<=N; i++){
            pq.offer(new int[]{i, degree[i]});
        }
        
        // 4. 큐에서 하나 뽑기
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDegree = cur[1];
            if(visited[curNode]) continue; // 이미 끝난 거는 넘어가기.
            if( curDegree > degree[curNode] ) continue; // 이전 값 버리기
            if( degree[curNode] == 0 ){ // 차수가 0 이라면,
                start(curNode, pq);
                visited[curNode] = true;
                sb.append(curNode+" ");
            }
            else {
                pq.offer(new int[]{curNode, curDegree});
            }
        }
        System.out.print(sb);
    }

    // 차수 -1 씩 감소해주기
    static void start(int x, PriorityQueue<int[]> pq ){
        for(int next : list[x]){
            if(degree[next] == 0) continue;
            degree[next]--;
            pq.offer(new int[]{next, degree[next]});
        }
    }
    
}