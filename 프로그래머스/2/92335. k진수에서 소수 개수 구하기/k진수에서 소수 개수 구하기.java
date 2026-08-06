import java.util.*;

class Solution {
    
    public int solution(int n, int k) {
        return search(Integer.toString(n,k));
    }
    
    static int search(String n) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean check = false;
        for(int i=0; i<n.length(); i++){
            if(n.charAt(i) != '0') {
                sb.append(n.charAt(i));
                check = true;
            }
            else {
                if(check)  {
                    list.add(sb.toString());
                }
                sb = new StringBuilder();
                check = false;
            }
        }
        if(check) {
            list.add(sb.toString());    
        }
        try{
            return (int) list.stream()
            .filter(i->(decimalCheck(i)))
            .count();    
        } catch (RuntimeException e){
            System.out.println("런타임 에러 발생");
        }
        return 1;
    }
    
    static boolean decimalCheck(String A){
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i) == '0'){
                return false;
            }
        }
        if(A.length()==1 && A.charAt(0)=='1'){
            return false;
        }
        long a = Long.parseLong(A);
        for(int i=2; i<=Math.sqrt(a); i++){
            if(a % i == 0){
                return false;
            }
        }
        return true;
    }
}