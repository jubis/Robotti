import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class RobotinMaailma {
	private static Map <Point, RobotinRuutu> ruudut = 
			new HashMap <Point,RobotinRuutu>();
	private static Point nykyinenRuutu;

	
	
	public static void siirry( int suunta ) {			
		Point uusiRuutu = nykyinenRuutu;
		
		/**
		 * Tähän ei tarvitse switch rakennetta, koska 
		 * tapauksessa 0 parametri on 0, tapauksessa 1 parametri on 1 jne.
		 * eli muuttujan suunta voi suoraan antaa metodikutsuksi
		 */
		switch (suunta){
		case 0:
			//mihin tätä metodikutsua käytetään
			//tai pitäisikö arvo tallentaa johonkin?
			annaNaapuriPiste(0);
			break;
		case 1:
			annaNaapuriPiste(1);
			break;
		case 2:
			annaNaapuriPiste(2);
			break;
		case 3:
			annaNaapuriPiste(3);
			break;
		}
		
		/**
		 * uusiRuutu on Point-tyyppinen ja ruudut-mapissa 
		 * Point on avain eikä arvo
		 * Ei voi siis käyttää metodia containsValue, 
		 * koska uusiRuutu ei ole arvo
		 */
		if (ruudut.containsValue(uusiRuutu)){
			ruudut.get(uusiRuutu);//mihin tässä haettua arvoa käytetään?
		}else {
			ruudut.put(uusiRuutu, new RobotinRuutu());
		}
		System.out.println(nykyinenRuutu);
		nykyinenRuutu = uusiRuutu;
		System.out.println(nykyinenRuutu);
		
		//Muuttaa nykyiseksi ruuduksi sen ruudun, johon siirrytään
		//*Huom* Tarvittaessa luo uuden ruudun, jos paikalla ei vielä ole sitä
	}
	
	public static RobotinRuutu annaSijainti() {
		return ruudut.get(nykyinenRuutu);
		//palauttaa nykyisen ruudun
	}
	
	public static Point annaNaapuriPiste (int suunta) {
		Point naapuri = nykyinenRuutu;
		switch(suunta){
		case Olo2Robotti.POHJOINEN:
			naapuri.translate(0, 1);
			break;
		case Olo2Robotti.ITA:
			naapuri.translate(1, 0);
			break;
		case Olo2Robotti.ETELA:
			naapuri.translate(0, -1);
			break;
		case Olo2Robotti.LANSI:
			naapuri.translate(-1, 0);
			break;
		default:
			return null;
		}	
		return naapuri;
	}	
		
	
	
	public static RobotinRuutu annaNaapuri( int suunta ) {
		return ruudut.get(annaNaapuriPiste(suunta));
		
		//Antaa RobonMaailmasta naapuriruudun suunnassa "suunta". 
		//Palauttaa null, jos suunnassa ei ole tunnettua ruutua.
	}
	
	
	
}
