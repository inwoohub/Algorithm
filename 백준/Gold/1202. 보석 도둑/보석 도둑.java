// -------------------------------------------------------
// 변수
// N : 보석의 개수
// K : 가방의 개수 (가방은 최대 1개의 보석만 가능)
// M, V ( M : 무게, V : 가격)
// C 가방에 담을 수 있는 무게

// gem[][] 배열 | [] : 보석의 순서,  [][0] : 무게,  [][1] : 가격
// bag[]   배열 | [] : 가방의 크기 (무게)

// -------------------------------------------------------
// 알고리즘
// DP (배낭문제)   vs    우선순위 큐 : 보석 가치별로 우선순위 만든 후 하나씩 대입? 시간복잡도 (최대 : 물품 수 * 배낭 수)

// -------------------------------------------------------
// 우선순위 큐 (PriorityQueue)
// 1. gem 배열 만들기, 무게 오름차순 (단, 무게가 같다면 가격 내림차순)
// 2. bag 배열 만들기, 무게 오름차순
// 3. 우선순위 큐에 현재 가방에 들어갈 수 있는 보석 다 넣기
// 4. 가격이 가장 비싼 보석 선택
// 5. 3~4 반복 (가방의 수 만큼))

import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int count = 0;
        long ans = 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b) -> { 
            if(a[1] == b[1]){
                return Integer.compare(a[0],b[0]);
            }
            return Integer.compare(b[1],a[1]);
        });

        int[][] gem = new int[N][2];
        int[] bag = new int[K];
        

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 무게
            int B = Integer.parseInt(st.nextToken()); // 가치
            gem[i][0] = A;
            gem[i][1] = B;
        }
        for(int i=0; i<K; i++){
            bag[i] = Integer.parseInt(br.readLine()); // 배낭 무게
        }
        boolean[] visited = new boolean[K];

        Arrays.sort( gem, (a,b) ->{
            if(a[0]==b[0]){
                return Integer.compare(b[1],a[1]);
            }return Integer.compare(a[0],b[0]);
        }); //무게 오름차순, 단 무게가 같다면 가치 내림차순
    
        Arrays.sort(bag); // 배낭 오름 차순
        
        int idx = 0;
    
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> Integer.compare(b,a)); // 내림차순 큐
        for(int i=0; i<K; i++){
            int cap = bag[i];
            while( idx < N && gem[idx][0] <= cap ){
                pq.offer(gem[idx][1]);
                idx++;
            }
            if(!pq.isEmpty()){
                ans = ans + (long) pq.poll();
            }
        }

        System.out.print(ans);        
        
    }
}