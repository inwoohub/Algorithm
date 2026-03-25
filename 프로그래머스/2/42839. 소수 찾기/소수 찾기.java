// 알고리즘
// 소수 찾기 - 소수 몇개 만들 수 있늬?

// 소수 필터 하나 만들고
// numbers 로 만들 수 있는 모든 수 싹다 만들기

// 소수 필터에서 걸리면 소수가 되고 아니면, 소수가 아님

import java.util.*;

class Solution {
    
    static boolean[] arr; // 소수라면 T
    static boolean[] visited; // 인덱스 사용했다면 T
    static boolean[] useNumber; // 해당 소수 사용했다면 T
    static int count;
    
    public int solution(String numbers) {
        
        count = 0;
        
        int SIZE = numbers.length(); // 문자열 길이
        int arrSize = 1;
        for(int i=0; i<SIZE; i++){
            arrSize = arrSize*10; // 소수배열 사이즈
        }
        
        // 배열 생성
        visited = new boolean[SIZE];
        useNumber = new boolean[arrSize];
        arr = new boolean[arrSize];
        
        Arrays.fill(arr,true); // 소수로 전부 초기화
        arr[1] = false; // 1은 소수가 아님
        for(int i=2; i<= (int) Math.sqrt(arrSize); i++){
            for(int j=i+i; j<arrSize; j=j+i){
                arr[j] = false; // 소수 아닌것들 전부 필터링 과정 (아리스토테네스의 채)
            }
        }
        
        // 문자 -> int 배열로 변환
        int[] intArr = new int[SIZE];
        for(int i=0; i<SIZE; i++){
            intArr[i] = numbers.charAt(i) - '0';
        }
        
        Arrays.sort(intArr); // 오름차순으로 정렬
        reverse(intArr); // 내림차순으로 정렬
        
        // dfs 탐색 단, 0으로 시작할 수는 없음
        for(int i=0; i<SIZE; i++){
            if(intArr[i] == 0) continue;
            visited[i] = true;
            dfs( intArr[i] , SIZE, intArr );
            visited[i] = false;
        }
        return count;
    }
    
    // dfs 함수 , cur : 현재값 , SIZE: 인덱스 총 크기
    static void dfs(int cur, int SIZE, int[] intArr){
        
        // 현재 들어온 값 소수 확인하기 (소수이고, 사용아직 안했다면)
        if(arr[cur] && !useNumber[cur]){
            count++;
            useNumber[cur] = true;
        }
        
        // 다음 방문 탐색하기
        for(int i=0; i<SIZE; i++){
            if(!visited[i]){
                visited[i] = true;
                String strCur =  String.valueOf(cur);
                String strI = String.valueOf(intArr[i]);
                strCur = strCur + strI; // 문자열 이어 붙이기
                dfs( Integer.parseInt(strCur), SIZE, intArr );
                visited[i] = false; // 백트래킹 (방문 처리 해제)
            }
        }   
    }
    
    
    static void reverse(int[] arr){
        
        // 복제용 배열 생성
        int[] trans = new int[arr.length];
        
        // 배열 복제
        for(int i=0; i<arr.length; i++){
            trans[i] = arr[i];
        }
        
        // 배열 뒤집기
        for(int i=0; i<arr.length; i++){
            arr[i] = trans[ arr.length-1-i ];
        }
    }
    
}