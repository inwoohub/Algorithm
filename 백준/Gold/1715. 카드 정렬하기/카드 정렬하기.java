// 알고리즘
// 우선 순위 큐 + 누적합
// =================
// 3
// 10
// 20
// 40
// =================
// 100
// =================

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> Integer.compare(a,b)); // 오름차순 우선순위 큐
        
        for(int i=0; i<N; i++){
            pq.offer(Integer.parseInt(br.readLine()));
        }

        // 1) 우선순위 큐가 비어있을 때까지 반복 단, N=1 이면 '0' 출력 후 종료
        if( N == 1){
            System.out.println(0);
        } else {
            int ans = 0;
            while(!pq.isEmpty()){
                if(pq.size()>=2){ // 큐에 2개 이상 남아있는
                    int A = pq.poll(); // 가장 작은거
                    int B = pq.poll(); // 두번째로 작은거
                    ans = ans+(A+B);
                    pq.offer(A+B); // 더 해주고 우선순위 큐 넣기
                } else { // 큐에 1개밖에 안남은 경우
                    pq.poll(); // 버리고 끝내기
                }
            } // END of while
            System.out.print(ans);
        } // END of else
    }
}