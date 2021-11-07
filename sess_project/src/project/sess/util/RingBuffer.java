package project.sess.util;

public class RingBuffer {
	private int max;        // 큐 용량        
    private int front;        // 첫번째 요소 커서
    private int rear;    // 마지막 요소 커서
    private int num;        // 현재 데이터 수
    private String[] que;    // 큐 본체

	// 실행 시 예외 : 큐이 비어있음
    public class EmptyIntQueueException extends RuntimeException{
        public EmptyIntQueueException(){
        }
    }
    
    // 실행 시 예외 : 큐이 가득 참
    public class OverflowIntQueueException extends RuntimeException{
        public OverflowIntQueueException(){
        }
    }
    
    public RingBuffer(int capacity) {
    	num = front = rear = 0;
        max = capacity;
        try{
            que = new String[max];     // 큐 본체용 배열을 생성
        }catch(OutOfMemoryError e){    // 생성할 수 없음
            max = 0;
        }
	}
    
    // 큐에 데이터를 인큐하는 메서드
    public String enque(String data) throws OverflowIntQueueException{
        if(num >= max){    // 큐이 가득 참
            throw new OverflowIntQueueException();
        }
        // que[0] 10 que[1] 9 que[2] 8
        // num = 3
        que[rear++] = data;
        num++;
        if(rear == max){
            rear = 0;
        }
        return data;
    }
    
    // 큐의 맨 첫번째에 있는 데이터를 제거(디큐) 하고 그 값을 반환하는 메서드
    public String deque() throws EmptyIntQueueException{
        if(num <= 0){
            throw new EmptyIntQueueException();    // 큐가 비어있음.
        }
        
        String x = que[front++];
        num--;
        if(front == max){
            front = 0;
        }
        return x;
    }
    
    // 큐의 가장 먼저 빠질 데이터를 몰래 엿보는 메서드(peek 메서드 )
    public String peek() throws EmptyIntQueueException{
        if(num <= 0){
            throw new EmptyIntQueueException();
        }
        return que[front];
    }
    
    // 큐에서 x를 찾아 인덱스를 반환
    public int indexOf(String x){
        for(int i=0; i<num; i++){
            int idx = (i+front) % max;
            if(que[idx] == x){    // 검색 성공
                return idx;
            }
        }
        return -1;                    // 검색 실패
    }
    
    // 큐을 비움
    public void clear(){
        num = front = rear = 0;
    }
    
    // 큐의 용량을 반환
    public int capacity(){
        return max;
    }
    
    // 큐에 쌓여있는 데이터 수를 반환
    public int size(){
        return num;
    }
    
    // 큐이 비어있는가?
    public boolean isEmpty(){
        return num <= 0;
    }
    
    // 큐이 가득찼는가?
    public boolean isFull(){
        return num >= max;
    }
    
    // 큐 안의 모든 데이터를 프런트 -> 리어 순으로 출력
    public void dump(){
        if(num <= 0){
            System.out.println("큐이 비어있습니다.");
        }else{
        	System.out.print("[Queue Log] : ");
            for(int i=0; i<num; i++){
                System.out.print(que[(i+front) % max] + " ");
            }
            System.out.println();
        }
    }
    
    public String[] getQue() {
		return que;
	}

	public void setQue(String[] que) {
		this.que = que;
	}
}
