public class Sektori {

    private Nappula[] nappulat;
    private int moneskoMerkki;

    public Sektori(Nappula[] nappulat1, int koko) {
	nappulat = new Nappula[koko];
	for (int i = 0; i < nappulat1.length; i++) {
	    nappulat[i] = nappulat1[i];
	}

    }

    public Sektori(int koko) {
	nappulat = new Nappula[koko];
    }

    public int kerroNappulat() {
	int nappulat = 0;
	for(int i = 0; i < this.nappulat.length; i++) {
	    if (this.nappulat[i] != null)
		nappulat++;
	}
	return nappulat;
    }

    public Nappula[] kerroNappulaTaulukko() {
	return this.nappulat;
    }
    public Nappula kerroNappula() {
	int apuindeksi;
	if (this.kerroNappulat() == 0) {
	    return null;
	}
	else {
	    apuindeksi = this.kerroNappulat() - 1;
	}
	return nappulat[apuindeksi];
    }

    public String toString() {
	String esitys = "";
	for(int i=0;i<this.kerroNappulat();i++) {
	    if(nappulat[i].kerroVari() == 0) {
		esitys = esitys + "0";
	    }
	    if(nappulat[i].kerroVari() == 1) {
		esitys = esitys + "1";
	    } 
	} 
	return esitys;

    }



    public Nappula poistaNappula() {
	int apuindeksi;
	if (this.kerroNappulat() == 0) {
	    return null;
	}
	else {
	    apuindeksi = this.kerroNappulat() - 1;
	}
	Nappula apunappula = this.nappulat[apuindeksi];
	this.nappulat[apuindeksi] = null;
	return apunappula;
    }

    public boolean lisaaNappula (Nappula nappula, int sektori) {
	if (this.kerroNappulat() == this.nappulat.length)
	    return false;
	else {
	    this.nappulat[this.kerroNappulat()] = nappula;
	    this.kerroNappula().asetaSijainti(sektori);
	    return true;
	}
    }


}
