public class TietokonePelaaja {

    private Backgammon peli;
    private int[] siirto = new int[2];
    private int nollaus;
    private boolean syominen;
    private boolean suojaus;

    public TietokonePelaaja(Backgammon peli) {
	this.peli = peli;
	this.syominen = false;
       
    }

    public int kysyLahtoSektori() {
	return this.siirto[0];
    }
    


    public int kysyTuloSektori() {
	return this.siirto[1];
    }

    public void syominen(boolean arvo) {
	this.syominen = arvo;
    }

    public boolean kerroSyominen() {
	return this.syominen;
    }

    public void suojaus(boolean arvo) {
	this.suojaus = arvo;
    }

    public boolean kerroSuojaus() {
	return this.suojaus;
    }

    public void kirjaaSiirto(int lahto, int tulo) {
	this.siirto[0] = lahto;
	this.siirto[1] = tulo;
	nollaus = 2;
    }

    public boolean maaritaSiirto() {
	//liikutus, jos baarissa nappula
	if (this.peli.kerroSektori(25).kerroNappulat() > 0) {
	    //liikutus pois baarista silmaluvulla1
	    if (this.peli.kerroSektori(25).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2() , 24 - this.peli.kerroNoppa1(), this)) {
		this.kirjaaSiirto(25,24-this.peli.kerroNoppa1());
		return true;
	    
	    } 

	    //liikutus pois baarista silmaluvulla2
	    if (this.peli.kerroSektori(25).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2() , 24 - this.peli.kerroNoppa2(), this)) {
		this.kirjaaSiirto(25,24-this.peli.kerroNoppa2());
		return true;
	    
	    } 

	    //liikutus pois baarista tuplasiirrolla
	    if (this.peli.kerroSektori(25).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2() , 24 - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()),this)) {
		this.kirjaaSiirto(25,24-(this.peli.kerroNoppa1() + this.peli.kerroNoppa2()));
		return true;
	    
	    } 


	}

	//liikutus kotiin
	if (this.peli.onkoKaikkiKotona(1) == true && this.peli.kerroSektori(25).kerroNappulat() == 0) { 
	    
	    //liikutus tasan silm‰luvulla 1 
	    if (this.peli.kerroNoppa1() > 0 && 
		this.peli.kerroSektori(this.peli.kerroNoppa1() - 1).kerroNappula() != null) {
		if (this.peli.kerroSektori(this.peli.kerroNoppa1() - 1).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 27, this)) {
		    this.kirjaaSiirto((this.peli.kerroNoppa1() - 1), 27);
		    return true;
		}
	    }
	    //liikutus tasan silm‰luvulla 2
	    if (this.peli.kerroNoppa2() > 0 &&
		this.peli.kerroSektori(this.peli.kerroNoppa2() - 1).kerroNappula() != null
		&& this.peli.kerroSektori(this.peli.kerroNoppa2() - 1).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 27, this)) {
		this.kirjaaSiirto((this.peli.kerroNoppa2() - 1), 27);
		return true;
	    }
	    //liikutus tasan silm‰luvulla 1 + 2
	    if (this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 &&
		this.peli.kerroSektori(this.peli.kerroNoppa1()  + this.peli.kerroNoppa2() -1).kerroNappula() != null 
		&& this.peli.kerroSektori((this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) - 1).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 27, this)) {
		this.kirjaaSiirto(((this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) - 1), 27);
		return true;
	    }
	    
	    //liikutus kotiin silm‰luvulla 1, jos silm‰luku ei mene tasan
	    for (int i = this.peli.kerroNoppa1() - 1;i>=0;i--) {
		if (this.peli.kerroSektori(i).kerroNappula() != null &&
		    this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 27, this)) {
		    this.kirjaaSiirto((i), 27);
		    return true;
		}
	    }

	    //liikutus kotiin silm‰luvulla 2, jos silm‰luku ei mene tasan
	    for (int i = this.peli.kerroNoppa2() - 1 ;i>=0;i--) {
		if (this.peli.kerroSektori(i).kerroNappula() != null
		    && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 27, this)) {
		    this.kirjaaSiirto((i), 27);
		    return true;
		}
	    }

	    //liikutus kotiin silm‰luvulla 1 + 2, jos silm‰luku ei mene tasan
	    for (int i = this.peli.kerroNoppa2() + this.peli.kerroNoppa1() - 1;i>=0;i--) {
		if (this.peli.kerroSektori(i).kerroNappula() != null &&
		    this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 27, this)) {
		    this.kirjaaSiirto((i), 27);
		    return true;
		}
	    }




	}


	//etsit‰‰n mahdollista syˆmist‰
	for (int i = 23; i > -1 ; i--) {
	    
	    if (this.peli.kerroSektori(i).kerroNappulat() > 0 && this.peli.kerroSektori(25).kerroNappulat() == 0) {
		
		if (i - this.peli.kerroNoppa1() > -1 && this.peli.kerroNoppa1() > 0 &&  this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (i - this.peli.kerroNoppa1()), this)) {
		    this.kirjaaSiirto(i, (i - this.peli.kerroNoppa1()));
		    
		    if (this.kerroSyominen() == true) {
			this.syominen(false);
			return true;
		    }
		}
		
		
		if (i - this.peli.kerroNoppa2() > -1 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (this.peli.kerroSektori(i).kerroNappula().kerroSijainti() - this.peli.kerroNoppa2()),this)) {
		    this.kirjaaSiirto(i, i - this.peli.kerroNoppa2());
		    
		    if (this.kerroSyominen() == true) {
			this.syominen(false);
			return true;
		    }
		}

		
		
		if (i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) > -1 && this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), ( i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())),this)) {

		    this.kirjaaSiirto(i, (i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())));
		    
		    if (this.kerroSyominen() == true) {
			this.syominen(false);
			return true;
		    }
		}	
	    }
	    
	}

	//etsit‰‰n mahdollista suojausta
	for (int i = 23; i > -1 ; i--) {
	    
	    if (this.peli.kerroSektori(i).kerroNappulat() > 0 && this.peli.kerroSektori(25).kerroNappulat() == 0) {
		
		if (i - this.peli.kerroNoppa1() > -1 && this.peli.kerroNoppa1() > 0 &&  this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (i - this.peli.kerroNoppa1()), this)) {
		    this.kirjaaSiirto(i, (i - this.peli.kerroNoppa1()));
		    
		    if (this.kerroSuojaus() == true) {
			this.suojaus(false);
			return true;
		    }
		}

		
		if (i - this.peli.kerroNoppa2() > -1 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (this.peli.kerroSektori(i).kerroNappula().kerroSijainti() - this.peli.kerroNoppa2()),this)) {
		    this.kirjaaSiirto(i, i - this.peli.kerroNoppa2());
		    
		    if (this.kerroSuojaus() == true) {
			this.suojaus(false);
			return true;
		    }
		}

		
		
		if (i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) > -1 && this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), ( i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())),this)) {

		    this.kirjaaSiirto(i, (i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())));
		    
		    if (this.kerroSuojaus() == true) {
			this.suojaus(false);
			return true;
		    }
		}	
	    }
	    
	}
	
	//tavallinen liikutus
	for (int i = 23; i > -1 ; i--) {
	    
	    if (this.peli.kerroSektori(i).kerroNappulat() > 0 && this.peli.kerroSektori(25).kerroNappulat() == 0) {
		
		if (i - this.peli.kerroNoppa1() > -1 && this.peli.kerroNoppa1() > 0 &&  this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (i - this.peli.kerroNoppa1()), this)) {
		    this.kirjaaSiirto(i, (i - this.peli.kerroNoppa1()));
		    
		    return true;
		}

		
		if (i - this.peli.kerroNoppa2() > -1 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (this.peli.kerroSektori(i).kerroNappula().kerroSijainti() - this.peli.kerroNoppa2()),this)) {
		    this.kirjaaSiirto(i, i - this.peli.kerroNoppa2());
		    
		    return true;
		}

		
		
		if (i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) > -1 && this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), ( i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())),this)) {

		    this.kirjaaSiirto(i, (i - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())));
		    
		    return true;
		}	
	    }
	    
	}
	this.peli.nollaaSilmaluku(1);
	this.peli.nollaaSilmaluku(2);
	this.peli.nollaaSilmaluku(1);
	this.peli.nollaaSilmaluku(2);
	this.kirjaaSiirto(0,0);
	return false;
	
    }






    
    public boolean maaritaSiirto0() {
	//liikutus, jos baarissa nappula
	if (this.peli.kerroSektori(24).kerroNappulat() > 0) {
	    //liikutus pois baarista silmaluvulla1
	    if (peli.kerroNoppa1() > 0 && this.peli.kerroSektori(24).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2() , this.peli.kerroNoppa1() - 1, this)) {
		this.kirjaaSiirto(24,this.peli.kerroNoppa1() - 1);
		return true;
	    
	    } 

	    //liikutus pois baarista silmaluvulla2
	    if (peli.kerroNoppa2() > 0 && this.peli.kerroSektori(24).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2() , this.peli.kerroNoppa2() - 1, this)) {
		this.kirjaaSiirto(24,this.peli.kerroNoppa2() - 1);
		return true;
	    
	    } 
	}

	//liikutus kotiin
	if (this.peli.onkoKaikkiKotona(0) == true && this.peli.kerroSektori(24).kerroNappulat() == 0) { 
	    
	    //liikutus tasan silm‰luvulla 1 
	    if (this.peli.kerroNoppa1() > 0 && 
		this.peli.kerroSektori(24 - this.peli.kerroNoppa1()).kerroNappula() != null) {
		if (this.peli.kerroSektori(24 - this.peli.kerroNoppa1()).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 26, this)) {
		    this.kirjaaSiirto((24 - this.peli.kerroNoppa1()), 26);
		    return true;
		}
	    }
	    //liikutus tasan silm‰luvulla 2
	    if (this.peli.kerroNoppa2() > 0 &&
		this.peli.kerroSektori(24 - this.peli.kerroNoppa2()).kerroNappula() != null
		&& this.peli.kerroSektori(24 - this.peli.kerroNoppa2()).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 26, this)) {
		this.kirjaaSiirto((24 - this.peli.kerroNoppa2()), 26);
		return true;
	    }
	    //liikutus tasan silm‰luvulla 1 + 2
	    if (this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 &&
		this.peli.kerroSektori(24 - (this.peli.kerroNoppa1()  + this.peli.kerroNoppa2())).kerroNappula() != null 
		&& this.peli.kerroSektori(24 - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 26, this)) {
		this.kirjaaSiirto((24 - (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())), 26);
		return true;
	    }

	    //liikutus kotiin silm‰luvulla 1, jos silm‰luku ei mene tasan
	    for (int i = 24 - this.peli.kerroNoppa1();i<24;i++) {
		if (this.peli.kerroSektori(i).kerroNappula() != null &&
		    this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 26, this)) {
		    this.kirjaaSiirto((i), 26);
		    return true;
		}
	    }

	    //liikutus kotiin silm‰luvulla 2, jos silm‰luku ei mene tasan
	    for (int i = 24 - this.peli.kerroNoppa2();i<24;i++) {
		if (this.peli.kerroSektori(i).kerroNappula() != null
		    && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 26, this)) {
		    this.kirjaaSiirto((i), 26);
		    return true;
		}
	    }

	    //liikutus kotiin silm‰luvulla 1 + 2, jos silm‰luku ei mene tasan
	    for (int i = 24 - (this.peli.kerroNoppa2() + this.peli.kerroNoppa1());i<24;i++) {
		if (this.peli.kerroSektori(i).kerroNappula() != null &&
		    this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 26, this)) {
		    this.kirjaaSiirto((i), 26);
		    return true;
		}
	    }




	}


	//etsit‰‰n mahdollista syˆmist‰
	for (int i = 0; i < 24 ; i++) {
	    
	    if (this.peli.kerroSektori(i).kerroNappulat() > 0 && this.peli.kerroSektori(24).kerroNappulat() == 0) {
		
		if (i + this.peli.kerroNoppa1() < 24 && this.peli.kerroNoppa1() > 0 
		    &&  this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 
									     (i + this.peli.kerroNoppa1()), this)) {
		    this.kirjaaSiirto(i, (i + this.peli.kerroNoppa1()));
		    
		    if (this.kerroSyominen() == true) {
			this.syominen(false);
			return true;
		    }
		}
		
		
		if (i + this.peli.kerroNoppa2() < 24 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (this.peli.kerroSektori(i).kerroNappula().kerroSijainti() + this.peli.kerroNoppa2()),this)) {
		    this.kirjaaSiirto(i, i + this.peli.kerroNoppa2());
		    
		    if (this.kerroSyominen() == true) {
			this.syominen(false);
			return true;
		    }
		}

		
		
		if (i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) < 24 
		    && this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 
		    && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), 
									    ( i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())),this)) {

		    this.kirjaaSiirto(i, (i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())));
		    
		    if (this.kerroSyominen() == true) {
			this.syominen(false);
			return true;
		    }
		}	
	    }
	    
	}

	//etsit‰‰n mahdollista suojausta
	for (int i = 0; i < 24 ; i++) {
	    
	    if (this.peli.kerroSektori(i).kerroNappulat() > 0 && this.peli.kerroSektori(24).kerroNappulat() == 0) {
		
		if (i + this.peli.kerroNoppa1() < 24 && this.peli.kerroNoppa1() > 0 
		    &&  this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), 
									     this.peli.kerroNoppa2(), (i + this.peli.kerroNoppa1()), this)) {
		    this.kirjaaSiirto(i, (i + this.peli.kerroNoppa1()));
		    
		    if (this.kerroSuojaus() == true) {
			this.suojaus(false);
			return true;
		    }
		}

		
		if (i + this.peli.kerroNoppa2() < 24 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (this.peli.kerroSektori(i).kerroNappula().kerroSijainti() + this.peli.kerroNoppa2()),this)) {
		    this.kirjaaSiirto(i, i + this.peli.kerroNoppa2());
		    
		    if (this.kerroSuojaus() == true) {
			this.suojaus(false);
			return true;
		    }
		}

		
		
		if (i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) < 24 && this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), ( i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())),this)) {

		    this.kirjaaSiirto(i, (i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())));
		    
		    if (this.kerroSuojaus() == true) {
			this.suojaus(false);
			return true;
		    }
		}	
	    }
	    
	}
	
	//tavallinen liikutus
	for (int i = 0; i < 24 ; i++) {
	    
	    if (this.peli.kerroSektori(i).kerroNappulat() > 0 && this.peli.kerroSektori(24).kerroNappulat() == 0) {
		
		if (i + this.peli.kerroNoppa1() < 24 && this.peli.kerroNoppa1() > 0 &&  this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (i + this.peli.kerroNoppa1()), this)) {
		    this.kirjaaSiirto(i, (i + this.peli.kerroNoppa1()));
		    
		    return true;
		}

		
		if (i + this.peli.kerroNoppa2() < 24 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), (this.peli.kerroSektori(i).kerroNappula().kerroSijainti() + this.peli.kerroNoppa2()),this)) {
		    this.kirjaaSiirto(i, i + this.peli.kerroNoppa2());
		    
		    return true;
		}

		
		
		if (i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2()) < 24 && this.peli.kerroNoppa1() > 0 && this.peli.kerroNoppa2() > 0 && this.peli.kerroSektori(i).kerroNappula().onkoLiikeOk(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), ( i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())),this)) {

		    this.kirjaaSiirto(i, (i + (this.peli.kerroNoppa1() + this.peli.kerroNoppa2())));
		    
		    return true;
		}	
	    }
	    
	}
	this.peli.nollaaSilmaluku(1);
	this.peli.nollaaSilmaluku(2);
	this.peli.nollaaSilmaluku(1);
	this.peli.nollaaSilmaluku(2);
	this.kirjaaSiirto(0,0);
	return false;
	
    }

}










