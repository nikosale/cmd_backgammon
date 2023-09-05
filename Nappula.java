public class Nappula {

    private int sijainti;
    private int vari;
    private Backgammon lauta;
    private boolean puolet;
    private int hyppy;
    

 
    public Nappula(int sijainti, int vari, Backgammon lauta) {
	this.sijainti = sijainti;
	this.vari = vari;
	this.lauta = lauta;
    }

    public int kerroSijainti() {
	return this.sijainti;
    }

    public void asetaSijainti(int sektori) {
	this.sijainti = sektori;
    }

    public int kerroBaari() {
	if (vari == 0)
	    return 24;
	else
	    return 25;
    }

    //tämä metodi palauttaa true jos nappulan tämän nappulan liikutus kyseisillä parametreilla onnistuu ja liikuttaa paramtrien mukaisesti
    public boolean liikuta (int silmaluku1, int silmaluku2, int tulosektori) throws VirheellinenSiirto {
	int toisenbaari;
	Nappula apunappula;
	int koti;
	int omabaari;
	if (this.kerroVari() == 1) {

	    koti = 27;
	}
	else {
	    koti = 26;
	}
	if (this.vari == 0) {
	    toisenbaari = 25;
	    omabaari = 24;
	}
	else {
	    toisenbaari = 24;
	    omabaari = 25;
	}

	if (tulosektori < 0 || tulosektori > 27) {
	    throw new VirheellinenSiirto("Anna sektori väliltä 0 - 27.");
	}
	
	//Erikoistuplasilmalukusiirto nollavärille ja ykkösvärille
	if (this.lauta.tulikoTuplat() == true && this.onkoQuadroSiirtoOk(this, silmaluku1, silmaluku2, tulosektori)) {
	    this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
	    if (this.hyppy == 3) {
		this.lauta.nollaaSilmaluku(1);
		this.lauta.nollaaSilmaluku(2);
		this.lauta.nollaaSilmaluku(1);
	    }
	    else {
		this.lauta.nollaaSilmaluku(1);
		this.lauta.nollaaSilmaluku(2);
		this.lauta.nollaaSilmaluku(1);
		this.lauta.nollaaSilmaluku(2);	    
	    }
	    
	    

	    return true;
	}

	//baarista takaisin varsinaiselle kentälle liikutus
	if (this.onkoBaariTyhja(this.vari) == false && this.kerroSijainti() == omabaari) {
	    if ((vari == 0 && tulosektori == silmaluku1 - 1) || (vari == 1 && tulosektori == 24 - silmaluku1)
		|| (vari == 0 && tulosektori == silmaluku2 - 1) || (vari == 1 && tulosektori == 24 - silmaluku2)) {
		
		if (silmaluku1 != 0 && (tulosektori == 24 - silmaluku1 || tulosektori == silmaluku1 -1)) {
		    if (this.onkoBaariSiirtoOk(this, silmaluku1, tulosektori) == true) {
			this.lauta.kerroSektori(tulosektori).lisaaNappula(lauta.kerroSektori(omabaari).poistaNappula(), tulosektori); 
			this.lauta.nollaaSilmaluku(1);

			return true;
		    }
		}
		if (silmaluku2 != 0 && (tulosektori == 24 - silmaluku2 || tulosektori == silmaluku2 - 1)) {
		    if (this.onkoBaariSiirtoOk(this, silmaluku2, tulosektori) == true) {
			this.lauta.kerroSektori(tulosektori).lisaaNappula(lauta.kerroSektori(omabaari).poistaNappula(), tulosektori); 
			this.lauta.nollaaSilmaluku(2);

			return true;
			
		    }

		}



	    }		 
	    else {
		throw new VirheellinenSiirto("Siirto baarista ei onnistunut silmäluvuilla " + silmaluku1 + " ja  " + silmaluku2 + "."); 		
		
		//return false;
	    }
	    
	}
    
 
	
	
	

	//Seuraava if-lause katsoo halutaanko siirtää nopan silmäluvulla 1
	if (silmaluku1 > 0 && this.onkoBaariTyhja(this.kerroVari()) == true  && this.onkoSiirtoOk(this, silmaluku1, tulosektori)) {
	    
	    //nappulan liikutus, jos tulosektori on tyhjä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula() == null) {
		    
		apunappula = this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula();
		this.lauta.kerroSektori(tulosektori).lisaaNappula(apunappula, tulosektori);
		this.lauta.nollaaSilmaluku(1);

		return true;
	    }
		
	    //syötävän nappulan siirto baariin syötäessä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() != this.kerroVari()) {
		this.lauta.kerroSektori(toisenbaari).lisaaNappula(this.lauta.kerroSektori(tulosektori).poistaNappula(), toisenbaari);
	    }
	   
	    //liikutus, jos sektorissa omia nappuloita yksi tai enemmän
	    this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
	    this.lauta.nollaaSilmaluku(1);

	    return true;
	}
	    




	//tämä metodi siirtää nopan silmäluvulla 2
	if (silmaluku2 > 0 && this.onkoBaariTyhja(this.kerroVari()) == true && this.onkoSiirtoOk(this, silmaluku2, tulosektori)) {
	    
	    //liikutus, jos sektori tyhjä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula() == null) {
		this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
		this.lauta.nollaaSilmaluku(2);

		return true;
	    }

	    //syöminen
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() != this.kerroVari()) {
		this.lauta.kerroSektori(toisenbaari).lisaaNappula(this.lauta.kerroSektori(tulosektori).poistaNappula(), toisenbaari);
		this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
	    this.lauta.nollaaSilmaluku(2);

	    return true;
	    }

	    //liikutus, jos sektorissa omia nappuloita yksi tai enemmän
	    this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
	    this.lauta.nollaaSilmaluku(2);

	    return true;

	}

	
	//tuplasiirtoliikutus
	if (this.onkoBaariTyhja(this.kerroVari()) == true && this.onkoTuplaSiirtoOk(this, silmaluku1, silmaluku2, tulosektori)) {
	    
	    //siirto, jos tulosektori tyhjä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula() == null) {
		this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
	    this.lauta.nollaaSilmaluku(1);
	    this.lauta.nollaaSilmaluku(2);

	    return true;
	    }

	    //syöminen
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() != this.kerroVari()) {
		this.lauta.kerroSektori(toisenbaari).lisaaNappula(this.lauta.kerroSektori(tulosektori).poistaNappula(), toisenbaari);
	    }

	    //liikutus, jos sektorissa oman värisiä nappuloita 0-4
	    this.lauta.kerroSektori(tulosektori).lisaaNappula(this.lauta.kerroSektori(this.kerroSijainti()).poistaNappula(), tulosektori);
	    this.lauta.nollaaSilmaluku(1);
	    this.lauta.nollaaSilmaluku(2);

	    return true;
	}
	else {
	    if (this.kerroVari() != this.lauta.kerroVuorossaOlija()) {
		throw new VirheellinenSiirto("Yritit siirtää väärän väristä nappulaa.");
	    }

	    if (tulosektori == koti && this.lauta.onkoKaikkiKotona(this.kerroVari()) == false) {
		throw new VirheellinenSiirto("Kaikki nappulat eivät ole kotineljänneksellä.");
	    }
	    if (this.lauta.kerroVuorossaOlija() == 0) {
	    throw new VirheellinenSiirto("Siirto sektorista " + (this.kerroSijainti() + 1) + " sektoriin " + (tulosektori + 1) + " ei ole mahdollinen silmäluvuilla " +  silmaluku1 + " ja " + silmaluku2 + ".");
	    }
	    if (this.lauta.kerroVuorossaOlija() == 1) {
	    throw new VirheellinenSiirto("Siirto sektorista " + (24 - this.kerroSijainti()) + " sektoriin " + (24 - tulosektori) + " ei ole mahdollinen silmäluvuilla " +  silmaluku1 + " ja " + silmaluku2 + ".");
	    }


	    return false;
	}
    }
    


    
    public boolean onkoLiikeOk(int silmaluku1, int silmaluku2, int tulosektori, TietokonePelaaja pelaaja) {
	int toisenbaari;
	Nappula apunappula;
	int koti;
	int omabaari;
	if (this.kerroVari() == 1) {

	    koti = 27;
	}
	else {
	    koti = 26;
	}
	if (this.vari == 0) {
	    toisenbaari = 25;
	    omabaari = 24;
	}
	else {
	    toisenbaari = 24;
	    omabaari = 25;
	}


	//Erikoistuplasilmalukusiirto nollavärille ja ykkösvärille
	if (this.lauta.tulikoTuplat() == true && this.onkoQuadroSiirtoOk(this, silmaluku1, silmaluku2, tulosektori)) {


	    
	    return true;
	}

	//baarista takaisin varsinaiselle kentälle liikutus
	if (this.onkoBaariTyhja(this.vari) == false && this.kerroSijainti() == omabaari) {
	    
	    if ((vari == 0 && tulosektori == silmaluku1 - 1) || (vari == 1 && tulosektori == 24 - silmaluku1)
		|| (vari == 0 && tulosektori == silmaluku2 - 1) || (vari == 1 && tulosektori == 24 - silmaluku2)) {
		
		if (silmaluku1 != 0 && (tulosektori == 24 - silmaluku1 || tulosektori == silmaluku1 -1)) {
		    if (this.onkoBaariSiirtoOk(this, silmaluku1, tulosektori) == true) {


			return true;
		    }
		}
		if (silmaluku2 != 0 && (tulosektori == 24 - silmaluku2 || tulosektori == silmaluku2 - 1)) {
		    if (this.onkoBaariSiirtoOk(this, silmaluku2, tulosektori) == true) {
			return true;
			}
		}
	    }		 
	    else {			
		return false;
	    }	    
	}    
			
	//Seuraava if-lause katsoo halutaanko siirtää nopan silmäluvulla 1
	if (this.onkoSiirtoOk(this, silmaluku1, tulosektori) && this.onkoBaariTyhja(this.kerroVari()) == true) {
	    
	    //nappulan liikutus, jos tulosektori on tyhjä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula() == null) {
		return true;
	    }
		
	    //syötävän nappulan siirto baariin syötäessä
      	    if (this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() != this.kerroVari()) {
		pelaaja.syominen(true);

	    } 
	    if (this.lauta.kerroSektori(tulosektori).kerroNappulat() == 1 && this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() == 1) {
		pelaaja.suojaus(true);
	    }
	    return true;
	}
	    




	//tämä metodi siirtää nopan silmäluvulla 2
	if (silmaluku2 > 0 && this.onkoSiirtoOk(this, silmaluku2, tulosektori) && this.onkoBaariTyhja(this.kerroVari()) == true) {
	    
	    //liikutus, jos sektori tyhjä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula() == null) {
		return true;
	    }

	    //syöminen
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() != this.kerroVari()) {
		pelaaja.syominen(true);
	    return true;
	    }

	    //liikutus, jos sektorissa omia nappuloita yksi tai enemmän
	    if (this.lauta.kerroSektori(tulosektori).kerroNappulat() == 1 && this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() == 1) {
		pelaaja.suojaus(true);
	    }
	    return true;

	}

	
	//tuplasiirtoliikutus
	if (this.onkoTuplaSiirtoOk(this, silmaluku1, silmaluku2, tulosektori) && this.onkoBaariTyhja(this.kerroVari()) == true) {
	    
	    //siirto, jos tulosektori tyhjä
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula() == null) {
	    return true;
	    }

	    //syöminen
	    if (this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() != this.kerroVari()) {
		pelaaja.syominen(true);
	    }

	    //liikutus, jos sektorissa oman värisiä nappuloita 0-4
	    if (this.lauta.kerroSektori(tulosektori).kerroNappulat() == 1 && this.lauta.kerroSektori(tulosektori).kerroNappula().kerroVari() == 1) {
		pelaaja.suojaus(true);
	    }
	    return true;
	}
	else {
	    if (this.kerroVari() != this.lauta.kerroVuorossaOlija()) {
	    }

	    if (tulosektori == koti && this.lauta.onkoKaikkiKotona(this.kerroVari()) == false) {

	    }		
	   return false;
	}    	
    }
    
    

    public int kerroVari() {
	return this.vari;
    }
    
    public boolean tulo(int tuloSektori, Nappula nappula) {

	
	//nollankotiinsiirtosääntö
	if(tuloSektori == 26 && this.lauta.onkoKaikkiKotona(0) == true) {
	    return true;
	}

	//ykkösenkotiinsiirtosääntö
	if(tuloSektori == 27 && this.lauta.onkoKaikkiKotona(1) == true) {
	    return true;
	}
	
	//liikutus, jos tulosektori tyhjä
	if(this.lauta.kerroSektori(tuloSektori).kerroNappula() == null && tuloSektori != 26 && tuloSektori != 27 ) {
	    return true;
	}
	
	//liikutus, jos tulosektorissa nappuloita 0 - 4 ja nappulat samaa väriä tai liikutus jos sektorissa yksi nappula
	if (tuloSektori != 26 && tuloSektori != 27 
	    && (this.lauta.kerroSektori(tuloSektori).kerroNappulat() >= 0 && 
	    this.lauta.kerroSektori(tuloSektori).kerroNappulat() < 5 &&
	     this.lauta.kerroSektori(tuloSektori).kerroNappula().kerroVari() == nappula.kerroVari()) 
	     || this.lauta.kerroSektori(tuloSektori).kerroNappulat() == 1) 
	    return true;
	else
	    return false;
    }

    public boolean onkoSiirtoOk(Nappula nappula, int silmaluku, int tulosektori) {
	int apuindeksi;

	


	//kotiinsiirtosäännöt nollavärille
	if (tulosektori == 26 && this.tulo(tulosektori, nappula) == true 
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari() && nappula.kerroVari() == 0) {
	    if (nappula.kerroSijainti() == 24 - silmaluku) {
		return true;
	    }
	    if (nappula.kerroSijainti() + silmaluku > 24) {
		apuindeksi = nappula.kerroSijainti() + silmaluku -24;
		for (int i = 0; i<apuindeksi;i++) {
		    if (this.lauta.kerroSektori((nappula.kerroSijainti() - (i+1))).kerroNappulat() > 0) {
			return false;
		    }
		}
		return true;
	    }
		
	}

	//kotiinsiirtosäännöt 1 värille
	if (tulosektori == 27 && this.tulo(tulosektori, nappula) == true 
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari() && nappula.kerroVari() == 1) {
	    if (nappula.kerroSijainti() == silmaluku - 1) {
		return true;
	    }
	    if (nappula.kerroSijainti() < silmaluku - 1) {
		apuindeksi = silmaluku - 1 - nappula.kerroSijainti();
		for (int i = 0; i<apuindeksi;i++) {
		    if (this.lauta.kerroSektori((nappula.kerroSijainti() + (i+1))).kerroNappulat() > 0) {
			return false;
		    }
		}
		return true;
	    }
		
	}

	//baarisiirtosääntö nollavärille
	if (silmaluku > 0 && nappula.kerroSijainti() == 24 && this.tulo(tulosektori, nappula) == true 
	    && this.lauta.kerroSektori(silmaluku - 1).kerroNappulat() == 0 
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari() && nappula.kerroVari() == 0) {
	    return true;
	}
	
	//baarisiirtosääntö ykkösvärille
	if (nappula.kerroSijainti() == 25 && this.tulo(tulosektori, nappula) == true 
	    && this.lauta.kerroSektori(24 - silmaluku).kerroNappulat() == 0 
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari() && nappula.kerroVari() == 0) {
	    return true;
	}

		
	//katsotaan onko siirto tällä silmäluvulla laillinen nollavärille
	if (this.tulo(tulosektori, nappula) == true && silmaluku == tulosektori - nappula.kerroSijainti() 
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari() && nappula.kerroVari() == 0) {
	    
	
    	    return true;
		    	}
	
	//siirto ykkösvärille
	if (this.tulo(tulosektori, nappula) == true && nappula.kerroSijainti() - silmaluku == tulosektori 
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari() && nappula.kerroVari() == 1) {
	     
	
    	    return true;
		    	}


      	else return false;
    }
    
    public boolean onkoQuadroSiirtoOk(Nappula nappula, int silmaluku1, int silmaluku2, int tulosektori) {
	
	

       	
	if (nappula.kerroVari() == 0 && nappula.kerroVari() == this.lauta.kerroVuorossaOlija()) {
	    
	    if( (tulosektori - nappula.kerroSijainti()) % silmaluku1 == 0 
		&& ((tulosektori - nappula.kerroSijainti()) / silmaluku1) <= (2 + this.lauta.kerroJaljellaOlevatTuplat())) {
		hyppy = (tulosektori - nappula.kerroSijainti()) / silmaluku1;
		
		if (hyppy == 3) {
		    
		    if (this.onkoTuplaSiirtoOk(nappula, silmaluku1, silmaluku2, (tulosektori - silmaluku1)) 
			&& this.lauta.kerroSektori(tulosektori - silmaluku1).kerroNappulat() == 0 
			&& this.onkoSiirtoOk(nappula, tulosektori - nappula.kerroSijainti(), tulosektori)) {

			return true;
		    }
		    else return false;
		}
		if (hyppy == 4) {
		    if (this.onkoTuplaSiirtoOk(nappula, silmaluku1, silmaluku2, tulosektori - (2 * silmaluku1)) 
			&& this.lauta.kerroSektori(tulosektori -(silmaluku1)).kerroNappulat() == 0 
			&& this.lauta.kerroSektori(tulosektori -(2 * silmaluku1)).kerroNappulat() == 0 
			&& this.onkoTuplaSiirtoOk(nappula, silmaluku1*2, silmaluku2*2, tulosektori)) {
			return true;
		    }
		}
		
		
		
		
	    } 
	    
	    
	}
	if (nappula.kerroVari() == 1 && nappula.kerroVari() == this.lauta.kerroVuorossaOlija()) {
	    
	    if( (nappula.kerroSijainti() - tulosektori) % silmaluku1 == 0 
		&& ((nappula.kerroSijainti() - tulosektori) / silmaluku1) <= (2 + this.lauta.kerroJaljellaOlevatTuplat())) {
		hyppy = (nappula.kerroSijainti() - tulosektori) / silmaluku1;
		
		if (hyppy == 3) {
		    
		    if (this.onkoTuplaSiirtoOk(nappula, silmaluku1, silmaluku2, (tulosektori + silmaluku1)) 
			&& this.lauta.kerroSektori(tulosektori + silmaluku1).kerroNappulat() == 0 
			&& this.onkoSiirtoOk(nappula, nappula.kerroSijainti() - tulosektori, tulosektori)) {

			return true;
		    }
		    else return false;
		}
		if (hyppy == 4) {
		    if (this.onkoTuplaSiirtoOk(nappula, silmaluku1, silmaluku2, tulosektori + (2 * silmaluku1)) 
			&& this.lauta.kerroSektori(tulosektori + (silmaluku1)).kerroNappulat() == 0 
			&& this.lauta.kerroSektori(tulosektori + (2 * silmaluku1)).kerroNappulat() == 0 
			&& this.onkoTuplaSiirtoOk(nappula, silmaluku1*2, silmaluku2*2, tulosektori)) {
			return true;
		    }
		}
		
		
		
		
	    } 
	    
	    
	}
	return false;
    }

    public boolean onkoTuplaSiirtoOk(Nappula nappula, int silmaluku1, int silmaluku2, int tulosektori) {
	int apuindeksi;
	//kotipesäänsiirto nollavärille
	if (nappula.kerroSijainti() + silmaluku1 < 28 && nappula.kerroSijainti() + silmaluku2 < 28 &&
	    tulosektori == 26 && this.tulo(tulosektori, nappula) == true 
	    && (this.lauta.kerroSektori(nappula.kerroSijainti() + silmaluku1).kerroNappulat() == 0 
		|| this.lauta.kerroSektori(nappula.kerroSijainti() + silmaluku2).kerroNappulat() == 0) 
	    && nappula.kerroVari() == 0
	    && this.lauta.kerroVuorossaOlija() == 0) {
	    if (nappula.kerroSijainti() == 24 - (silmaluku1 + silmaluku2) ) {
		return true;
	    }
	    if (nappula.kerroSijainti() + silmaluku1 + silmaluku2 > 24) {
		apuindeksi = nappula.kerroSijainti() + silmaluku1 + silmaluku2 - 24;
		for (int i = 0; i<apuindeksi;i++) {
		    if (this.lauta.kerroSektori((nappula.kerroSijainti() - (i+1))).kerroNappulat() > 0) {
			return false;
		    }
		}
		return true;
	    }
		
	}

	//kotipesäänsiirto ykkösvärille
	if (tulosektori == 27 && this.tulo(tulosektori, nappula) == true 
	    && (this.lauta.kerroSektori(nappula.kerroSijainti() - silmaluku1).kerroNappulat() == 0 
		|| this.lauta.kerroSektori(nappula.kerroSijainti() - silmaluku2).kerroNappulat() == 0) && nappula.kerroVari() == 1
	    && this.lauta.kerroVuorossaOlija() == nappula.kerroVari()) {
	    if (nappula.kerroSijainti() == (silmaluku1 + silmaluku2) - 1) {
		return true;
	    }
	    if (nappula.kerroSijainti() <  (silmaluku1 + silmaluku2) - 1) {
		apuindeksi = (silmaluku1 + silmaluku2) - 1 - nappula.kerroSijainti();

		for (int i = 0; i<apuindeksi;i++) {
		    if (this.lauta.kerroSektori((nappula.kerroSijainti() + (i+1))).kerroNappulat() > 0) {
			return false;
		    }
		}
		return true;
	    }
		
	}

	//liikutus,jos tulosektorissa nappuloita 0-4 ja samaa väriä tai yksi nappula ja tuplasiirron välisektori tyhjä nollavärille
	if (lauta.kerroSektori(24).kerroNappulat() == 0 && nappula.kerroVari() == 0 && nappula.kerroVari() == this.lauta.kerroVuorossaOlija()) {
	    if (this.tulo(tulosektori, nappula) == true 
		&& (this.lauta.kerroSektori(nappula.kerroSijainti() + silmaluku1).kerroNappulat() == 0 
		    || this.lauta.kerroSektori(nappula.kerroSijainti() + silmaluku2).kerroNappulat() == 0) 
		&& (silmaluku1 + silmaluku2) == tulosektori - nappula.kerroSijainti()) {
		return true;
	}
	}

	//liikutus, jos tulosektorissa nappuloita 0-4 ja samaa väriä tai yksi nappula ja tuplasiirron välisektori tyhjä ykkösvärille
	if (lauta.kerroSektori(25).kerroNappulat() == 0 && nappula.kerroVari() == 1 && this.lauta.kerroVuorossaOlija() == nappula.kerroVari()) {
	    if (this.tulo(tulosektori, nappula) == true && (this.lauta.kerroSektori(nappula.kerroSijainti() - silmaluku1).kerroNappulat() == 0 || this.lauta.kerroSektori(nappula.kerroSijainti() - silmaluku2).kerroNappulat() == 0) && (silmaluku1 + silmaluku2) == nappula.kerroSijainti() - tulosektori) {
		return true;
	    }
	}
	return false;
    }
    
    public boolean onkoBaariTyhja(int vari) {
	if (this.vari == 0) {
	    if (this.lauta.kerroSektori(24).kerroNappulat() == 0)
		return true;
	    else
		return false;
	}
	if (this.vari == 1) { 
	    if (this.lauta.kerroSektori(25).kerroNappulat() == 0)
		return true;
	
	    else
		return false;
	}
	else {
	    return true;
	} 
	  
    }
    
    public boolean onkoBaariSiirtoOk(Nappula nappula, int silmaluku, int tulosektori) {
	if (nappula.kerroVari() == 0 && this.lauta.kerroVuorossaOlija() == nappula.kerroVari()) {
	    if (//onkoSiirtoOk(nappula, silmaluku, tulosektori) == true 
		/*&&*/ this.lauta.kerroSektori(tulosektori).kerroNappulat() == 0) {
		return true;
	    }
	    else 
		return false;
	
	}
	if (nappula.kerroVari() == 1 && this.lauta.kerroVuorossaOlija() == nappula.kerroVari()) {
	    if (//onkoSiirtoOk(nappula, silmaluku, tulosektori) == true 
		/*&&*/  this.lauta.kerroSektori(tulosektori).kerroNappulat() == 0) {
		return true;
	    }
	    else 
		return false;
	}
	else return false;
	
	
	
    }

    public boolean onkoTuplaBaariSiirtoOk(Nappula nappula, int silmaluku1, int silmaluku2, int tulosektori) {
    	if (nappula.kerroVari() == 0 && this.lauta.kerroVuorossaOlija() == nappula.kerroVari()) {
	    if ((this.lauta.kerroSektori(silmaluku1 - 1).kerroNappulat() == 0 || 
		 this.lauta.kerroSektori(silmaluku2 - 1).kerroNappulat()== 0) &&
		/*this.tulo(tulosektori, nappula) == true*/ silmaluku1 + silmaluku2 == tulosektori + 1) {
		return true;   
	    }
	    
	    if (this.lauta.kerroSektori(tulosektori).kerroNappulat() == 0 &&
		(this.lauta.kerroSektori(24 - silmaluku1).kerroNappulat() == 0 ||
		 this.lauta.kerroSektori(24 - silmaluku2).kerroNappulat() == 0)){
		return true;
	    }
	    
	}
	
	if (nappula.kerroVari() == 1 && this.lauta.kerroVuorossaOlija() == nappula.kerroVari()) {
	    if ((this.lauta.kerroSektori(24 - silmaluku1).kerroNappulat()== 0 || 
		 this.lauta.kerroSektori(24 - silmaluku2).kerroNappulat()== 0) &&
		/*this.tulo(tulosektori, nappula) == true*/  24 - (silmaluku1 + silmaluku2) == tulosektori) {
		return true;   
	    }
	    if (vari == 1 && tulosektori == 24 - silmaluku1 - silmaluku2 &&
		this.lauta.kerroSektori(tulosektori).kerroNappulat() == 0 &&
		(this.lauta.kerroSektori(24 - silmaluku1).kerroNappulat() == 0 ||
		 this.lauta.kerroSektori(24 - silmaluku2).kerroNappulat() == 0)){
		return true;  
	    }
	    else
		return false;	
	}
	else return false;       
    }

    

}



    


    
