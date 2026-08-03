import java.util.*;

class Solution {
    
    public int solution(String str1, String str2) {
        // 1. str1 두 글자씩 끊어서 ArrayList에 넣기
        ArrayList<String> listA = new ArrayList<>();
        cutting(listA, str1);
        
        // 2. str2 두 글자씩 끊어서 ArrayList에 넣기
        ArrayList<String> listB = new ArrayList<>();
        cutting(listB, str2);
        
        // 3. 자카드 유사도
        int jaccard = jaccardSearch(listA, listB);
        
        return jaccard;
    }
    
    static int jaccardSearch(ArrayList<String> listA, ArrayList<String> listB) {
        // 1. 해당 문자열 몇개있는지 개수세기
        HashMap<String, Integer> mapA = new HashMap<>();
        HashMap<String, Integer> mapB = new HashMap<>();
        
        for(String cur : listA) {
            int curCnt = mapA.getOrDefault(cur, 0);
            curCnt++;
            mapA.put(cur, curCnt);
        }
        for(String cur : listB) {
            int curCnt = mapB.getOrDefault(cur, 0);
            curCnt++;
            mapB.put(cur, curCnt);
        }
        
        // 교집합 만들기 
        int A = 0; // 교집합 개수
        HashMap<String, Boolean> map = new HashMap<>();
        for(String cur : listA) {
            if(!listB.contains(cur)) continue; // 없음
            boolean check = map.getOrDefault(cur, false);
            if(check) continue; // 이미 처리
            int cntA = mapA.get(cur);
            int cntB = mapB.get(cur);
            A += Math.min(cntA, cntB);
            map.put(cur, true);  // 사용 처리
        }
        
        int B = 0; // 합집합 개수
        map = new HashMap<>();
        // 합집합 만들기 (listA 처리)
        for(String cur : listA) {
            boolean check = map.getOrDefault(cur, false);
            if(check) continue; // 이미 처리
            if(!listB.contains(cur)) {
                B += mapA.get(cur);
            } else { // 겹치는 경우
                int cntA = mapA.get(cur);
                int cntB = mapB.get(cur);
                B += Math.max(cntA, cntB);
            }
            map.put(cur, true);  // 사용 처리
        }
        // 합집합 만들기 (listB 처리)
        for(String cur : listB) {
            boolean check = map.getOrDefault(cur, false);
            if(check) continue; // 이미 처리
            B += mapB.get(cur);
            map.put(cur, true);  // 사용 처리
        }
        if(A==0 && B==0) {
            return 65536;
        } else if (A==0) {
            return 0;
        }
        return (65536 * A) / B;
    }
    
    
    
    static void cutting(ArrayList<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length()-1; i++){
            char firstChar = str.charAt(i);
            char secondChar = str.charAt(i+1);
            // 1. 대문자/소문자 구분 없게 모두 소문자로 변환
            firstChar = genderChar(firstChar);
            secondChar = genderChar(secondChar);
            // 2. 특수문자, 공백 확인
            if((int) firstChar<97 || 122 < (int) firstChar) continue;
            if ((int) secondChar<97 || 122 < (int) secondChar) continue;
            // 3. 문자 더하기
            sb = new StringBuilder();
            sb.append(firstChar);
            sb.append(secondChar);
            // 4. 리스트에 추가
            list.add(sb.toString());
        }
    }
    
    // 대/소문자 -> 소문자로 변환 젠더
    static Character genderChar(char A) {
        int curInt = (int) A;
        if(curInt<97 || curInt>122) {
            curInt += 32;
        }
        return (char) curInt;
    }
    
}