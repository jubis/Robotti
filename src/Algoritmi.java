import java.util.Arrays;
import java.util.Random;

public class Algoritmi {

	private Random kone = new Random();
	
	
	/**
	 * Algoritmi on siis tietoinen robotista, joka sen omistaa
	 
		
		private final Olo2Robotti robo;
		
		public void asetaRobotti( Olo2Robotti robotti ) {
			this.robo = robotti;
		}
	
	Johannes 28.10: tämä ei olekaan tarpeellinen, jos algo nyt toimii.
	algon viittaukset tehdään RobotinMaailman kautta.
	
	
	*/
	
	
	/** luottolähteemme
	 * 
	http://www.astrolog.org/labyrnth/algrithm.htm
	http://en.wikipedia.org/wiki/Maze_solving_algorithm

	 */
	
	/**
	 * 
	 * Koodin oletukset: 
	 * 1. on olemassa RobotinMaailma.java, jonne talletetaan ruudut joissa on 
	 * 		käyty ja niiden tiedot.
	 * 2. on olemassa RobotinRuutu.java jonka oliot kootaan robotin keräämien 
	 * 		tietojen pohjalta ja lisätään RobotinMaailmaan.
	 * 3. on olemassa OmaRobotti.java (vast) joka käyttää tämän luokan metodeja 
	 * 		(tai metodeja siirretään sinne, miten vain on järkevää).
	 * 4. OmaRobotti perii valmiin Robotti-luokan.
	 * 
	 * koodissa "robo" tarkoittaa OmaRobotti-oliota.
	 *
	 * tämä algoritmi on siis kaikki mitä tehdään ennen askelta
	 * ts. robotin päätöksentekoprosessi.
	 *  -Johannes
	 */ 
	
	
	/**
	 * Robotti-luokkahan tarjoaa meille valmiiksi seuraavat: 
	 *  int annaSuunta()
	 *  Ruutu annaSijainti()
	 *  void kaannyVasemmalle()
	 *  void kaannyOikealle()
	 *  boolean voiEdeta()
	 *  boolean etene()
	*/
	
	
	/**tarvittavat metodit:
	
		RobotinRuutu RobotinMaailma.annaNaapuri(int suunta)		
				antaa RobonMaailmasta naapuriruudun suunnassa "suunta". 
				Algoritmi huomioi sen jos ruutua ei vielä RobonMaailmassa ole.
				
Johannes 28.10: ei tarvita, jos algo voi saada naapurit suoraan R-Maailmalta kuten nyt
		
		
		int RobotinRuutu.annaLaskurinArvo()				
				antaa laskurin arvon
		
Johannes 28.10: Ei tarvita tällä hetkellä koska naapurienLaskurit[] käytetään attribuuttina. 
						Ehkä tarvitaan vielä.
		
		
		void RobotinRuutu.asetaVoikoEdeta( int suunta, boolean voikoEdeta )
				tallentaa ruudun tietoihin, mihin suuntiin tästä ruudusta pääsee
		
Johannes 28.10: en ole varma
		
		boolean voikoSuuntaanEdeta( int suunta )
				palauttaa, voiko tästä ruudusta kulkea annettuun suuntaan
		
Johannes 28.10: tarvitaan vielä.
		
		
		RobotinRuutu robo.annaSijaintiruutu()
		 	OmaRobotissa sijaitseva tieto tämänhetkisestä sijainnista. 
		 	käyttää	seuraavaa metodia

					RobotinRuutu RobonMaailma.annaRuutu(RobotinRuutu tamaRuutu)

Johannes 28.10: on näköjään jo toteutettu RobotinMaailmaan, ei tarvita enää
*/
	
	
	
	public void tutkaile() {
		int suunta;
		/*
		 muutos 28.10:	ei palauta mitään, metodit
		 				käyttävät taulukkoa RobotinRuudusta
		 */
		
		
		//alla on for-lause joka käy kaikki ilmansuunnat läpi 
		//ja tallettaa taulukkoon
		for (suunta = 0; suunta < 4; suunta++) {

			if (RobotinMaailma.annaSijainti().voikoSuuntaanEdeta(suunta)) {

				if (RobotinMaailma.annaNaapuri(suunta) == null 
					&& RobotinMaailma.annaSijainti().voikoSuuntaanEdeta(suunta)) {

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
			if (keskiRuutu.voikoSuuntaanEdeta( a )) {
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
		int ulossuunta = kone.nextInt(4);
		
		if (ulossuunta == nytsuunta) {
			return arvoEriSuunta(nytsuunta);
			}
		
		else return ulossuunta;
		//tämä metodi antaa suunnan, joka on eri kuin parametri. 
		//sisältää kivan rekursion.
	}

	public int teeSuuntaPaatos() {
		int suunta = 0;
		
		//haetaan naapurien käyntilaskurit suoraan ruudulta

		int a = Arrays.binarySearch(RobotinMaailma.annaSijainti().naapurienLaskurit, 0);
		//etsii taulukosta sellaisen indeksin (eli suunnan) 
		//jonka arvo (eli käyntilaskuri) on nolla
		if (a >= 0) {
			//jos löytyy 0 tai suurempi indeksi, sijoitetaan
			suunta = a;
		}

		else {
			int b = Arrays.binarySearch(RobotinMaailma.annaSijainti().naapurienLaskurit, 1);
			//muuten etsii taulukosta sellaisen indeksin (eli suunnan) 
			//jonka arvo (eli käyntilaskuri) on yksi
			if (b >= 0) {
				//jos löytyy 0 tai suurempi indeksi, sijoitetaan
				suunta = b;
			}
		}

		//binarySearch siis palauttaa negatiivisen luvun jos se ei löydä mitään.
		//Jos 0- tai 1- arvoisia ei löydy yhtäkään, ollaan maalissa tai kusessa.
		
		if (onkoUmpikuja() == false
				&& RobotinMaailma.annaSijainti().naapurienLaskurit[suunta] == 1 
				&& onkoRisteys(RobotinMaailma.annaNaapuri(suunta))) {
		
			int uusiSuunta = arvoEriSuunta(suunta);
			
			/**
			
			jos ruutu jossa ollaan nyt EI ole umpikuja (kts. umpikujametodi)
			JA
			"suunta"-muuttujaan sijoitetussa suunnassa on ruutu joka on risteys 
			JA
			tuon risteyksen laskurin arvo on 1
			
			-> arvotaan uusi suunta joka on eri kuin nykyinen.
			
			kts. Tremaux:n algoritmin idea.
			
			*/
			
			
			
		
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

	/**
		 * binäärihaku palauttaa negatiivisen luvun, jos se ei löytänyt mitään
		 * jos se löysi, se palauttaa _muistaakseni_ ensimmäisen täsmäävän
		 * alkion indeksin
		 * kts. Java API
		 * -Matias
		 * 
		 * Muokattu - Johannes
		 * 
		 * API sanoo näin:
		 * 
		 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.
		 * 
		 * 			otettu huomioon
		 * 
		 * The array must be sorted (as by the sort method, above) prior to making this call. 
		 * If it is not sorted, the results are undefined.
		 * 
		 * 			mutta onko tämä paha? sorttaus tuhoaisi informaation.
		 * 
		 * If the array contains multiple elements with the specified value, 
		 * there is no guarantee which one will be found.
		 * 
		 * 			tämä ei kai haittaa, satunnainen sen pitäisi kai ollakin
		 * 
		 * 
		 * Johannes 28.10.
		 */
	}



	public static void main (String[] args) {
		System.out.println("Testing more");

	}

}
