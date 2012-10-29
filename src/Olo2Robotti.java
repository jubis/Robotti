
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
	
	public void pyorahda() {
		//Tämä metodi tutkii naapurustostaan mihin suuntiin voi mennä.
		for (int i = 0; i < 4; i++) {
			RobotinMaailma.annaSijainti().asetaVoikoEdeta( this.annaSuunta(), 
			                                               this.voiEdeta() ); 
			this.kaannyOikealle();	
		}
	}
	
	public boolean onkoUmpikuja() {
		int naapurit = 0;
		for( int i = 0; i < 4; i++ ) {
			this.kaannyOikealle();
			if( this.voiEdeta() && 
				RobotinMaailma.annaSijainti().naapurienLaskurit[ this.annaSuunta() ] < 2 ) {
				naapurit++;
			}
		}
		if( naapurit > 1 ) {
			return false;
		} else {
			return true;
		}
	}

	public void teeSiirto() {
		if( this.onkoUmpikuja() ) {
			System.out.println("Ollaan umpikujassa -> lisää laskuria");
			RobotinMaailma.annaSijainti().lisaaLaskuria();
		}
		
		this.pyorahda();
		alg.tutkaile();
		int suunta = alg.teeSuuntaPaatos();
		
		while( this.annaSuunta() != suunta ) {
			this.kaannyVasemmalle();
			System.out.println( this + " kääntyi suuntaan " + this.annaSuunta());
		}
		
		RobotinMaailma.siirry( suunta );
		
		System.out.println( this + " etenee suuntaan: " + this.annaSuunta() + "*************" );
		this.etene();
	}

	public static void main( String[] args ) {
		Olo2Robotti robo = new Olo2Robotti();
		while(true) {
			robo.teeSiirto();
		}
		//Turnaus turnaus = new Turnaus();
		//turnaus.run;
		
	}
}

