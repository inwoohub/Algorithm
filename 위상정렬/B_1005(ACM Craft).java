// 위상 정렬
import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] list;
    static int[] buildTime;
    static int[] DP;
    static int[] dgree;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        
        for(int tC=0; tC<testCase; tC++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 수
            int K = Integer.parseInt(st.nextToken()); // 건물 연결 수
            buildTime = new int[N+1]; // 건물 완공 시간
            list = new ArrayList[N+1];  // 연결 건물
            for(int i=1; i<=N; i++){
                list[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                buildTime[i] = Integer.parseInt(st.nextToken());
            }
            DP = new int[N+1]; // DP 값
            dgree = new int[N+1]; // 차수 (완공 덜 된 차수)
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                list[A].add(B);
                dgree[B] = dgree[B] + 1;
            }
            Queue<Integer> q = new ArrayDeque<>();
            int target = Integer.parseInt(br.readLine());
            for(int i=1; i<=N; i++){
                DP[i] = buildTime[i];
                if(dgree[i]==0){
                    q.offer(i);
                }
            }

            while(!q.isEmpty()){
                int cur = q.poll();
                for(int next : list[cur]){
                    DP[next] = Math.max(DP[next], DP[cur]+buildTime[next]);
                    dgree[next] = dgree[next]-1;
                    if(dgree[next]==0){
                        q.offer(next);
                    }
                }
            
            }
            sb.append(DP[target]+"\n");
        }
        System.out.print(sb);
    }
}