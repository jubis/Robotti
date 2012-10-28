
public class Olo2Robotti extends Robotti {	
	public static final int POHJOINEN = 0;
	public static final int ITA = 1;
	public static final int ETELA = 2;
	public static final int LANSI = 3;
	
	private Algoritmi alg = null;
	
	public Olo2Robotti() {
		RobotinMaailma.alusta();
		this.alg = new Algoritmi( this );
	}
	
	public boolean voikoSuuntaanEdeta( int suunta ) {
		while( this.annaSuunta() != suunta ) {
			this.kaannyVasemmalle();
		}
		return this.voiEdeta();
	}

	public void teeSiirto() {
		int suunta = alg.teeSuuntaPaatos();
		
		while( this.annaSuunta() != suunta ) {
			this.kaannyVasemmalle();
			System.out.println( this + " kääntyi suuntaan " + this.annaSuunta());
		}
		
		RobotinMaailma.siirry( suunta );
		
		System.out.println( this + " etenee suuntaan: " + this.annaSuunta() );
		this.etene();
	}

	public static void main( String[] args ) {
		Olo2Robotti robo = new Olo2Robotti();
		while(true) {
			robo.teeSiirto();
		}
		
	}
}

