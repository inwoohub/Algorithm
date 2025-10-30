import java.io.*;
import java.util.*;

public class Main{
    static int person , party ;
    static ArrayList<Integer> list;
    static ArrayList<Integer>[] partyList;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        person = Integer.parseInt(st.nextToken());
        party = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        int turePerson = Integer.parseInt(st.nextToken());
        for(int i=0; i<turePerson; i++){
            int tP = Integer.parseInt(st.nextToken());
            list.add(tP);
        }

        partyList = new ArrayList[party+1];
        for(int i=1; i<=party; i++){
            partyList[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int pL = Integer.parseInt(st.nextToken());
            for(int k=0; k<pL; k++){
                int pLnum = Integer.parseInt(st.nextToken());
                partyList[i].add(pLnum);
            }
        }
        Collections.sort(list);
        // 관련있는사람 모두 다 list 에 추가 (list 사이즈 변화없을 때 까지 반복)
        boolean circle = true;
        boolean BOL = false;
        while(circle){
            int listSize = list.size();
            for(int i=1; i<= party; i++){
                BOL = false;
                for(int k=0; k<partyList[i].size(); k++){
                    if( Collections.binarySearch(list, partyList[i].get(k)) >= 0){
                        BOL = true;
                        break;
                    } 
                }
                if(BOL){
                    for(int k=0; k<partyList[i].size(); k++){
                        if( Collections.binarySearch(list, partyList[i].get(k))<0){
                            list.add(partyList[i].get(k));
                        }
                    }
                }
                Collections.sort(list);
            }
            if(listSize == list.size()){
                circle = false;
            }
        }
        

        // 파티에서 관련 없는 인물들만 있다면, 카운트 증가
        int count=0;
        for(int i=1; i<= party; i++){
            BOL = false;
            for(int k=0; k<partyList[i].size(); k++){
                if( (Collections.binarySearch(list, partyList[i].get(k)) >= 0)){
                    BOL = true;
                    break;
                }
            }
            if(!BOL){
                count++;
            }
        }

        System.out.print(count);        
    }
}