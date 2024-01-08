package DS_HW2;

public class IntStackDemo {
    public static void main(String[] args) {
        IntStack stack1 = new IntStack();
        IntStack stack2 = new IntStack(20);

        System.out.println("stack1 capacity: " + stack1.getCapacity());
        System.out.println("stack2 capacity: " + stack2.getCapacity());
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            stack1.push(i);
        }
        System.out.println("stack1 top item: " + stack1.peek());
        System.out.println("stack1 size: " + stack1.size());
        stack1.pop();
        System.out.println("stack1 size after pop: " + stack1.size());
        System.out.println("stack1 top item after pop: " + stack1.peek());
        System.out.println();

        for (int i = 1; i <= 20; i++) {
            stack2.push(i);
        }
        System.out.println("stack2 top item: " + stack2.peek());
        stack2.pop();
        System.out.println("stack2 top item after pop: " + stack2.peek());
        System.out.println();

        IntStack stack3 = (IntStack) stack2.clone();
        System.out.println("Now stack3 is clone of stack2, stack3 top item: " + stack3.peek());
        System.out.println();

        stack2.ensureCapacity(30);
        System.out.println("stack2 capacity after ensureCapacity: " + stack2.getCapacity());
        stack2.trimToSize();
        System.out.println("stack2 capacity after trimToSize: " + stack2.getCapacity());
    }
}
