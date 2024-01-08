package DS_HW2;

public class IntQueueDemo {
    public static void main(String[] args) {
        IntQueue queue = new IntQueue();

        System.out.println("empty queue인지 확인(empty이면 false) => " + queue.isEmpty());
        System.out.println();

        System.out.println("1, 2, 3, 4, 5 enqueue!");
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);

        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.getCapacity());
        System.out.println();

        queue.insert(6);
        System.out.println("enqueue 6!");

        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.getCapacity());
        System.out.println();

        System.out.println("dequeue element: " + queue.getFront());

        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.getCapacity());
        System.out.println();

        IntQueue queueCopy = (IntQueue) queue.clone();

        System.out.println("clone making!");
        System.out.println("Copy size: " + queueCopy.size());
        System.out.println("Copy capacity: " + queueCopy.getCapacity());
    }
}
