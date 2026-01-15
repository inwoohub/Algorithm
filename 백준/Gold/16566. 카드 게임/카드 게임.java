// ---------------
// 알고리즘
// upperBound 사용 (2분 탐색) 
// → 해당 값 초과 첫 위치 탐색
// Union-Find (DSU)
// → 사용한 카드는 밀어내기
// ---------------

import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static int N,M,K;
    static int[] card;
    static int[] parent;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // 카드 받기
        card = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            card[i] = Integer.parseInt(st.nextToken());    
        }
        Arrays.sort(card);

        parent = new int[M+1];
        for(int i=0; i<=M; i++){
            parent[i] = i;
        }

        // 게임 시작
        st= new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int A = Integer.parseInt(st.nextToken());

            int pos = upperBound(card,A); // A보다 큰 첫 번째 위치
            int idx = find(pos); // pos 이상에서 사용가능한 첫 카드

            sb.append(card[idx]+"\n");
            parent[idx] = find(idx+1); // 사용 후 다음 으로 넘기기 (부모)
        } 
        System.out.print(sb);
    }

    static int upperBound(int[] card, int A){
        int left = 0;
        int right = card.length-1;
        while(left < right ){
            int mid = (left+right) / 2;
            if (card[mid] <= A){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int find(int x){
        if (parent[x] == x) return x; //자기 자신 
        return parent[x] = find(parent[x]); // 부모 찾기
    }
    
}