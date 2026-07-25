import java.io.*;
import java.util.*;

class Main {

    static int N, deleteNode, root;
    static int[] arr;
    static int[] tree;

    static ArrayList<Integer>[] list;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 1. 초기 입력 받기
        init();

        // 2. 트리 만들기
        makeTree();

        // 3. 노드하나 지우고 자식 노드 개수 반환
        int answer = deleteAndGet();

        // 4. 정답 출력
        System.out.println(answer);
    }

    static int deleteAndGet() {
        // 자식 노드 탐색 dfs 활용
        return dfs(root);
    }

    static int dfs(int curNode) {
        if(root == deleteNode) return 0;
        if(list[curNode].size() == 0) return 1; // 자식 노드인 경우 1 반환
        int count = 0;
        for(int nextNode : list[curNode]) {
            if( nextNode == deleteNode ) continue;
            count += dfs(nextNode);
        }
        if(count == 0){ // 자식이 없어져서 자식 노드가 된 경우
            return 1;
        }
        return count;
    }

    static void makeTree() {
        root = 0;
        list = new ArrayList[N];
        for(int i=0; i<N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<N; i++) {
            if(arr[i]==-1) {
                root = i; // 루트인 경우
            } else {
                list[arr[i]].add(i);     // 자식노드 추가
            }

        }
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        deleteNode = Integer.parseInt(br.readLine());
    }

}