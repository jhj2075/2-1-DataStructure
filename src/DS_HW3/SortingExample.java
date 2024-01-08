package DS_HW3;
import java.util.Arrays;

public class SortingExample {
    public static void main(String[] args) {
        // 정수 배열 생성
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            arr[i] = i;
        }
        shuffleArray(arr);

        // Insertion Sort
        System.out.println("정렬할 배열 : " + Arrays.toString(arr));
        System.out.println();

        System.out.println("Insertion Sort:");
        insertionSort(Arrays.copyOf(arr, arr.length));
        System.out.println();

        // Selection Sort
        System.out.println("Selection Sort:");
        selectionSort(Arrays.copyOf(arr, arr.length));
        System.out.println();

        // Merge Sort
        System.out.println("Merge Sort:");
        mergeSort(Arrays.copyOf(arr, arr.length));
        System.out.println();

        // Quick Sort
        System.out.println("Quick Sort:");
        quickSort(Arrays.copyOf(arr, arr.length), 0, arr.length - 1);
        System.out.println();
    }

    public static void shuffleArray(int[] arr) { // 0 ~ 31 배열을 무작위로 섞는 메소드
        for (int i = arr.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void insertionSort(int[] arr) { // Insertion Sort 메소드
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 배열의 두 번째 인덱스부터 key로 설정
            int j = i - 1;
            while (j >= 0 && arr[j] > key) { // key 인덱스 바로 앞 인덱스부터 key보다 크면 바로 뒤 인덱스에 그 값을 저장하는 반복 수행
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // 정렬에 필요한 반복 수행 후 key 값을 Insertion

            System.out.println(i + "번 수행 => " + Arrays.toString(arr)); // i번 정렬 후 상태 출력
        }
    }

    public static void selectionSort(int[] arr) { // Selection Sort 메소드
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // 배열의 i번째 인덱스를 minIndex라 설정하고 시작
            for (int j = i + 1; j < arr.length; j++) { // j번째 인덱스부터 순서대로 minIndex와 비교하는 형식으로 minIndex 찾는 밥복 수행
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp; // minIndex 값을 배열의 맨 앞에서부터 순서대로 채워 넣음

            System.out.println((i + 1) + "번 수행 => " + Arrays.toString(arr)); // i번 정렬 후 상태 출력
        }
    }

    public static void mergeSort(int[] arr) { // recursive한 mergeSort 메소드
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid); // left 배열을 arr의 시작부터 mid까지로 설정
        int[] right = Arrays.copyOfRange(arr, mid, arr.length); // right 배열을 arr의 mid부터 끝까지로 설정

        mergeSort(left); // left에 대해 recursive하게 mergeSort 호출
        mergeSort(right); // right에 대해 recursive하게 mergeSort 호출
        merge(arr, left, right); // arr에 left와 right를 merge하는 메소드 호출

        System.out.println(Arrays.toString(arr));
    }

    public static void merge(int[] arr, int[] left, int[] right) { // left, right 두 배열을 merge해 arr에 넣는 메소드
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) { // left와 right 중 어느 한 쪽이 전부 사용될 때 까지 left와 right 각각의 첫 인덱스부터 비교하는 반복 수행
            if (left[i] <= right[j]) { // right의 j 인덱스가 크면 작은 left의 i 인덱스를 arr의 k번째에 저장
                arr[k] = left[i];
                k++; // arr의 k에 값이 저장되었으니 k++로 다음 인덱스로 커서 이동
                i++; // left의 i는 비교가 완료되었으니 i++로 다음 인덱스로 커서 이동
            } else { // left 인덱스 값이 큰 경우 실행
                arr[k] = right[j];
                k++;
                j++; // right의 j는 비교가 완료되었으니 j++로 다음 인덱스로 커서 이동
            }
        }
        while (i < left.length) { // left, right 배열 간 비교가 끝난 후에도 left 배열에 남은 값들이 있으면 그대로 arr에 넣는 반복 수행
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < right.length) { // left, right 배열 간 비교가 끝난 후에도 right 배열에 남은 값들이 있으면 그대로 arr에 넣는 반복 수행
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void quickSort(int[] arr, int low, int high) { // recursive한 quickSort 메소드
        if (low < high) { // 재귀함수의 중단 조건
            int pivotIndex = partition(arr, low, high); // 맨 처음엔 한 번 conquer하고 시작

            System.out.println(Arrays.toString(arr));

            quickSort(arr, low, pivotIndex - 1); // pivot 기준 앞 부분에 대해 recursive하게 quickSort 호출
            quickSort(arr, pivotIndex + 1, high); // pivot 기준 뒷 부분에 대해 recursive하게 quickSort 호출
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // pivot을 arr의 첫 번째 인덱스로 설정
        int i = low + 1;
        int j = high;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) { // pivot보다 큰 값이 나올 때 까지 i 커서 오른쪽으로 이동
                i++;
            }
            while (i <= j && arr[j] > pivot) { // pivot보다 작은 값이 나올 때 까지 j 커서 왼쪽으로 이동
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp; // i와 j의 값 swap
            }
        }
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp; // pivot과 j 인덱스 값 swap
        return j; // 새로운 pivot 값 return
    }
}
