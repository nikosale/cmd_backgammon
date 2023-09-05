public class Liikkeentallennus {
    int lahto;
    int tulo;
    int vari;
    int kuutio;
    int panos;
    int vuoro;
    int cube;
    int noppa1;
    int noppa2;
    int tupla;

    public Liikkeentallennus (int lahto, int tulo, int vuoro, 
			      int kuutio, int panos, int cube, 
			      int noppa1, int noppa2, int tupla) {
	
	this.lahto = lahto;
	this.tulo = tulo;
	this.vuoro = vuoro;
	this.kuutio = kuutio;
	this.panos = panos;
	this.cube = cube;
	this.noppa1 = noppa1;
	this.noppa2 = noppa2;
	this.tupla = tupla;
    }

    public int kerroLahto() {
	return tulo;
    }
    
    public int kerroTulo() {
	return lahto;
    }

    public int kerroVuoro() {
	return vuoro;
    }

    public int kerroKuutio() {
	return kuutio;
    }

    public int kerroPanos() {
	return panos;
    }

    public int kerroCube() {
	return cube;
    }
    public int kerroNoppa1() {
	return noppa1;
    }

    public int kerroNoppa2() {
	return noppa2;
    }

    public int kerroTupla() {
	return tupla;
    }


}
