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
	
	private static Map <Point, RobotinRuutu> ruudut;
	
	
	private static Point edellinenRuutu;
	private static Point nykyinenRuutu;
	private static Point uusiRuutu;
	
	
	public static void siirry( int suunta ) {
		ruudut=new HashMap <Point,RobotinRuutu>();
		if (ruudut.containsValue(uusiRuutu)){
		ruudut.get(uusiRuutu);
		}else {
			ruudut.put(uusiRuutu,???);
		}
		uusiRuutu=nykyinenRuutu;

		//Muuttaa nykyiseksi ruuduksi sen ruudun, johon siirrytään
		//*Huom* Tarvittaessa luo uuden ruudun, jos paikalla ei vielä ole sitä
	}
	
	public static RobotinRuutu annaSijainti() {
	
		//palauttaa nykyisen ruudun
	}
	
	public static RobotinRuutu annaNaapuri( int suunta ) {

		
		//Antaa RobonMaailmasta naapuriruudun suunnassa "suunta". 
		//Palauttaa null, jos suunnassa ei ole tunnettua ruutua.
	}
	
	
	
}
