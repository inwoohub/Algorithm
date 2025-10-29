import java.io.*;
import java.util.*;

public class testclass{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // M개 입력 받기 , N개 맞추기 , HashMap 만들기
        String[] parts = (br.readLine()).split(" ");
        int M = Integer.parseInt(parts[0]);
        int N = Integer.parseInt(parts[1]);
        Map<String,Integer> Stringmap = new HashMap<>();
        Map<Integer,String> Intmap = new HashMap<>();

        for(int i=0; i<M; i++){
            String poket = br.readLine();
            Stringmap.put(poket, i+1);
            Intmap.put(i+1, poket);
        }

        for(int i=0; i<N; i++){
            String input = br.readLine();
            int v = input.charAt(0);
            
            //받은 값이 문자인 경우
            if(v>9){
                sb.append(Stringmap.get(input)+"\n");
            }

            //받은 값이 정수인 경우
            else{
                int value = Integer.parseInt(input);
                sb.append(Intmap.get(value)+"\n");
            }
        }
        System.out.print(sb);
    }
}