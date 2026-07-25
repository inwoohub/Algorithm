import java.io.*;
import java.util.*;

class Main{

    static int N, M, K;
    static long[] arr;    // 원본
    static long[] tree;   // 세그먼트 트리

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        // 1. 초기 세팅 (입력 값 받기)
        init();

        // 2. 세그먼트 트리 만들기
        makeTree(1, N, 1);

        // 3. 입력값에 따라 Update or Get
        updateOrGet();

        // 4. 정답 출력
        System.out.println(sb);
    }

    static void updateOrGet() throws IOException {
        for(int i=0; i<M+K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean update = (Integer.parseInt(st.nextToken()) == 1); // T : Update , F : Get

            if(update){
                int B = Integer.parseInt(st.nextToken()); // Update Index
                long C = Long.parseLong(st.nextToken()); // Update Value
                long diff = C - arr[B];
                updateTree(1, N, 1, B, diff, C);
            }
            else {
                int B = Integer.parseInt(st.nextToken()); // Update Index
                int C = Integer.parseInt(st.nextToken()); // Update Value
                sb.append(getTree(1,N,1,B,C)+"\n");
            }
        }
    }

    static long getTree(int start, int end, int node, int left, int right) {
        // 범위 밖
        if( start>right || end < left ) return 0;

        // 범위 안
        if( left <= start && end <= right ) return tree[node];

        int mid = (start+end) / 2;
        return (getTree(start, mid, node*2, left, right) + (getTree(mid+1, end, node*2+1, left, right)));
    }

    static void updateTree(int start, int end, int node, int index, long diff, long value) {
        // 범위 밖 return
        if(start > index || end < index) return;

        // 자식 노드까지 도달한 경우
        if(start == end) {
            tree[node] = 1L * value;
            arr[index] = value;
            return;
        }

        // 범위 내부인 경우 (갱신)
        tree[node] = tree[node] + diff;
        int mid = (start+end) / 2;
        updateTree(start, mid, node*2, index, diff, value);
        updateTree(mid+1, end, node*2+1, index, diff, value);
    }

    static long makeTree(int start, int end, int node ) {
        // 자식 노드인 경우
        if(start == end){
            return tree[node] = arr[start];
        }

        int mid = (start + end)/2;
        return tree[node] = makeTree(start, mid, node*2) + makeTree(mid+1, end, node*2+1);
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N+1];   // 원본 배열 생성
        tree = new long[N*4];  // 세그먼트 트리 생성
        for(int i=1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
    }

}