import java.util.*;
public class Main {
    public static void main(String args[]) { 
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> result = new ArrayList<>();
        // 最上位が最大値のヒープ
        ArrayList<Integer> heap = new ArrayList<>();
        String[] sCommandNum = scan.nextLine().split(" ");
        while (!sCommandNum[0].equals("end")) {
            if (sCommandNum[0].equals("insert")) {
                insert(heap, Integer.valueOf(sCommandNum[1]));
            } else {
                result.add(extractMax(heap));
            }
            sCommandNum = scan.nextLine().split(" ");
        }
        for (Integer ext : result) {
            System.out.println(ext);
        }
    }

    private static void insert(ArrayList<Integer> heap, Integer key) {
        heap.add(key);
        int i = heap.size() - 1;
        while (1 <= i && heap.get(i / 2) < heap.get(i)) {
            // 親と交換
            Integer parent = heap.get(i / 2);
            key = heap.get(i);
            heap.set(i, parent);
            heap.set(i / 2, key);
            i = i / 2;
        }
    }
        
    private static Integer extractMax(ArrayList<Integer> heap) {
        if (heap.isEmpty()) return null;
        Integer max = heap.get(0);
        Integer last = heap.get(heap.size() - 1);
        // 末尾を先頭に持ってくる
        heap.set(0, last);
        heap.remove(heap.size() - 1);
        // ヒープ再構成
        maxHeapify(heap, 0);
        return max;
    }

    private static void maxHeapify(ArrayList<Integer> heap, int i) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        // 左の子、自分、右の子で値が最大のノードを選ぶ
        int largest = i;
        if (l < heap.size() && heap.get(i) < heap.get(l)) {
            largest = l;
        }
        if (r < heap.size() && heap.get(largest) < heap.get(r)) {
            largest = r;
        }
        // 子の方が値が大きい場合交換する
        if (largest != i) {
            Integer largestVal = heap.get(largest);
            Integer iVal = heap.get(i);
            heap.set(largest, iVal);
            heap.set(i, largestVal);
            // 再帰呼び出し
            maxHeapify(heap, largest);
        }
    }
}