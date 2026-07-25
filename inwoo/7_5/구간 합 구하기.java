import java.io.*;
import java.util.*;

class Main{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static long[] arr;
    static long[] tree;
    static ArrayList<Long> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        // 1. N, M, K 입력 받기
        init();

        // 2. N개의 수 배열로 만들기
        makeArr();

        // 3. 배열에 있는 수로 세그먼트 트리 만들기 (구간합)
        tree = new long[N*4]; // 세그먼트 트리 미리 생성
        makeTree(1, N, 1); // 구간 합 트리 만들기

        // 4. M + K 만큼 Update or Select 하기
        updateOrSelect();

        // 5. 정답 출력
        StringBuilder sb = new StringBuilder();
        for(long answer : answerList){
            sb.append(answer+"\n");
        }

        System.out.println(sb);
    }

    static long selectTree(int start, int end, int index, int left, int right){
        if(start > right || left > end) {
            return 0; // 범위 이탈
        }

        if(start >= left && right >= end){
            return tree[index]; // 범위 내부
        }

        // 범위 내부이지만, 밖도 걸린 곳
        int mid = (start+end) / 2;
        return selectTree(start, mid, index*2, left, right)
                + selectTree(mid+1, end, index*2+1, left, right);
    }


    static void updateTree(int start, int end, int node, int index, long value, long diff){
        // 해당 구간인 밖이면 업데이트 x
        if(start > node || end < node) return;

        // 목적지인 경우
        if(start == end){
            tree[index] = value; // 해당 노드 값 변경
            arr[node] = value ;   // 원본 DB 값도 변경
            return;
        }

        // 변경해야 하는 구간
        tree[index] = tree[index] + diff; // 값 업데이트
        int mid = (start+end) / 2;
        updateTree(start, mid, node, index*2, value, diff);   // 다음 구간 업데이트 (좌측)
        updateTree(mid+1, end, node, index*2+1, value, diff); // 다음 구간 업데이트 (우측)
    }


    static void updateOrSelect() throws IOException {
        for(int i=0; i<M+K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 1 : update, 2: select
            if(A == 1){
                int B = Integer.parseInt(st.nextToken()); // 변경 위치
                long C = Long.parseLong(st.nextToken());  // 변경 값
                updateTree(1, N, B, 1, C, C-arr[B] );     // 트리 업데이트
            } else if(A == 2) {
                int B = Integer.parseInt(st.nextToken()); // 구간 도입
                int C = Integer.parseInt(st.nextToken()); // 구간 마감
                answerList.add(selectTree(1, N, 1, B, C));// 구간 합 조회
            }
        }
    }

    static long makeTree(int start, int end, int index ){
        if(start == end) {
            tree[index] = arr[start];
            return tree[index];
        }
        int mid = (start+end) / 2;
        return tree[index] = makeTree(start, mid, index*2)
                                + makeTree(mid+1, end, index*2+1);
    }



    static void makeArr() throws IOException{
        arr = new long[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
    }

    static void init() throws IOException {
        // N, M, K 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

}