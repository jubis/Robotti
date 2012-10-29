import java.util.Arrays;

public class RobotinRuutu {
	
	private int laskuri;
	private boolean[] avoimetSuunnat = new boolean[4];
	/** 
	 * Attribuutteihin ainakin:
	 *	- laskuri
	 *	- tietorakenne, josta voi hakea suunnan avulla, 
	 *		voiko siihen suuntaan edetä tästä ruudusta
	 * 
	 */
	
	public int[] naapurienLaskurit = new int[4];
	
	public RobotinRuutu() {
		Arrays.fill( this.avoimetSuunnat, false );
	}
	
	public int annaLaskurinArvo() {
		return this.laskuri;
		//palauttaa ruudun laskurin arvon
	}
	
	public void lisaaLaskuria() {
		this.laskuri++;
		//tallentaa tiedon laskarin lisämisestä oikeaan ruutuun
	}
	
	public void asetaVoikoEdeta( int suunta, boolean voikoEdeta ) {
		this.avoimetSuunnat[ suunta ] = voikoEdeta;
		//tallentaa ruudun tietoihin, mihin suuntiin tästä ruudusta pääsee
	}
	
	public boolean voikoSuuntaanEdeta( int suunta ) {
		return this.avoimetSuunnat[ suunta ];
	}
}
