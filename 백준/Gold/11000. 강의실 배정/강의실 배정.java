// 알고리즘
// 우선순위 큐 + 그리디
// ===============
// 3
// 1 3
// 2 4
// 3 5
// ===============
// 2
// ===============

import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        // 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken()); // N개의 수업
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list.add(new int[]{A,B});
        } // 데이터 입력 끝

        // 1) 리스트 시작점 기준 정렬
        Collections.sort(list, (a,b) -> Integer.compare(a[0],b[0]) );

        // 2) 우선 순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a,b));
        int count = 1; // 사용한 강의실 (초기값 1)
        pq.offer(0);
        
        // 3) 강의실 탐색
        for(int i=0; i<N; i++){   
            int curEnd = pq.poll(); // 가장 빨리 끝나는 시간 갱신
            int[] next = list.get(i);
            int nextStart = next[0];
            int nextEnd = next[1];
            // 4) 만약 curEnd <= nextStart 라면, 기존 방 사용 가능 (대신, 우선순위 큐 업데이트 해야함)
            if(curEnd <= nextStart){
                pq.offer(nextEnd);
            } else { // 5) 그렇지 않다면, 방 새로 추가해야함
                pq.offer(nextEnd);
                pq.offer(curEnd);
                count++;
            }
        } // End of for

        // 데이터 출력
        System.out.print(count);
    }
}