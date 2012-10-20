import java.util.Arrays;
import java.util.Random;

public class Algoritmi {

	private Random kone = new Random();

	/** luottolähteemme
	 * 
	http://www.astrolog.org/labyrnth/algrithm.htm
	http://en.wikipedia.org/wiki/Maze_solving_algorithm

	 */
	
	/**
	 * 
	 * Koodin oletukset: 
	 * 1. on olemassa RobotinMaailma.java, jonne talletetaan ruudut joissa on käyty ja niiden tiedot.
	 * 2. on olemassa RobotinRuutu.java jonka oliot kootaan robotin keräämien tietojen pohjalta ja lisätään RobotinMaailmaan.
	 * 3. on olemassa OmaRobotti.java (vast) joka käyttää tämän luokan metodeja (tai metodeja siirretään sinne, miten vain on järkevää).
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
				antaa RobonMaailmasta naapuriruudun suunnassa "suunta". Algoritmi huomioi sen jos ruutua ei vielä RobonMaailmassa ole.
		
		int RobotinRuutu.annaLaskurinArvo()				
				antaa laskurin arvon

	Tarvittava attribuutti: 
		
		RobotinRuutu robo.sijaintiRuutu
		 	OmaRobotissa sijaitseva tieto tämänhetkisestä sijainnista. käyttää seuraavaa metodia

						RobotinRuutu RobonMaailma.annaRuutu(RobotinRuutu tamaRuutu)
												

	algo viittaa robon sijaintiin vain attribuutilla. 
	*/
	
	
	/** alla vielä luokan vaatimia mitkä varmaankin kuuluvat muualle:
	*/
	
	private RobotinRuutu edellinenRuutu; 
	//^ tämä kuuluu ehkä RobonMaailmalle -Johannes
	
	boolean[] eteneminenSuunnittain = new boolean[4]; 
	//^tämä on oikeastaan RobotinRuudun ominaisuus. Se on boolean-taulukko, jonka arvo kertoo voiko suuntaan (indeksi 0-3) edetä.
	
	robo.RobotinMaailma.lisaaLaskuria(this.tamaRuutu);
	//^ RobotinRuudulla tai RobotinMaailmassa on käyntilaskuri jota kasvatetaan aina kun ko. ruutuun mennään. luokka oikeastaan ei käytä tätä suoraan.
	
	int[] naapurienLaskurit = new int[4];
	//^tämä taulukko voisi olla RobotinRuudussa. Käyttäjä on tämän luokan tutkaile()-metodi.
	
	//robotissa tehdään esim. seuraava temppu: 
		//edellinenruutu = nykyinenruutu
		//nykyinenruutu = uusiruutu 
		//-Johannes
	
	


	
	




	public void pyorahda() {
		//Tämä metodi tutkii naapurustostaan mihin suuntiin voi mennä.



		for (int i = 0; i < 4; i++) {

			RobotinRuutu.eteneminenSuunnittain[robo.annaSuunta()] = robo.voiEdeta();
			robo.kaannyOikealle();	

		}

	}

	public int[] tutkaile() {
		int suunta;

		//alla on for-lause joka käy kaikki ilmansuunnat läpi ja tallettaa taulukkoon
		for (suunta = 0; suunta < 4; suunta++) {

			if (robo.sijaintiRuutu.eteneminenSuunnittain[suunta] = true) {

				if (RobotinMaailma.annaNaapuri(suunta) == null 
						&& robo.sijaintiRuutu.eteneminenSuunnittain[suunta] == true) {

					//eli jos naapuria ei tunneta (eli siellä ei ole käyty) 
					//mutta sinne voi edetä, sen laskurin katsotaan olevan 0 
					//vaatii metodin RobotinMaailma.annaNaapuri(suunta)
	
					/**
					 * sijaintiRuutu tai vast. attribuutti tarvitaan robolle. kts. alussa.
					*/

					naapurienLaskurit[suunta] = 0;

				}
				
				else {
					naapurienLaskurit[suunta] = RobotinMaailma
							.annaNaapuri(suunta)
							.annaLaskurinArvo();
					//muutoin kysytään RobonMaailmalta ruudun laskurin arvo
				}


			}
			else if (robo.sijaintiRuutu.eteneminenSuunnittain[suunta] == false) {
				naapurienLaskurit[suunta] = 5;
				//seinää edustaa käyntilaskurin luku 5
			}
		}
		//palautetaan taulukko, jossa indeksi (0-3) 
		//on ilmansuunta ja sen arvo on naapuriruudun laskurin arvo
		return naapurienLaskurit; 
	}



	public int annaNaapurienMaara(RobotinRuutu keskiRuutu) {
		int naapurit;
		for (int a = 0; a < 3; a++) { 
			if (keskiRuutu.eteneminenSuunnittain[a]) {
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
		// käyttää naapurimäärälaskuria määrittäessään, onko parametriruutu risteys
		// risteys <=> yli 2 naapuria
	}

	public boolean onkoUmpikuja() {
		int naapurienLaskurit[] = tutkaile(); 
		//haetaan taulukko tutkaile() -metodilta. tutkaile() käyttää aina tämänhetkistä sijaintiruutua, siksi metodilla ei parametria.
		
		int liikaa;

		for (int i = 0; i > 3; i++) {

			if (naapurienLaskurit[i] >= 2) {
				liikaa++;
			}
		}
		if (liikaa == 3) {
			return true;
		}
		
		else return false;

		//ruutu on "umpikuja" jos tasan kolmen naapurin käyntilaskurit 2 tai yli (seinät = 5)
		//jos 4 naapurilla >=2, ollaan alussa, maalissa tai kusessa (umpikujassa, jossa ollaan jo käyty).
		//tämä metodi siis käsittelee sekä seiniä että käytävien käyntilaskureita int:einä.
	}



	public int arvoEriSuunta(int nytsuunta) {
		int ulossuunta = kone.nextInt(4);
		
		if (ulossuunta == nytsuunta) {
			arvoEriSuunta(nytsuunta);
			}
		
		else return ulossuunta;
		//tämä metodi antaa suunnan, joka on eri kuin parametri. sisältää kivan rekursion.
	}

	public int teeSuuntaPaatos() {
		int suunta;
		int[] naapurienLaskurit = tutkaile();	
		//haetaan taas naapurien käyntilaskurit tutkailijalta

		int a = Arrays.binarySearch(naapurienLaskurit, 0);
		//etsii taulukosta sellaisen indeksin (eli suunnan) jonka arvo (eli käyntilaskuri) on nolla
		if (a >= 0) {
			//jos löytyy 0 tai suurempi indeksi, sijoitetaan
			suunta = a;
		}

		else {
			int b = Arrays.binarySearch(naapurienLaskurit, 1);
			//muuten etsii taulukosta sellaisen indeksin (eli suunnan) jonka arvo (eli käyntilaskuri) on yksi
			if (b >= 0) {
				//jos löytyy 0 tai suurempi indeksi, sijoitetaan
				suunta = b;
			}
		}

		//binarySearch siis palauttaa negatiivisen luvun jos se ei löydä mitään.
		//Jos 0- tai 1- arvoisia ei löydy yhtäkään, ollaan maalissa tai kusessa.
		
		if (onkoUmpikuja() == false
				&& naapurienLaskurit[suunta] == 1 
				&& onkoRisteys(RobonMaailma.annaNaapuri(suunta))) {
		
			int uusisuunta = arvoEriSuunta(suunta);
			
			/**jos ruutu jossa ollaan nyt EI ole umpikuja (kts. umpikujametodi)
			*JA
			*"suunta"-muuttujaan sijoitetussa suunnassa on ruutu joka on risteys 
			*JA
			*tuon risteyksen laskurin arvo on 1
			*arvotaan uusi suunta joka on eri kuin nykyinen.
			*
			*kts. Tremaux:n algoritmin idea.
			*/
			
			
			
		
			if (eteneminenSuunnittain[uusisuunta] == false) {
				teeSuuntaPaatos();
			}
			//^testataan vielä voiko uuteen suuntaan edetä, jos ei niin kutsutaan tätä metodia uudestaan (rekursio)
			else if (eteneminenSuunnittain[uusisuunta] == true) {
				return uusisuunta;
				//^jos voi niin mennään sinne.
			}
		}

		else 

		return suunta;

	/**
		 * binäärihaku palauttaa negatiivisen luvun, jos se ei löytänyt mitään
		 * jos se löysi, se palauttaa _muistaakseni_ ensimmäisen täsmäävän
		 * alkion indeksin
		 * kts. Java API
		 * -Matias
		 * 
		 * Muokattu - Johannes
		 */
	}



	public static void main (String[] args) {
		System.out.println("Testing more");

	}

}
