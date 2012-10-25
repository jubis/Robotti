
public class Olo2Robotti extends Robotti {	
	public static final int POHJOINEN = 0;
	public static final int ITA = 1;
	public static final int ETELA = 2;
	public static final int LANSI = 3;
	
	public void teeSiirto() {
		RobotinMaailma.siirry( suunta );
		
		System.out.println("Etene");
		this.etene();
	}
	
}
