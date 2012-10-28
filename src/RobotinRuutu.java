public class RobotinRuutu {
	
	private int laskuri;
	/** 
	 * Attribuutteihin ainakin:
	 *	- laskuri
	 *	- tietorakenne, josta voi hakea suunnan avulla, 
	 *		voiko siihen suuntaan edetä tästä ruudusta
	 * 
	 */
	
	public int[] naapurienLaskurit = new int[4];
	
	
	
	public int annaLaskurinArvo() {
		return this.laskuri;
		//palauttaa ruudun laskurin arvon
	}
	
	public void lisaaLaskuria() {
		this.laskuri == this.laskuri++;
		//tallentaa tiedon laskarin lisämisestä oikeaan ruutuun
	}
	
	public void asetaVoikoEdeta( int suunta, boolean voikoEdeta ) {
		//tallentaa ruudun tietoihin, mihin suuntiin tästä ruudusta pääsee
	}
	
	boolean voikoSuuntaanEdeta( int suunta ) {
		//palauttaa, voiko tästä ruudusta kulkea annettuun suuntaa
	}
}
