import java.io.*;
import java.util.*;

public class Main{

    static StringBuilder sb;
    static Deque<Integer> dq;
    static boolean Erbool;
    static boolean Rbool;
    static Queue<String> q;
    static String input;
    
    static void start(){
        for(int i=0; i<input.length(); i++){
            char A = input.charAt(i);
            if(dq.size()==0 && A=='D'){
                Erbool = true;
                break;
            }
            if(A=='R'){
                if(Rbool){
                    Rbool = false;
                }
                else{
                    Rbool = true;
                }
            }
            else{
                if(Rbool){
                    dq.pollLast();
                }
                else{
                    dq.pollFirst();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());
        
        for(int TC = 0; TC<TestCase; TC++){
            q = new LinkedList<>();
            dq = new ArrayDeque<>();
            Erbool = false;
            Rbool = false;
            
            //R , D 입력 받고 q에 저장
            input = br.readLine();
            
            // N 입력받기
            int N = Integer.parseInt(br.readLine());

            //배열 입력받기
            if(N>0){
                String str = br.readLine();
                String arr =str.substring(1, str.length()-1);
                String[] parts = arr.split(",");
                for(int i=0; i<parts.length; i++){
                    dq.addLast(Integer.parseInt(parts[i]));
                }    
            }
                
            else{
                String str = br.readLine();
                String arr ="";
            }
            
            start();

            if(Erbool){
                sb.append("error\n");
                continue;
            }

            int dqsize = dq.size();
            sb.append("[");
            for(int i=0; i<dqsize; i++){
                if(i==dqsize-1){
                    if(Rbool){
                        sb.append(dq.pollLast());
                }
                    else{
                        sb.append(dq.pollFirst());
                    }    
                    break;
                }
                if(Rbool){
                    sb.append(dq.pollLast()+",");
                }
                else{
                    sb.append(dq.pollFirst()+",");
                }
            }
            sb.append("]\n");
        }
        System.out.print(sb);
    }
}