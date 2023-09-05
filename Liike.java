import java.util.*;
public class Liike {
    
   
    Backgammon peli;
    ArrayList liikelista = new ArrayList();
    
    public Liike(Backgammon peli) {
	this.peli = peli;
    }

    //Lis�� uuden siirron siirtojen listaan
    public void lisaa(Liikkeentallennus liikutus) {
	liikelista.add(liikelista.size(), liikutus);
	
	}
    


  
    //
    public void peruuta(int peruutuslkm) {
	//jos yritet��n peruuttaa liian isolla arvolla niin laitetaan arvo sopivaan v�liin
	if (peruutuslkm >= liikelista.size()) {
	    if (liikelista.size() == 1) {
		peruutuslkm = 0;
	    }
	    else peruutuslkm = liikelista.size() - 1;
	}
	
	//t�m� silmukka kopeloi Backgammon-oliota sopivasta, jotta pelitilanne vastaa sit�, johon halutaan peruuttaa
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



