import java.util.HashMap;
import java.util.Map;

/*

Given the head of a Singly LinkedList that contains a cycle,
write a function to find the starting node of the cycle.

 */
public class LinkedListCycleStartMedium {

    /*
    not this correct technique :( instd using hashtable
     */
    public static Node findCycleStart(Node head) {
        Map<Node, Integer> map = new HashMap<>();
        Node current = head;
        while (current != null) {
            // check for cycle -assumption that all nodes unique vals
            if (!map.containsKey(current)) {
                map.put(current, 1);
            }
            else {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).data);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).data);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).data);
    }
}
