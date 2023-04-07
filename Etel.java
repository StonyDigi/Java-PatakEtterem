package patakEtterem;

public class Etel {
	
	private String azonosito;
	private String nev;
	private Byte kategoriaId;
	private Integer egysegar;
	private Integer eladottMennyiseg;
	
	public Etel(String azonosito, String nev, Byte kategoriaId, Integer egysegar, Integer eladottMennyiseg) {
		
		this.azonosito = azonosito;
		this.nev = nev;
		this.kategoriaId = kategoriaId;
		this.egysegar = egysegar;
		this.eladottMennyiseg = eladottMennyiseg;
	}

	public String getAzonosito() {
		return azonosito;
	}

	public String getNev() {
		return nev;
	}

	public Byte getKategoriaId() {
		return kategoriaId;
	}

	public Integer getEgysegar() {
		return egysegar;
	}

	public Integer getEladottMennyiseg() {
		return eladottMennyiseg;
	}
	public String toString() {
		return this.azonosito + ";"+this.nev + ";"+this.kategoriaId + ";"+this.egysegar + ";"+this.eladottMennyiseg;
		
	}
	public String getKategoriaSzoveg() {
		String kategoriaSzoveg = null;
		switch (this.kategoriaId) {
		case 1:kategoriaSzoveg = "leves";
		break;
		case 2:kategoriaSzoveg = "fõétel";
		break;
		case 3:kategoriaSzoveg = "desszert";
		break;

		default:kategoriaSzoveg = "hibás adat";
			break;
		}
		return kategoriaSzoveg;
	}
	
	
	

}
