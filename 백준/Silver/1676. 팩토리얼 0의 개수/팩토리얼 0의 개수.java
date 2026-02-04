import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        for(int i=1; i<=N; i++){
            int num = i;
            while(num%5==0){
                count++;
                num /= 5;
            }
        }
        pw.print(count);
        pw.flush();
        pw.close();
        br.close();
    }
}