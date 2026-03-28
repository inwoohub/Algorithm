/**
알고리즘 - 이중 우선순위 큐

1. PriorityQueue 2개 사용하기
    -> 구현 난이도 어려움 왜? 2개 사용하는 만큼 두개 사용상태 추적해야함
    -> 학습을 위해 사용해보고 TreeMap 까지 사용해보자.

2. TreeMap 사용하기 
    -> 이진 트리로 이미 정렬까지 다 구현되어있는 레드블랙트리임
    -> 이걸로 날먹 가능함. 우선순위큐 사용해보고 학습 해보자.
*/

import java.util.*;

class Solution {
    
    
    public int[] solution(String[] operations) {
        
        // 사용 추적용 HashMap
        // Integer: Index
        // Boolean : T: 사용 불가능(이미 삭제) F: 사용 가능
        HashMap<Integer, Boolean> map = new HashMap<>(); 
        
        // 우선순위 큐 2개 생성
        // [0] : Index    [1] : Value
        PriorityQueue<int[]> pqMin = new PriorityQueue<>( (a,b) -> Integer.compare(a[1], b[1]) ); // 오름차순 MIN 관리
        PriorityQueue<int[]> pqMax = new PriorityQueue<>( (a,b) -> Integer.compare(b[1], a[1]) ); // 내림차순 MAX 관리
        
        // operations 순회 하면서 삽입/삭제 시도 (i == Index)
        for(int i=0; i<operations.length; i++){
            
            String input = operations[i];
            String[] arr = input.split(" ");  // 공백을 기준으로 나누기
            char A = arr[0].charAt(0);        // 'I' or 'D'
            int B = Integer.parseInt(arr[1]); // Value (현재 수)
            
            // Insert
            if(A == 'I'){
                // 두 개의 우선순위 큐에 다 넣어주기
                pqMin.offer(new int[]{i, B});
                pqMax.offer(new int[]{i, B});
            }
            
            // Delete
            else{
                
                // 최댓값 삭제
                if(B == 1){
                    while(!pqMax.isEmpty()){
                        int[] cur = pqMax.poll();
                        int x = cur[0]; // Index
                        int y = cur[1]; // Value
                        
                        // 삭제 가능하다면, 삭제 후 종료 아니라면 아니라면 다음 숫자 탐색
                        boolean check = map.getOrDefault(x, false);
                        if(check){ // 이미 삭제한 상태
                            continue; 
                        } else { // 삭제 가능한 상태
                            map.put(x, true);
                            break;
                        }
                    }
                }
                
                // 최솟값 삭제
                else {
                    while(!pqMin.isEmpty()){
                        int[] cur = pqMin.poll();
                        int x = cur[0]; // Index
                        int y = cur[1]; // Value
                        
                        // 삭제 가능하다면, 삭제 후 종료 아니라면 아니라면 다음 숫자 탐색
                        boolean check = map.getOrDefault(x, false);
                        if(check){ // 이미 삭제한 상태
                            continue; 
                        } else { // 삭제 가능한 상태
                            map.put(x, true);
                            break;
                        }
                    }
                }
            }
        }
        
        // 결과 출력용 최댓값 찾기
        int MAX = search(pqMax, map);
        int MIN = search(pqMin, map);
        
        
        int[] answer = {MAX, MIN};
        
        return answer;
    }
    
    // 사용가능한 값(최대,최소) 찾아주기 없다면 0
    static int search(PriorityQueue<int[]> pq, HashMap<Integer, Boolean> map ){
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int A = cur[0]; // Index
            int B = cur[1]; // Value
            boolean check = map.getOrDefault(A, false);
            if(check){
                continue;
            }
            return B;
        }
        return 0;
    }
    
    
}