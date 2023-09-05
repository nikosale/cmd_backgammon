public class VirheellinenSiirto extends Exception {

    private String kuvausviesti;
    private Nappula nappula;
    private int silmaluku1;
    private int silmaluku2;

    public VirheellinenSiirto(String kuvausviesti) {
	this.kuvausviesti = kuvausviesti;
	//	this.nappula = nappula;
	//	this.silmaluku1 = silmaluku1;
	//	this.silmaluku2 = silmaluku2;
    }

    public String kerroKuvausViesti() {
	return this.kuvausviesti;
    }

    /*
    public Nappula kerroNappula() {
	return this.nappula;
    }

    public int kerroSilmaluku1() {
	return this.silmaluku1;
    }

    public int kerroSilmaluku2() {
	return this.silmaluku2;
    }
    */

}
