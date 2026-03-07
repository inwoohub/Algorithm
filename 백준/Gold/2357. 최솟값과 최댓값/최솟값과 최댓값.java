// 알고리즘
// 세그먼트 트리

import java.io.*;
import java.util.*;

public class Main{

    static int[] arr, minTree, maxTree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1. 세그먼트 트리를 위해 4배 공간 생성
        minTree = new int[N*4];
        maxTree = new int[N*4];

        // 2. 트리 초기 세팅
        minInit(1, N, 1); // 구간 최소
        maxInit(1, N, 1); // 구간 최대

        // 3. 구간에서 최소 값 찾기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append( minSearch(1,N, 1, A, B) + " " + maxSearch(1, N, 1, A,B)+"\n" );
        }

        // 데이터 출력
        System.out.print(sb);
        
    }    

    // 구간에서 최소값 찾기
    public static int minSearch(int start, int end, int node, int left, int right){
        if( left > end || right < start ) return Integer.MAX_VALUE;
        if( left<= start && right >= end ) return minTree[node];
        int mid = midValue(start, end);
        return Math.min( minSearch(start, mid, node*2, left, right), minSearch(mid+1, end, node*2+1, left, right) );
    }

    // 구간에서 최대값 찾기
    public static int maxSearch(int start, int end, int node, int left, int right){
        if( left > end || right < start ) return Integer.MIN_VALUE;
        if( left<= start && right >= end ) return maxTree[node];
        int mid = midValue(start, end);
        return Math.max( maxSearch(start, mid, node*2, left, right), maxSearch(mid+1, end, node*2+1, left, right) );
    }

    // 각 구간 최소값 저장
    public static int minInit(int start, int end, int node){
        if(start == end) return minTree[node] = arr[start];
        int mid = midValue(start, end);
        return minTree[node] = Math.min(minInit(start, mid, node*2), minInit(mid+1, end, node*2+1));
    }

    // 각 구간 최대값 저장
    public static int maxInit(int start, int end, int node){
        if(start == end) return maxTree[node] = arr[start];
        int mid = midValue(start, end);
        return maxTree[node] = Math.max(maxInit(start, mid, node*2), maxInit(mid+1, end, node*2+1));
    }

    // // mid 값 구하기
    public static int midValue(int start, int end){
        return (start+end) / 2;
    }
    
}