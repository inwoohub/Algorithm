/**
알고리즘 :
    비트 연산

문제 요약 :
    1. 네오 -> 프로도의 비상금 비밀지도 GET
    2. 숫자로 암호화 되어있음 (해독 가능)
    3. "공백" or "#"(벽) 두 종류
    4. 두 장의 지도 겹쳐서 전체 지도 얻을 수 있음
    5. 전체 지도를 알 수 있게 반환
    
    * 지도 한 변의 크기 1 <= n <= 16
    
전략 :
    1. 각 지도의 배열을 비트로 변환
    2. 비트 or 연산을 통해 더하기
    3. 해당 비트를 문자로 변환하여 정답 반환
*/

import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        for(int i=0; i<arr1.length; i++){
            answer[i] = searchMap(arr1, arr2, i);
        }
        return answer;
    }
    
    static String searchMap(int[] arr1, int[] arr2, int index){
        StringBuilder sb = new StringBuilder();
        int bit = (arr1[index]|arr2[index]); // or 연산
        String strBit = Integer.toBinaryString(bit); // 비트로 변환
        for(int i=0; i<arr1.length - strBit.length(); i++){
            sb.append(" ");
        }
        // 문자열로 변환
        for(int i=0; i<strBit.length(); i++){
            if( strBit.charAt(i) == '1' ){
                sb.append("#");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
}