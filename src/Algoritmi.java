


import java.util.Arrays;

public class Algoritmi {

	/**Alla oleva koodi on vielä karkeahkoa.
	 * 
	 * Koodi olettaa että on olemassa RobotinMaailma.java, 
	 * jonne talletetaan ruudut joissa on käyty ja niiden tiedot.
	 *
	 *tämä algoritmi on siis kaikki mitä tehdään ennen askelta
	 *ts. robotin päätöksentekoprosessi. -Johannes
	 */ 

	private Ruutu edellinenRuutu; 
	//^ tämä voisi oikeastaan kuulua robotille. -Johannes

	//robotissa tehdään esim. seuraava temppu: 
	//edellinenruutu = nykyinenruutu
	//nykyinenruutu = uusiruutu -Johannes


	robo.ruutu.lisaaLaskuria();
	//^ ruudulla (tai RobotinMaailmassa) on laskuri jota kasvatetaan aina kun
	//ko. ruutuun mennään. Tämäkin rivi voi ehkä olla robotissa. -Johannes

	public void pyorahda() {
		//Tämä metodi tutkii naapurustosta mihin suuntiin voi mennä.

		boolean[] eteneminenSuunnittain = new boolean[4]; 
		//^tämä on oikeastaan ruudun ominaisuus, pitää ajallaan siirtää sinne -Johannes

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

				if (RobotinMaailma.annaRuutu(/*suunnassa aaa*/) == null 
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
		}
		//palautetaan taulukko, jossa indeksi (0-3) 
		//on ilmansuunta ja sen arvo on naapuriruudun laskurin arvo
		return naapurienLaskurit; 
	}


	public boolean onkoRisteys() {
		
	}

	/** luottolähteemme
	 * 
	http://www.astrolog.org/labyrnth/algrithm.htm
	http://en.wikipedia.org/wiki/Maze_solving_algorithm
	
	kertovat, että risteykset vaativat erityiskohtelua, siksi ylläoleva metodi (vielä tyhjä).
	-Johannes
	*/
	
	public int teeSuuntaPaatos() {

		int[] naapurienLaskurit = tutkaile();	

		int a = Arrays.binarySearch(naapurienLaskurit, 0);
		if (a >= 0) {
			return a;
		}
		int b = Arrays.binarySearch(naapurienLaskurit, 1);
		if (b >= 0) {
			return b;
		}

		/**	a on siis ensimmäinen suunta pohjoisesta myötäpäivään sellaiseen ruutuun, jossa käyty 0 kertaa
		 *	b sellaiseen jossa käyty kerran
		 *
		 * tämä ei nyt vielä ota risteyslaskureita huomioon (saavat arvoja > 2)
		/-Johannes
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



	public static void main(String[] args) {
		System.out.println("Testi");

	}

}
