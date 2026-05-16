/**
알고리즘 :
    브루트 포스 (전부 탐색)    

문제 정리 :
    1번 수포자 : 1 ~ 5 계속해서 반복해서 품
    2번 수포자 : 2 - 1 - 2 - 3 - 2 - 4 처럼 2가 먼저 앞서 나오고 하나씩 돌림
    3번 수포자 : 3 - 1 - 2 - 4 - 5 순으로 2번씩 돌림
    
    * 시험 최대 10,000 문제 최대 연산 횟수는 10,000 * 3 = 3만번

전략 :
    1. 1번 부터 3번 까지 answer 에 맞게 끼워 나가면서 카운티
    2. 가장 높았던 값을 return 하기
*/

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        // 각 사람의 정답을 미리 배열 만들어서 넣어두기
        int[] person1 = new int[answers.length];
        int[] person2 = new int[answers.length];
        int[] person3 = new int[answers.length];
        
        make1(person1);
        make2(person2);
        make3(person3);
        
        // 정답 카운팅
        int p1 = check(person1, answers);
        int p2 = check(person2, answers);
        int p3 = check(person3, answers);
        
        // 가장 높은 것 비교
        ArrayList<int[]> list = new ArrayList<>();
        
        list.add(new int[]{1,p1});
        list.add(new int[]{2,p2});
        list.add(new int[]{3,p3});
        
        // 점수가 높은 순으로 정렬
        Collections.sort(list, (a,b) -> Integer.compare(b[1], a[1]));
        
        int MAX = 0;
        int count = 0;
        for(int i=0; i<3; i++){
            if(MAX <= list.get(i)[1]){
                MAX = list.get(i)[1];
                count++;
            } else {
                break;
            }
        }
        
        int[] answer = new int[count];
        for(int i=0; i<count; i++){
            answer[i] = list.get(i)[0];
        }
        
        return answer;
    }
    
    // 정답 카운팅
    static int check(int[] person, int[] answers){
        int p = 0;
        for(int i=0; i<answers.length; i++){
            if(person[i] == answers[i]){
                p++;
            }
        }
        return p;
    }
    
    
    // 1번 수포자 정답 매핑
    static void make1(int[] person1){
        for(int i=1; i<=person1.length; i++){
            int value = i % 5;
            if(value == 0){
                value = 5;
            }
            person1[i-1] = value;
        }
    }
    
    // 2번 수포자 정답 매핑
    static void make2(int[] person2){
        int value = 1; // 짝수번째만 1씩 증가함. 다만 2는 들어갈 수 없고 3부터 가능
        for(int i=0; i<person2.length; i++){
            if(i%2 == 0){ // 짝수
                person2[i] = 2;
            }
            else {
                person2[i] = value;
                value++;
                if(value == 2){
                    value++;
                }
                if(value == 6){
                    value = 1;
                }
            }
        }
    }
    
    // 3번 수포자 정답 매핑
    static void make3(int[] person3){
        boolean check = true; // 2번 체크를 위한 (T/F)
        int value = 3;
        for(int i=0; i<person3.length; i++){
            person3[i] = value;
            if(check){
                check = false; // 한번 더 씀
            }
            else {
                check = true; // 값 바꿔줘야함
                if(value == 3){
                    value = 1;
                } else if(value == 1){
                    value = 2;
                } else if(value == 2){
                    value = 4;
                } else if(value == 4){
                    value = 5;
                } else {
                    value = 3;
                }
            }
        }
    }   
}