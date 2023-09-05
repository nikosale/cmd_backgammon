import java.util.*;
public class Liike {
    
   
    Backgammon peli;
    ArrayList liikelista = new ArrayList();
    
    public Liike(Backgammon peli) {
	this.peli = peli;
    }

    //Lisää uuden siirron siirtojen listaan
    public void lisaa(Liikkeentallennus liikutus) {
	liikelista.add(liikelista.size(), liikutus);
	
	}
    


  
    //
    public void peruuta(int peruutuslkm) {
	//jos yritetään peruuttaa liian isolla arvolla niin laitetaan arvo sopivaan väliin
	if (peruutuslkm >= liikelista.size()) {
	    if (liikelista.size() == 1) {
		peruutuslkm = 0;
	    }
	    else peruutuslkm = liikelista.size() - 1;
	}
	
	//tämä silmukka kopeloi Backgammon-oliota sopivasta, jotta pelitilanne vastaa sitä, johon halutaan peruuttaa
	for (int i = 0; i < peruutuslkm; i++) {
	    //poistaa viimeisen alkion listasta
	    Liikkeentallennus apu = (Liikkeentallennus) liikelista.remove(liikelista.size() - 1); 
	    int tulo = apu.kerroTulo();
	    int lahto = apu.kerroLahto();
	    peli.asetaVuorossaOlija(apu.kerroVuoro());
	    peli.vaihdaCube(apu.kerroCube());
	    peli.asetaPanos(apu.kerroPanos());
	    peli.asetaTuplanollaus(apu.kerroTupla());
	    peli.asetaNoppa1(apu.kerroNoppa1());
	    peli.asetaNoppa2(apu.kerroNoppa2());
	    this.peli.kerroSektori(tulo).lisaaNappula(this.peli.kerroSektori(lahto).poistaNappula(), tulo);
	     
	}
    }
}   



