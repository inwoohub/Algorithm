// -----------------------------
// 알고리즘
// 1. 비행기가 게이트에 맞춰서 들어옴
// 2. 조건은 gi보다 큰 게이트는 못 들어감
// 3. 아무곳도 들어갈 수 없다면 그 상태로 종료.
// 4. 비행기 최대 도킹 수 찾기
// -> 가능한 최대 큰 수에 넣기 전략 (DSU)
// -> DSU란? find(x) = x 이하에서 가장 큰 번호
// -----------------------------
// 변수
// gate[], G, P, airPlane
// -----------------------------

import java.io.*;
import java.util.*;

public class Main{

    static int[] gate;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int count = 0;
        gate = new int[100001];
        for(int i=1; i<=100000; i++){
            gate[i] = i;
        }
        
        for(int i=1; i<=P; i++){
            int airPlane = Integer.parseInt(br.readLine());
            int x = find(airPlane); // 최대값으로 감.
            if(x==0) break; // 들어갈 수 없다면 종료
            gate[x] = find( x-1 );
            count++;
        }
        System.out.print(count);        
    }

    static int find(int x){
        if(gate[x] == x) return x;
        return gate[x] = find(gate[x]);
    }
    
}

