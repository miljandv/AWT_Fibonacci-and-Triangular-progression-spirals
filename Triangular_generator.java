package spiral;

public class Triangular_generator extends Generator {
	
	public Triangular_generator() {
		super(new int[] {0,1,1});
	}
	@Override
	public int get_value() {
		int k=r[0]+r[1];
		int a=r[1]+r[2];
		r[0]=k;r[1]=a;
		return k;
	}

}
