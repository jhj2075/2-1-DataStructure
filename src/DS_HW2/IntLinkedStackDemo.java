package DS_HW2;

public class IntLinkedStackDemo {
    public static void main(String[] args) {
        IntLinkedStack stack = new IntLinkedStack();
        System.out.println("Stack size: " + stack.size());
        System.out.println("Stack is empty: " + stack.isEmpty());
        System.out.println();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("push 1, 2, 3!");
        System.out.println("Stack is empty: " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());
        System.out.println();

        int poppedElement = stack.pop();
        System.out.println("pop!");
        System.out.println("Popped element: " + poppedElement);
        System.out.println("Stack size: " + stack.size());

        System.out.println("Top element: " + stack.peek());
    }
}