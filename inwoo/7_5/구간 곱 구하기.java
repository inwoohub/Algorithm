import java.util.*;
import java.io.*;

class Main{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final long DIV = 1000000007L; // 10억 7
    static int N, M, K;
    static long[] arr;
    static long[] tree;
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        // 1. N, M, K 입력 받기 | arr 배열 입력 받기 | tree 생성
        init();

        // 2, 세그먼트 트리 데이터 채우기
        makeTree(1, N, 1);

        // 3. 업데이트 or 조회
        selectOrUpdate();

        // 4. 정답 구하기
        solution();

        // 5. 정답 출력
        System.out.println(sb);

    }

    // 정답 구하기
    static void solution(){
        for(long answer : list){
            sb.append(answer+"\n");
        }
    }

    // 구간 조회
    static long selectTree(int start, int end, int index, int left, int right){
        // 구간 밖인 경우
        if(start > right || end < left){
            return 1L;
        }

        // 구간 내부인 경우
        if(left <= start && end <= right){
            return tree[index];
        }

        // 그 외 걸쳐있는 경우
        int mid = (start+end) / 2;
        return (selectTree(start, mid, index*2, left, right)
                * selectTree(mid+1, end, index*2+1, left, right)) % DIV ;

    }


    // 구간 업데이트
    static long updateTree(int start, int end, int node, int index, long value){
        // 구간 밖 검사
        if(start > node || end < node){
            return tree[index];
        }

        // 해당 노드 인 경우
        if(start == end){
            arr[node] = value;   // 원본 업데이트
            tree[index] = value; // 트리 노드 업데이트
            return value;
        }

        // 구간 걸친 경우
        int mid = (start+end)/2;
        return tree[index] = (updateTree(start, mid, node, index*2, value)
                                * updateTree(mid+1, end, node, index*2+1, value)) % DIV;

    }


    // 업데이트 or 조회
    static void selectOrUpdate() throws IOException{
        for (int i=0; i<M+K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());

            if(A==1){                                     // Update
                int B = Integer.parseInt(st.nextToken()); // 바꾸는 위치
                long C = Long.parseLong(st.nextToken());  // 바꾸는 값
                updateTree(1, N, B, 1, C);                // 업데이트

            } else if (A==2){                             // Select
                int B = Integer.parseInt(st.nextToken()); // 구간 도입부
                int C = Integer.parseInt(st.nextToken()); // 구간 마감부
                list.add(selectTree(1, N, 1, B, C));      // 조회 후 리스트에 추가
            }
        }
    }

    // 트리 생성하기
    static long makeTree(int start, int end, int index) {
        if(start == end){ // 해당 노드 도착
            return tree[index] = arr[start];
        }
        int mid = (start+end) / 2;
        return tree[index] = (makeTree(start, mid, index*2) * makeTree(mid+1, end, index*2+1)) % DIV;
    }


    // 초기 세팅
    static void init() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];  // 원본 배열 생성
        tree = new long[N*4]; // 세그먼트 트리 생성 (크기는 보통 4배면 넉넉함)
        for(int i=1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine()); // 원본 배열 데이터 채워넣기
        }
    }

}