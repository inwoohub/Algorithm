import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        for(int tC=0; tC<testCase; tC++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            boolean check = false;

            // 1) 일단 10 나머지값으로 시작 (맨 끝 자리만 필요)
            int ans = a % 10;
            
            // 2) 끝이 0 이 아니라면 for문 시작
            if(ans != 0 ){
                for(int i=1; i<b; i++){
                    ans = ans * a ; // b 만큼 제곱
                    ans = ans % 10; // 맨 끝자리만 남기기
                    if(ans == 0){   // 끝자리가 0 이라면 멈추는 조건
                        check = true;
                        break;
                    }
                }    
            } else {
                check = true;
            }
            
            if(check){ // 4) 끝자리가 0이되서 멈춘다면,
                sb.append(10+"\n");
            } else {   // 5) 끝자리 = 사용 컴퓨터 출력
                sb.append(ans+"\n");
            }
        }
        System.out.print(sb);
    }
}