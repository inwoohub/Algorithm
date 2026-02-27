// 알고리즘
// 세그먼트 트리 (구간 합 구하기)

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();

    static int N;
    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException{

        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 합 // 문제에서 근데 들어오는건 필터해주는데 굳이 이 횟수는 왜 받지?

        arr = new long[N]; // 입력받은 배열
        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        int cycle = M+K; // cycle = M+K 번 A,B,C 들어옴

        // 1. 세그먼트 구간 합 트리 만들기
        tree = new long[N*4]; // 넉넉하게 N*4 크기로 구간 합 트리 생성
        tree[1] = makeTree(0, N-1, 1);

        // 2. cycle 만큼 for문 시작
        for(int i=0; i<cycle; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 1: updateTree, 2: searchTree
            int B = Integer.parseInt(st.nextToken()); // 1: idx ,  2: 구간
            

            // updateTree
            if(A==1){
                long C = Long.parseLong(st.nextToken()); // 1: value, 2: 구간
                long diff = C - arr[B-1] ;
                updateTree( 0, N-1, 1, B-1 , diff );
                arr[B-1] = C; // 값 갱신
            }

            // searchTree
            else {
                int C = Integer.parseInt(st.nextToken()); // 1: value, 2: 구간
                sb.append( searchTree(0,N-1,1,B-1,C-1) + "\n" );
            }
        }

        // 결과 출력
        System.out.print(sb);


        // // 디버깅
        // for(int i=0; i<N*4; i++){
        //     System.out.println("tree["+i+"] : "+tree[i]);
        // }
        
    } // End main

    // 구간합 업데이트 하기
    // idx: 목표, diff: 이전노드와 차이
    static void updateTree(int start, int end, int node, int idx, long diff){
        if(idx < start || idx > end) return; // 범위 초과
        tree[node] = tree[node] + diff;
        if(start == end){
            return;
        }
        int mid = (start+end) / 2;
        updateTree(start, mid, node*2, idx, diff);
        updateTree(mid+1, end, node*2+1, idx, diff);
    }

    // 구간합 트리 구하기
    // start: 시작점 , end: 끝점, node: 현재 노드, left~right 범위 구하기
    static long searchTree(int start, int end, int node, int left, int right){
        if( left>end || right<start ) return 0;
        if( left<=start && right>=end ) return tree[node];
        int mid = (start+end)/2;
        return searchTree(start, mid, node*2, left, right) + searchTree(mid+1, end, node*2+1, left, right);
    }
    

    // 구간합 트리 만들기
    // start : 시작점, end : 끝점, node : 현재 노드 위치
    static long makeTree(int start, int end, int node){
        if(start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = makeTree(start, mid, node*2) + makeTree(mid+1, end, node*2+1);
    } // End makeTree
    
}