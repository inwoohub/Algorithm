/**
알고리즘 :
    정렬    

문제 요약 :
    i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수 구하기

전략 :

    1. ArrayList 만들고 정렬하기


    -- 아래 전략은 중복 처리가 안되기 때문에 불가능! --
    1. TreeMap 만들기
    2. array에서 i ~ j 번째 숫자 TreeMap 에 넣어주기
    3. k번째에 있는 수 꺼내기 
*/

import java.util.*; 

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0; i<commands.length; i++){
            
            ArrayList<Integer> list = new ArrayList<>();
            
            int[] command = commands[i];
            for(int j=command[0]-1; j<command[1]; j++){
                list.add(array[j]);
            }
            Collections.sort(list); // 정렬
            answer[i] = list.get(command[2]-1);
        }
        return answer;
    }
}