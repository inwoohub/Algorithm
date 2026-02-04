import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        String[] parts = (br.readLine()).split(" ");
        int M = Integer.parseInt(parts[0]);
        int N = Integer.parseInt(parts[1]);
        int[] arr = new int[1000001];
        
        //배열초기화
        for(int i=0; i<arr.length; i++){
            arr[i] = 1;
        }
        
        //배열에서 소수만 남기기
        for(int i=1; i<Math.sqrt(arr.length); i++){
            if(i==1){
                arr[i]=0;
            }
            if(i>=2){
                for(int j=i*i; j<arr.length; j+=i){
                arr[j]=0;
                }    
            }
        }
        
        //M~N까지 소수만 출력
        for(int i=M; i<=N; i++){
            if(arr[i]==1){
                pw.println(i);
            }
        }
        pw.flush();
        pw.close();
        br.close();
    }
}