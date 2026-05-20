/**
알고리즘 :
    TreeSet, 구현

문제 요약 :
    1. 주차장의 요금표가 있고, 차가 들어오고 나간 기록이 주어짐
    2. 차량별로 주차 요금 계산하려고함
    
    * fees : 
        [0] : 기본 시간
        [1] : 기본 요금
        [2] : 단위 시간
        [3] : 단위 요금
        
    * records :
        records는 스트링 타입 " " 띄어쓰기로 구분되어있음 -> split(" ") 처럼 사용함
        [0] : 또 ":" 이 걸로 스플릿해서 시간으로 변환해야함
        [1] : 차량 번호
        [2] : IN or OUT
    
    * 한번 나간 차량이 또 들어올 수 있음
    * 기본 요금은 무조건 내고 그 만큼은 이용 가능함
    * 기본 시간보다 초과되면 이제 단위 요금 내야함
    * 60 * 24 = 총 1440 으로 시간 관리

전략 :
    1. records 문자열 분해하기 (시간, 차량번호, 입∙출 차)
    2. 차량 번호를 기준으로 HashMap 에 입차 시간, 출차 시간, 현재 누적 시간, 나갔는지 들어왔는지 넣어주기
    3. 마지막에 앞에서 부터 꺼내면서 요금 계산해주기 -> 중복이 없고 우선 순위를 먹일수있는 자료구조? -> TreeSet

*/

import java.util.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        TreeSet<Integer> set = new TreeSet<>();      // 차량 번호 담는 용도
        HashMap<Integer, Car> map = new HashMap<>(); // 차 관리하는 해시 맵
        
        for(String record : records) {
            int curTime = timeParse(record);
            int carNumber = numParse(record);
            boolean curCheck = inOrOut(record); // T : 들어옴, F : 나감 (디폴트)
            set.add(carNumber); // 차 번호 담아주기
            
            if(curCheck){ // 들어오는 차
                Car curCar = map.getOrDefault( carNumber, null );
                // null 이면 새롭게 넣어주기
                if(curCar == null){
                    map.put( carNumber, new Car(curTime, 1439, 0, true) );
                } 
                // 기존에 있던 차라면
                else {
                    curCar.in = curTime; // 들어온 시간 갱신
                    curCar.out = 1439;   // 23:59 에 나간다고 가정
                    curCar.check = true; // 들어온 처리
                    map.put( carNumber, curCar ); // map 에 업데이트
                }
            }
            
            else { // 나가는 차
                Car curCar = map.get( carNumber ); // 나가는 차는 무조건 map에 있음
                // 시간 계산 해줘야함
                int midTime = curTime - curCar.in; // 현재 시간 - 들어와던 시간 차액
                curCar.time += midTime; // 시간 누적 시키기
                curCar.check = false;   // 출차 처리
                map.put(carNumber, curCar); // map 에 업데이트
            }
        }
        
        // set으로 돌면서 정산해주기
        int[] answer = new int[set.size()];
        int index = 0;
        for(int number : set){
            Car curCar = map.get(number);
            if(curCar.check){ // 주차장에 있다면
                int totalTime = curCar.out - curCar.in;
                totalTime += curCar.time-fees[0]; // 총 주차장에 머문 시간
                if(totalTime <= 0){ // 기본 요금으로 처리 가능한 경우
                    answer[index] = fees[1];
                }
                else { // 기본 요금으로 처리 불가능한 경우
                    // 시간이 맞아 떨어지는지 먼저 확인
                    int count = 0;
                    if (totalTime%fees[2] == 0){
                        count = totalTime/fees[2]; // 비용 딱 맞음
                    } else {
                        count = (totalTime/fees[2]) + 1; // 비용 잔액 있음
                    }
                    answer[index] = fees[1] + (count)*fees[3]; // 비용계산
                }
            }

            else { // 주차장에 없다면
                int totalTime = curCar.time - fees[0];
                if(totalTime <= 0){
                    answer[index] = fees[1];
                }
                else {
                    int count = 0;
                    if (totalTime%fees[2] == 0){
                        count = totalTime/fees[2]; // 비용 딱 맞음
                    } else {
                        count = (totalTime/fees[2]) + 1; // 비용 잔액 있음
                    }
                    answer[index] = fees[1] + count*fees[3]; // 비용계산
                }
            }
            index++;
        }

        return answer;
    }
    
    // 들어왔는지 나갔는지 boolean 로 처리하기
    static boolean inOrOut(String record){
        StringTokenizer st = new StringTokenizer(record);
        st.nextToken(); // 첫 번째 인자 넘기기
        st.nextToken(); // 두 번째 인자 넘기기
        String str = st.nextToken();
        if(str.equals("IN")){
            return true;
        }
        return false;
    }
    
    // 차량 번호만 꺼내오기
    static int numParse(String record){
        StringTokenizer st = new StringTokenizer(record);
        st.nextToken(); // 첫 번째 인자 넘기기
        return Integer.parseInt(st.nextToken()); // 차량 번호만 반환
    }
    
    // 시간 + 분 -> 분으로 환산
    static int timeParse(String record){
        StringTokenizer st = new StringTokenizer(record);
        String strTime = st.nextToken();
        String[] arr = strTime.split(":");
        int hours = Integer.parseInt(arr[0]);
        int minutes = Integer.parseInt(arr[1]);
        return (hours*60) + minutes; // 시간 분으로 환산
    }    
    
    static class Car{
        Integer in;    // 들어온 시간
        Integer out;   // 나가는 시간
        Integer time;  // 현재 누적된 시간 (이미 나갔다 들어온 경우)
        boolean check; // 들어와있으면 T, 나가있으면 F
        Car(int in, int out, int time, boolean check){
            this.in = in;
            this.out = out;
            this.time = time;
            this.check = check;
        }
    }   
    
}