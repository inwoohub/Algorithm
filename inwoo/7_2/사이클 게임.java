import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int N = 7;
    
    static int[][] Edges =
        {
            {0, 1},
            {2, 3},
            {1, 2},
            {4, 5},
            {0, 3},
            {5, 6}
        };

    static int[] parent; // 부모 노드

    public static void main(String[] args) {

        int answer = 0;

        // 부모 노드 배열 생성
        parent = new int[N];

        // 1. ArrayList로 노드 만들기
        ArrayList<Integer>[] list = new ArrayList[N];
        for(int i=0; i<N; i++){
            list[i] = new ArrayList<>(); // 리스트 초기화
            parent[i] = i; // 시작 부모는 자기 자신
        }

        // 작은게 [][0] 처럼 앞으로 뺴는 정렬
        for(int i=0; i<Edges.length; i++){
            Arrays.sort(Edges[i]);
        }

        // 2. Edge 돌며 연결하기
        for(int i=0; i<Edges.length; i++){
            int A = Edges[i][0]; // 작은 수 
            int B = Edges[i][1]; // 큰 수

            // 양 방향 연결
            list[A].add(B);
            list[B].add(A);

            int find_A = find(A); // A의 부모 찾기
            int find_B = find(B); // B의 부모 찾기

            if(find_A == find_B){ // 부모가 같은 경우 싸이클 발생!
                answer = i+1; // '+1' 번째를 해야함
                break;
            }
            parent[find_B] = find_A; // 찾은 B의 루트 부모를 A로 바꾸기 (Union)
        }
        System.out.println(answer);
    }

    // 부모를 찾는 함수
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return find(parent[a]); // 부모 재귀로 찾기
    }
    
}