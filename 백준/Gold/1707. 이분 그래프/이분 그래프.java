// 알고리즘
// 이분 그래프 & bfs

// Union-Find 까지 해야할거같음
// 왜냐하믄... 이분그래프인지 확인하는 조건에서 혹은... for문 돌려서 모든 그래프 확인해보기?

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        for(int tC = 0; tC<testCase; tC++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점의 개수
            int E = Integer.parseInt(st.nextToken()); // 간선의 개수
            ArrayList<Integer>[] graph = new ArrayList[V+1];
            for(int i=0; i<=V; i++){
                graph[i] = new ArrayList<>();
            }
            int[] visited = new int[V+1]; // 0 : 미방문 , 1 : 색(1),  2 : 색(2)
            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph[A].add(B); graph[B].add(A); // 양방향 그래프
            }
            if(V==1 || E==1){
                sb.append("YES\n");
            } else {
                boolean result = true;
                for(int i=1; i<=V; i++){
                    if(visited[i] == 0){
                        result = bfs(i,graph,visited);
                        if(!result){
                            sb.append("NO\n");
                            break;
                        }
                    }
                } // for of End
                if(result){
                    sb.append("YES\n");
                }
            }
        }
        System.out.println(sb);
    }

    static boolean bfs(int startNode , ArrayList<Integer>[] graph, int[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode); // 1번 노드 / 1번 색
        visited[startNode] = 1;

        // 1) 큐가 비어있을 때 까지 반복
        while(!q.isEmpty()){
            // 2) 큐 꺼내기
            int curNode = q.poll();

            for(int nextNode : graph[curNode]){
                // 3) 다음 노드 방문 상태 확인
                if( visited[nextNode] == 0 ){
                    // 3-1) 부모의 색에 따라 자식 색 적용
                    if( visited[curNode] == 1 ){
                        visited[nextNode] = 2;
                        q.offer(nextNode);
                    } else {
                        visited[nextNode] = 1;
                        q.offer(nextNode);
                    }
                }

                // 4) 이미 색상이 있다면 (방문했다면)
                else{
                    // 4-1) 다음 노드와 현재 노드 색상 일치 시 -> 실패
                    if(visited[nextNode] == visited[curNode]){
                        return false;
                    }
                    // 4-2) 다음 노드와 현재 노드 색상 불 일치 시 -> continue
                    else{
                        continue;
                    }
                }
            }
        } // while of end
        return true;
    } // bfs of end

} // Main of end

