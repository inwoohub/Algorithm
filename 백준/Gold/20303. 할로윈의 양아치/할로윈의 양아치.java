// -------------------
// 알고리즘
// 1. 연결되어 있는 링크 먼저 찾기 (수 + 총 사탕 수)
// 2. K 미만 인 경우 큐에 넣기
// 3. 큐 사이즈 만큼 배열 만들고 정렬
// 4. DP (배낭 알고리즘) 로 최대 값 구하기
// -------------------
// 변수
// N : 사람 수, M : 관계 수, K :  공명 수
// -------------------
// 10 6 6
// 9 15 4 4 1 5 19 14 20 5
// 1 3
// 2 5
// 4 9
// 6 2
// 7 8
// 6 10
// -------------------
import java.io.*;
import java.util.*;

public class Main{

    static int N, M, K;
    static int[] child; // 사탕 수
    static ArrayList<Integer>[] list; // 친구 관계
    static boolean[] bfsVisited; // bfs 방문 표시

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        child = new int[N+1];
        bfsVisited = new boolean[N+1];
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            child[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);
            list[B].add(A);
        }

        // 변수 생성 완료--------------

        
        // 연결되어있는 링크 및 사탕 수 찾기 (bfs 활용)
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) ->{
            if(a[0]==b[0]) { return (Integer.compare(a[1],b[1])); }
            return (Integer.compare( a[0],b[0])); });
        
        for(int i=1; i<=N; i++){
            if(!bfsVisited[i]){
                bfsVisited[i] = true;
                int[] linkAndCandy = bfs(i);
                if(linkAndCandy[0] < K){
                    pq.offer(new int[]{linkAndCandy[0], linkAndCandy[1]});
                }
            }
        }

        // 큐 사이즈 만큼 배열 생성
        int size = pq.size();
        int[][] peopleAndCandy = new int[size+1][2];
        for(int i=1; i<=size; i++){
            int[] cur = pq.poll();
            peopleAndCandy[i][0] = cur[0];
            peopleAndCandy[i][1] = cur[1];
        }

        int[][] DP = new int[K][size+1];
        for (int i = 1; i <= size; i++) {
        int w = peopleAndCandy[i][0];
        int v = peopleAndCandy[i][1];
    
        for (int j = 1; j < K; j++) { // 용량: 1..K-1
            // 일단 안 고르는 경우
            DP[j][i] = DP[j][i-1];
    
            // 고를 수 있으면(0/1이니까 i-1에서 가져옴)
            if (w <= j) {
                DP[j][i] = Math.max(DP[j][i], DP[j - w][i-1] + v);
            }
        }
    }
        System.out.print(DP[K-1][size]);
    }

    static int[] bfs(int x){
        int people = 1;
        int candy = child[x];
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : list[cur]){
                if(!bfsVisited[next]){
                    bfsVisited[next] = true;
                    people++;
                    candy = candy + child[next];
                    q.offer(next);
                }
            }
        }
        // sb.append(x+") 무리 수 : "+people+" , 캔디 수 : "+candy+"\n");
        return new int[]{people, candy};
    }
}