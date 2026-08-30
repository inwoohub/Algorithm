import java.util.*;

class Solution {    
    static Queue<Log> q;
    static HashMap<String, String> map;
    
    public String[] solution(String[] record) {
        init(record);
        return getAnswer();
    }
    
    static String[] getAnswer() {
        int count = 0;
        String[] answer = new String[q.size()];
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            Log cur = q.poll();
            if(cur.activity==0){
                sb.append(map.get(cur.id));
                sb.append("님이 들어왔습니다.");
            } else {
                sb.append(map.get(cur.id));
                sb.append("님이 나갔습니다.");
            }
            answer[count] = sb.toString();
            sb.setLength(0);
            count++;
        }
        return answer;
    }
    
    static void init(String[] record) {
        map = new HashMap<>();
        q = new ArrayDeque<>();
        for(int i=0; i<record.length; i++) {
            StringTokenizer st = new StringTokenizer(record[i]);
            String activity = st.nextToken();
            String id = st.nextToken();
            if(activity.equals("Enter")){
                String nickname = st.nextToken();
                q.offer(new Log(0, id));
                map.put(id, nickname);
            } else if (activity.equals("Leave")){
                q.offer(new Log(1, id));
            } else {
                String nickname = st.nextToken();
                map.put(id, nickname);
            }
        }
    }
    
    static class Log{
        int activity;
        String id;
        Log(int activity, String id){
            this.activity = activity;
            this.id = id;
        }
    }
}