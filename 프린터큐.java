import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        int tt = Integer.parseInt(br.readLine());
        for(int i=0; i<tt; i++){

            LinkedList<Integer[]> q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringTokenizer value = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int count = 0;

            for(int k=0; k<N; k++){
                q.offer(new Integer[]{k, Integer.parseInt(value.nextToken())});
            }
            while(!q.isEmpty()){
                //가장 앞에 것을 기준
                Integer[] cur = q.peek();

                //큐에 2개 이상 있을 경우
                if(q.size()>1){
                    //뒤에 큰걸 찾기
                    for(int k=1; k<q.size(); k++){
                        //가장 앞 보다 크다면
                        if(cur[1] < (q.get(k)[1])){
                            //찾았다면 그전까지 뒤로 보내기
                            for(int j=0; j<k; j++){
                                q.offer(q.poll());
                            }
                            break;
                        }
                        //cur이 가장 크다면,
                        else if( k== ( q.size()-1 ) && 
                                 cur[1] >= (q.get(k)[1]) ){
                            q.poll();
                            count++;
                            if(cur[0] == M){
                                System.out.println(count);
                            }
                            break;
                        }
                    }    
                }
                //1개 밖에 없는 경우
                else{
                    q.poll();
                    if(cur[0] == M){
                                count++;
                                System.out.println(count);
                            }
                    break;
                }
            }
        }
    }
}
