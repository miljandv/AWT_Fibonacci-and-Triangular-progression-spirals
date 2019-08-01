package spiral;

public class Fibonacci_generator extends Generator{

	public Fibonacci_generator() {
		super(new int[] {0,1});
	}
	public int get_value() {
		int k=r[0]+r[1];
		r[0]=r[1];
		r[1]=k;
		System.err.println(k+" ");
		return k;
	}

}
