import java.util.*;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        init(map);
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int value = 27;
        int curIndex = 0;
        int addValue = 0;
        while(curIndex < msg.length()){
            sb.append( msg.charAt(curIndex) );
            if( map.containsKey(sb.toString()) ){
                addValue = map.get(sb.toString());
                curIndex++;
            }
            else {
                map.put(sb.toString(), value++);
                list.add(addValue);
                sb = new StringBuilder();
            }
        }
        list.add( map.get(sb.toString()) );
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    static void init(HashMap<String, Integer> map) {
        int cnt = 1;
        for(int i=65; i<=90; i++){
            map.put( String.valueOf( (char) i ), cnt++);
        }
    }
}
