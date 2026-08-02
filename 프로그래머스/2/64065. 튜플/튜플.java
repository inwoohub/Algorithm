import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 1. 맨 앞과 맨 뒤에 '{', '}' 제거
        s = removeFirst(s);
        
        // 2. '{ }' 을 기준으로 집합 분리 후 ArrayList에 저장
        ArrayList<String> list = new ArrayList<>();
        removeSecond(list, s);
        
        // 3. 문자열의 원소가 적게 들어있는 것을 우선으로 우선순위 큐에 담기
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.size, b.size));
        getTuple(list, pq);
        
        // 4. 가장 앞 원소부터 꺼내며 튜플 만들기
        int[] answer = search(list, pq);

        return answer;
    }
    
    static int[] search(ArrayList<String> list, PriorityQueue<Tuple> pq) {
        int count = 0;
        HashMap<Integer, Boolean> map = new HashMap<>();
        int[] answer = new int[pq.size()];
        // 1. PQ 하나씩 꺼내기
        while(!pq.isEmpty()){
            Tuple cur = pq.poll();
            String curStr = list.get(cur.index);
            // 2. ',' 을 기준으로 나누어서 배열에 담기
            String[] arr = curStr.split(",");
            // 3. 배열에서 숫자하나씩 꺼내가보며 map에 없으면 정답배열에 추가 있다면 넘어가기
            for(int i=0; i<arr.length; i++) {
                int curInt = Integer.parseInt(arr[i]);
                boolean check = map.getOrDefault(curInt, false);
                if(!check) {
                    map.put(curInt, true);
                    answer[count] = curInt;
                    count++;
                }
            }
        }
        return answer;
    }
    
    static void getTuple(ArrayList<String> list, PriorityQueue<Tuple> pq) {
        // 1. list 순회하며 pq에 담기
        for(int i=0; i<list.size(); i++) {
            int count = 0;
            String str = list.get(i);
            for(int j=0; j<str.length(); j++){
                if(str.charAt(j) == ','){
                    count++;
                }
            }
            pq.offer(new Tuple(i, count));
        }
    }
    
    static void removeSecond(ArrayList<String> list, String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            // 1. 집합이 시작되는 경우
            if(str.charAt(i) == '{') {
                // 문자열 만들 준비
                sb = new StringBuilder(); 
            }
            // 2. 집합이 끝나는 경우 리스트에 추가하기
            else if(str.charAt(i) == '}') {
                list.add(sb.toString());
            }
            // 3. 집합 사이인 경우
            else {
                sb.append(str.charAt(i));
            }
        }
    }
    
    static String removeFirst(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<str.length()-1; i++){
            sb.append( str.charAt(i) );
        }
        return sb.toString();
    }
    
    static class Tuple{
        int index; // list의 위치 Index
        int size;  // 원소의 개수
        Tuple(int index, int size) {
            this.index = index;
            this.size = size;
        }
    }
    
}