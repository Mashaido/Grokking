/*

Given the head of a Singly LinkedList,
write a function to determine if the LinkedList has a cycle in it or not.

Example 1:

See picture

Example 2:

See picture

 */

public class LinkedListCycleEasy {
    public static class Node {
        int data;
        Node next;

        Node (int data) {
            this.data = data;
            Node next;
        }

        public static int size() {
            return 6;
        }
    }

    /*
    O(n) time with no extra space
     */
    public static Boolean hasCycleBruteF(Node l1) {
        int len = l1.size();
        // begin looping
        Node current = l1;
        int loops = 0;
        while (current != null) {
            loops++;
            current = current.next;

            if (loops > len) {
                return Boolean.TRUE;
            }
        }
        return false;
    }

    /*
    O(n) time with no extra space
     */
    public static Boolean hasCycle (Node l1) {
        Node slow = l1;
        Node fast = l1;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // found a cycle!
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        System.out.println("LinkedList has cycle: " + hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + hasCycle(head));
    }
}
