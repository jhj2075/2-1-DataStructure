package DS_HW1;

public class DoubleArraySeqDemo{
    // 데모 프로그램입니다.

    public static void main(String[] args){
        DoubleArraySeq seq1 = new DoubleArraySeq();
        seq1.addAfter(1.0);
        seq1.addAfter(2.0);
        seq1.addAfter(3.0);
        System.out.print("seq1: ");
        printSeq(seq1);

        seq1.addBefore(0.0);
        System.out.print("addBefore(0.0) 실행 후 seq1: ");
        printSeq(seq1);

        DoubleArraySeq seq2 = new DoubleArraySeq();
        seq2.addAfter(4.0);
        seq2.addAfter(5.0);
        seq2.addAfter(6.0);

        seq1.addAll(seq2);

        System.out.print("addAll(seq2) 실행 후 seq1: ");
        printSeq(seq1);

        seq1.start();
        seq1.advance();
        seq1.advance();

        System.out.println();
        System.out.println("start 한 번, advance 두 번 실행 후 current element: " + seq1.getCurrent());

        seq1.removeCurrent();

        System.out.print("removeCurrent 실행 후 seq1: ");
        printSeq(seq1);
        System.out.println();

        seq1.ensureCapacity(15);
        System.out.println("ensureCapacity 실행 후 seq1의 manyItems: " + seq1.data.length);
        seq1.trimToSize();
        System.out.print("trimToSize 실행 후 seq1: ");
        printSeq(seq1);
        System.out.println("trimToSize 실행 후 seq1의 manyItems: " + seq1.data.length);
        System.out.println();

        DoubleArraySeq seq3 = DoubleArraySeq.concatenation(seq1, seq2);

        System.out.print("seq1과 seq2를 concatenation 한 seq3: ");
        printSeq(seq3);

    }

    public static void printSeq(DoubleArraySeq seq){ // 시퀀스 데이터를 순서대로 출력
        seq.start(); // 커서를 맨 앞으로 옮김
        while (seq.isCurrent()){
            System.out.print(seq.getCurrent() + " ");
            seq.advance();
        } // 커서가 시퀀스 안에 있는 동안 커서 위치의 데이터 값을 출력하고 한 칸 띄운 뒤 커서를 뒤로 한 칸 옮기는 작업 반복
        System.out.println();
    }
}
