/*
Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.

If the total number of nodes in the LinkedList is even, return the second middle node.

 */

public class MiddleOfLinkedListEasy {
    public static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null) {
            if (fast.next == null) {
                // odd-sized linked list
                return slow;
            }
            if (fast.next.next == null) {
                // even-sized linked list
                return slow.next;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.println("Middle Node: " + findMiddle(head).data);

        head.next.next.next.next.next = new Node(6);
        System.out.println("Middle Node: " + findMiddle(head).data);

        head.next.next.next.next.next.next = new Node(7);
        System.out.println("Middle Node: " + findMiddle(head).data);
    }
}

