import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int[] parent;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 초기 세팅
        init();

        // 2. M번 순회하며 사이클이 만들어지는 경우 조회
        int answer = search();

        // 3. 정답 출력
        System.out.print(answer);
    }

    static int search() throws IOException {
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 1. 부모 찾기
            int pA = find(A);
            int pB = find(B);

            // 2. 루트가 같은지 비교 (사이클)
            if(pA == pB) {
                return i+1;
            }

            // 3. 다르다면 더 작은 수가 부모가 되게끔 연결
            if(pA < pB) {
                parent[pB] = pA;
            } else {
                parent[pA] = pB;
            }
        }
        return 0; // 싸이클을 찾기못한 경우
    }

    static int find(int A) {
        if(parent[A] == A){
            return A;
        }
        return parent[A] = find(parent[A]);
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for(int i=0; i<N; i++) {
            parent[i] = i;
        }
    }
}