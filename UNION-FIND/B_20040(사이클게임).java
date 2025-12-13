// union-find 적용

import java.io.*;
import java.util.*;

public class Main{

    static int[] parent;
    static boolean check;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 노드 개수 , M : 차례 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        check = false;
        // parent : 노드의 부모 노드
        parent = new int[N];
        Arrays.fill(parent,-1);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            union(A, B);
            if(check){
                System.out.print(i+1);
                break;
            }
        }
        if(!check){
            System.out.print(0);
        }
    }
    static void union(int A, int B){
        int x = find(A);
        int y = find(B);
        if(x == y){
            check = true;
            return;
        }
        if(x<y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }

    static int find(int x){
        if(parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }    
}