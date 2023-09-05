public class Pelaa {

    Pelilauta pelilauta;

    public Pelaa() {
	pelilauta = new Pelilauta();
    }

    public void aja() {
	pelilauta.pelaa("uusi");
    }
    
    public static void main(String[] komentoriviparametrit) {
	Pelaa pelaa = new Pelaa();
	pelaa.aja();
    }

    
}
