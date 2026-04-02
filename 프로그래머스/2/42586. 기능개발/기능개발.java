/**
알고리즘 :
    우선순위 큐

풀어야하는 문제 :
    1. 먼저 배포 되야 하는 순서는 어떻게 만들까?
        -> 1. 가장 맨 앞 앞에 있는거 꺼냈을 때 배포해야하는 idx 를 가졌다면 뒤에 배포 (이어서만 가능)
        -> 2. 우선순위 큐 만들기 idx 기준으로 만들기
    2. 날마다 작업은 어떻게 진행 시켜야 할까?
        -> 1. 배포 불가능하면 전부 다 꺼내서 다른 또 다른 우선순위 큐에 담아주기
        -> 2. 1번 우선순위 큐 <-> 2번 우선순위 큐 돌아가면서 실행
        -> 3. or 돌아가면서 만들기 어렵다면, 함수하나 만들어서 ArrayList 에 담아주고 다시 큐에 넣어주기
            -> 뭐가 더 재밌을까? 우선순위 큐 2개 만들어서 다 옮겨주기 vs ArrayList 쓰기
            -> 우선순위 큐 2개로 왔다 갔다 해보자
        
전략 :
    1. 우선순위 큐 2개 생성 (idx가 앞서는 순)
    2. 1번 큐에 progresses 와 idx 다 넣어주기
    3. 1번 큐 가장 앞에꺼 꺼냈을 때 연쇄적으로 처리 가능하다면 count 후 정답 배열에 넣어주기
    4. 처리 불가능 한거는 2번 큐에 progresses 와 idx 다 넣어주기
    5. 1~4 반복
    
*/

import java.util.*;

class Solution {
    
    // 2개의 우선순위큐 (idx가 빠른 순으로 우선순위)
    static PriorityQueue<Progress> pqA = new PriorityQueue<>( (a,b) -> Integer.compare(a.idx, b.idx) );
    static PriorityQueue<Progress> pqB = new PriorityQueue<>( (a,b) -> Integer.compare(a.idx, b.idx) );
    static boolean check ; // T: pqA 작업, F: pqB 작업
    static ArrayList<Integer> list; // 정답 반환용 리스트
    
    public int[] solution(int[] progresses, int[] speeds) {
        
        list = new ArrayList<>();
        
        // 순회하면서 pqA 에 전부 넣어주기
        for(int i=0; i<progresses.length; i++){
            pqA.offer(new Progress(i, progresses[i], speeds[i]));
        }
        
        check = true; // check 기본값은 true    
        return search(); // 탐색 시작
    }
    
    // 탐색 시작
    static int[] search(){
        // pqA 와 pqB 두개 다 비어있어야만, 종료
        while( !pqA.isEmpty() || !pqB.isEmpty() ){
            if(check){ // pqA 작업 순서
                work(pqA, pqB);
                check = false;
            } else {   // pqB 작업 순서
                work(pqB, pqA);
                check = true;
            }
        }
        // list -> int[] 로 변환 필요함
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    // 작업 시작
    static void work( PriorityQueue<Progress> pq1, PriorityQueue<Progress> pq2 ){
        int count = 0;
        
        // 1. 가장 앞에 있는 큐 꺼내와서 job (처리량 확인하기)
        Progress cur = pq1.poll();
        cur.job += cur.speed;
        
        // 2. 가장 앞에 있는거 배포할 수 있는 경우
        if(cur.job >= 100){
            count++;
            while(!pq1.isEmpty()){
                Progress next = pq1.poll();
                next.job += next.speed;
                // 2-1. 연쇄적으로 배포 가능 (배포 준비완료 + 연쇄 가능)
                if(next.job >= 100 && cur.idx + 1 == next.idx){
                    cur = next; // 갱신
                    count++;
                }   
                // 2-2. 연쇄적으로 배포 불가능
                else{
                    pq2.offer(next);
                }
            }
            list.add(count);
        }
        
        // 3. 가장 앞에 있는거 배포 불가능 한 경우
        else{
            pq2.offer(cur); // 가장 앞에 꺼 pq2에 넣어주기
            while(!pq1.isEmpty()){ // 나머지 다 pq2에 넣어주기
                Progress next = pq1.poll();
                next.job += next.speed;
                pq2.offer(next);
            }
        }
    }

    static class Progress{
        int idx;   // Index
        int job;   // 작업 처리량 (max = 100)
        int speed; // 작업 속도
        Progress(int idx, int job, int speed){
            this.idx = idx;
            this.job = job;
            this.speed = speed;
        }
    }
    
}