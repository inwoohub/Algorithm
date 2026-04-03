/**
문제 :
    트럭 여러대가 일차선 다리를 정해진 순으로 건너려고함.
    모든 트럭이 다리를 건너려면 최소 몇초가 걸리는지!
    
제한 조건 :
    1. 트럭이 bridge_length 대 올라갈 수 있음 (1 ~ 10000)
    2. 다리는 weigth 이하까지 무게 버틸 수 있음 (1 ~ 10000)
    3. truck_weigth 는 각 트럭의 무게 (길이는 1 ~ 10000)
    
전략 :
    1. 다리를 건너는 트럭 큐 (1개 만들기) -> 대기 트럭은 큐 없이 for문으로 순회
    2. 대기 트럭이 하나 꺼내기
    3. 다리를 건너는 트럭 큐가 비어있다면
        -> 다리를 건너는 트럭에 해당 트럭 넣어주기
        -> 시간 1 증가
    4. 다리를 건너는 트럭 큐가 비어있지 않다면,
        -> 현재 쓰고있는 useWeigth 확인하기
        -> 현재 올라가있는 트럭개수 확인하기 (큐 사이즈)
        4-1. 추가 가능 하다면
            -> 끝나는 시간 + 해당 트럭 무게 넣어주기
        4-2. 추가 불가능 하다면
            -> 먼저 끝나는 트럭 꺼내면서 시간 갱신
            -> 다시 4번 으로 돌아가기
    5. for문 순회 다했다면, 이제 남은 다리 (마지막) 트럭 지나가고 +1 하며 마무리
*/

import java.util.*;

class Solution { 
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int curTimes = 0;  // 현재 시간
        int useWeight = 0; // 다리 위에 트럭의 총 무게
        Deque<Truck> dq = new ArrayDeque<>(); // 다리를 건너는 트럭 큐
        for(int next : truck_weights){
            // 3. 다리를 건너는 트럭큐가 비어있는 경우
            if(dq.isEmpty()){
                dq.offer(new Truck(curTimes+bridge_length, next)); // 끝나는 시간 , 무게 넣어주기
                useWeight = next; // 현재 사용중인 무게
                curTimes++;
            }
            // 4. 다리를 건너는 트럭큐가 비어있지 않다면
            else{
                while(true){
                    if( useWeight + next <= weight && dq.size() < bridge_length ){ // 추가 가능 하다면,
                        dq.offer(new Truck(curTimes+bridge_length, next)); // 큐에 추가
                        useWeight += next; // 현재 사용중인 무게 증가                   
                        curTimes++;
                        break;
                    }
                    else{ // 추가 불가능 하다면,
                        Truck truck = dq.poll();
                        useWeight -= truck.w; // 사용중인 무게 감소
                        curTimes = Math.max(truck.end, curTimes);
                    }    
                }
            }
        }
        // 5. for문 빠져나와서 dq에서 마지막꺼만 털고 끝내기
        if(!dq.isEmpty()){
            curTimes = dq.pollLast().end + 1;
        }
        return curTimes;
    }
    
    static class Truck{
        int end; // 끝나는 시간
        int w;   // 무게
        Truck(int end, int w){
            this.end = end;
            this.w = w;
        }
    }   
}