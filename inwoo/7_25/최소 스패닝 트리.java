import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static ArrayList<Node>[] list;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 초기 입력 받고 트리 만들기
        init();

        // 2. 최소 스패닝 트리 가중치 구하기
        int answer = getDist();

        // 3. 정답 출력
        System.out.println(answer);
    }

    static int getDist() {
        int curDist = 0;
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.dist, b.dist));
        pq.offer(new Node(1,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.node]) continue;
            visited[cur.node] = true;
            curDist += cur.dist; // 가중치 갱신
            for(Node next : list[cur.node]){
                if(!visited[next.node]) {
                    pq.offer(new Node(next.node, next.dist));
                }
            }
        }
        return curDist;
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 시작
            int B = Integer.parseInt(st.nextToken()); // 도착
            int C = Integer.parseInt(st.nextToken()); // 거리
            list[A].add(new Node(B,C));
            list[B].add(new Node(A,C));
        }
    }

    static class Node{
        int node;
        int dist;
        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}