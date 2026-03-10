// 알고리즘
// 세그먼트 트리 (구간곱)

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static Long[] arr, tree;
    static final Long DIV = 1000000007L;

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int SIZE = Integer.parseInt(st.nextToken());
        int testCase = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        arr = new Long[SIZE+1];
        tree = new Long[SIZE*4];
        for(int i=1; i<=SIZE; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        // 1. 세그먼트 트리 생성
        Init(1, SIZE, 1);

        // 2. update or search
        for(int tC=0; tC<testCase; tC++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            

            if(A == 1){
                // update
                int B = Integer.parseInt(st.nextToken());
                Long C = Long.parseLong(st.nextToken());
                arr[B] = C;
                update(1,SIZE,1,B, C);
            }

            else {
                // search
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                sb.append(search(1,SIZE,1,B,C)+"\n");
            }
            
        }
        // 데이터 출력
        System.out.print(sb);
        
    }

    // 세그먼트 트리 탐색
    static Long search(int start, int end, int node, int left, int right){
        // 범위 밖 체크
        if(start > right || end < left) return 1L;

        // 범위 안 체크
        if(start >= left && right >= end) return tree[node];

        // 다음 구간 탐색
        int mid = (start+end)/2;
        return search( start, mid, node*2, left, right ) * search( mid+1, end, node*2+1 , left, right ) % DIV;
        
    }

    // 세그먼트 트리 업데이트
    static Long update(int start, int end, int node, int idx, Long value){
        // 범위 밖이라면
        if(start > idx || end < idx) return tree[node];

        // update (리프노드)
        if(start == idx && end == idx){
            return tree[node] = value;
        }

        // 다음 노드 탐색
        int mid = (start+end) / 2;
        return tree[node] = update(start , mid , node*2, idx, value) * update(mid+1, end, node*2+1, idx, value) % DIV;
    }

    // 세그먼트 트리 만들기 (구간곱)
    static Long Init(int start, int end, int node){
        if(start == end) return tree[node] = arr[start];
        int mid = (start+end) / 2;
        return tree[node] = Init(start, mid, node*2) * Init(mid+1, end, node*2+1) % DIV;
    }
    
}