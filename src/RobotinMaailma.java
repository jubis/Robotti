import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class RobotinMaailma {
	private static Map <Point, RobotinRuutu> ruudut = 
			new HashMap <Point,RobotinRuutu>();
	private static Point nykyinenRuutu;

	
	
	public static void siirry( int suunta ) {			
		Point uusiRuutu = nykyinenRuutu;
		ruudut.get(annaNaapuriPiste(suunta));
		
		if (!ruudut.containsKey(uusiRuutu)){
			ruudut.put(uusiRuutu, new RobotinRuutu());
		}
		nykyinenRuutu = uusiRuutu;
		
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
