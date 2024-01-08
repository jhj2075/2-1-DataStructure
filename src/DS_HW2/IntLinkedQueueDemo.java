package DS_HW2;

public class IntLinkedQueueDemo {
    public static void main(String[] args) {
        IntLinkedQueue queue = new IntLinkedQueue();

        System.out.println("empty queue인지 확인(empty이면 false) => " + queue.isEmpty());
        System.out.println();

        System.out.println("10, 20, 30, 40 enqueue!");
        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);

        System.out.println("Queue size: " + queue.size());
        System.out.println();

        int frontItem = queue.getFront();
        System.out.println("dequeue!");
        System.out.println("Front item: " + frontItem);

        System.out.println("Queue size: " + queue.size());
        System.out.println();

        System.out.println("clone making!");
        IntLinkedQueue clone = (IntLinkedQueue) queue.clone();
        System.out.println("Clone size: " + clone.size());
        System.out.println();
    }
}
