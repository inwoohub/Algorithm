import java.io.*;
import java.util.*;

public class Main{
    static int[] graph;
    static int[] dict;
    static HashMap<Integer,Integer> Nmap;
    static HashMap<Integer,Integer> Mmap;
    static final int[] dice = {1,2,3,4,5,6};

    static void find(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        while(!q.isEmpty()){
            int A = q.poll();
            for(int i=0; i<6; i++){
                int CA = A + dice[i];
                if(CA >100) continue;
                if(Nmap.getOrDefault(graph[CA],0) != 0 ){
                    if(dict[Nmap.get(CA)]>dict[A]+1){
                        dict[Nmap.get(CA)] = dict[A]+1;
                        q.offer(Nmap.get(CA));
                    }
                    continue;
                }
                if(Mmap.getOrDefault(graph[CA],0) != 0 ){
                    if(dict[Mmap.get(CA)]>dict[A]+1){
                        dict[Mmap.get(CA)] = dict[A]+1;
                        q.offer(Mmap.get(CA));
                    }
                    continue;
                }
                if(dict[CA] > dict[A]+1){
                    dict[CA] = dict[A]+1;
                    q.offer(CA);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new int[101];
        dict = new int[101];
        Nmap = new HashMap<>();
        Mmap = new HashMap<>();

        //graph 1~100 넣어주기 dict 거리 모두다 200으로 맞춤
        int size = 1;
        for(int i=1; i<101; i++){
            graph[i] = size;
            dict[i] = 200;
            size++;
        }
        dict[1] = 0;

        // N은 사다리 개수(+) , M은 뱀의 개수(-)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            Integer A = Integer.parseInt(st.nextToken());
            Integer B = Integer.parseInt(st.nextToken());
            Nmap.put(A,B);
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            Integer A = Integer.parseInt(st.nextToken());
            Integer B = Integer.parseInt(st.nextToken());
            Mmap.put(A,B);
        }
        find();
        System.out.print(dict[100]);
    }
}