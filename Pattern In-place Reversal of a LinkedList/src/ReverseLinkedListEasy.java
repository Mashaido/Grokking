/*Given the head of a Singly LinkedList, reverse the LinkedList. Write a function to return the
new head of the reversed LinkedList.

 */
public class ReverseLinkedListEasy {

    public static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        Node next = null;

        while (curr != null) {
            // keep reference b4 losing it
            next = curr.next;
            // now swap back ptr
            curr.next = prev;
            // increment prev b4 losing reference
            prev = curr;
            curr = next;

        }
        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);

        Node result = reverse(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }
}
