import java.io.*;
import java.util.*;

public class Main{
    static int nodeSize, searchSize, pathSize;
    static ArrayList<Integer>[] node;
    static int[] item;
    static int[][] dist;
    static boolean[] visited;
    static int max;
    static int sum;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeSize = Integer.parseInt(st.nextToken());
        searchSize = Integer.parseInt(st.nextToken());
        pathSize = Integer.parseInt(st.nextToken());

        // "Node" ArrayList 생성
        node = new ArrayList[nodeSize+1];
        for(int i=1; i<=nodeSize; i++){
            node[i] = new ArrayList<>();
        }

        // "item" int[] 배열 생성
        item = new int[nodeSize+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=nodeSize; i++){
            item[i] = Integer.parseInt(st.nextToken());
        }

        // "node" 연결 및 "dist" 생성
        dist = new int[nodeSize+1][nodeSize+1];
        for(int i=0; i<pathSize; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            // 양방향 연결
            node[A].add(B);
            node[B].add(A);
            // 거리 저장
            dist[A][B] = C;
            dist[B][A] = C;
        }

        // "max" 변수 생성 (최대값)
        max = 0;
        
        // dijkstra 로 도달 할 수 있는 최단 거리 탐색
        for(int i=1; i<=nodeSize; i++){
            int[] D = dijkstra(i);
            sum = 0;
            for(int j=1; j<=nodeSize; j++){
                if(D[j] <= searchSize){
                    sum = sum+item[j];
                }
            }
            max = Math.max(max,sum);
        }
        System.out.print(max);
    }

    static int[] dijkstra(int i){
        int[] D = new int[nodeSize+1];
        Arrays.fill(D,Integer.MAX_VALUE);
        D[i] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b) -> a[1]-b[1] );
        q.offer(new int[]{i,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(D[curNode] < curDist) continue;
            for(int nextNode : node[curNode]){
                int nextDist = curDist + dist[curNode][nextNode];
                if(nextDist < D[nextNode]){
                    D[nextNode] = nextDist;
                    q.offer(new int[]{nextNode, nextDist});
                }
            }
        }
        return D;
    }
}