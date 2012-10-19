import java.util.Arrays;
import java.util.Random;

public class Algoritmi {

	private Random kone = new Random();

	/**Alla oleva koodi on vielä karkeahkoa.
	 * 
	 * Koodi olettaa että on olemassa RobotinMaailma.java, 
	 * jonne talletetaan ruudut joissa on käyty ja niiden tiedot.
	 *
	 *tämä algoritmi on siis kaikki mitä tehdään ennen askelta
	 *ts. robotin päätöksentekoprosessi. -Johannes
	 */ 

	private RobotinRuutu edellinenRuutu; 
	//^ tämä voisi oikeastaan kuulua robotille. -Johannes

	//robotissa tehdään esim. seuraava temppu: 
	//edellinenruutu = nykyinenruutu
	//nykyinenruutu = uusiruutu -Johannes

	boolean[] eteneminenSuunnittain = new boolean[4]; 
	//^tämä on oikeastaan ruudun ominaisuus, pitää ajallaan siirtää sinne -Johannes

	robo.maailma.lisaaLaskuria( this.tamaRuutu );
	//^ ruudulla (tai RobotinMaailmassa) on laskuri jota kasvatetaan aina kun
	//ko. ruutuun mennään. -Johannes

	public void pyorahda() {
		//Tämä metodi tutkii naapurustosta mihin suuntiin voi mennä.



		for (int i = 0; i < 4; i++) {

			eteneminenSuunnittain[robo.annaSuunta()] = robo.voiEdeta();
			robo.kaannyOikealle();	

			/**
			 * oisko tähän parempi suoraan 
			 * ilman switch- tai if-lauseita pelkästään:
			 * ###
			 * eteneminenSunnittain[ robo.annaSuunta() ] = voikoEdeta();
			 * ### 
			 * -Matias
			 */

			/**
			 * Poistin switchin, nyt aloittaa menosuunnasta ja käy 4 suuntaa läpi
			 * -Johannes
			 */
		}

	}

	public int[] tutkaile() {

		int[] naapurienLaskurit = new int[4];
		//^^tämäkin taulukko voisi olla Ruudussa. -Johannes

		int suunta;



		//alla on for-lause joka käy kaikki ilmansuunnat läpi ja tallettaa taulukkoon
		for (suunta = 0; suunta < 4; suunta++) {

			if (robo.ruutu.eteneminenSuunnittain[suunta] = true) {

				if (RobotinMaailma.annaNaapuri(suunta) == null 
						&& robo.ruutu.eteneminenSuunnittain[suunta] == true) {

					//eli jos naapuria ei tunneta (eli siellä ei ole käyty) 
					//mutta sinne voi edetä, sen laskurin katsotaan olevan 0 

					//johonkin tarvitaan metodi joka antaa RobonMaailmasta 
					//naapuriruudun suunnassa "suunta"

					naapurienLaskurit[suunta] = 0;

				}
				//muutoin kysytään RobonMaailmalta ruudun laskurin arvo
				else {
					naapurienLaskurit[suunta] = RobotinMaailma
							.annaRuutu(/*suunnassa aaa*/)
							.annaLaskurinArvo();
				}


			}
			else if (robo.ruutu.eteneminenSuunnittain[suunta] == false) {
				naapurienLaskurit[suunta] = 5;
				//seinää edustaa käyntilaskurin luku 5
			}
		}
		//palautetaan taulukko, jossa indeksi (0-3) 
		//on ilmansuunta ja sen arvo on naapuriruudun laskurin arvo
		return naapurienLaskurit; 
	}



	public int annaNaapurienMaara(Ruutu tamaruutu) {
		int naapurit;
		for (int a = 0; a < 3; a++) { 
			if (tamaruutu.eteneminenSuunnittain[a]) {
				naapurit++;
			}
		}
		return naapurit;
		//metodi laskee ruudun etenemiskelpoisten naapurien määrän
	}

	public boolean onkoRisteys(Ruutu tamaruutu) {

		if (annaNaapurienMaara(tamaruutu) > 2) {
			return true;
		}
		else return false;
		//käyttää naapurimäärälaskuria määrittäessään, onko parametriruutu risteys
		// risteys <=> yli 2 naapuria
	}

	public boolean onkoUmpikuja(RobotinRuutu tamaruutu) {
		int naapurienLaskurit[] = tutkaile(); //haetaan taulukko tutkailumetodilta
		int noGo;

		for (int i = 0; i > 3; i++) {

			if (naapurienLaskurit[i] >= 2) {
				noGo++;
			}
		}
		if (noGo == 3) {
			return true;
		}
		
		else return false;

		//ruutu on "umpikuja" jos tasan kolmen naapurin käyntilaskurit 2 tai yli (seinät = 5)
		//jos 4 naapurilla >=2, ollaan alussa, maalissa tai kusessa.
	}

	/** luottolähteemme
	 * 
	http://www.astrolog.org/labyrnth/algrithm.htm
	http://en.wikipedia.org/wiki/Maze_solving_algorithm

	-Johannes
	 */

	public int arvoEriSuunta(int nytsuunta) {
		int ulossuunta = nytsuunta + (kone.nextInt(3) + 1);
		return ulossuunta;
		//tämä metodi antaa suunnan, joka ei ole sama kuin parametrina annettu
	}

	public int teeSuuntaPaatos() {
		int suunta;
		int[] naapurienLaskurit = tutkaile();	

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

		if (onkoUmpikuja(RobonMaailma.annaRuutu(/*tama*/)) == false
				&& naapurienLaskurit[suunta] == 1 
				&& onkoRisteys(RobonMaailma.annaNaapuri(suunta))) {
			/**jos ruutu jossa ollaan nyt EI ole umpikuja (kts. umpikujametodi)
			*JA
			*"suunta"-muuttujaan sijoitetussa suunnassa on ruutu joka on risteys 
			*JA
			*tuon risteyksen laskurin arvo on 1
			*arvotaan uusi suunta joka on eri kuin nykyinen.
			*/
			int uusisuunta = arvoEriSuunta(suunta);
			
		
			if (eteneminenSuunnittain[uusisuunta] == false) {
				teeSuuntaPaatos();
			}
			//^testataan vielä voiko uuteen suuntaan edetä, jos ei niin kutsutaan tätä metodia uudestaan
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
