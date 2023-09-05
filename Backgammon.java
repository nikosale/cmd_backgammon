import java.util.*;
public class Backgammon {
    
    private int silmaluku1;
    private int silmaluku2;
    Random generator = new Random();
    private int vuorossa;
    private boolean tuplat;
    private int tuplanollaus = 0;
    private int tuplanollaus2 = 0;
    private boolean cube0;
    private boolean cube1;
    private int pelinArvo;
    
    private boolean onkoVuoroaJaljella;
    //sektorit 0-23 (pelilauta) 
    //24 baari0(nollavarin baari), 25 baari1(ykkosvarin baari)
    //26 koti0, 27 koti1
    private Sektori[] sektorit;
    private int[] nopat;
    

    public Backgammon() {
	this.cube0 = true;
	this.cube1 = true;
	this.pelinArvo = 1;
	Nappula[] nappulat1 = new Nappula[2];
	Nappula[] nappulat6 = new Nappula[5];
	Nappula[] nappulat8 = new Nappula[3];
	Nappula[] nappulat12 = new Nappula[5]; 
	Nappula[] nappulat13 = new Nappula[5];
	Nappula[] nappulat17 = new Nappula[3];
	Nappula[] nappulat19 = new Nappula[5];
	Nappula[] nappulat24 = new Nappula[2];
	//        /*
	for (int i=0;i<2;i++) {
	    nappulat1[i]=new Nappula(0,0,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat6[i]=new Nappula(5,1,this);
	}
	for (int i=0;i<3;i++) {
	    nappulat8[i]=new Nappula(7,1,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat12[i]=new Nappula(11,0,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat13[i]=new Nappula(12,1,this);
	}
	for (int i=0;i<3;i++) {
	    nappulat17[i]=new Nappula(16,0,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat19[i]=new Nappula(18,0,this);
	}
	for (int i=0;i<2;i++) {
	    nappulat24[i]=new Nappula(23,1,this);
	}

	sektorit=new Sektori[28];

	sektorit[0]=new Sektori(nappulat1,5);
	sektorit[1]=new Sektori(5);
	sektorit[2]=new Sektori(5);
	sektorit[3]=new Sektori(5);
	sektorit[4]=new Sektori(5);
	sektorit[5]=new Sektori(nappulat6,5);
	sektorit[6]=new Sektori(5);
	sektorit[7]=new Sektori(nappulat8,5);
	sektorit[8]=new Sektori(5);
	sektorit[9]=new Sektori(5);
	sektorit[10]=new Sektori(5);
	sektorit[11]=new Sektori(nappulat12,5);
	sektorit[12]=new Sektori(nappulat13,5);
	sektorit[13]=new Sektori(5);
	sektorit[14]=new Sektori(5);
	sektorit[15]=new Sektori(5);
	sektorit[16]=new Sektori(nappulat17,5);
	sektorit[17]=new Sektori(5);
	sektorit[18]=new Sektori(nappulat19,5);
	sektorit[19]=new Sektori(5);
	sektorit[20]=new Sektori(5);
	sektorit[21]=new Sektori(5);
	sektorit[22]=new Sektori(5);
	sektorit[23]=new Sektori(nappulat24,5);
	sektorit[24]=new Sektori(15);
	sektorit[25]=new Sektori(15);
	sektorit[26]=new Sektori(15);
	sektorit[27]=new Sektori(15); 
	/*
	for (int i=0;i<2;i++) {
	    nappulat1[i]=new Nappula(18,0,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat6[i]=new Nappula(1,1,this);
	}
	for (int i=0;i<3;i++) {
	    nappulat8[i]=new Nappula(2,1,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat12[i]=new Nappula(19,0,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat13[i]=new Nappula(3,1,this);
	}
	for (int i=0;i<3;i++) {
	    nappulat17[i]=new Nappula(20,0,this);
	}
	for (int i=0;i<5;i++) {
	    nappulat19[i]=new Nappula(21,0,this);
	}
	for (int i=0;i<2;i++) {
	    nappulat24[i]=new Nappula(0,1,this);
	}

	sektorit=new Sektori[28];

	sektorit[0]=new Sektori(nappulat24,5);
	sektorit[1]=new Sektori(nappulat6,5);
	sektorit[2]=new Sektori(nappulat8,5);
	sektorit[3]=new Sektori(nappulat13,5);
	sektorit[4]=new Sektori(5);
	sektorit[5]=new Sektori(5);
	sektorit[6]=new Sektori(5);
	sektorit[7]=new Sektori(5);
	sektorit[8]=new Sektori(5);
	sektorit[9]=new Sektori(5);
	sektorit[10]=new Sektori(5);
	sektorit[11]=new Sektori(5);
	sektorit[12]=new Sektori(5);
	sektorit[13]=new Sektori(5);
	sektorit[14]=new Sektori(5);
	sektorit[15]=new Sektori(5);
	sektorit[16]=new Sektori(5);
	sektorit[17]=new Sektori(5);
	sektorit[18]=new Sektori(nappulat1,5);
	sektorit[19]=new Sektori(nappulat12,5);
	sektorit[20]=new Sektori(nappulat17,5);
	sektorit[21]=new Sektori(nappulat19,5);
	sektorit[22]=new Sektori(5);
	sektorit[23]=new Sektori(5);
	sektorit[24]=new Sektori(15);
	sektorit[25]=new Sektori(15);
	sektorit[26]=new Sektori(15);
	sektorit[27]=new Sektori(15);
      	*/

    }
   
    
    public int kerroTuplanollaus() {
	return tuplanollaus;
    }
    
    public void asetaTuplanollaus(int uusitupla) {
	tuplanollaus = uusitupla;
    }
    
    public void asetaPanos(int panos) {
	pelinArvo = panos;
    }
    
    public boolean gammoned(int vari) {
	if (vari == 0) {
	    if (this.kerroSektori(26).kerroNappulat() < 1) {
		return true;
	    }
	    else return false;
	}
	if (vari ==1) {
	    if (this.kerroSektori(27).kerroNappulat() < 1) {
		return true;
	    }
	    else return false;
	}
	return false;
    }


    public boolean backGammoned(int vari) {
	int nappulatToisenKotona = 0;
	if (vari == 0) {
	    for (int i = 0; i<6;i++) {
		if (this.kerroSektori(i).kerroNappulat() > 0 && this.kerroSektori(i).kerroNappula().kerroVari() == 0) {
		    nappulatToisenKotona = nappulatToisenKotona + this.kerroSektori(i).kerroNappulat();
		}	
	    }
	    if (this.gammoned(vari) == true && (this.kerroSektori(24).kerroNappulat() > 1 || nappulatToisenKotona > 0)) {
		return true;
	    }
	}
	    
	    
	if (vari == 1) {
	    for (int i = 18; i<24;i++) {
		if (this.kerroSektori(i).kerroNappulat() > 0 && this.kerroSektori(i).kerroNappula().kerroVari() == 1) {
		    nappulatToisenKotona = nappulatToisenKotona + this.kerroSektori(i).kerroNappulat();
		}	
	    }
	    if (this.gammoned(vari) == true && (this.kerroSektori(25).kerroNappulat() > 1 || nappulatToisenKotona > 0)) {
		return true;
	    }
		
	}
	return false;    
    }

    public void vaihdaVuorossaOlija() {
	if (this.vuorossa == 1)
	    this.vuorossa = 0;
	else
	    this.vuorossa = 1;
    }
    public int kerroPelinArvo() {
	return this.pelinArvo;
    }

    public void tuplaaPelinArvo() {
	this.pelinArvo = 2 * this.pelinArvo;
    }

    public void vaihdaCube(int vari) {
	if (vari == 0) {
	    this.cube0=true;
	    this.cube1=false;
	}
	if (vari == 1) {
	    this.cube0=false;
	    this.cube1=true;
	}
	if (vari == 2) {
	    cube0 = true;
	    cube1 = true;
	}
    }
    public boolean kerroCube(int vari) {
	if (vari == 0 && this.cube0 == true) {
	    return true;
	}
	if (vari == 1 && this.cube1 == true) {
	    return true;
	}
	return false;
    }

    public int kerroCubenVari() {
	if (cube0 == true)
	    return 0;
	if (cube1 == true)
	    return 1;
	else
	    return 2;
    }

    public boolean onkoKaikkiKotona (int vari) {
	

	if (vari == 0) {
	    int summa0 = 0;
	    if (this.kerroSektori(18).kerroNappulat()>0 &&
		this.kerroSektori(18).kerroNappula().kerroVari() == 0)
	        summa0 =this.kerroSektori(18).kerroNappulat();
	    if (this.kerroSektori(19).kerroNappulat()>0 &&
		this.kerroSektori(19).kerroNappula().kerroVari() == 0)
		summa0 = summa0 +  this.kerroSektori(19).kerroNappulat();
	    if(this.kerroSektori(20).kerroNappulat()>0 &&
	       this.kerroSektori(20).kerroNappula().kerroVari() == 0)
		summa0 = summa0 + this.kerroSektori(20).kerroNappulat();
	    if (this.kerroSektori(21).kerroNappulat()>0 &&
		this.kerroSektori(21).kerroNappula().kerroVari() == 0)
		summa0 = summa0 + this.kerroSektori(21).kerroNappulat(); 
	    if (this.kerroSektori(22).kerroNappulat()>0 &&
		this.kerroSektori(22).kerroNappula().kerroVari() == 0)
		summa0 = summa0 + this.kerroSektori(22).kerroNappulat();
	    if (this.kerroSektori(23).kerroNappulat()>0 &&
		this.kerroSektori(23).kerroNappula().kerroVari() == 0)
		summa0  = summa0 + this.kerroSektori(23).kerroNappulat();
	    if (this.kerroSektori(26).kerroNappulat()>0 &&
		this.kerroSektori(26).kerroNappula().kerroVari() == 0)
		summa0 = summa0 + this.kerroSektori(26).kerroNappulat();
	    
	    if (summa0 == 15) {
		return true;
	    }
	    else return false;
	    
	}
	
	if (vari == 1) {
	    int summa1 = 0;
	    if (this.kerroSektori(0).kerroNappulat()>0 &&
		this.kerroSektori(0).kerroNappula().kerroVari() == 1)
	        summa1 = this.kerroSektori(0).kerroNappulat();
	    if (this.kerroSektori(1).kerroNappulat()>0 &&
		this.kerroSektori(1).kerroNappula().kerroVari() == 1)
		summa1 = summa1 +  this.kerroSektori(1).kerroNappulat();
	    if(this.kerroSektori(2).kerroNappulat()>0 &&
	       this.kerroSektori(2).kerroNappula().kerroVari() == 1)
		summa1 = summa1 + this.kerroSektori(2).kerroNappulat();
	    if (this.kerroSektori(3).kerroNappulat()>0 &&
		this.kerroSektori(3).kerroNappula().kerroVari() == 1)
		summa1 = summa1 + this.kerroSektori(3).kerroNappulat(); 
	    if (this.kerroSektori(4).kerroNappulat()>0 &&
		this.kerroSektori(4).kerroNappula().kerroVari() == 1)
		summa1 = summa1 + this.kerroSektori(4).kerroNappulat();
	    if (this.kerroSektori(5).kerroNappulat()>0 &&
		this.kerroSektori(5).kerroNappula().kerroVari() == 1)
		summa1  = summa1 + this.kerroSektori(5).kerroNappulat();
	    if (this.kerroSektori(27).kerroNappulat()>0 &&
		this.kerroSektori(27).kerroNappula().kerroVari() == 1)
		summa1 = summa1 + this.kerroSektori(27).kerroNappulat();
	    
	    if (summa1 == 15) {
		return true;
	    }
	    else return false;
	}
	else return false;
    }

    public void asetaVuorossaOlija(int vuoro) {
	this.vuorossa = vuoro;
    }

    public int[] heitaNopat() {
	int[] nopat = new int[2];
       	this.silmaluku1 = this.heitaNoppaa();
       	this.silmaluku2 = this.heitaNoppaa();
	nopat[0] = this.silmaluku1;
	nopat[1] = this.silmaluku2;
	if (nopat[0] == nopat[1] && nopat[0] != 0) {
	    this.tuplat = true;
	    this.tuplanollaus = 2;
	}
	return nopat;
    }

    public int kerroVuorossaOlija() {
	return this.vuorossa;
    }

    public int kerroNoppa1() {
	return this.silmaluku1;
    }
    
    public void asetaNoppa1(int noppa1) {
	this.silmaluku1 = noppa1;
    }

    public void asetaNoppa2(int noppa2) {
	this.silmaluku2 = noppa2;
    }

    public int kerroNoppa2() {
	return this.silmaluku2;
    }

    public boolean onkoVuoroLoppu() {
	if (this.silmaluku1 == 0 && this.silmaluku2 == 0) {
	    return true;
	}
	else
	    return false;
    } 

    public int onkoVoittajaa() {
	if(sektorit[26].kerroNappulat() == 15) {
	    return 0;
	}
	if(sektorit[27].kerroNappulat() == 15) {
	    return 1;
	}    
	else 
	    return 2;
    }

    public int heitaNoppaa() {	
	return generator.nextInt(5) + 1;
    }

    public Sektori kerroSektori(int sektori) {
	return this.sektorit[sektori];
    }

    public boolean tulikoTuplat() {
	return this.tuplat;
    }

    public int kerroJaljellaOlevatTuplat() {	
	return (tuplanollaus);
    }



    public void nollaaSilmaluku(int silmaluku) {
	if (silmaluku == 1) {
	    if (this.tuplanollaus>0) {
		this.tuplanollaus--;
		if (this.tuplanollaus == 0) {
		    this.tuplat = false;
		}
	    }	    
	    else this.silmaluku1 = 0;	    	    
	}	
	if (silmaluku == 2) { 
	    if (this.tuplanollaus>0) {
		this.tuplanollaus--;
		if (this.tuplanollaus == 0) {
		    this.tuplat =false;
		}	
	    }
	    else this.silmaluku2 = 0;	    	    
	}	
    }
}
