package project.sess.util;

public class RingBuffer {
	private int max;        // ť �뷮        
    private int front;        // ù��° ��� Ŀ��
    private int rear;    // ������ ��� Ŀ��
    private int num;        // ���� ������ ��
    private String[] que;    // ť ��ü

	// ���� �� ���� : ť�� �������
    public class EmptyIntQueueException extends RuntimeException{
        public EmptyIntQueueException(){
        }
    }
    
    // ���� �� ���� : ť�� ���� ��
    public class OverflowIntQueueException extends RuntimeException{
        public OverflowIntQueueException(){
        }
    }
    
    public RingBuffer(int capacity) {
    	num = front = rear = 0;
        max = capacity;
        try{
            que = new String[max];     // ť ��ü�� �迭�� ����
        }catch(OutOfMemoryError e){    // ������ �� ����
            max = 0;
        }
	}
    
    // ť�� �����͸� ��ť�ϴ� �޼���
    public String enque(String data) throws OverflowIntQueueException{
        if(num >= max){    // ť�� ���� ��
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
    
    // ť�� �� ù��°�� �ִ� �����͸� ����(��ť) �ϰ� �� ���� ��ȯ�ϴ� �޼���
    public String deque() throws EmptyIntQueueException{
        if(num <= 0){
            throw new EmptyIntQueueException();    // ť�� �������.
        }
        
        String x = que[front++];
        num--;
        if(front == max){
            front = 0;
        }
        return x;
    }
    
    // ť�� ���� ���� ���� �����͸� ���� ������ �޼���(peek �޼��� )
    public String peek() throws EmptyIntQueueException{
        if(num <= 0){
            throw new EmptyIntQueueException();
        }
        return que[front];
    }
    
    // ť���� x�� ã�� �ε����� ��ȯ
    public int indexOf(String x){
        for(int i=0; i<num; i++){
            int idx = (i+front) % max;
            if(que[idx] == x){    // �˻� ����
                return idx;
            }
        }
        return -1;                    // �˻� ����
    }
    
    // ť�� ���
    public void clear(){
        num = front = rear = 0;
    }
    
    // ť�� �뷮�� ��ȯ
    public int capacity(){
        return max;
    }
    
    // ť�� �׿��ִ� ������ ���� ��ȯ
    public int size(){
        return num;
    }
    
    // ť�� ����ִ°�?
    public boolean isEmpty(){
        return num <= 0;
    }
    
    // ť�� ����á�°�?
    public boolean isFull(){
        return num >= max;
    }
    
    // ť ���� ��� �����͸� ����Ʈ -> ���� ������ ���
    public void dump(){
        if(num <= 0){
            System.out.println("ť�� ����ֽ��ϴ�.");
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
