import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<int[]> arr = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A =Integer.parseInt(st.nextToken());
            int B =Integer.parseInt(st.nextToken());
            arr.add(new int[]{A,B});
        }
        arr.sort((a,b)->{
           if(a[1]!=b[1]) return a[1]-b[1];
            return a[0] - b[0];
        });

        int count = 0;
        int cur = 0;
        for(int i=0; i<arr.size(); i++){
            if(cur<=arr.get(i)[0] ){
                count++;
                cur=arr.get(i)[1];
            }
        }
        System.out.print(count);
    }
}