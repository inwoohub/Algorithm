/**
목표 : 모든 항공권을 사용해서 여행경로 짜기
제한 사항:
    1. 모든 알파벳 대문자 3글자
    2. ICN 에서 무조건 시작
    3. 전부 다 쓰기
    4. 경로 2개 이상이면 알파벳 순서가 앞서기
    
전략 : (dfs + backtracking)
    1. ArrayList 만들기                      (단방향 연결 용도) ex) 'ICN' -> 'JFK', 'ICN' -> 'SFO' ...
    2. tickets 꺼내보면서 단방향 연결 해주기       (데이터 매핑) 
    3. ArrayList에서 단어 사전순으로 정렬해두기     (정렬)
    4. dfs로 방문하기, 정답 인덱스에 하나씩 담기     (dfs 탐색) 
    5. 완성하지 못한 경우 dfs 빠져나오면서 방문 해제  (backtracking)
    6. 끝가지 방문했을 때 True 반환하며 dfs 벗어나기 (종료 조건)
*/

import java.util.*;

class Solution {
    
    static int ICN; // 시작 여행지는 인천이므로 인천 위치 저장용도
    static HashMap<String, Integer> map = new HashMap<>(); // 스트링 -> Int 로 변환용도
    static ArrayList<Trip>[] list; // 여행 경로를 담은 ArrayList
    static boolean[] visited; // 방문 배열
    static int indexMax; // 여행 경로 크기
    
    public String[] solution(String[][] tickets) {
        
        indexMax = tickets.length + 1;
        ICN = -1; // 인천 배열 index 초기값 세팅 ('-1')
        visited = new boolean[tickets.length+1]; // 방문 처리용 배열 생성
        
        // 1. list 배열 생성 후 초기화 (맵 연결 용도)
        list = new ArrayList[tickets.length + 1];
        for(int i=0; i<tickets.length+1; i++){
            list[i] = new ArrayList<>();
        }
        
        int count = 0; // ex) "ICN" -> '0', 'SFO' -> '1' 처럼 HashMap 써서 변환 용도
        int idx = 0;   // ex) "ArrayList[]" 배열에 인덱스
                
        // 2. 여행지 리스트로
        for(int i=0; i<tickets.length; i++){
            String[] ticket = tickets[i];
            
            // '출발지' 에 맞는 리스트의 idx 가져오기
            int getT = map.getOrDefault(ticket[0], count);
            
            if(getT == count){ // 만약, 처음 '출발지' 발견
                map.put(ticket[0], count); // String -> Integer 로 HashMap 사용해서 변환
                list[count].add(new Trip(ticket[1], idx)); // 출발지 -> 도착지로 단방향 연결
                idx++;
                if(ticket[0].equals("ICN")){
                    ICN = count; // 인천 발견 시 Index 저장
                }
                count++;
            } else { // '출발지' 첫 발견이 아니라면,
                list[getT].add(new Trip(ticket[1], idx)); // 출발지 -> 도착지로 단방향 연결
                idx++;
            }
            
            // '도착지' 에 맞는 리스트의 idx 가져오기
            getT = map.getOrDefault(ticket[1], count);
            
            if(getT == count){ // 만약, 처음 '도착지' 발견
                map.put(ticket[1], count);
                if(ticket[1].equals("ICN")){
                    ICN = count; // 인천 Index 저장
                }
                count++;
            }   
        }
        
        // 3. ArrayList[] 여행지명 기준 사전순으로 정렬
        for(int i=0; i<count; i++){
            Collections.sort(list[i]);
        }
        
        // 정답용 String 리스트 생성
        ArrayList<String> result = new ArrayList<>();
        result.add("ICN"); // 인천 시작
        
        // 4. 시작지가 인천인 경우로 시작
        search(result, ICN, 1);
        
        String[] answer = new String[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    // dfs 탐색 시작
    static boolean search(ArrayList<String> result, int searchIdx, int idx){
        
        if(indexMax == idx ) return true; // 종료! (모두 찾은 경우)
        
        for(int i=0; i<list[searchIdx].size(); i++){
            Trip cur = list[searchIdx].get(i);
            if(visited[cur.index]) continue; // 방문했던 여행지라면 넘어가기
            
            // 처음 방문한다면,
            result.add(cur.region);
            visited[cur.index] = true;
            int nextIdx = map.get(cur.region);
            boolean check = search(result, nextIdx, idx+1); // 다음 dfs 탐색
            
            if(check) return true;  // 종료! (탐색 성공 시)
            
            result.remove(idx); // 백트래킹 리스트에서 제거
            visited[cur.index] = false; // 백트래킹 방문해제   
        }
        return false;   
    }

    // trip (ArrayList 에 담을 타입)
    // region : 지역
    // index : 방문 처리용 index
    static class Trip implements Comparable <Trip> {
        String region;
        int index;
        
        Trip(String region, int index){
            this.region = region;
            this.index = index;
        }
        
        @Override
        public int compareTo(Trip other) {
            return (this.region).compareTo(other.region);  // compare → compareTo
        }
        
    }
}
