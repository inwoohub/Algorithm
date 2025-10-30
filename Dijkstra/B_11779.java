import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int NodeSize;
    static ArrayList<int[]>[] list;
    static int[][] dist;

    static void Dijkstra(int start, int end ){
        dist = new int[2][NodeSize+1];
        Arrays.fill(dist[0],Integer.MAX_VALUE);
        dist[0][start] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b)->a[1]-b[1] );
        q.offer(new int[]{start,0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curNode = cur[0];
            int curWeight = cur[1];
            if( dist[0][curNode] < curWeight ) continue; //우선순위 밀려서 지난거 제거 용도--
            for(int i=0; i<list[curNode].size(); i++){
                int[] next = list[curNode].get(i);
                int nextNode = next[0];
                int nextWeight = next[1];
                if(dist[0][nextNode] > dist[0][curNode]+nextWeight){
                    dist[0][nextNode] = dist[0][curNode]+nextWeight;
                    dist[1][nextNode] = curNode;
                    q.offer(new int[]{nextNode,dist[0][nextNode]});
                }
            }
        }
        sb.append(dist[0][end]+"\n");
        ArrayList<Integer> AL = new ArrayList<>();
        int num = dist[1][end];
        AL.add(end);
        while(true){
            if(num==start){
                AL.add(num);
                break;
            }
            AL.add(num);
            num = dist[1][num];
        }
        sb.append(AL.size()+"\n");
        for(int i=AL.size()-1; i>=0; i--){
            sb.append(AL.get(i)+" ");
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        NodeSize = Integer.parseInt(st.nextToken());
        list = new ArrayList[NodeSize+1];
        for(int i=1; i<=NodeSize; i++){
            list[i] = new ArrayList<>();
        }
        int start = 0;
        int end = 0;
        int weight = 0;
        st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            list[start].add(new int[]{end,weight});
        }
        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        Dijkstra(startNode,endNode);
        System.out.print(sb);
    }
}