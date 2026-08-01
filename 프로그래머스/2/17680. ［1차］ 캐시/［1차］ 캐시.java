import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 1. 정답 탐색
        answer = search(cacheSize, cities);
        
        // 2. 정답 반환
        return answer;
    }
    
    static int search(int cacheSize, String[] cities) {
        int time = 0;
        
        // 1. 캐시 존재 유무를 확인하는 HashMap 생성
        HashMap<String, CityInfo> map = new HashMap<>();
        
        // 2. LRU 알고리즘 활용을 위한 우선순위 큐 생성 (rank 오름차순)
        PriorityQueue<CityInfo> pq = new PriorityQueue<>( (a,b) -> Integer.compare(a.rank, b.rank) );
        
        // 3. 도시 순회
        for(int i=0; i<cities.length; i++) {
            // 3-1. 현재 도시 대/소문자 -> 소문자로 변경
            String curCity = converter(cities[i]);
            
            // 3-1. 현재 도시가 캐시에 존재하는지 확인
            CityInfo cur = map.getOrDefault(curCity,null);
            
            // 3-2. 현재 캐시에 존재하지 않다면
            if( cur == null ) {
                // 3-2-1. 현재 캐시 추가가능한지 확인
                if( map.size() < cacheSize ) {
                    // 바로 캐시 추가 가능한 경우
                    CityInfo info = new CityInfo(curCity, i);
                    map.put(curCity, info);
                    pq.offer(info);
                } else {
                    // 캐시 추가 불가능한 경우 오래된 것 하나 버리기
                    CityInfo infoOld = pq.poll();
                    try {
                        map.remove(infoOld.name); // 여기서 왜 NullPoniterException ?? -> 캐시 사이즈 0 인경우
                    } catch (NullPointerException e) {
                        if(cacheSize == 0) {
                            time+=5;
                            continue;
                        }
                    }
                    CityInfo infoNew = new CityInfo(curCity, i);
                    map.put(curCity, infoNew);
                    pq.offer(infoNew);
                }
                time += 5; // 캐시 미스로 시간 증가
            }
            // 3-3. 현재 캐시에 존재하다면
            else {
                pq.remove(cur);      // 우선순위큐에서 제거
                map.remove(curCity); // 오래된것 제거
                
                CityInfo infoNew = new CityInfo(curCity, i); // 새로운 객체 생성
                map.put(curCity, infoNew);
                pq.offer(infoNew); // 가장 상단으로 업데이트
                time += 1;
            }
        }
        // 4. 정답 반환
        return time;
    }
    
    // 문자열 소문자로 변경해주는 변환기
    static String converter(String A) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<A.length(); i++){
            int curInt = (int) A.charAt(i);
            if(curInt < 97){ // 소문자인 경우
                curInt += 32;
                sb.append( ( (char) curInt ) );
            } else {          // 대문자인 경우
                sb.append( ( (char) curInt ) );
            }
        }
        return sb.toString();
    }
    
    static class CityInfo{
        String name;
        int rank; // 우선 순위
        CityInfo(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
    }
    
}