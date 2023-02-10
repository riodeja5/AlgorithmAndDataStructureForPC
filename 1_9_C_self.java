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

    private static void insert(ArrayList<Integer> heap, Integer x) {
        heap.add(x);
        if (heap.size() == 1) return;
        
        // 親と比較して大きければ入れ替え
        int targetIdx = heap.size() - 1;
        int parentIdx = (targetIdx - 1) / 2;
        Integer parent = heap.get(parentIdx);
        while (parent < x) {
            heap.set(parentIdx, x);
            heap.set(targetIdx, parent);
            if (parentIdx == 0) break;
            targetIdx = parentIdx;
            parentIdx = (targetIdx - 1) / 2;
            parent = heap.get(parentIdx);
        }
    }

    private static Integer extractMax(ArrayList<Integer> heap) {
        Integer max = heap.get(0);
        // 最後尾を先頭に持ってきて交換する
        Integer last = heap.get(heap.size() - 1);
        heap.set(0, last);
        heap.remove(heap.size() - 1);
        int targetIdx = 0;
        int leftIdx = targetIdx * 2 + 1;
        int rightIdx = targetIdx * 2 + 2;
        Integer left = leftIdx < heap.size() ? heap.get(leftIdx) : null;
        Integer right = rightIdx < heap.size() ? heap.get(rightIdx) : null;
        
        while ((left != null && left > last) || right != null && right > last) {
            if (left == null) {
                heap.set(targetIdx, right);
                heap.set(rightIdx, last);
                targetIdx = rightIdx;
            } else if (right == null) {
                heap.set(targetIdx, left);
                heap.set(leftIdx, last);
                targetIdx = leftIdx;
            } else if (left < right) {
                heap.set(targetIdx, right);
                heap.set(rightIdx, last);
                targetIdx = rightIdx;
            } else {
                heap.set(targetIdx, left);
                heap.set(leftIdx, last);
                targetIdx = leftIdx;
            }
            leftIdx = targetIdx * 2 + 1;
            rightIdx = targetIdx * 2 + 2;
            left = leftIdx < heap.size() ? heap.get(leftIdx) : null;
            right = rightIdx < heap.size() ? heap.get(rightIdx) : null;
        }
        return max;
    }
}