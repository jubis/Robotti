import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class RobotinMaailma {
	/**
	 * Attribuutteihin ainakin:
	 *	- jonkinlainen tietorakenne, johon ruudut talletetaan
	 *	- tieto nykyisestä ruudusta
	 * 
	 */
	
	// Luo ja alusta Map<Point, RobotinRuutu> tässä
	private static Map <Point, RobotinRuutu> ruudut;
	
	
	private static Point edellinenRuutu;
	private static Point nykyinenRuutu;
	private static Point uusiRuutu;
	
	
	public static void siirry( int suunta ) {
		// et halua alustaa Mappia tässä enää uudestaan
		ruudut=new HashMap <Point,RobotinRuutu>(); 
		
		/**
		 * Tässä kohdassa pitää laskea, mikä uusiRuutu on.
		 * Siinä kannattaa ensin muuttaa nykyistäRuutua Point-olion 
		 * translate-metodin avulla ja 
		 * lopuksi laittaa apumuuttujan arvo uudenRuudun arvoksi.
		 */
		
		/*** Tässä esimerkki ***/
		Point piste = new Point(4,5);
		if( suunta == Olo2Robotti.ITA ) {
			piste.translate( 1, 0 );
		}
		/*** Esimerkki loppuu ***/
		
		
		/**
		 * suunta-parametrin arvoilla voi tutkia suuntaa näin
		 * 
		 * suunta == Olo2Robotti.ITA tai
		 * suunta == Olo2Robotti.LANSI
		 * 
		 */
		
		if (ruudut.containsValue(uusiRuutu)){
			ruudut.get(uusiRuutu);
		}else {
			// tähän voit (kysymysmerkkien tilalle) luoda vaan uuden olion
			// RobotinRuudusta
			ruudut.put(uusiRuutu,???);
		}
		uusiRuutu=nykyinenRuutu; //pitäiskö tän olla toisin päin

		//Muuttaa nykyiseksi ruuduksi sen ruudun, johon siirrytään
		//*Huom* Tarvittaessa luo uuden ruudun, jos paikalla ei vielä ole sitä
	}
	
	public static RobotinRuutu annaSijainti() {
		return ruudut.get(nykyinenRuutu);
		//palauttaa nykyisen ruudun
	}
	
	public static RobotinRuutu annaNaapuri( int suunta ) {
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
		return ruudut.get(naapuri);
		/**
		 * tallennetaan nykyinenSijainti apumuuttujaan
		 * 
		 * Switch-lauseella käydään kaikki suunta-muuttujan arvot läpi
		 * siis Olo2Robobtti.ITA jne
		 * 
		 * siirretään apumuuttujaa oikeeseen suuntaan
		 * 
		 * haetaan apumuuttujan avulla ruudut-mapista oikea RobotinRuutu
		 * ja palautetaan se
		 */
		
		//Antaa RobonMaailmasta naapuriruudun suunnassa "suunta". 
		//Palauttaa null, jos suunnassa ei ole tunnettua ruutua.
	}
	
	
	
}
