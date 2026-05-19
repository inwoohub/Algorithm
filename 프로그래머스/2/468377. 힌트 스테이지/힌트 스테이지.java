/**
알고리즘 :
    Queue

문제 요약 :
    1. 각 스테이지를 깨기 위해 비용이 듦
    2. 각 스테이지마다 힌트권을 구매 or 패스 선택 가능
    3. 힌트권을 사용하면 가격이 더 쌈
    4. 최소 비용을 쓴다 가정하에 최소 비용 출력
    
    * 스테이지는 16 이하, n은 스테이지의 길이 및 힌트권 사용 시 가격 표기할 때
    * 힌트의 길이는 n-1

전략 :
    1. 큐 만들기
    2. 초기 값 넣어주기 (산 경우, 안 산경우 분기해서 값 넣어주기)
    3. n번째까지 도달했다면 최소값 갱신시켜서 업데이트 하기
*/

import java.util.*;

class Solution {
    public int solution(int[][] cost, int[][] hint) {
        
        int count = 0;
        
        int answer = Integer.MAX_VALUE;
        
        // 1. 큐 만들기
        ArrayDeque<Stage> q = new ArrayDeque<>();
        
        // 2. 초기 q 담아주기
        // 힌트를 안 산 경우
        int[] hints = new int[cost.length+1];
        q.offer(new Stage(1, cost[0][0], hints.clone() ));
        
        // 힌트를 산 경우
        for(int i=1; i<hint[0].length; i++){
            hints[hint[0][i]]++; // 힌트 개수 증가
        }
        q.offer(new Stage(1, cost[0][0]+hint[0][0], hints.clone() ));
        
        
        // 3. 큐에서 계속 꺼내면서 산 경우 안 산경우 분기하기
        while(!q.isEmpty()){
            
            Stage cur = q.poll();
            int curIndex = cur.index;
            int curValue = cur.value;
            
            // 종료 처리 (최소값 업데이트)
            if(curIndex == cost.length){
                answer = Math.min(answer, curValue);
                continue;
            }
            
            int[] curHint = cur.hintArr;
            int[] copyArr = new int[cost.length+1]; // 복제해서 넣어줄 배열
            copy(curHint, copyArr); // 배열 복제
            
            // 안 산 경우 새롭게 큐에 추가
            q.offer(new Stage(curIndex+1, curValue+ cost[curIndex][ curHint[curIndex+1] ], curHint.clone() )); // curHint[] ; 구매한 힌트 값 
            
            // 산 경우 큐에 추가
            // 힌트 개수 늘려주기
            if(curIndex < cost.length-1){ // 힌트가 있는 경우만 구매가능
                
                for(int i=1; i<hint[curIndex].length; i++){
                    int index = hint[curIndex][i];
                    copyArr[index] = (copyArr[index]) + 1; // 힌트 1개 증가
                    if(copyArr[index] > cost[0].length-1){ // 티켓수가 만약에 최대 사용가능한 티켓수보다 많다면,
                        copyArr[index] = cost[0].length-1; // 최대 사용 티켓수로 조절
                    }
                }    
                q.offer(new Stage(curIndex+1,  // 다음 스테이지
                                  curValue + cost[curIndex][copyArr[curIndex+1]] + hint[curIndex][0], // 현재 가격 + 힌트사용해서 구매 + 힌트 구매
                                  copyArr.clone() )); // 힌트 사서 담아서 넘기기
            }
            else { // 마지막 문제는 힌트 구매 못함.
                q.offer(new Stage(curIndex+1,  // 다음 스테이지
                                  curValue + cost[curIndex][curHint[curIndex+1]], // 현재 가격 + 힌트사용해서 구매
                                  copyArr.clone() )); // 힌트 사서 담아서 넘기기
            }
            
        }
        
        return answer;
    }
    
    // 배열 복제
    static void copy(int[] curHint, int[] copyArr){
        for(int i=0; i<curHint.length; i++){
            copyArr[i] = curHint[i];
        }
    }
    
    // 큐에 들어갈 객체 Stage
    static class Stage{
        int index;     // 현재 스테이지 번호
        int value;     // 누적 비용
        int[] hintArr; // 들어있는 힌트
        Stage(int index, int value, int[] hintArr){
            this.index = index;
            this.value = value;
            this.hintArr = hintArr;
        }
    }
    
}
