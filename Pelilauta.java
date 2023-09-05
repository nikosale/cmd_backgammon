import java.util.*;

public class Pelilauta{
    
    //    private Tilanteet seivaus;
    private Backgammon peli;
    private Nappiksenlukija nappis;
    private String konettavastaan;
    private TietokonePelaaja tietokonepelaaja;
    private TietokonePelaaja tarkastaja;
    private String pelaaja0;
    private String pelaaja1;
    private int pisteet0;
    private int pisteet1;
    Liike liikelista;
 

    boolean kysy;

    public Pelilauta() {
	nappis = new Nappiksenlukija();
	peli = new Backgammon();
	tarkastaja = new TietokonePelaaja(this.peli);
	liikelista = new Liike(this.peli);
	pisteet0 = 0;
	pisteet1 = 0;

	
	
    }
   
    public String kerroVastustaja() {
	if (this.peli.kerroVuorossaOlija() == 0) {
	    return this.pelaaja1;
	}
	else return this.pelaaja0;
    }

    public String kerroPelaajaVuorossa() {
	if (this.peli.kerroVuorossaOlija() == 1) {
	    return this.pelaaja1;
	}
	else return this.pelaaja0;
    
    } 
    
    public void arvoAloittaja() {
	int ekaluku = 0;
	int tokaluku = 0;
	
       	while (ekaluku == tokaluku) {
	    ekaluku = peli.heitaNoppaa(); 
	    tokaluku = peli.heitaNoppaa();
    	}

	if(ekaluku>tokaluku) {
	    this.peli.asetaVuorossaOlija(0);
	}
	else this.peli.asetaVuorossaOlija(1);
    }

    public void pelaa(String viesti) {
	int lahto = 0;
	int tulo = 0;
	Nappula apunappula;
	int noppa1;
	int noppa2;
	//aluksi arvotaanaloittaja
	this.arvoAloittaja();

	//tallennetaan liikelistaan pelin alkutilanne
	Liikkeentallennus alkutilanne = new Liikkeentallennus(0, 0,this.peli.kerroVuorossaOlija(),this.peli.kerroCubenVari(), peli.kerroPelinArvo(), peli.kerroCubenVari(), peli.kerroNoppa1(), peli.kerroNoppa2(), peli.kerroTuplanollaus());
	liikelista.lisaa(alkutilanne);

	//jos pelaa-metodi saa parametrinaan viestin "uusi", kysyy pelaajien nimet yms.
	//mik‰li viesti on joku muu, niin nimi‰ ei kysyt‰
	if (viesti.equals("uusi")) {
	    //kysyt‰‰n halutaanko pelata koneta vastaan
	    konettavastaan = this.nappis.lueMerkkijono("Haluatko pelata tietokonetta vastaan? Kyll‰ = k, Ei = e.");

	
	    //niin kauan kun ei saada vastaukseksi e:t‰ tai k:ta kysyt‰‰n uudestaan
	    while(!(konettavastaan.equals("e") || konettavastaan.equals("k"))) {
		konettavastaan = this.nappis.lueMerkkijono("Haluatko pelata tietokonetta vastaan? Kyll‰ = k, Ei = e.");
	    }

	    //jos saadaan k, luodaan uusi tietokonepelaaja
	    if (this.konettavastaan.equals("k")) {
		tietokonepelaaja = new TietokonePelaaja(this.peli);
		this.pelaaja1 = "tietokone";
	    }
	    //kysyt‰‰n pelaajien nimet
	    this.pelaaja0 = this.nappis.lueMerkkijono("Anna ensimm‰isen pelaajan nimi");
	    if (this.konettavastaan.equals("e")) {
		this.pelaaja1 = this.nappis.lueMerkkijono("Anna toisen pelaajan nimi");
	    }
	}
	if (this.peli.kerroVuorossaOlija() == 0) {
	    System.out.println("Vuorossa " + this.pelaaja0 + ".");
	}
	else System.out.println("Vuorossa " + this.pelaaja1 + ".");

	//peli pyˆrii,kunnes voittaja lˆytyy, eli seuraava metodikutsu saa arvokseen 0 tai 1 voittajasta riippuen
	while (this.peli.onkoVoittajaa() == 2) {
	    
	    //katsotaan onko nolla vuorossa
	    if (this.peli.kerroVuorossaOlija() == 0) {
		
		this.peli.heitaNopat();
		this.paivitaRuutu2();
		//otetaan nopat talteen peruutusta varten
		noppa1 = this.peli.kerroNoppa1();
		noppa2 = this.peli.kerroNoppa2();
		System.out.println("Vuorossa " + this.pelaaja0 + ".");
		

		//vuoron loppuun asti, eli kunnes molemmat nopat on nollattu, kysell‰‰n siirtoja ja liikutetaan niiden mukaisesti
		while (!(this.peli.onkoVuoroLoppu())) {
		    //kysyt‰‰n l‰htˆsektori
		    lahto = this.kysyLahtoSektori();
		    //niin kauan kunnes saadaan j‰rkev‰ l‰htˆsektori kysyt‰‰n uudestaan
		    while (lahto < 0 || lahto > 26 ||
			   this.peli.kerroSektori(lahto).kerroNappulat() == 0) {
			if (lahto < 0 || lahto >26) {
			    System.out.println("Sektorista " + (lahto+1) + " ei voida liikuttaa.");
			}
			else
			    System.out.println("Sektorissa " + (lahto+1) + " ei ole nappuloita.");
			
			lahto = this.kysyLahtoSektori();
		    }
		    //kysyt‰‰n tulosektori
		    tulo = this.kysyTuloSektori();
		    //yritet‰‰n tehd‰ siirto ja virheellisen siirron sattuessa printataan virheviesti
		    try {
			if (this.peli.kerroSektori(lahto).kerroNappula().liikuta(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), tulo) == true) {
			    this.paivitaRuutu2();
			    Liikkeentallennus tehtyliike = new Liikkeentallennus(lahto, tulo,this.peli.kerroVuorossaOlija(),this.peli.kerroCubenVari(), peli.kerroPelinArvo(), peli.kerroCubenVari(), noppa1, noppa2, peli.kerroTuplanollaus());
			    liikelista.lisaa(tehtyliike);
			}
		    }
		    catch (VirheellinenSiirto virhe) {
			System.out.println(virhe.kerroKuvausViesti());
			
		    }
		    
		    this.suoritaKomento(this.kysyKomento());
		    
		}
		this.peli.vaihdaVuorossaOlija();		
	    }
	    //katsotaan vuorojen v‰liss‰ onko voittajaa lˆytynyt ja keskeytet‰‰n silmukka jos on
	    if(this.peli.onkoVoittajaa() != 2) {
		break;
	    }


	    //ihmisen vuoro, v‰ri1
	    if (this.peli.kerroVuorossaOlija() == 1 && this.konettavastaan.equals("e")) {
		this.peli.heitaNopat();
		//otetaan nopat talteen peruutusta varten
		noppa1 = this.peli.kerroNoppa1();
		noppa2 = this.peli.kerroNoppa2();
		this.paivitaRuutu2();
		System.out.println("Vuorossa " + this.pelaaja1 + ".");
		
		while (!(this.peli.onkoVuoroLoppu())) {
		    lahto = this.kysyLahtoSektori();
		    while (lahto < 0 || lahto > 26
			   || this.peli.kerroSektori(lahto).kerroNappulat() == 0
			   ) {
			if (lahto < 0 || lahto > 26) {
			    System.out.println("Sektorista " + (24 - lahto) + " ei voida liikuttaa.");
			}
			else
			    System.out.println("Sektorissa " + (24 - lahto) + " ei ole nappuloita.");
			
			lahto = this.kysyLahtoSektori();
		    }
		    tulo = this.kysyTuloSektori();
		    try {
			if (this.peli.kerroSektori(lahto).kerroNappula().liikuta(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), tulo) == true) {
			    this.paivitaRuutu2();
			    //seuraavassa luodaan uusi liikkeentallennus, joka kuvaa siirtoa ja pelin tilannetta siirron vallitessa. liikkeentallennus tallennetaan liikelistaan
			    Liikkeentallennus tehtyliike = new Liikkeentallennus(lahto, tulo,this.peli.kerroVuorossaOlija(),this.peli.kerroCubenVari(), peli.kerroPelinArvo(), peli.kerroCubenVari(), noppa1, noppa2, peli.kerroTuplanollaus());
			    liikelista.lisaa(tehtyliike);
			}
		    }
		    catch (VirheellinenSiirto virhe) {
			System.out.println(virhe.kerroKuvausViesti());
		    }

		    this.suoritaKomento(this.kysyKomento());
		}
		this.peli.vaihdaVuorossaOlija();       
	    }
	    
		

	    //tietokoneen vuoro, v‰ri1
	    if (this.peli.kerroVuorossaOlija() == 1 && this.konettavastaan.equals("k")) {
		
		this.peli.heitaNopat();
		//otetaan nopat talteen peruutusta varten
		noppa1 = this.peli.kerroNoppa1();
		noppa2 = this.peli.kerroNoppa2();
		this.paivitaRuutu2();
		System.out.println("VUOROSSA 1!!");
		
		while (!(this.peli.onkoVuoroLoppu())) {
		    

		    this.tietokonepelaaja.maaritaSiirto();
		    lahto = this.tietokonepelaaja.kysyLahtoSektori();
		    tulo = this.tietokonepelaaja.kysyTuloSektori();
		    if (lahto == 0 && tulo ==0) {
			this.peli.nollaaSilmaluku(1);
			this.peli.nollaaSilmaluku(2);
			this.peli.nollaaSilmaluku(1);
			this.peli.nollaaSilmaluku(2);
			break;
		    }
		    
		    try {
			if (this.peli.kerroSektori(lahto).kerroNappula().liikuta(this.peli.kerroNoppa1(), this.peli.kerroNoppa2(), tulo) == true) {
			    this.paivitaRuutu2();
			}
		    }
		    catch (VirheellinenSiirto virhe) {
			System.out.println(virhe.kerroKuvausViesti());
		    }
		    //seuraavassa luodaan uusi liikkeentallennus, joka kuvaa siirtoa ja pelin tilannetta siirron vallitessa. liikkeentallennus tallennetaan Liike-olioon peruutusta varten
		    Liikkeentallennus tehtyliike = new Liikkeentallennus(lahto, tulo,this.peli.kerroVuorossaOlija(),this.peli.kerroCubenVari(), peli.kerroPelinArvo(), peli.kerroCubenVari(), noppa1, noppa2, peli.kerroTuplanollaus());
		    liikelista.lisaa(tehtyliike);
		}
		this.peli.vaihdaVuorossaOlija();
		
	    }
	    
	}
	this.voitto();
    }


    public int kysyLahtoSektori() {
	if (this.peli.kerroVuorossaOlija() == 0) {
	    return (this.nappis.lueKokonaisluku("Anna l‰htosektorin numero:") - 1);
	}

	if (this.peli.kerroVuorossaOlija() == 1) {
	    int syote1 = this.nappis.lueKokonaisluku("Anna l‰htosektorin numero:");
	    if (syote1 == 26) {
		return 25;
	    }
	    else return (24 - syote1); 
	}
	else return 0;
    }

    public void suoritaKomento(String komento) {
	int lahto;
	int tulo;
	if (komento.equals("6")) {
	    System.out.println("Pelin arvo on t‰ll‰ hetkell‰ " + this.peli.kerroPelinArvo() + " pistett‰.");
	}

	if(komento.equals("5")) {
	    System.out.println("Pelaajalla " + this.pelaaja0 + " pisteit‰ " + this.pisteet0 + ".");
	    System.out.println("Pelaajalla " + this.pelaaja1 + " pisteit‰ " + this.pisteet1 + ".");   
	}

	if(komento.equals("4")) {
	    this.uusiPeli();
	}

	if (komento.equals("3")) {
	    int peruutus = nappis.lueKokonaisluku("Montako askelta peruutetaan?");
	    this.liikelista.peruuta(peruutus);
	    paivitaRuutu2();
	}

	

	if (komento.equals("1")) {
	    if (this.peli.kerroVuorossaOlija() == 0) {
		this.tarkastaja.maaritaSiirto0();
	    }
	    else this.tarkastaja.maaritaSiirto();
	    lahto = this.tarkastaja.kysyLahtoSektori();
	    tulo = this.tarkastaja.kysyTuloSektori();

	    if (lahto == 0 && tulo ==0) {
	    this.peli.nollaaSilmaluku(1);
	    this.peli.nollaaSilmaluku(2);
	    this.peli.nollaaSilmaluku(1);
	    this.peli.nollaaSilmaluku(2);
	   	    } 
	    else {
		if (this.peli.kerroVuorossaOlija() == 0) {
		    System.out.println("Siirto sektorista " + (lahto + 1) + " sektoriin " + (tulo + 1) + " on mahdollinen");
		}
		if (this.peli.kerroVuorossaOlija() == 1) {
		    System.out.println("Siirto sektorista " + (24 - lahto) + " sektoriin " + (24 - tulo) + " on mahdollinen");
		}
	    }

	}
	if(komento.equals("2")) {
	    if(this.peli.kerroCube(this.peli.kerroVuorossaOlija()) == true) {
		if(this.konettavastaan.equals("e")) {
		    String vastaus = this.nappis.lueMerkkijono("Suostuuko " + this.kerroVastustaja() + " pelin arvon tuplaamiseen? Kylla = k, Ei = e");
		    while(!(vastaus.equals("e") || vastaus.equals("k"))) {
			vastaus = this.nappis.lueMerkkijono("Suostuuko " + this.kerroVastustaja() + " pelin arvon tuplaamiseen? Kylla = k, Ei = e");
		    }
		    if (vastaus.equals("k")) {
			this.peli.tuplaaPelinArvo();
			this.peli.vaihdaCube(this.kerroVuorossaOlevaVastustaja());
			System.out.println("Pelin panos on nyt " + this.peli.kerroPelinArvo() + ".");
		    }
		    else {
			System.out.println(this.kerroVastustaja() + " h‰visi pelin.");
			
			if (this.peli.kerroVuorossaOlija() == 0) {
			    this.pisteet0 = this.pisteet0 + this.peli.kerroPelinArvo();
			    System.out.println(this.pelaaja0 + " omaa " + this.pisteet0 + " pistett‰.");
			}
			if (this.peli.kerroVuorossaOlija() == 1) {
			    this.pisteet1 = this.pisteet1 + this.peli.kerroPelinArvo();
			    System.out.println(this.pelaaja1 + " omaa " + this.pisteet1 + " pistett‰.");
			}
			this.uusiPeli();
		        
		    }
		}
		else {
		    this.peli.tuplaaPelinArvo();
		    this.peli.vaihdaCube(this.kerroVuorossaOlevaVastustaja());
		    System.out.println("Pelin panos on nyt " + this.peli.kerroPelinArvo() + ".");
		}
		
	    }
	    else System.out.println("Et voi ehdottaa tuplausta, koska ehdotit sit‰ viimeksi.");
	}
	    
	  	    
	    
	
	   
    
    }

    public int kerroVuorossaOlevaVastustaja() {
	if (this.peli.kerroVuorossaOlija() == 0) {
	    return 1;
	}
	else return 0;
    }

    public void uusiPeli() {
	String vastaus = this.nappis.lueMerkkijono("Pelataanko uusi peli samoilla pelaajilla? Kyll‰ = k, Ei = e");
	while(!(vastaus.equals("e") || konettavastaan.equals("k"))) {
	    konettavastaan = this.nappis.lueMerkkijono("Haluatko pelata tietokonetta vastaan? Kyll‰ = k, Ei = e.");
	}
	if (vastaus.equals("e")) {
	    System.out.println(this.pelaaja0 + " omaa " +this.pisteet0 + " pistett‰.");
	    System.out.println(this.pelaaja1 + " omaa " +this.pisteet1 + " pistett‰.");
	    System.exit(0);
	}
	if (vastaus.equals("k")) {
	    this.peli = new Backgammon();
	    tarkastaja = new TietokonePelaaja(this.peli);
	    liikelista = new Liike(this.peli);
	    this.pelaa("vanha");
	}
    }

    public void voitto() {
	if (this.peli.onkoVoittajaa() == 1) {
	    System.out.println(this.pelaaja1 + " voitti pelin.");
	    if (this.peli.gammoned(0) == true) {
		if (this.peli.backGammoned(0) == true ) {
		    this.pisteet1 = this.pisteet1 + (3*this.peli.kerroPelinArvo());
		    System.out.println(this.pelaaja0 + " got Backgammoned ja h‰visi " + (this.peli.kerroPelinArvo()*3) + " pistett‰.");
		}
		else { 
		    this.pisteet1 = this.pisteet1 + (2*this.peli.kerroPelinArvo());
		    System.out.println(this.pelaaja0 + " got Gammoned ja h‰visi " + (this.peli.kerroPelinArvo()*2) + " pistett‰.");
		}
	    }
	    else  this.pisteet1 = this.pisteet1 + this.peli.kerroPelinArvo();
	}
	if (this.peli.onkoVoittajaa() == 0) {
	    System.out.println(this.pelaaja0 + " voitti pelin.");
	    if (this.peli.gammoned(1) == true) {
		if (this.peli.backGammoned(1) == true) {
		    this.pisteet0 = this.pisteet0 + (3*this.peli.kerroPelinArvo());
		    System.out.println(this.pelaaja1 + " got Backgammoned ja h‰visi " + (this.peli.kerroPelinArvo()*3) + " pistett‰.");
		}
		else  { 
		    this.pisteet0 = this.pisteet0 + (2*this.peli.kerroPelinArvo());
		    System.out.println(this.pelaaja0 + " got Gammoned ja h‰visi " + (this.peli.kerroPelinArvo()*2) + " pistett‰.");
		}
	    }
	    else this.pisteet0 = this.pisteet0 + this.peli.kerroPelinArvo();
	}

	this.uusiPeli();
    
    }


    public String kysyKomento() {
	System.out.println("Paina 1 skipataksesi vuoron.");
	System.out.println("Paina 2 tuplataksesi pelin arvon.");
	System.out.println("Paina 3 peruuttaksesi vuoroja.");
	System.out.println("Paina 4 lopettaaksesi pelin.");
	System.out.println("Paina 5 n‰ytt‰‰ksesi pistetilanteen.");
	System.out.println("Paina 6 n‰ytt‰‰ksesi pelin arvon.");
	System.out.println("Paina muuta n‰pp‰int‰ jos et halua antaa komentoa.");

	return this.nappis.lueMerkkijono("Anna komento:");
    }

    public int kysyTuloSektori() {
	int syote = this.nappis.lueKokonaisluku("Anna tulosektorin numero:");
	if (this.peli.kerroVuorossaOlija() == 0) {
	    if (syote == 27) {
		return 26;
	    }
	    else return (syote - 1);
	}
	if (this.peli.kerroVuorossaOlija() == 1) {
	    if (syote == 28) {
		return 27;
	    }
	    else return (24 - syote);
	}
	return 0;
    }

    //t‰m‰ on testausvaiheen yksinkertainen ruudunp‰ivitys
    public void paivitaRuutu() {
	System.out.println("Noppa 1: " + this.peli.kerroNoppa1() 
			   + " Noppa 2: " + this.peli.kerroNoppa2());
	for(int i = 0; i<28;i++) {
	    if (i==26)
		System.out.print("O:llan koti: ");
	    if (i==27)
		System.out.print("1:sen koti: ");
	    if (i==25)
		System.out.print("1:sen baari: ");
	    if(i==24)
		System.out.print("0:llan baari: ");
	    System.out.println(" " + i + " " + this.peli.kerroSektori(i).toString());
	}
    }

    //varsinaisen pelitilanteen p‰ivitys ruudulle
    public void paivitaRuutu2() {
	System.out.println("Noppa 1: " + this.peli.kerroNoppa1() + " Noppa 2: " + this.peli.kerroNoppa2());
	//ruudun printtaus, mik‰li ykkˆsv‰ri vuorossa
	if (this.peli.kerroVuorossaOlija() == 1) {
	    System.out.println("");



	    //yl‰rivin sektorinumerot
	    for (int i = 13; i <25;i++) {
		System.out.print(i + " ");
		if (i == 18) {
		    System.out.print("bar ");
		}
		if (i == 24) {
		    System.out.println("koti = 28");
		}
	    }
	    //ruudun yl‰osa
	    for (int j = 0; j<5; j++) {
		for(int i = 11; i>-1;i--) {
		    //printataan nollanappula, jos nollanappuloita enemm‰n, kuin rivinumero(j)
		    if (this.peli.kerroSektori(i).kerroNappulat() > j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 0 ) {
			System.out.print("0  ");
		    }
		    //printataan ykkˆsnappula, jos ykkˆsnappuloita enemm‰n, kuin rivinumero(j)
		    else
		    if (this.peli.kerroSektori(i).kerroNappulat() > j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 1 ) {
			System.out.print("1  ");
		    }
		    //printataan tyhj‰‰, jos nappuloita nolla tai v‰hemm‰n tai yht‰ paljon kuin rivinumero
		    else
		    if  (this.peli.kerroSektori(i).kerroNappulat() == 0 || this.peli.kerroSektori(i).kerroNappulat() <= j) {
			System.out.print("   ");
		    }
		    //baariprinttaus
		    if (i == 6 && this.peli.kerroSektori(24).kerroNappulat() > j && this.peli.kerroSektori(24).kerroNappula().kerroVari() == 0) {
			System.out.print("0   ");
		    }
		    else
		    if (i == 6 && (this.peli.kerroSektori(24).kerroNappulat() == 0 || this.peli.kerroSektori(i).kerroNappulat() <= j)) {
			System.out.print("    ");
		    }
		    if (i == 0) {
			if (j==0) {
			    System.out.print(this.peli.kerroSektori(27).toString());
			}
			System.out.println("");
		    }
		
		}

	    }
	    //ruudun alaosa
	    System.out.println("");
	    for (int j = 5; j>0; j--) {
		for(int i = 12; i<24;i++) {
		    //printataan nollanappula, jos nollanappuloita enemm‰n tai yht‰ paljon, kuin rivinumero(j)
		    if (this.peli.kerroSektori(i).kerroNappulat() >= j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 0 ) {
			System.out.print("0  ");
		    }
		    //printataan ykkˆsnappula, jos ykkˆsnappuloita enemm‰n tai yht‰ paljon, kuin rivinumero(j)
		    else
		    if (this.peli.kerroSektori(i).kerroNappulat() >= j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 1 ) {
			System.out.print("1  ");
		    }
		    //printataan tyhj‰‰, jos nappuloita nolla tai v‰hemm‰n tai yht‰ paljon kuin rivinumero
		    else
		    if  (this.peli.kerroSektori(i).kerroNappulat() == 0 || this.peli.kerroSektori(i).kerroNappulat() <= j) {
			System.out.print("   ");
		    }
		    //baariprinttaus
		    if (i == 17 && this.peli.kerroSektori(25).kerroNappulat() >= j && this.peli.kerroSektori(25).kerroNappula().kerroVari() == 1) {
			System.out.print("1   ");
		    }
		    else
		    if (i == 17 && (this.peli.kerroSektori(25).kerroNappulat() == 0 || this.peli.kerroSektori(25).kerroNappulat() < j)) {
			System.out.print("    ");
		    }
		    if (i == 23) {
			if(j==1) {
			    System.out.print(this.peli.kerroSektori(26).toString());
			}
			System.out.println("");
		    }
		
		}

	    }

	    //alarivin sektorinumerot
	    for (int i = 12; i >0;i--) {
		System.out.print(i + " ");
		if (i<10) {
		    System.out.print(" ");
		}
		if (i == 7) {
		    System.out.print("bar ");
		}
		if (i == 1) {
		    System.out.println("toisen koti");
		}
	    }
	    System.out.println("                  =26");

	}

	//ruudun printtaus, mik‰li nollav‰ri vuorossa
	if (this.peli.kerroVuorossaOlija() == 0) {
	    System.out.println("");
	    System.out.println("                  bar");



	    //yl‰rivin sektorinumerot
	    for (int i = 12; i >0;i--) {
		System.out.print(i + " ");
		if (i<10) {
		    System.out.print(" ");
		}
		if (i == 7) {
		    System.out.print("=25 ");
		}
		if (i == 1) {
		    System.out.println("toisen koti");
		}
	    }
	    //ruudun yl‰osa
	    for (int j = 0; j<5; j++) {
		for(int i = 11; i>-1;i--) {
		    //printataan nollanappula, jos nollanappuloita enemm‰n, kuin rivinumero(j)
		    if (this.peli.kerroSektori(i).kerroNappulat() > j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 0 ) {
			System.out.print("0  ");
		    }
		    //printataan ykkˆsnappula, jos ykkˆsnappuloita enemm‰n, kuin rivinumero(j)
		    else
		    if (this.peli.kerroSektori(i).kerroNappulat() > j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 1 ) {
			System.out.print("1  ");
		    }
		    //printataan tyhj‰‰, jos nappuloita nolla tai v‰hemm‰n tai yht‰ paljon kuin rivinumero
		    else
		    if  (this.peli.kerroSektori(i).kerroNappulat() == 0 || this.peli.kerroSektori(i).kerroNappulat() <= j) {
			System.out.print("   ");
		    }
		    //baariprinttaus
		    if (i == 6 && this.peli.kerroSektori(24).kerroNappulat() > j && this.peli.kerroSektori(24).kerroNappula().kerroVari() == 0) {
			System.out.print("0   ");
		    }
		    else
		    if (i == 6 && (this.peli.kerroSektori(24).kerroNappulat() == 0 || this.peli.kerroSektori(i).kerroNappulat() <= j)) {
			System.out.print("    ");
		    }
		    if (i == 0) {
			if (j==0) {
			    System.out.print(this.peli.kerroSektori(27).toString());
			}
			System.out.println("");
		    }
		
		}

	    }
	    //ruudun alaosa
	    System.out.println("");
	    for (int j = 5; j>0; j--) {
		for(int i = 12; i<24;i++) {
		    //printataan nollanappula, jos nollanappuloita enemm‰n tai yht‰ paljon, kuin rivinumero(j)
		    if (this.peli.kerroSektori(i).kerroNappulat() >= j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 0 ) {
			System.out.print("0  ");
		    }
		    //printataan ykkˆsnappula, jos ykkˆsnappuloita enemm‰n tai yht‰ paljon, kuin rivinumero(j)
		    else
		    if (this.peli.kerroSektori(i).kerroNappulat() >= j && this.peli.kerroSektori(i).kerroNappula().kerroVari() == 1 ) {
			System.out.print("1  ");
		    }
		    //printataan tyhj‰‰, jos nappuloita nolla tai v‰hemm‰n tai yht‰ paljon kuin rivinumero
		    else
		    if  (this.peli.kerroSektori(i).kerroNappulat() == 0 || this.peli.kerroSektori(i).kerroNappulat() <= j) {
			System.out.print("   ");
		    }
		    //baariprinttaus
		    if (i == 17 && this.peli.kerroSektori(25).kerroNappulat() >= j && this.peli.kerroSektori(25).kerroNappula().kerroVari() == 1) {
			System.out.print("1   ");
		    }
		    else
		    if (i == 17 && (this.peli.kerroSektori(25).kerroNappulat() == 0 || this.peli.kerroSektori(25).kerroNappulat() < j)) {
			System.out.print("    ");
		    }
		    if (i == 23) {
			if(j==1) {
			    System.out.print(this.peli.kerroSektori(26).toString());
			}
			System.out.println("");
		    }
		
		}

	    }
	    //alarivin sektorinumerot
	    for (int i = 13; i <25;i++) {
		System.out.print(i + " ");
		if (i == 18) {
		    System.out.print("bar ");
		}
		if (i == 24) {
		    System.out.println("koti = 27");
		}
	    }

	}
    }

}
