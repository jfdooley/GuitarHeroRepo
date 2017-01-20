
/********************************************************************
 * Name         : 
 * PennKey      : 
 * Recitation # :
 *
 * Dependencies :
 * Description  : 
 *  
 *  This is a template file for RingBuffer.java.
 *  It lists the constructors and
 *  methods you need, along with descriptions of what
 *  they're supposed to do.
 *  
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *********************************************************************/

 import java.util.LinkedList;

public class RingBuffer {
    private LinkedList<Double> rb;          // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int size;             // number of items in the buffer

	/** constructors */
	public RingBuffer() {
		this.rb = new LinkedList<Double>();
	}

    // create an empty buffer, with given max capacity
    public RingBuffer(int capacity) {
		this.rb = new LinkedList<Double>();
        // YOUR CODE HERE
    }

    // return number of items currently in the buffer
    public int size() {
		return rb.size();
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        return rb.isEmpty(); 
    }

    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
        return false; // linked list is never full
    }

    // add item x to the end
    public void add(double x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
		rb.add(x);
    }

    // delete and return item from the front
    public double remove() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb.remove();
    }

    // return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb.peek(); 
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.add(i);
        }
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
