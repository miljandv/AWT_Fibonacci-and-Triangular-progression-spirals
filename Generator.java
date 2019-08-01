package spiral;

import java.util.Random;

public abstract class Generator implements Producer {
	protected int r[];
	public Generator(int[] rr) {r=rr;}
	public abstract int get_value();

}
