import java.util.*;

class Solution {
    
    public int solution(String str1, String str2) {
        ArrayList<String> listA = new ArrayList<>();
        cutting(listA, str1);
        ArrayList<String> listB = new ArrayList<>();
        cutting(listB, str2);
        return jaccardSearch(listA, listB);
    }
    
    static int jaccardSearch(ArrayList<String> listA, ArrayList<String> listB) {
        HashMap<String, Integer> mapA = new HashMap<>();
        HashMap<String, Integer> mapB = new HashMap<>();
        for(String cur : listA) {
            int curCnt = mapA.getOrDefault(cur, 0);
            mapA.put(cur, ++curCnt);
        }
        for(String cur : listB) {
            int curCnt = mapB.getOrDefault(cur, 0);
            mapB.put(cur, ++curCnt);
        }
        int A = 0;
        HashMap<String, Boolean> map = new HashMap<>();
        for(String cur : listA) {
            if(!listB.contains(cur)) continue;
            if(map.getOrDefault(cur, false)) continue;
            A += Math.min(mapA.get(cur), mapB.get(cur));
            map.put(cur, true);
        }
        int B = 0;
        map = new HashMap<>();
        for(String cur : listA) {
            if(map.getOrDefault(cur, false)) continue;
            if(!listB.contains(cur)) {
                B += mapA.get(cur);
            } else {
                B += Math.max(mapA.get(cur), mapB.get(cur));
            }
            map.put(cur, true);
        }
        for(String cur : listB) {
            if(map.getOrDefault(cur, false)) continue;
            B += mapB.get(cur);
            map.put(cur, true);
        }
        if(A==0 && B==0) return 65536;
        return (65536 * A) / B;
    }
    
    static void cutting(ArrayList<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length()-1; i++){
            char firstChar = genderChar(str.charAt(i));
            char secondChar = genderChar(str.charAt(i+1));
            if((int) firstChar<97 || 122 < (int) firstChar) continue;
            if ((int) secondChar<97 || 122 < (int) secondChar) continue;
            sb = new StringBuilder();
            sb.append(firstChar);
            sb.append(secondChar);
            list.add(sb.toString());
        }
    }
    
    static Character genderChar(char A) {
        int curInt = (int) A;
        if(curInt<97 || curInt>122) {
            curInt += 32;
        }
        return (char) curInt;
    }
}