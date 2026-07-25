import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] degree;
    static ArrayList<Integer>[] list;

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 초기 입력
        init();

        // 2. 줄 세우기
        getLine();

        // 3. 정답 출력
        System.out.println(sb);
    }

    static void getLine() {
        boolean[] visited = new boolean[N+1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {   // 차수 0 모두 큐에 넣기 (앞 순서 올 수 있음)
                q.offer(i);
                visited[i] = true; // 사용 처리
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur+" ");
            for(int next : list[cur]) {
                if(!visited[next]) {
                    degree[next]--; // 차수 감소
                    if(degree[next] <= 0) { // 차수 0 즉, 앞자리 올 수 있는 경우
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        degree = new int[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            degree[B]++;         // 차수 증가
            list[A].add(B);
        }
    }

}