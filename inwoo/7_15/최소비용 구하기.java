import java.io.*;
import java.util.*;

class Main{

    static int N, M, A, B; // N : 도시의 개수, M : 버스의 개수
    static int answer;
    static ArrayList<Node>[] list;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        // 1. 도시 및 버스 정보 입력받기
        init();

        // 2. 최소 비용 구하기
        search();

        // 3. 정답 출력하기
        System.out.println(answer);
    }

    static void search() {
        boolean[] visited = new boolean[N+1];

        // 1. 우선순위 큐 생성
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        pq.offer(new Node(A, 0));

        // 2. 우선순위 큐 빌 때까지 반복
        while(!pq.isEmpty()){

            Node cur = pq.poll();
            if(visited[cur.node]) continue;
            visited[cur.node] = true;

            // 3. 도착지에 도착한 경우 종료
            if(cur.node == B){
                answer = cur.cost;
                return;
            }

            // 4. 다음 경로 우선순위 큐에 넣기
            for(Node next : list[cur.node]){
                if(visited[next.node]) continue;
                pq.offer(new Node(next.node, cur.cost + next.cost));
            }
        }
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[S].add(new Node(E,C));
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

    static class Node {
        int node;
        int cost;
        Node(int next, int cost) {
            this.node = next;
            this.cost = cost;
        }
    }

}