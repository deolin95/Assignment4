
public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;
    int count = 0;

    /**
     * Create a default list
     */
    public MyLinkedList() {
    }

    /**
     * Create a list from an array of objects
     */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /**
     * Return the head element in the list
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    public int comps() {
        int comp = count;
        count = 0;
        return comp;
    }

    /**
     * Return the last element in the list
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /**
     * Add an element to the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }

    }

    /**
     * Add an element to the end of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /**
     * Override toString() to return elements in the list
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /**
     * Clear the list
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * @param Generic E type e a generic element which is tested to be contained
     * in linkedList
     *
     * @requires e to be nonempty
     *
     * @ensures a boolean value based on the presence of a generic value is
     * returned to the calling class
     *
     * @returns true if value found in list, false if not
     *
     * This method recieves a Generic value, tests it against all values in a
     * linked list and returns a true if value is found in list, false if value
     * is not found
     */
    public boolean contains(E e) {
        Node<E> tempNode = head;

        for (int i = 0; i < size; i++) {
            count++;
            if (e.equals(tempNode.element)) {
                return true;
            } else {
                tempNode = tempNode.next;
            }
        }
        return false;

    }

    /**
     * @param int a contains the index of a linkedList Element to be returned
     *
     * @requires 'a' to be nonempty, to be with the bounds of list size and -1
     *
     * @ensures the index specified by 'a' is returned to calling class
     *
     * @returns a Generic list element at list index of the value of 'a'
     *
     * This Method recieves an integer 'a' to find and return the element at the
     * linkedList index which corresponds to the value of 'a'. Null is returned
     * if the value of 'a' doesn't correspond to any given index
     */
    public E get(int a) {
        if (!(a < size && a > -1)) {
            return null;
        }
        Node<E> temp = head;
        for (int i = 0; i < a; i++) {

            temp = temp.next;

        }
        return temp.element;
    }

    /**
     * @param E e contains a generic object used to find and return the first
     * linkedList index whoses contents is of like value
     * @requires e to be a reference type variable and nonempty
     *     
* @ensures The recieved object is found if contained in linkedList and an
     * integer containing a corresponding index value or a -1 is returned
     *     
* @returns an integer of -1 or an index that contains a value like the
     * passed value
     *     
* This Method recieves a generic type value, tests it against the values of
     * a linkedList, and if found, returns the correct index, else it returns -1
     */
    public int indexOf(E e) {

        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (e.equals(temp.element)) {
                return i;
            }
            temp = temp.next;

        }
        return -1;
    }

    /**
     * @param E e contains a generic object used to find and return the last
     * linkedList index whoses contents is of like value
     * @requires e to be a reference type variable and nonempty
     *     
* @ensures The recieved object is found if contained in linkedList and an
     * integer containing a corresponding index value or a -1 is returned
     *     
* @returns an integer of -1 or an index that contains a value like the
     * passed value
     *     
* This Method recieves a generic type value, tests it against the values of
     * a linkedList, and if found, returns the correct index, else it returns -1
     */
    public int lastIndexOf(E e) {

        int b = -1;

        Node<E> temp = head;

        for (int i = 0; i < size; i++) {
            if (e.equals(temp.element)) {
                b = i;
            }
            temp = temp.next;
        }
        return b;
    }//lastIndexOf

    /**
     * @param int ind int ind is an integer corresponding to an index whose
     * contents are to be changed
     * @param E e is a generic object to replace the contents of the
     * corresponding index
     * @requires int ind to be nonempty; E e to be nonempty and a reference type
     * variable
     *     
* @ensures that if the contents of the linkedList corresponding to ind are
     * found, then the content is replaced by 'e' and returned
     *     
* @returns a generic value of index corresponding to 'ind' if found in
     * linkedList, and a null value if not
     *     
*
     */
    public E set(int ind, E e) {

        Node<E> temp = (Node) head;
        for (int i = 0; i < size; i++) {
            if (i == ind) {
                Node<E> old = temp;
                remove(i);
                add(i, e);
                return old.element;
            }
            temp = temp.next;
        }

        return null;

    }

    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}

/*


 run:
 (1) [America]
 (2) [Canada, America]
 (3) [Canada, America, Russia]
 (4) [Canada, America, Russia, France]
 (5) [Canada, America, Germany, Russia, France]
 (6) [Canada, America, Germany, Russia, France, Norway]
 (7) [Poland, Canada, America, Germany, Russia, France, Norway]
 (8) [Canada, America, Germany, Russia, France, Norway]
 (9) [Canada, America, Russia, France, Norway]
 (10) [Canada, America, Russia, France]
 (11) The list does not contain Germany
 (12) Invalid position
 (13) The list element Franceis at position 3
 (14) [India, Canada, America, Russia, France]
 (15) [India, Canada, America, Russia, France, America]
 (16) The list element America occurs last at 5
 (17) [India, Canada, America, Russia, France, China]
 BUILD SUCCESSFUL (total time: 0 seconds)


 */
