/*
Given the head of a LinkedList with a cycle, find the length of the cycle.
 */

public class LinkedListCycleLength {

    public static int findCycleLength(LinkedListCycleEasy.Node head) {
        LinkedListCycleEasy.Node fast = head;
        LinkedListCycleEasy.Node slow = head;
        while (fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                int len = 0;
                LinkedListCycleEasy.Node currFast = fast;
                LinkedListCycleEasy.Node currSLow = slow;
                currSLow = currSLow.next;
                len++;
                while (slow != currSLow) {
                    currSLow = currSLow.next;
                    len++;
                }
                return len;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LinkedListCycleEasy.Node head = new LinkedListCycleEasy.Node(1);
        head.next = new LinkedListCycleEasy.Node(2);
        head.next.next = new LinkedListCycleEasy.Node(3);
        head.next.next.next = new LinkedListCycleEasy.Node(4);
        head.next.next.next.next = new LinkedListCycleEasy.Node(5);
        head.next.next.next.next.next = new LinkedListCycleEasy.Node(6);
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head));
    }
}
