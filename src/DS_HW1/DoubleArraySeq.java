package DS_HW1;

public class DoubleArraySeq implements Cloneable{
    // DoubleArraySeq의 Invariant
    // 1. 시퀀스의 항목 수가 변수 manyItems에 있습니다.
    // 2. 시퀀스의 capacity는 정수 2,147,483,647을 초과할 수 없습니다.
    // 3. currentIndex 변수는 시퀀스의 현재 항목을 의미합니다.

    public double[] data;
    private int manyItems;
    private int currentIndex;

    public DoubleArraySeq(){
        /**
         * capacity 10으로 배열 초기화
         * @postcondition
         * This sequence is empty and has an initial capacity of 10.
         * @exception OutOfMemoryError
         * Indicates insufficient memory for: new int[10].
         */
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        currentIndex = 0;
        data = new double[INITIAL_CAPACITY];
    }

    public DoubleArraySeq(int initialCapacity){
        /**
         * 지정된 capacity로 배열 초기화
         * @param initialCapacity
         * the initial capacity of this sequence
         * @precondition
         * initialCapacity is non-negative.
         * @postcondition
         * This sequence is empty and has the given initial capacity.
         * @exception IllegalArgumentException
         * Indicates that initialCapacity is negative.
         * @exception OutOfMemoryError
         * Indicates insufficient memory for: new int[initialCapacity].
         */
        if (initialCapacity < 0)
            throw new IllegalArgumentException
                    ("initialCapacity too small " + initialCapacity);
        manyItems = 0;
        data = new double[initialCapacity];
    }

    public void addAfter(double element){
        /**
         * element를 현재 인덱스 뒤에 추가
         * @param
         * element – the new element that is being added
         * @postcondition
         * A new copy of the element has been added to this sequence.
         * If there was a current element,
         * then this method places the new element after the current element.
         * If there was no current element,
         * then this method places the new element at the end of this sequence.
         * @exception OutOfMemoryError
         * Indicates insufficient memory to increase the size of this sequence.
         */
        ensureCapacity(manyItems + 1);
        if (manyItems > currentIndex){
            System.arraycopy(data, currentIndex, data, currentIndex + 1, manyItems - currentIndex);
        } // 현재 인덱스를 가리키는 커서가 배열의 맨 뒤가 아니라면 element가 추가될 위치 뒷 부분을 모두 뒤로 한칸씩 이동
        data[currentIndex] = element; // 커서 다음에 element 값 추가
        manyItems++;
        currentIndex++;
        // 시퀀스 크기를 1 늘리고 커서를 새로 추가된 값이 있는 위치로 이동
    }

    public void addBefore(double element){
        /**
         * element를 현재 인덱스 앞에 추가
         * @param
         * element – the new element that is being added
         * @postcondition
         * A new copy of the element has been added to this sequence.
         * If there was a current element,
         * then this method places the new element before the current element.
         * If there was no current element,
         * then this method places the new element at the front of this sequence.
         * @exception OutOfMemoryError
         * Indicates insufficient memory to increase the size of this sequence.
         */
        ensureCapacity(manyItems + 1);
        if (manyItems > 0 && currentIndex > 0){
            System.arraycopy(data, currentIndex - 1, data, currentIndex, manyItems - currentIndex + 1);
            currentIndex--;
        }// 빈 배열이 아니고, 현재 인덱스를 가리키는 커서가 맨 앞이 아니라면 커서 앞 부분을 모두 뒤로 한칸씩 이동, 커서도 한칸 왼쪽으로 이동
        data[currentIndex] = element; // 커서 이전 위치에 element 값 추가
        manyItems++; // 시퀀스 크기 1 늘림
    }

    public void addAll(DoubleArraySeq addend){
        /**
         * 인자로 받은 시퀀스를 기존 시퀀스 뒤에 이어 붙임
         * @param
         * addend – a sequence whose contents will be placed at the end of this sequence.
         * @precondition
         * The parameter, addend, is not null.
         * @postcondition
         * The elements from addend have been placed at the end of this sequence.
         * The current element of this sequence remains where it was, and the addend is also unchanged.
         * @exception NullPointerException
         * Indicates that addend is null.
         * @exception OutOfMemoryError
         * Indicates insufficient memory to increase the capacity of this sequence.
         */
        ensureCapacity(manyItems + addend.manyItems); // 시퀀스의 capacity를 param 시퀀스의 capacity만큼 늘림
        System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems); // 기존 시퀀스 뒤에 param 시퀀스의 데이터를 추가
        manyItems += addend.manyItems; // 시퀀스의 capacity 값을 추가된 만큼 더함
    }

    public void advance(){
        /**
         * 커서가 시퀀스 안에 있다면, 한 칸 뒤로 이동
         * @precondition
         * isCurrent( ) returns true.
         * @postcondition
         * If the current element was already the end element of this sequence (with nothing after it),
         * then there is no longer any current element. Otherwise, the new element is the element
         * immediately after the original current element.
         * @exception IllegalStateException
         * Indicates that there is no current element, so advance may not be called.
         */
            if (isCurrent()){
                currentIndex++;
            } // isCurrent()가 true이면 currentIndex를 1 증가시킴
        }

    public Object clone(){
        /**
         * 이 시퀀스의 클론 생성
         * @return
         * The return value is a copy of this sequence.
         * Subsequent changes to the copy will not affect the original, nor vice versa.
         * The return value must be typecast to a DoubleArraySeq before it is used.
         * @exception OutOfMemoryError
         * Indicates insufficient memory for creating the clone.
         */
        DoubleArraySeq answer;
        try{
            answer = (DoubleArraySeq) super.clone();
        }
        catch (CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable");
        } // 클론 생성 가능 여부 확인
        answer.data = data.clone(); // 시퀀스 배열의 클론 생성
        return answer;
    }

    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2){
        /**
         * s1 시퀀스의 데이터와 그 뒤에 s2 시퀀스의 데이터가 연결된 새로운 시퀀스 생성해 리턴
         * @param
         * s1 – the first of two sequences
         * s2 – the second of two sequences
         * @precondition
         * Neither s1 nor s2 is null.
         * @return
         * a new sequence that has the elements of s1 followed by the elements of s2 (with no current element)
         * @exception NullPointerException
         * Indicates that one of the arguments is null.
         * @exception OutOfMemoryError
         * Indicates insufficient memory for the new sequence.
         */
        DoubleArraySeq answer = new DoubleArraySeq(s1.getCapacity() + s2.getCapacity()); // s1과 s2의 시퀀스 길이 만큼의 시퀀스를 새로 생성

        System.arraycopy(s1.data, 0, answer.data, 0, s1.manyItems); // answer의 시작부터 s1 시퀀스의 데이터 추가
        System.arraycopy(s2.data, 0, answer.data, s1.manyItems, s2.manyItems); // answer의 s1 데이터 뒤 부터 s2 시퀀스의 데이터 추가
        answer.manyItems = s1.manyItems + s2.manyItems; // answer의 manyItems 값에 s1과 s2의 capacity 크기를 합친 값 저장

        return answer;
    }
    public void ensureCapacity(int minimumCapacity){
        /**
         * 기존 시퀀스의 capacity가 param 값 보다 작다면 데이터는 같고 capacity가 param 값인 시퀀스로 대체
         * @param
         * minimumCapacity – the new capacity for this sequence
         * @postcondition
         * This sequence’s capacity has been changed to at least minimumCapacity.
         * @exception OutOfMemoryError
         * Indicates insufficient memory for: new double[minimumCapacity].
         */
        if (data.length < minimumCapacity){ // 현재 시퀀스 길이 param 값보다 작으면 실행
            double[] biggerArray = new double[minimumCapacity]; // param 값을 크기로 갖는 새로운 배열 생성
            System.arraycopy(data, 0, biggerArray, 0, manyItems); // 새로운 배열에 기존 배열 데이터 복사
            data = biggerArray; // 데이터는 같고 길이가 늘어난 시퀀스로 기존 시퀀스 대체
        }
    }
    public int getCapacity(){
        /**
         * 시퀀스의 길이 리턴
         * @return
         * the current capacity of this sequence
         */
        return data.length; // 시퀀스의 길이 리턴
    }
    public double getCurrent(){
        /**
         * 커서가 시퀀스 안에 있으면 커서가 가리키는 값 리턴, 그렇지 않으면 예외를 출력
         * @precondition
         * isCurrent( ) returns true.
         * @return
         * the current element of this sequence.
         * @exception IllegalStateException
         * Indicates that there is no current element.
         */
        if (isCurrent()){
            return data[currentIndex];
        } // 커서가 시퀀스 안에 있으면 커서가 가리키는 값 리턴
        else {
            throw new IllegalStateException("No current element");
        } // 커서가 시퀀스 안에 있지 않으면 예외 출력
    }
    public boolean isCurrent(){
        /**
         * 커서가 시퀀스 안에 있으면 true 그렇지 않으면 false
         * @return
         * true (there is a current element) or false (there is no current element at the moment)
         */
        return (currentIndex >= 0) && (currentIndex < manyItems);
        // currentIndex값이 0보다 크거나 같고 manyItems 값보다 작아야 true 즉, 시퀀스 안에 커서가 있으면 true 값 리턴
    }
    public void removeCurrent(){
        /**
         * @precondition
         * isCurrent( ) returns true.
         * @postcondition
         * The current element has been removed from this sequence, and the following element (if there is one) is now the new current element.
         * If there was no following element, then there is now no current element.
         * @exception IllegalStateException
         * Indicates that there is no current element, so removeCurrent may not be called.
         */
        if (isCurrent()){ // 커서가 시퀀스 안에 있을 때 실행
            for (int i = currentIndex; i < manyItems - 1; i++) {
                data[i] = data[i + 1];
            } // 커서 다음 값으로 커서 값을 덮어 씌우는 것을 시작으로 커서 뒤의 모든 값들을 한 칸 앞에 덮어 씌움
            manyItems--; // 시퀀스의 값 하나를 삭제했으므로 capacity 값을 의미하는 manyItems도 1을 뺌
        }
    }
    public int size(){
        /**
         * 시퀀스에 들어 있는 데이터의 개수 리턴
         * @return
         * the number of elements in this sequence
         */
        return manyItems;
    }
    public void start(){
        /**
         * 커서가 시퀀스의 맨 앞으로 이동
         * @postcondition
         * The front element of this sequence is now the current element (but if this sequence has no elements at all, then there is no current element).
         */
        currentIndex = 0;
    }
    public void trimToSize(){
        /**
         * 시퀀스의 데이터 개수와 길이가 같게 만듦
         * @postcondition
         * This sequence’s capacity has been changed to its current size.
         * @exception OutOfMemoryError
         * Indicates insufficient memory for altering the capacity.
         */
        double[] trimmedData = new double[manyItems]; // 기존 시퀀스의 데이터 개수만큼을 길이로 하는 새로운 시퀀스 생성
        System.arraycopy(data, 0, trimmedData, 0, manyItems); // 새로운 시퀀스에 기존 시퀀스 데이터를 복사
        data = trimmedData; // 기존 시퀀스를 새로운 시퀀스로 대체
    }
}

