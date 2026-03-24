// 알고리즘
// 해시 + 우선순위 큐

import java.util.*;

class Solution {
    
    static HashMap<String, Integer> map = new HashMap<>(); // 재생 많이 한 장르 | key: 장르, value : 횟수
    static HashMap<String, PriorityQueue<int[]> > pMap = new HashMap<>(); // key : 장르, value : 우선순위큐( 많이 재생한 순)
    
    public int[] solution(String[] genres, int[] plays) {
        
        
        // HashMap 2개 다 데이터 저장해주기
        for(int i=0; i<genres.length; i++){
            // 1.  map : <장르, 횟수> 저장
            int curPlays = map.getOrDefault(genres[i] ,0); // 기존에 있으면 가져오고 없다면 '0'
            curPlays += plays[i]; // 값 변경
            map.put(genres[i], curPlays); // 횟수 업데이트
            
            // 2. pMap : <장르, 우선순위 큐> 저장
            PriorityQueue<int[]> pq = pMap.getOrDefault(genres[i], new PriorityQueue<>( (a,b) ->{
                if (a[1] == b[1]){ 
                    return a[0] - b[0]; // 횟수가 같다면 인덱스가 높은 순으로 저장
                }
                return b[1] - a[1]; // 횟수가 다르다면, 높은게 먼저
            }));
            pq.offer(new int[]{i, plays[i]}); // 우선순위 큐에 저장
            pMap.put(genres[i], pq); // 우선순위 큐 업데이트
        }
        
        // 횟수 많은 것부터 정렬하기 위해 리스트 생성
        ArrayList<Art> list = new ArrayList<>();
        
        // map 순회
        for(String key : map.keySet()){
            list.add(new Art(key, map.get(key)));
        }
        
        // 리스트 정렬 (재생많이 한 순으로)
        Collections.sort(list, (a,b) -> Integer.compare(b.value, a.value) ); 
        
        // 정답 출력용 리스트 만들기
        ArrayList<Integer> answerList = new ArrayList<>();
        
        // 리스트 인덱스 0 부터 꺼내면서 pMap 접근하며 return 값 만들기
        for(Art next : list){
            int count = 0; // 최대 2개까지만 출력을 위한 카운트
            PriorityQueue<int[]> q = pMap.get(next.name); // pMap에 value 가져오기
            while(!q.isEmpty()){
                if(count == 2) break; // 2개 꺼내기 완료 후 종료
                int[] cur = q.poll();
                answerList.add(cur[0]); // return 용 인덱스 쌓기
                count++; // 카운트 증가
            }
        }
        
        int[] answer = new int[answerList.size()]; // 리턴용 배열 생성
        for(int i=0; i<answerList.size(); i++){
            answer[i] = answerList.get(i); // 매핑
        }
        
        return answer;
    }
    
    // ArrayList에 담길 객체
    static class Art{
        String name;
        int value;
        Art(String name, int value){
            this.name = name;
            this.value = value;
        }
    }
    
}