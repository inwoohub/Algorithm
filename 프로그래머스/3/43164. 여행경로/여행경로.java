/**
목표 : 모든 항공권을 사용해서 여행경로 짜기
제한 사항:
    1. 모든 알파벳 대문자 3글자
    2. ICN 에서 무조건 시작
    3. 전부 다 쓰기
    4. 경로 2개 이상이면 알파벳 순서가 앞서기
    
전략 : (dfs + backtracking)
    1. ArrayList 만들기 (단방향 연결 가능하게)
    2. tickets 꺼내보면서 단방향 연결 해주기 (여러개 가능함으로 index 표기해주기)
    3. ArrayList 사전순으로 정렬해두기
    4. dfs로 방문하기, 정답 인덱스에 하나씩 담기
    5. 틀리다면 방문 해제 하면서 정답 인덱스 다시 !
*/

import java.util.*;

class Solution {
    
    static int ICN; // 시작 여행지는 인천이므로 인천 위치 저장용도
    static HashMap<String, Integer> map ; // 스트링 -> Int 로 변환용도
    static ArrayList<Trip>[] list;
    static boolean[] visited;
    static int indexMax;
    static int ticketsSize;
    
    public String[] solution(String[][] tickets) {
        
        ticketsSize = tickets.length;
        
        map = new HashMap<>();
        
        indexMax = tickets.length + 1;
        ICN = -1; // 인천 배열 index 초기 위치
        visited = new boolean[tickets.length]; // 방문 처리용 배열 생성
        
        // 1. list 배열 생성 후 초기화 (맵 연결 용도)
        list = new ArrayList[tickets.length*2+1];
        for(int i=0; i<tickets.length*2+1; i++){
            list[i] = new ArrayList<>();
        }
        
        // 해당 티켓에 맞는 int (배열에 넣을 매핑 용도)
        int count = 0;
        int idx = 0;
                
        // 2. 여행지 리스트로
        for(int i=0; i<tickets.length; i++){
//             String[] ticket = tickets[i];
            
//             // 해당 ticket이 없다면 count, 있다면 해당 매핑된 count 가져오기
//             int getT = map.getOrDefault(ticket[0], count);
            
//             // 처음 여행지 발견
//             if(getT == count){
//                 map.put(ticket[0], count);
//                 list[count].add(new Trip(ticket[1], idx)); // 단방향 연결
//                 idx++;
//                 if(ticket[0].equals("ICN")){
//                     ICN = count; // 인천 Index 저장
//                 }
//                 count++;
//             } else {
//                 // 기존 여행지 경우
//                 list[getT].add(new Trip(ticket[1], idx)); // 단방향 연결
//                 idx++;
//             }
            
//             // 도착지도 map에 등록 (없을 때만)
//             if(!map.containsKey(ticket[1])){
//                 map.put(ticket[1], count);
//                 count++;
//             }
            
            // 클선생
            String[] ticket = tickets[i];
            
            // 출발지 처리
            if (map.containsKey(ticket[0])) {
                int getT = map.get(ticket[0]);
                list[getT].add(new Trip(ticket[1], idx));
                idx++;
            } else {
                map.put(ticket[0], count);
                list[count].add(new Trip(ticket[1], idx));
                idx++;
                if (ticket[0].equals("ICN")) {
                    ICN = count;
                }
                count++;
            }
            
            if (!map.containsKey(ticket[1])) {
                map.put(ticket[1], count);
                if (ticket[1].equals("ICN")) {
                    ICN = count;
                }
                count++;
            } //클선생
            
        }
        
        // 3. ArrayList[] 여행지명 기준 사전순으로 정렬
        for(int i=0; i<count; i++){
            Collections.sort(list[i], (a,b) -> a.region.compareTo(b.region) );
        }
        
        // 정답용 String 리스트
        ArrayList<String> result = new ArrayList<>();
        result.add("ICN"); // 인천 시작
        
        // 4. 시작지가 인천인 경우로 시작
        for(int i=0; i<list[ICN].size(); i++){
            Trip cur = list[ICN].get(i);
            result.add(cur.region);
            visited[cur.index] = true; // 방문 처리
            int searchIdx = map.get(cur.region); // 다음 여행지 배열의 인덱스
            boolean check = search(result, searchIdx, 2);
            if(check) break; // 탐색 완료시 종료
            
            result.remove(1); // 백트래킹 리스트에서 제거
            visited[cur.index] = false; // 백트래킹 방문해제
        }
        
        String[] answer = new String[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    // dfs 탐색 시작
    static boolean search(ArrayList<String> result, int searchIdx, int idx){
        
        if(indexMax == idx ){
            return true;
        }
        for(int i=0; i<list[searchIdx].size(); i++){
            Trip cur = list[searchIdx].get(i);
            if(visited[cur.index]) continue; // 방문했던 여행지라면
            
            // 처음 방문한다면,
            result.add(cur.region);
            visited[cur.index] = true;
            int nextIdx = map.get(cur.region);
            // System.out.println(cur.region + " 방문");
            boolean check = search(result, nextIdx, idx+1);
            if(check){ // 탐색 성공 시
                return true;
            }
            result.remove(result.size() - 1); // 백트래킹 리스트에서 제거
            visited[cur.index] = false; // 백트래킹 방문해제   
        }
        return false;   
    }
    
    
    // trip (ArrayList 에 담을 타입)
    // region : 지역
    // index : 방문 처리용 index
    static class Trip{
        String region;
        int index;
        Trip(String region, int index){
            this.region = region;
            this.index = index;
        }
    }
}