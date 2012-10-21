
public class Olo2Robotti extends Robotti {	
	
	public void teeSiirto() {
		RobotinMaailma.siirry( suunta );
		
		System.out.println("Etene");
		this.etene();
	}
	
}
