import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int size, maxChicken;
    static ArrayList<int[]> house, chicken;
    static boolean[] visited;
    static int result;

    static void startChicken(int i, int depth){
        if(depth == maxChicken){
            int[] sum = new int[house.size()];
            Arrays.fill(sum,Integer.MAX_VALUE);
            
            //방문 가능한 치킨집 중 치킨거리 최소값 구하기
            for(int k=0; k<chicken.size(); k++){
                int[] curChicken = chicken.get(k);
                int cX = curChicken[0];
                int cY = curChicken[1];
                if(visited[k]){
                    for(int j=0; j<house.size(); j++){
                        int[] curHouse = house.get(j);
                        int hX = curHouse[0];
                        int hY = curHouse[1];
                        sum[j] = Math.min(sum[j], (Math.abs(cX-hX)+Math.abs(cY-hY)));
                    }
                }
            }
            
            //최소값 다 더하고, result 비교
            int curResult = 0;
            for(int j=0; j<house.size(); j++){
                curResult = curResult+sum[j];
            }
            result = Math.min(curResult,result);
            return;
        }
        for(int k=i; k<chicken.size(); k++){
            if(!visited[k]){
                visited[k] = true;
                startChicken(k,depth+1);
                visited[k] = false; //백트래킹
            }    
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        maxChicken = Integer.parseInt(st.nextToken());
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        result = Integer.MAX_VALUE;
        // 집 , 치킨집 위치 저장
        for(int x=1; x<=size; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=1; y<=size; y++){
                int A = Integer.parseInt(st.nextToken());
                if(A==1){
                    house.add(new int[]{x,y});
                }
                if(A==2){
                    chicken.add(new int[]{x,y});
                }
            }
        }
        visited = new boolean[chicken.size()+1];
        
        startChicken(0,0);
        
        System.out.print(result);
        
    }
}