package au.edu.uq.itee.comp3506.assn2.entities.ADTs;



/**
 * Generic unsorted Binary tree Implementation of a Data Tree.
 * Each node has, at most, two child nodes and is sorted by the Comparable.
 * insert and removal time.
 *
 * Created for COMP3506 Assignment 2 at the University Of Queensland.
 *
 * Memory Efficiency: O(n), where n represents the number of nodes created.
 *
 * @author Daniel Gormly, Student Number: 43503348
 *
 * @param <K>
 *     Key to order the tree by.
 * @param <E>
 *     Element type to store inside the nodes.
 */
public class BinaryTree<K extends Comparable<? super K>, E> implements AbstractBinaryTree<K, E> {

    protected Node<K, E> root = null;
    protected ProbeHashMap<K, Node<K, E>> map = new ProbeHashMap<>();
    protected SinglyLinkedList<E> list = new SinglyLinkedList<>();

    protected int size = 0;

    protected Node<K, E> setRoot(K key, E element) {
        if (size() > 0) {
            return null;
        } else {
            root = new Node<>(null, key, element);
            map.put(key, root);
            size++;
            return root;
        }
    }


    /**
     * Checks if the tree contains a given key.
     *
     * Runtime efficiency: O(1)
     *
     * @param key
     *      Key to check for.
     * @return
     *      True, key is in tree.
     *      False, key is not in the tree
     */
    public boolean contains(K key) {
        if (map.contains(key)) {
            return true;
        }
        return false;
    }


    /**
     * Adds and element to the tree.
     *
     * Runtime efficieny: O(logn)
     *
     * @param element,
     *      Element to be stored in the tree.
     * @return
     *      Node that element is stored in.
     */
    public Node<K, E> add(K key, E element) {
        Node<K, E> current;

        if (size == 0) {
            setRoot(key, element);
            return root;
        } else {
            current = root;
        }

        while (true) {
            /* Add to the left if key less or equal to current. */
            if (key.compareTo(current.key) <= 0) {
                if (current.left == null) {
                    return addLeft(current, key, element);
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    return addRight(current, key, element);
                } else {
                    current = current.right;
                }
            }
        }
    }


    /**
     * Adds a new node to the left child of the given tree.
     *
     * @param parent
     *      Parent node to insert into.
     * @param key
     *      Key to add to node.
     * @param element
     *      Element to store.
     * @return
     *      Node created from insert.
     *      Null if left child is taken.
     */
    @Override
    public Node<K, E> addLeft(Node<K, E> parent, K key, E element) {
        if (parent == null || parent.left != null) {
            return null;
        } else {
            Node<K, E> n = new Node<>(parent, key, element);
            parent.left = n;
            map.put(key, n);
            size++;
            return n;
        }
    }


    /**
     * Adds a new node to the left child of the given tree.
     *
     * @param parent
     *      Parent node to insert into.
     * @param key
     *      Key to add to node.
     * @param element
     *      Element to store.
     * @return
     *      Node created from insert.
     *      Null if left child is taken.
     */
    @Override
    public Node<K, E> addRight(Node<K, E> parent, K key, E element) {

        if (parent == null || parent.right != null) {
            return null;
        } else {
            Node<K, E> n = new Node<>(parent, key, element);
            parent.right = n;
            map.put(key, n);
            size++;
            return n;
        }
    }


    /**
     * Highest node in the tree.
     *
     * Runtime efficiency: O(1)
     *
     * @return
     */
    @Override
    public Node<K, E> getRoot() {
        return root;
    }


    /**
     * Retrieve a node at for a given key.
     *
     * @param key
     *      Key to find element associated with.
     * @return
     *      Node associated with given key.
     */
    @Override
    public Node<K, E> get(K key) {
        return map.get(key);
    }


    /**
     * Number of elements in the tree.
     * @return
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Checks if the tree is empty.
     * @return
     *  True, Tree is empty,
     *  False, Tree is not empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Gets a node closes to the given node on the right side.
     *
     * Runtime efficiency: O(logn)
     *
     * @param key
     *      Key to search for.
     * @return
     *      Node found on right side of given key.
     */
    public Node<K, E> getFrom(K key) {
        Node<K, E> firstNode = getRoot();
        while (true) {
            if (key.compareTo(firstNode.getKey()) < 0 && firstNode.getLeft() != null) {
                firstNode = firstNode.getLeft();
            } else if (key.compareTo(firstNode.getKey()) > 0 && firstNode.getRight() != null) {
                firstNode = firstNode.getRight();
            } else {
                return firstNode;
            }
        }
    }


    /**
     * Gets a node closes to the given node on the right side.
     *
     * Runtime efficiency: O(logn)
     *
     * @param key
     *      Key to search for.
     * @return
     *      Node found on right side of given key.
     */
    public Node<K, E> getTo(K key) {
        Node<K, E> firstNode = getRoot();
        while (true) {
            if (key.compareTo(firstNode.getKey()) < 0 && firstNode.getLeft() != null) {
                firstNode = firstNode.getLeft();
            } else if (key.compareTo(firstNode.getKey()) > 0 && firstNode.getRight() != null) {
                firstNode = firstNode.getRight();
            } else {
                return firstNode;
            }
        }
    }


    /**
     * Returns the right most element in the Array.
     *
     * Runtime efficiency: O(Logn)
     *
     * @return
     *      Most left node in the tree.
     */
    public Node<K, E> getFirst() {
        Node<K, E> current = getRoot();
        if (current == null) {
            return null;
        }

        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    /**
     * Returns the most right node in the tree.
     *
     * Runtime efficiency: O(n)
     *
     * @return
     *      Most right node in the tree.
     */
    public Node<K, E> getLast() {
        Node<K, E> current = getRoot();
        if (current == null) {
            return null;
        }

        while (current.right != null) {
            current = current.right;
        }
        return current;
    }


    public SinglyLinkedList<E> getRange(K start,K end) {
        list = new SinglyLinkedList<>();
        traverseRange(root, start, end, list);
        return list;
    }


    private void traverseRange(Node<K, E> position, K start, K end, SinglyLinkedList<E> list) {
        if (position.left != null) {
            K key = position.left.key;
            if (key.compareTo(key) >= 0) {
                traverseRange(position.left, start, end, list);
            } else if (position.left.right != null) {
                traverseRange(position.left.right, start, end, list);
            }
        }

        if (position.key.compareTo(start) >= 0 && position.key.compareTo(end) <= 0) {
            list.addToEnd(position.element);
        }

        if (position.right != null) {
            K key = position.getRight().getKey();
            if (key.compareTo(end) <= 0) {
                traverseRange(position.right, start, end, list);
            } else if (position.right.left != null) {
                traverseRange(position.right.left, start, end, list);
            }
        }
    }
}
