import java.util.Random;

public class Algoritmi {

	private Random kone = new Random();
	private Olo2Robotti robo = null;
	private int	vanhaSuunta;

	public Algoritmi( Olo2Robotti robo ) {
		this.robo = robo;
	}


	public void tutkaile() {
		int suunta = 0;
		//alla on for-lause joka käy kaikki ilmansuunnat läpi 
		//ja tallettaa taulukkoon
		for (suunta = 0; suunta < 4; suunta++) {

			if (RobotinMaailma.annaSijainti().voikoSuuntaanEdeta(suunta)) {
				System.out.println( this + " suuntaan " + suunta + " voi edetä" );
				
				System.out.println( RobotinMaailma.annaSijainti() );
				System.out.println( RobotinMaailma.annaNaapuri( suunta ) );
				if ( (RobotinMaailma.annaNaapuri(suunta) == null) 
						&& (RobotinMaailma.annaSijainti().voikoSuuntaanEdeta(suunta)) ) {
					//eli jos naapuria ei tunneta (eli siellä ei ole käyty) 
					//mutta sinne voi edetä, sen laskurin katsotaan olevan 0 
					RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] = 0;
				}
				else {
					RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] = 
										RobotinMaailma
										.annaNaapuri(suunta)
										.annaLaskurinArvo();
					//muutoin kysytään RobonMaailmalta ruudun laskurin arvo
				}
			}
			else {
				RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] = 5;
				//seinää edustaa käyntilaskurin luku 5
			}
		}
		//palautetaan taulukko, jossa indeksi (0-3) 
		//on ilmansuunta ja sen arvo on naapuriruudun laskurin arvo
	}

	/*public int annaNaapurienMaara(RobotinRuutu keskiRuutu) {
		return keskiRuutu.annaLaskurinArvo();
		for (int a = 0; a < 4; a++) {
			this.robo.kaannyVasemmalle();
			if (this.robo.voiEdeta()) {
				naapurit++;
			}
		}
		return naapurit;
		// metodi laskee ruudun etenemiskelpoisten naapurien määrän
	}*/

	public boolean onkoRisteys(RobotinRuutu ruutu) {
		if (ruutu.annaNaapurienMaara() > 2) {
			return true;
		}
		else return false;
		// käyttää naapurimäärälaskuria määrittäessään, 
		// onko parametriruutu risteys
		// risteys <=> yli 2 naapuria
	}

	public boolean onkoUmpikuja() {
		/*muutos 28.10: käytetään taulukkoa suoraan ruudusta
		 */
		int suunnassaOnLiikaaKaynteja = 0;
		for (int i = 0; i < 4; i++) {
			if (RobotinMaailma.annaSijainti().naapurienLaskurit[i] >= 2) {
				suunnassaOnLiikaaKaynteja++;
			}
		}
		if (suunnassaOnLiikaaKaynteja == 3) {
			System.out.println( this + " ollaan umpikujassa");
			return true;
		}
		else return false;
		//ruutu on "umpikuja" jos tasan kolmen naapurin käyntilaskurit 2 tai 
		//yli (seinät = 5)
		//jos 4 naapurilla >=2, ollaan alussa, maalissa tai kusessa 
		//(umpikujassa, jossa ollaan jo käyty).
		//tämä metodi siis käsittelee sekä seiniä että käytävien 
		//käyntilaskureita int:einä.
	}



	public int arvoEriSuunta(int nytsuunta) {
		int ulossuunta = 0;// Math.abs( nytsuunta-2 ) ;
		do {
			ulossuunta = kone.nextInt(4);
		} while(ulossuunta == nytsuunta);
		
		return ulossuunta;
		//tämä metodi antaa suunnan, joka on eri kuin parametri. 
	}

	public int haeArvo (int[] taulukko, int arvo) {
		int indeksi;
		for(indeksi = 0; 
				indeksi < taulukko.length; 
				indeksi++) {

			if(taulukko[indeksi] == arvo) {
				return indeksi;
			//jos löytyy "arvo", palautetaan sen indeksi
			}
		}
		return -1;
		//jos ei löydä mitään niin palauttaa -1
	}

	public int teeSuuntaPaatos() {
		int suunta = -1;
		int a = -1; 

		a = haeArvo(RobotinMaailma
				.annaSijainti()
				.naapurienLaskurit, 0);

		if (a >= 0) {
			suunta = a;
		}
		else {
			a = haeArvo(RobotinMaailma
					.annaSijainti()
					.naapurienLaskurit, 1); 
			if (a >= 0) {
				suunta = a;
			}
			System.out.println( "Ei löydy 0:llaa, suunta on " + suunta );
		}
		
		/*if( onkoUmpikuja()  ) {
			for( int i = 0; i < 4; i++ ) {
				if( RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] ) {
					
				}
			}
		}*/
		//^tämä siis palauttaa negatiivisen luvun jos se ei löydä mitään.
		//0-arvoista suuntaa suositaan.
		//Jos 0- tai 1- arvoisia ei löydy yhtäkään, ollaan maalissa tai kusessa.
		if(onkoUmpikuja() == false
				&& ( RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] % 2 ) == 1 
				&& onkoRisteys(RobotinMaailma.annaNaapuri( suunta ) )) {
			int uusiSuunta = 0;
			do {
				uusiSuunta = arvoEriSuunta(suunta);
			} while (! RobotinMaailma.annaSijainti().voikoSuuntaanEdeta( uusiSuunta ));			
			//^testataan vielä voiko uuteen suuntaan edetä, 
			//jos ei niin kutsutaan tätä metodia uudestaan (rekursio)
			suunta = uusiSuunta;
		} 
		if( RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] == 2
			&& !onkoRisteys( RobotinMaailma.annaNaapuri( suunta ) )) {
			int uusiSuunta = 0;
			do {
				uusiSuunta = arvoEriSuunta(suunta);
			} while (! RobotinMaailma.annaSijainti().voikoSuuntaanEdeta( uusiSuunta ));	
			suunta = uusiSuunta;
		}
		
		this.vanhaSuunta = suunta;
		return suunta;
	}

	public static void main (String[] args) {
		System.out.println("Testing more");

	}

}
