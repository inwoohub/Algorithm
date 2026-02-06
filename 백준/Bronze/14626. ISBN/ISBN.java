import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int SUM=0;
        int STAY = 0;
        String input = br.readLine();
        String[] parts = input.split("");
        for(int i=0; i<13; i++){
            if( parts[i].charAt(0)  =='*'){
                STAY = i;
            }
            else if( i%2 == 0 ){
                SUM=SUM+ ( Integer.parseInt(parts[i])*1 );
            }
            else if( i%2 == 1){
                SUM=SUM+ ( Integer.parseInt(parts[i])*3 );
            }
        }
        if(STAY%2==0){
            pw.println(10 - SUM%10);
        }
        else if(STAY%2==1){
            for(int a=0; a<10; a++){
                if( (SUM + (a*3)) % 10 == 0  ){
                    pw.println(a);
                }
            }
        }
        pw.flush();
        pw.close();
        br.close();
    }
}