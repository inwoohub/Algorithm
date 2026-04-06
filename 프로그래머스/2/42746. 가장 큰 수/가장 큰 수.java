/**
알고리즘 :
    정렬 ( ArrayList 사용 )

전략 :
    1. ArrayList<Integer> 생성
    2. Sort(정렬) 시
        -> 두개의 문자열 이어 붙이고, 더 큰수가 -> 더 큰 수
    3. ArrayList.stream.map.collet(Collectors.joining()) 으로 이어 붙어주기
*/

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        // 1. ArrayList 생성
        ArrayList<Integer> list = new ArrayList<>();
        for(int next : numbers){
            list.add(next);
        }
        
        // 2. Sort
        Collections.sort(list, (a,b) -> {
            String strA = String.valueOf(a) + String.valueOf(b);
            String strB = String.valueOf(b) + String.valueOf(a);
            return strB.compareTo(strA);
        });
        
        if(list.get(0) == 0){ // 가장 큰수가 0이라면?
            return "0";
        }
        
        // joining() 으로 이어붙이기
        return list.stream()
                .map(i -> String.valueOf(i))
                .collect(Collectors.joining());
    }
}