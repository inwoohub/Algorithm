// 알고리즘
// 정렬
import java.util.*;

class Solution {
    
    public String solution(String X, String Y) {
        
        StringBuilder sb = new StringBuilder();
        
        // 1. 0~9 까지 몇개씩 있는지 카운팅한 배열 생성
        int[] countA = new int[10];
        int[] countB = new int[10];
        
        // 2. 카운팅 하기
        counting(countA, X);
        counting(countB, Y);
        
        boolean checkA = false; // 첫번째가 0 인것 체크
        boolean checkB = false; // StringBuilder 채웠는지 아닌지 체크
        
        // 3. 비교 후 해당 수 만큼 String 에 넣기
        for(int i=9; i>=0; i--){
            int count = Math.min( countA[i], countB[i] ); // i 가 공통된 횟수
            
            // 0이 첫번째로 나온다면,
            if(i==0 && !checkA && count>0){
                return "0";
            }
            // 0말고 다른 수가 첫번째로 나온다면,
            else if( count>0 && !checkA ){
                checkA = true;
            }
            
            // StringBuilder에 i를 count 만큼 추가
            for(int j=0; j<count; j++){
                sb.append(String.valueOf(i));
                checkB = true;
            }
            
        }
        
        if(!checkB){
            return "-1";
        }
        
        // 정답 출력
        return sb.toString();
    }
    
    // 숫자 카운팅 (count: 카운팅 배열, arr: 들어있는 수 배열)
    static void counting(int[] count,String str){
        for(int i=0; i<str.length(); i++){
            int a = str.charAt(i) - '0' ;
            count[a]++;
        }
    }
    
}