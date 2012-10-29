import java.util.Random;

public class Algoritmi {

	private Random kone = new Random();
	private Olo2Robotti robo = null;

	public Algoritmi( Olo2Robotti robo ) {
		this.robo = robo;
	}


	public void tutkaile() {
		int suunta = 0;
		//alla on for-lause joka käy kaikki ilmansuunnat läpi 
		//ja tallettaa taulukkoon
		for (suunta = 0; suunta < 4; suunta++) {

			if (RobotinMaailma.annaSijainti().voikoSuuntaanEdeta(suunta)) {
				
				System.out.println( RobotinMaailma.annaSijainti() );
				System.out.println( RobotinMaailma.annaNaapuri( suunta ) );
				if ( (RobotinMaailma.annaNaapuri(suunta) == null) 
						&& (RobotinMaailma.annaSijainti().voikoSuuntaanEdeta(suunta)) ) {
					//eli jos naapuria ei tunneta (eli siellä ei ole käyty) 
					//mutta sinne voi edetä, sen laskurin katsotaan olevan 0 
					RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] = 0;
				}
				else {
					RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] = RobotinMaailma
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

	public int annaNaapurienMaara(RobotinRuutu keskiRuutu) {
		int naapurit = 0;
		for (int a = 0; a < 3; a++) { 
			if (robo.voikoSuuntaanEdeta( a )) {
				naapurit++;
			}
		}
		return naapurit;
		// metodi laskee ruudun etenemiskelpoisten naapurien määrän
	}

	public boolean onkoRisteys(RobotinRuutu tamaruutu) {
		if (annaNaapurienMaara(tamaruutu) > 2) {
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
		for (int i = 0; i < 3; i++) {
			if (RobotinMaailma.annaSijainti().naapurienLaskurit[i] >= 2) {
				suunnassaOnLiikaaKaynteja++;
			}
		}
		if (suunnassaOnLiikaaKaynteja == 3) {
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
		int ulossuunta;
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
		}
		//^tämä siis palauttaa negatiivisen luvun jos se ei löydä mitään.
		//0-arvoista suuntaa suositaan.
		//Jos 0- tai 1- arvoisia ei löydy yhtäkään, ollaan maalissa tai kusessa.
		if (onkoUmpikuja() == false
				&& RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] == 1 
				&& onkoRisteys(RobotinMaailma.annaNaapuri(suunta))) {
			int uusiSuunta = arvoEriSuunta(suunta);
			if (! RobotinMaailma.annaSijainti().voikoSuuntaanEdeta( uusiSuunta )) {
				teeSuuntaPaatos();
			}
			//^testataan vielä voiko uuteen suuntaan edetä, 
			//jos ei niin kutsutaan tätä metodia uudestaan (rekursio)
			else {
				return uusiSuunta;
				//^jos voi niin mennään sinne.
			}
		}
		return suunta;
	}

	public static void main (String[] args) {
		System.out.println("Testing more");

	}

}
