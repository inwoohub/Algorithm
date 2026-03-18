class Solution {
    public int solution(int n) {
        int left = 1;  // 왼쪽 포인터
        int right = 1; // 오른쪽 포인터
        int cur = 1;   // 현재 값
        int answer = 0;
        while(left<=n){
            if(cur == n){
                answer++;
            }
            if( cur <= n ){
                right++;
                cur = cur + right;
            } else {
                cur = cur - left;
                left++;
            }
        }
        return answer;
    }
}
