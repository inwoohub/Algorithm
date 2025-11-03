import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int[] graph;
    static ArrayList<Integer> list;
    static int N, idx = 0;

    static void postfix(int low, int high){
        if(idx>=N) return;
        int val = graph[idx];
        if(val<low || val > high) return;

        idx++;
        postfix(low,val-1);
        postfix(val+1,high);
        sb.append(val+"\n");
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        String input;
        while((input = br.readLine())!=null){
            int A = Integer.parseInt(input);
            list.add(A);               
        }
        N = list.size();
        graph = new int[N];
        for(int i=0; i<N; i++){
            graph[i] = list.get(i);
        }

        postfix(Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.print(sb);
    }
}