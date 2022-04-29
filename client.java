package projet_gestion_supermarche;

public class client {
	
		int id_client;
		String nom;
		String adresse;
		int numero_de_telephone;
	    public client(int id, String nom, String adresse, int num) {
	    	this.id_client=id;
	    	this.nom=nom;
	    	this.adresse=adresse;
	    	this.numero_de_telephone=num;
	    }
	    public client(int id ) {
	    	this.id_client=id;
	    }
	    public int getid() {
	    	return id_client;
	    }
	    public String getnom() {
	    	return nom;
	    }
	    public String getadresse() {
	    	return adresse;
	    }
	    public int getnum() {
	    	return numero_de_telephone;
	    }
	    public void setid(int id) {
	    	this.id_client=id;
	    }
	    public void setnom(String nom) {
	    	this.nom=nom;
	    }
	    public void setadresse(String adresse) {
	    	this.adresse=adresse;
	    }
	    public void setnum(int num) {
	    	this.numero_de_telephone=num;
	    }
	    @Override
	    public String toString() {
			return this.id_client+"   "+this.nom+"   "+this.adresse+"   "+this.numero_de_telephone;
		}
	    
	}


