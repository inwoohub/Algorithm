import java.io.*;
import java.util.*;

public class Main{

    static class MeetingRoom implements Comparable<MeetingRoom>{
        int start, end;
        MeetingRoom(int s, int e){
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(MeetingRoom o){
            if(this.end!=o.end){
                return this.end - o.end;
            }
            return this.start - o.start;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        MeetingRoom[] arr = new MeetingRoom[N];
        for(int i=0; i<arr.length; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[i] = new MeetingRoom(A,B);
        }
        Arrays.sort(arr);
        int cur = 0;
        int count = 0;
        for(int i=0; i<arr.length; i++){
            if(cur<=arr[i].start){
                count++;
                cur = arr[i].end;
            }
        }
        System.out.print(count);
    }
}