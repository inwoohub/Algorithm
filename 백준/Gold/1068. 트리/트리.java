// =================
// 알고리즘
// bfs 쓰자
// 1. 주어진 노드 제거 (노드 끊김)
// 2. 리프노드 탐색
// =================
// 5
// -1 0 0 1 1
// 2
// =================
// 2
// =================

import java.io.*;
import java.util.*;

public class Main{

    static ArrayList<Integer>[] tree; // 트리 -> ArrayList 사용
    static int N, root, target;       // 노드 개수, 루트, 타겟
    static boolean[] visited;         // 방문처리 배열
    
    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N];
        visited = new boolean[N];
        for(int i=0; i<N; i++){
            tree[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int A = Integer.parseInt(st.nextToken());
            if(A==-1){
                root = i; // 노드의 루트
                continue;
            }
            tree[A].add(i); // 단반향이면 충분한가?
        }
        target = Integer.parseInt(br.readLine());

        // 1) 타겟 방문 처리 (끊기 위함)
        visited[target] = true;

        // 2) bfs로 리프노드 갯수 탐색 단, 타겟이 0이라면 0 출력
        if(target == root){
            System.out.println(0);
        } else {
            System.out.println(bfs());
        }
        
    }

    static int bfs(){
        Queue<Integer> q = new LinkedList<>();

        // 3) 첫 시작 루트 노드 넣기
        q.offer(root);
        int ans = 0;
        while(!q.isEmpty()){
            int count = 0;
            int curNode = q.poll();
            
            // 4) 다음 노드 탐색
            for(int nextNode : tree[curNode]){
                
                // 5) 끊어낸 노드인 경우는 지나가기
                if(visited[nextNode]) continue;

                // 6) 아니라면 count 증가
                count++;

                // 7) 큐에 넣기
                q.offer(nextNode);
            }

            // 8) 리프노드 즉, 자식이 없다면
            if(count==0){
                ans++;
            }
        }
        return ans;
    }
}