// 알고리즘
// dfs

import java.util.*;

class Solution {
    
    static int[] answer = new int[11]; // 같은 점수 중 가장 낮은거 찾는 배열
    static int MAX; // 라이언의 최대값
    static int[] result; // 최대값에 맞은 결과 배열
    
    public int[] solution(int n, int[] info) {
        MAX = 0; // 이기는 최대값
        result = new int[11];
        
        // 1. 이기는 최대값 찾기
        searchMAX(n, 10, info, n);
    
        // 2. 이기는 최대값이 없다면 그냥 {-1} 반환
        if(MAX == 0){
            result = new int[1];
            result[0] = -1;
            return result;
        }
        
        // 3. 최대값과 같은 결과인 (낮은 과녁 더 많이 맞추기 찾기)
        searchArr(n, 10, info, n);
        
        // 4. 결과 배열 반환하기 
        return result;
        
    }
    
    // n: 화살수, idx: 채워 넣는 곳, info: 어피치의 과녁 - 최대값중 가장 우선순위 높은 배열 찾기
    static boolean searchArr(int n, int idx, int[] info, int total){
        
        boolean check = false;
        
        if(idx<0){ // 인덱스 초과시 터트리기
            return false;
        }
        
        if(n<=0){ // 화살 부족해도 터트리기
            return false;
        }
        
        for(int i=n; i>=0; i--){
            result[idx] = i; // 정답용 배열에 화살 넣기 (완전 탐색)
            
            check = matchArr(info, total);
            if(check) return check;
            
            check = searchArr(n-i, idx-1, info, total);
            if(check) return check;
        }
        return check;
    }
    
    // 배열 찾기 (MAX 값 찾아야함)
    static boolean matchArr(int[] info, int total){
        int RYAN = 0;
        int APPECH = 0;
        int RYAN_COUNT = 0;
        for(int i=0; i<11; i++){
            RYAN_COUNT = RYAN_COUNT + result[i]; // 라이언이 쓴 화살 수
            
            if(info[i] == result[i] && info[i] == 0) continue; // 둘 다 하나도 안 맞추면 넘어가기
            
            if(info[i] >= result[i]){ 
                APPECH = APPECH + (10-i); // 어피치가 더 많이 맞추거나 같은 경우는 어피치 승리
            } else if (info[i] < result[i]){
                RYAN = RYAN + (10-i); // 라이언이 더 많이 맞춰야 라이언 승리
            }
        }
        if( (RYAN-APPECH) == MAX && RYAN > APPECH && RYAN_COUNT == total){ // 최대값과 똑같다면 true 반환, 화살도 꼭 다 써야함
            return true;
        }
        return false;
        
    }
    
    
    // n: 화살수, idx: 채워 넣는 곳 info: 어피치의 과녁 - 최대값 찾기
    static void searchMAX(int n, int idx, int[] info, int total){
        
        // 더 이상 바꿀 idx가 없다면 돌려 보내기
        if(idx<0){
            return;
        }
        if(n<=0){
            return;
        }
        
        for(int i=n; i>=0; i--){
            answer[idx] = i; // 현재 answer[i] 에 i 넣어주기
            
            // 그 상태로 info 와 비교 시 더 높은 값 찾아주기
            matchMAX(info, total);
            
            searchMAX(n-i, idx-1, info, total); // 안 쓴 화살 들고 다음으로 이동
        }

        
    }
    
    // info <-> result 비교 후 라이언이 이긴다면 MAX 값 갱신
    static void matchMAX(int[] info, int total){
        int RYAN_COUNT = 0;
        int RYAN = 0;
        int APPECH = 0;
        for(int i=0; i<11; i++){
            RYAN_COUNT += answer[i];
            if(info[i] == answer[i] && info[i] == 0) continue; // 둘 다 하나도 안 맞추면 넘어가기
            
            if(info[i] >= answer[i]){ 
                APPECH = APPECH + (10-i); // 어피치가 더 많이 맞추거나 같은 경우는 어피치 승리
            } else if (info[i] < answer[i]){
                RYAN = RYAN + (10-i); // 라이언이 더 많이 맞춰야 라이언 승리
            }
        }
        
        // 라이언 vs 어피치 비교하기 
        if(RYAN > APPECH && (RYAN-APPECH) > MAX && total == RYAN_COUNT){
            MAX = RYAN - APPECH; // 라이언이 이긴다면 MAX 값 갱신하기
        }
        
    }
    
}