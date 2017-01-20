
/*********************************************************************
 * Name         : 
 * PennKey      : 
 * Recitation # :
 *
 * Dependencies :
 * Description  : 
 *  
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *  
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *********************************************************************/

public class GuitarString {

    private RingBuffer rb; // ring buffer
	private int N;				// size of the buffer for this string
	private double frequency;
	private int tics = 0;

    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
		this.frequency = frequency;
		this.N = (int) (Math.floor(44100.0/frequency)+1);
		this.rb = new RingBuffer();
		for(int i = 0; i < N ; i++)
			rb.add(0.0);
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
		this.N = init.length;
		this.rb = new RingBuffer();
		for(int i = 0; i < N ; i++)
			rb.add(init[i]);
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
		double value = 0.0;
		for(int i = 0; i < N; i++){
			value = 0.5* Math.sin(i * 2.0 * Math.PI * frequency / 44100.0);
			rb.remove();
			rb.add(value);
		}
    }

	/**
	 * Apply the Karplus-Strong update: delete the sample at the front
	 * of the ring buffer
	 * and add to the end of the ring buffer the average of the
	 * first two samples,
	 * multiplied by the energy decay factor.
	 *
	 * From a mathematical physics viewpoint, the
	 * Karplus-Strong algorithm approximately
	 * solves the 1D wave equation, which describes
	 * the transverse motion of the string
	 * as a function of time.
	 */
	public void tic(){
		double a = rb.remove();
		double b = rb.peek();
		double value = 0.996 * (0.5 * (a+b));
		this.rb.add(value);
		this.tics++;
	}

    // return the current sample
    public double sample() {
        return this.rb.peek();
    }

    // return number of times tic was called
    public int time() {
        return this.tics;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }

}
