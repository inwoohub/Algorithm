import java.util.*;

class Solution {
    
    static int curInt, preInt;
    static char bonus, option;
    static boolean check;
    static StringBuilder sb = new StringBuilder();
    
    public int solution(String dartResult) {
        int answer = 0;
        int index = 0;
        preInt = 0;
        curInt = 0;
        bonus = ' ';
        option = ' ';
        check = false;
        sb = new StringBuilder();
        while(index < dartResult.length()) {
            if( 0<=(dartResult.charAt(index)-'0') && 10>=(dartResult.charAt(index)-'0' )) {
                if(bonus == ' '){
                    sb.append( dartResult.charAt(index++) );
                    continue;
                }
                curInt = Bonus(Integer.parseInt(sb.toString()), bonus);
                if(option == '*') {
                    answer = answer + preInt + (curInt*2);
                    preInt = (curInt*2);
                    reset();
                    sb.append( dartResult.charAt(index++) );
                    continue;
                } else if (option == '#') {
                    answer = answer - curInt;
                    preInt = (-1) * curInt;
                    reset();
                    sb.append( dartResult.charAt(index++) );
                    continue;
                }
                preInt = curInt;
                answer += curInt;    
                reset();
                sb.append( dartResult.charAt(index++) );
            }
            else if( dartResult.charAt(index) == 'S'|| dartResult.charAt(index) == 'D' || dartResult.charAt(index) == 'T' ){
                bonus = dartResult.charAt(index++);
            }
            else {
                option = dartResult.charAt(index++);
                check = true;
            }
        }
        curInt = Bonus(Integer.parseInt(sb.toString()), bonus);
        if(check) {
            if(option == '*') {
                curInt = curInt * 2;
                answer += preInt ;
            } else if (option == '#') {
                answer -= (curInt * 2);
                preInt = (-1) * curInt;
            }
        }
        answer += curInt;
        return answer;
    }
    
    static void reset() {
        bonus = ' ';
        option = ' ';
        sb.setLength(0);
        check = false;
        curInt = 0;
    }
    
    static int Bonus(int curInt, char bonus) {
        if(bonus == 'S'){
            return (int) Math.pow( curInt, 1 );
        } else if(bonus == 'D'){
            return (int) Math.pow( curInt, 2 );
        }
        return curInt = (int) Math.pow( curInt, 3 );
    }
}