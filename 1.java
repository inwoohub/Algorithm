import java.io.*;
import java.math.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        BigInteger big1 = new BigInteger("1");
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for(int i=2; i<=N; i++){
            BigInteger mul = BigInteger.valueOf(i);
            big1 = big1.multiply(mul);
        }
        
        String str = big1.toString();
        
        for(int i=str.length()-1; i>=0; i--){
            int A = (int)str.charAt(i)-'0';
            if(0 == A){
                cnt++;
            }
            else{
                break;
            }
        }

        pw.print(cnt);
        pw.flush();
        pw.close();
        br.close();
    }
}