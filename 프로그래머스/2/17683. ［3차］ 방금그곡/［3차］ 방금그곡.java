/**
알고리즘 :
    구현

문제 요약 :
    1. 입력으로 네오가 기억한 멜로디를 담은 문자열 m 과 방송된 곡의 정보 담고있는 배열 musicinfos 주어짐
    2. musicinfos 는 100개 이하 곡 정보 담고있는 배열임
        - 시작한 시각, 끝난 시각, 음악 제목, 악보 정보 가 ',' 로 구분된 문자열임
        - "HH:MM" 이 형식임
    3. 조건과 일치하는 음악 제목을 출력함
    4. 조건은 m 에 포함되는 멜로디 인지
    
    * 100개 이하 곡 정보
    * 악보 정보 1~1439개 이하

전략 :
    1. contains?
    2. StringBuilder 에 넣어두고 그만큼 반복해서 넣기?
        -> 최악 시간 복잡도는? = 60*24 = 1440
    3. 시간만큼 악보를 이어붙여서 시간만큼 만들어 놓고 contains 사용하기
        -> 근데 만약있다면 맨 뒤자리에 # 확인해야함 (그럼 # 도 확인해보고 처리)
        
    반례 : "CCB", ["03:00,03:10,FOO,CCB#CCB", "04:00,04:08,BAR,ABC"] -> "FOO"
    1. 자바 메서드 replace() 활용해서 "A#" -> "a" 로 바꾸어서 #을 없애기
    2. 바뀐 악보를 기준으로 다시 악보만들고 contains 활용
*/

import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        
        String answer = "(None)"; // 노래 없음 초기 세팅
        int maxTime = -1; // 최대 시간
        m = replacing(m); // # -> 소문자 처리
        
        // 모든 음악 찾기
        for(String music : musicinfos){
            StringBuilder sb = new StringBuilder();
            String[] input = music.split(",");
            int[] times = time(input); // 현재 소모한 시간
            int time = times[1] - times[0];
            
            // 악보 시간만큼 이어 붙이기
            String musicinfo = input[3];
            musicinfo = replacing(musicinfo); // # -> 소문자 처리
            int curIndex=0;
            
            // 악보 만들기
            for(int j=0; j<time; j++){
                sb.append( musicinfo.charAt(curIndex) );
                curIndex++;
                curIndex = curIndex % musicinfo.length();
            }

            // 악보에 포함되었디면 갱신
            if (sb.toString().contains(m)) {
                if (time > maxTime) {
                    maxTime = time;
                    answer = input[2];
                }
            }  
        }
        return answer;
    }
    
    // A# -> a (#처리 스트링 변환)
    static String replacing(String input) {
        input = input.replace("C#","c");
        input = input.replace("A#","a");
        input = input.replace("D#","d");
        input = input.replace("F#","f");
        return input.replace("G#","g");
    }
    
    // 시간 꺼내서 int로 변환
    static int[] time(String[] input) {
        String[] A = input[0].split(":"); // 시작하는 시간 시간:분 자르기
        String[] B = input[1].split(":"); // 끝나는 시간 시간:분 자르기
        int timeStart = (Integer.parseInt(A[0]) * 60) + Integer.parseInt(A[1]); // 시간*60 + 분
        int timeEnd = (Integer.parseInt(B[0]) * 60) + Integer.parseInt(B[1]); // 시간*60 + 분
        int[] times = new int[2];
        times[0] = timeStart;
        times[1] = timeEnd;
        return times; // 시간 차이 반환
    }
}