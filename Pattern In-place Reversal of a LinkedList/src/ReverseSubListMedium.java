/*
Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position
‘p’ to ‘q’.

 */

public class ReverseSubListMedium {
    public static Node reverse(Node head, int p, int q) {
        Node dummyHead = head;
        Node prev = null;
        Node next = null;
        Node curr = head;

        // assuming p isn't head
        while (curr != null) {
            curr = curr.next;
            if (curr.data == p) {
                break;
            }
        }

        // now current == p
        Node pCurr = curr;

        // reverse
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

            if (prev.data == q) {
                break;
            }
        }

        // assuming p starts at 2nd node
        // link head to prev now
        dummyHead.next = prev;

        // link initial p to now curr/next
        pCurr.next = curr;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node result = reverse(head, 2, 4);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }
}
