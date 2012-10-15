


import java.util.Arrays;

public class Algoritmi {
	
	/*Alla oleva on karkea hutaisu, jossa on tavoiteltu oikeaa ideaa mutta kaikki muu on melko väärin. 
	* Koodi olettaa että on olemassa RobotinMaailma.java, 
	* jonne talletetaan ruudut joissa on käyty ja niiden tiedot.
	*
	*tämä algoritmi on siis kaikki mitä tehdään ennen askelta
	*ts. robotin päätöksentekoprosessi.
	 */

	private Ruutu edellinenRuutu; 
	//^ tämä voisi oikeastaan kuulua robotille.
	
	//robotissa tehdään esim. seuraava temppu: 
	//edellinenruutu = nykyinenruutu
	//nykyinenruutu = uusiruutu
	
	
	robo.ruutu.lisaaLaskuria();
	//^ ruudulla (tai RobotinMaailmassa) on laskuri jota kasvatetaan aina kun
	//ko. ruutuun mennään. Tämäkin rivi voi ehkä olla robotissa.

	public void pyorahda() {
		//Tämä metodi tutkii naapurustosta mihin suuntiin voi mennä.
		
		boolean voiEdetaPohjoiseen, 
		voiEdetaItaan, voiEdetaEtelaan,
		voiEdetaLanteen;

		for (int i = 0; i < 4; i++) {
			boolean voikoEdeta = robo.annaSuunta().voiEdeta();
			robo.kaannyOikealle();	

			boolean[] eteneminenSuunnittain = new boolean[4]; 
			//^tämä taas on oikeastaan ruudun ominaisuus
			
			
			/**
			 * oisko tähän parempi suoraan 
			 * ilman switch- tai if-lauseita pelkästään:
			 * ###
			 * eteneminenSunnittain[ robo.annaSuunta() ] = voikoEdeta();
			 * ###
			 */
			
			
			switch(robo.annaSuunta()) {
			case 0 : { 
				//eli case 0 tarkoittaa että robo katsoo pohjoiseen

				if(voikoEdeta) {
					eteneminenSuunnittain[0]= true;
					
				}
				else eteneminenSuunnittain[0] = false;
			}
			case 1 : {
				if(voikoEdeta) {
					eteneminenSuunnittain[1]= true;
				}
				else eteneminenSuunnittain[1] = false;
			}
			case 2 : {
				if(voikoEdeta) {
					eteneminenSuunnittain[2]= true;
				}
				else eteneminenSuunnittain[2] = false;
			}
			case 3 : {
				if(voikoEdeta) {
					eteneminenSuunnittain[3]= true;
				}
				else eteneminenSuunnittain[3] = false;
			}
			}

		}
	
		public int[] tutkaile() {
		int[] naapurienLaskurit = new int[4];
		int aaa;
		
		
		
		//alla on for-lause joka käy kaikki ilmansuunnat l�pi ja tallettaa taulukkoon
		for (aaa = 0; aaa < 4; aaa++) {
	
		if (robo.ruutu.eteneminenSuunnittain[aaa] = true) {
		
				if (RobotinMaailma.annaRuutu(/*suunnassa aaa*/) == null 
						&& robo.ruutu.eteneminenSuunnittain[aaa] == true) {
			
					//eli jos naapuria ei tunneta (eli siellä ei ole käyty) mutta sinne voi edetä, 
					//sen laskurin katsotaan olevan 0 
			
					//johonkin tarvitaan metodi joka antaa RobonMaailmasta naapuriruudun suunnassa "aaa"
					
					naapurienLaskurit[aaa] = 0;
				
				}
				else naapurienLaskurit[aaa] = RobotinMaailma.annaRuutu(/*suunnassa aaa*/).annaLaskurinArvo();
				//muutoin kysytään RobonMaailmalta ruudun laskurin arvo
		
		}
		}
		return naapurienLaskurit; //palautetaan taulukko, jossa indeksi (0-3) on ilmansuunta ja sen arvo on naapuriruudun laskurin arvo
		}
		
	public int teeSuuntaPaatos() {
		int[] naapurienLaskurit = tutkaile();	
	int a = Arrays.binarySearch(naapurienLaskurit, 0);
		if (a) {
		return a;}
	int b = Arrays.binarySearch(naapurienLaskurit, 1);
		//pulma: miten tarkastaa löytääkö binary mitään? int: lle kun ei voi tehdä true/false tarkastelua 
	}
	
	
	}
	public static void main(String[] args) {


	}

}
