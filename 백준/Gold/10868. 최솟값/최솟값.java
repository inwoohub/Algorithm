// 알고리즘
// 세그먼트 트리 (26.03.07 복습)

// 10 4
// 75
// 30
// 100
// 38
// 50
// 51
// 52
// 20
// 81
// 5
// 1 10
// 3 5
// 6 9
// 8 10

import java.io.*;
import java.util.*;

public class Main{

    static int[] arr, minTree;
    
    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1]; // 숫자 받은 배열
        minTree = new int[N*4]; // 세그먼트 트리 (최솟값)

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1. 세그먼트 트리 만들기
        minInit(1, N, 1);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            // 2. 구간 탐색
            sb.append( minSearch(1, N, 1, A, B) +"\n" );

        }

        // 데이터 출력
        System.out.print(sb);

    }

    // 최소 구간 탐색
    static int minSearch( int start, int end, int node, int left, int right ){
        
        // 범위 초과 체크
        if( start > right || end < left ) return Integer.MAX_VALUE;
        
        // 범위 내부 체크
        if ( start >= left && end <= right ) return minTree[node];

        // 범위 초과도 아니고, 내부도 아니라면 분할
        int mid = (start+end) / 2;

        return Math.min( minSearch( start,mid,node*2, left, right ) , minSearch(mid+1, end, node*2+1, left, right) );
        
    }
    
    // 최소 세그먼트 트리 만들기
    static int minInit(int start, int end, int node){
        if(start == end) return minTree[node] = arr[start];
        int mid = (start+end)/2;
        return minTree[node] = Math.min( minInit(start, mid, node*2), minInit(mid+1, end, node*2+1) );
    }
    
}