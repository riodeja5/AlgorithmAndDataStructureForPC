import java.util.*;
import java.lang.Math;
public class Main {
    public static void main(String args[]) { 
        Scanner scan = new Scanner(System.in);
        int n = Integer.valueOf(scan.nextLine());

        DLList dlList = new DLList();
        for (int i = 0; i < n; i++) {
            String[] command = scan.nextLine().split(" ");
            int key = Integer.valueOf(command[1]);
            if (command[0].equals("insert")) {
                dlList.insert(key);
            } else if (command[0].equals("delete")) {
                dlList.delete(key);
            }
        }
        dlList.print();
    }

    private static final class DLList {
        private DLNode dmFirst;
        private DLNode dmLast;

        DLList() {
            dmFirst = new DLNode(-1);
            dmLast = new DLNode(-1);
            dmFirst.next = dmLast;
            dmLast.prev = dmFirst;
        }

        void insert(int key) {
            DLNode node = new DLNode(key);
            node.prev = dmFirst;
            node.next = dmFirst.next;
            dmFirst.next.prev = node;
            dmFirst.next = node;
        }

        void delete(int key) {
            DLNode node = dmFirst.next;
            while (node != dmLast) {
                if (node.key == key) {
                    // 削除
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                    node.prev = null;
                    node.next = null;
                    return;
                } else {
                    node = node.next;
                }
            }
            // ここに到達した場合キー無しで削除不可能
        }

        void deleteFirst() {
            if (dmFirst.next == dmLast) {
                // 削除不可能
                return;
            }
            DLNode deleteNode = dmFirst.next;
            dmFirst.next = deleteNode.next;
            deleteNode.next.prev = dmFirst;
            deleteNode.prev = null;
            deleteNode.next = null;
        }

        void deleteLast() {
            if (dmFirst.next == dmLast) {
                // 削除不可能
                return;
            }
            DLNode deleteNode = dmLast.prev;
            dmLast.prev = deleteNode.prev;
            deleteNode.prev.next = dmLast;
            deleteNode.prev = null;
            deleteNode.next = null;
        }

        void print() {
            DLNode node = dmFirst.next;
            while (node != dmLast) {
                System.out.print(node.key + " ");
                node = node.next;
            }
        }
    }

    private static final class DLNode {
        int key;
        DLNode prev;
        DLNode next;

        DLNode(int key) {
            this.key = key;
        }
    }
}