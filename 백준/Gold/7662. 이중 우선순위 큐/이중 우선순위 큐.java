import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb ;
    static PriorityQueue<Integer> PQ1;
    static PriorityQueue<Integer> PQ2;
    static HashMap<Integer,Integer> map;
    static int size;

    static void clean(PriorityQueue<Integer> PQ){
        while(!PQ.isEmpty()){
            int A = PQ.peek();
            if(map.getOrDefault(A,0) == 0){
                PQ.poll();
            }
            else{
                break;
            }
        }
    }

    static void del1(){
        if(size==0){
            return;
        }
        clean(PQ1);
        if(!PQ1.isEmpty()){
            int A = PQ1.poll();
            if(map.getOrDefault(A,0)>0){
                map.put(A,map.get(A)-1);
                size--;
            } 
        }
    }

    static void del2(){
        if(size==0) return;
        clean(PQ2);
        if(!PQ2.isEmpty()){
            int A = PQ2.poll();
            if(map.getOrDefault(A,0)>0){
                map.put(A,map.get(A)-1);
                size--;
            }    
        }
    }

    static void view1(){
        while(!PQ1.isEmpty()){
            int A = PQ1.peek();
            if(map.getOrDefault(A,0)>0){
                sb.append(A+"\n");
                break;
            }
            else{
                PQ1.poll();
            }    
        }
    }

    static void view2(){
        while(!PQ2.isEmpty()){
            int A = PQ2.peek();
            if(map.getOrDefault(A,0)>0){
                sb.append(A+" ");
                break;
            }
            else{
                PQ2.poll();
            }    
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int TestCase = Integer.parseInt(st.nextToken());
        for(int TC=0; TC<TestCase; TC++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            //PQ1 최소, PQ2 최대
            PQ1 = new PriorityQueue<>();
            PQ2 = new PriorityQueue<>( (a,b)-> Integer.compare(b,a) );
            map = new HashMap<>();
            size = 0;
            
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                char A = st.nextToken().charAt(0);
                int B = Integer.parseInt(st.nextToken());
                if(A=='I'){
                    PQ1.add(B);
                    PQ2.add(B);
                    map.put(B,map.getOrDefault(B,0)+1);
                    size++;
                }
                else{
                    if(size>0){
                        if(B==-1){
                            del1();
                        }
                        else{
                            del2();
                        }    
                    }
                    else{
                        continue;
                    }
                }
            }
            if(size==0){
                sb.append("EMPTY\n");
            }
            else{
                view2();
                view1();
            }
        }
        System.out.print(sb);
    }
}