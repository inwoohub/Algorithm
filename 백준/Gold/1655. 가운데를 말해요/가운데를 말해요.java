// =================
// 알고리즘
// Tree 만들기 (인덱스)
// =================
// 7
// 1
// 5
// 2
// 10
// -99
// 7
// 5
// =================
// 1
// 1
// 2
// 2
// 2
// 2
// 5
// =================

import java.io.*;
import java.util.*;

public class Main{
    

    static StringBuilder sb = new StringBuilder();
    static int N; // 정수의 개수

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 우선순위 큐 2개 사용
        PriorityQueue<Integer> pq1 = new PriorityQueue<>( (a,b) -> Integer.compare(b,a) ); // 내림차순 pq
        PriorityQueue<Integer> pq2 = new PriorityQueue<>( (a,b) -> Integer.compare(a,b) ); // 오름차순 pq

        // 시작

        // 1) pq1에 처음 1개 넣고 시작.
        int cur = Integer.parseInt(br.readLine());
        pq1.offer(cur);
        sb.append(pq1.peek()+"\n");

        // 2) 2번째까지 넣어줌
        if(N >= 2){
            cur = Integer.parseInt(br.readLine());
            if(pq1.peek() > cur){
                pq2.offer( pq1.poll() );
                pq1.offer(cur);
                sb.append( pq1.peek()+"\n" );
            } else {
                pq2.offer(cur);
                sb.append( pq1.peek()+"\n" );
            }
        }

        // 3) 3번째 수 부터 탐색 시작
        for(int i=2; i<N; i++){
            
            cur = Integer.parseInt(br.readLine());

            int peek1 = pq1.peek();
            int peek2 = pq2.peek(); 
            
            // 4) 1번 PQ가 사이즈가 더 클 때
            if(pq1.size() > pq2.size()){
                // 4-1) peek1 이 cur 보다 큰 경우 : peek1을 PQ2로 이동 , cur을 PQ1으로
                if(peek1 > cur){
                    pq2.offer( pq1.poll() );
                    pq1.offer( cur );
                    sb.append( pq1.peek()+"\n" );
                } else {
                    pq2.offer( cur );    
                    sb.append( pq1.peek()+"\n" );
                }
            }

            // 5) 2번 PQ가 사이즈가 더 큰 경우
            else {
                // 5-1) peek1 이 cur 보다 큰경우 : cur을 PQ1으로 넣으면 됨
                if(peek1 > cur){
                    pq1.offer(cur);
                    sb.append(pq1.peek()+"\n");
                } else {

                    // 5-2) cur이 peek2보다 작으면 왼쪽
                    if(peek2>cur){
                        pq1.offer(cur);
                        sb.append(pq1.peek()+"\n");
                    } else {
                        pq1.offer(pq2.poll());
                        pq2.offer(cur);
                        sb.append(pq1.peek()+"\n");
                    }
                }
            }
        } // End of for
        System.out.print(sb);

    }    
    
}