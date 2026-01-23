import java.io.*;
import java.util.*;

public class Main{

    static int N;
    static ArrayList<int[]> list =  new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 1) 데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A =  Integer.parseInt(st.nextToken());
            int B =  Integer.parseInt(st.nextToken());
            list.add(new int[]{A,B});
        }

        // 2) ArrayList 정렬하기
        Collections.sort(list, (a,b)->Integer.compare(a[0],b[0]) );

        // 3) LIS / DP 사용
        // LIS 점화식 2중 for 문을 통해 dp[i] = Math.max(dp[i], dp[j]+1)
        int[] dp = new int[list.size()];
        Arrays.fill(dp,1);
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<i; j++){
                if(list.get(i)[0] > list.get(j)[0] && list.get(i)[1] > list.get(j)[1] ){ //안겹치고 오름차순
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        // 4) 전체 개수 - dp 최대값 계산
        int MAX = 0;
        for (int i : dp) {
            MAX = Math.max(MAX,i);
        }
        int ans = list.size() - MAX;

        // 5) 결과 출력
        System.out.println(ans);
    }
}