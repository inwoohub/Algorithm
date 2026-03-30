/**
알고리즘
이진탐색
둘의 차이가 1이 될 때까지 반복
단, 큰 수가 무조건 짝수여야함
*/

class Solution
{
    public int solution(int n, int a, int b)
    {
        int count = 0;
        
        while(true){
            
            if(a == b){
                break;
            }
        
            if(a%2 == 0){
                a = a / 2;
            } else {
                a = (a+1) / 2 ;
            }
            
            if(b%2 == 0){
                b = b / 2;
            } else {
                b = (b+1) / 2 ;
            }
            
            count++;
            
            
        }

        return count;
    }
}