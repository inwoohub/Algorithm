// 알고리즘
// DP ->? 점화식이 있어먀만 DP 인가? 아니면 큐를 써도 되는가. -> HashSet을 써야함

import java.util.*;

class Solution {
    
    static HashSet<Integer>[] map = new HashSet[9]; // 중복 저장 x [1] = 5, [2] = 0,1,10,25,55
    
    public int solution(int N, int number) {
        
        for(int i=1; i<=8; i++){
            map[i] = new HashSet<>();    
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<i; j++){
                sb.append(N); // 미리 N 넣어주기, ex) 5, 55, 555, 5555 처럼
            }
            map[i].add(Integer.parseInt( sb.toString() ));
        }
        
        if(N == number){ // N == number 가 같으면 바로 반환
            return 1;
        }
        
        for(int i=2; i<=8; i++){
            for(int j=1; j<i; j++){ // ex) i=4: [1][3], [2][2], [3][1]
                for(Integer next : map[j]){ 
                    for(Integer next2 : map[i-j]){
                        map[i].add( next + next2 );
                        map[i].add( next - next2 );
                        if(next2!=0){ // 0 나누니까 -> ArithmeticException 에러 발생해서 분기처리
                            map[i].add( next / next2 );    
                        }
                        map[i].add( next * next2 );   
                    }   
                }
                
            }
            // 해당 number 들어있는지 확인하기
            if( map[i].contains(number) ){
                return i;
            }

        }    
        return -1;
    }
    
}