import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int count=1;

        // 2차원 배열에 입력받은 값 넣기
        for(int i=0; i<N; i++){
            String[] parts = (br.readLine()).split(" ");
            
            for(int k=0; k<2; k++){
                arr[i][k] = Integer.parseInt(parts[k]);
            }
        }

        // 키, 몸무게 비교하기
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                
                // 비교대상이 본인이라면,
                if(i==k){
                    continue;
                }
                // 비교 대상보다 키가작고, 몸무게가 낮다면,
                else if(arr[i][0] < arr[k][0] && arr[i][1]<arr[k][1]){
                    count++;
                }
            }
            pw.print(count+" ");
            count=1;
        }

        pw.flush();
        pw.close();
        br.close();
    }
}