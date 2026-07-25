import java.io.*;
import java.util.*;

class Main{

    static int N;
    static int[] rank;
    static int[] degree;
    static ArrayList<Integer>[] list;

    static StringBuilder sb;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int testCase = Integer.parseInt(br.readLine());
        for(int tC=0; tC<testCase; tC++){
            sb = new StringBuilder();
            // 1. 입력 값 받기
            init();

            // 2. 탐색 (T : 순서 보장, F : IMPOSSIBLE)
            int check = search();

            // 3. 결과 출력
            if(check==0){
                System.out.println(sb);
            } else if(check==1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println("?");
            }
        }
    }

    static int search() {
        // 1. 위상정렬 알고리즘 사용
        int count = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            if(degree[i] == 0) q.offer(i); // 입력차수 0인 경우 큐에 추가
        }

        while(!q.isEmpty()){
            if(q.size()>1) return 2;   // 우선순위를 가릴 수 없음 (서로 높다고 하는 상황)
            count++;
            int cur = q.poll();
            sb.append(cur+" ");            // 출력 값 추가

            for(int next : list[cur]){
                degree[next]--;            // 연결된 노드 차수 1 차감
                if(degree[next] == 0){
                    q.offer(next);         // 입력차수 0 인경우 큐에 추가
                }
            }
        }
        if(count != N) return 1;       // 노드 전부 탐색 안한 경우 (싸이클이 발생한 경우)
        return 0;
    }

    static void init() throws IOException {
        // 1. 배열 및 리스트 생성
        N = Integer.parseInt(br.readLine());
        rank = new int[N+1];
        degree = new int[N+1];
        list = new ArrayList[N+1];
        int[] team = new int[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 1등 먼저 꺼내기
        int cur = Integer.parseInt(st.nextToken());
        rank[1] = cur;
        team[cur] = 1;

        // 2. 등수 지정
        for(int i=2; i<=N; i++){
            cur = Integer.parseInt(st.nextToken());
            rank[i] = cur; // 랭킹 -> 노드
            team[cur] = i; // 노드 -> 랭킹
        }

        // 3. 진입 노드 연결 & 차수 증가
        for(int i=1; i<N;  i++){
            cur = rank[i]; // 랭킹 순
            for(int j=i+1; j<=N; j++){
                int next = rank[j];
                list[cur].add(next); // 단방향 연결
                degree[next]++;
            }
        }

        // 4. 상대적으로 등수가 바뀌는 경우
        int m = Integer.parseInt(br.readLine()); // 상대가 바뀐 수
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if( team[A] < team[B]) { // A의 랭크가 더 높은 경우
                list[B].add(A);      // B->A 로 노드 추가
                degree[A]++;         // A의 차수 증가
                list[A].remove(Integer.valueOf(B)); // A->B노드 제거
                degree[B]--;         // B의 차수 감소

            } else {                 // B의 랭크가 더 높은 경우
                list[A].add(B);      // A->B 로 노드 추가
                degree[B]++;         // B의 차수 증가
                list[B].remove(Integer.valueOf(A)); // B->A노드 제거
                degree[A]--;         // A의 차수 감소
            }
        }
    }

}