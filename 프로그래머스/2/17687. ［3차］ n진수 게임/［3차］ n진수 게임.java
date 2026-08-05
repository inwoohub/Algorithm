import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        int curPerson = 0;
        int curInt = 0;
        while(cnt<t) {
            String curStr = Integer.toString(curInt, n).toUpperCase();
            for(int i=0; i<curStr.length(); i++) {
                int cur = (curPerson % m) + 1;
                if(cur==p) {
                    sb.append(curStr.charAt(i));
                    cnt++;
                }
                curPerson++;
                if(cnt == t) break;
            }
            curInt++;
        }
        return sb.toString();
    }
}