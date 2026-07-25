import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static long[] arr;
    static long[] tree;

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 초기 입력값 받기 (N, M)
        init();

        // 트리 만들기
        makeTree(1,N,1);

        // 트리 조회 및 업데이트
        getOrUpdateTree();

        // 정답 출력
        System.out.println(sb);
    }

    static void getOrUpdateTree() throws IOException {
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());

            if(A == 1){  // Update
                int B = Integer.parseInt(st.nextToken());
                long C = Long.parseLong(st.nextToken());
                long diff = C - arr[B]; // 바뀌는 값에 대한 차이
                updateTree(1, N, 1, diff, B, C);
            } else {     // Get
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                long value = getTree(1, N, 1, B, C);
                sb.append(value+"\n");
            }
        }
    }

    static long getTree(int start, int end, int node, int left, int right) {
        if( start > right || end < left ) {
            return 0; // 해당 구간 x
        }
        if(left <= start && end <= right){
            return tree[node]; // 해당 구간 o
        }
        int mid = (start+end)/2;
        return getTree(start, mid, node*2, left, right) + getTree(mid+1, end, node*2+1, left, right);
    }

    static void updateTree(int start, int end, int node, long diff, int index, long value) {
        if( index < start || index > end) {
            return; // 범위 밖으로 해당 구간 업데이트 x
        }

        if( start == end && start == index ) {
            arr[index] = value;
            tree[node] = arr[index];
            return; // 해당 index 도달한다면, tree 및 arr 업데이트 후 종료
        }
        tree[node] += diff; // 구간합 갱신
        int mid = (start + end) / 2;
        updateTree(start, mid, node*2, diff, index, value);
        updateTree(mid+1, end, node*2+1, diff, index, value);
        return;
    }

    static long makeTree(int start, int end, int node) {
        // 리프노드까지 도달한 경우
        if(start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start+end)/2;
        return tree[node] = makeTree(start, mid, node*2) + makeTree(mid+1, end, node*2+1);
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        M += Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        tree = new long[N*4];
    }

}