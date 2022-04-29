package projet_gestion_supermarche;

public class Produit {
	int id_produit;
	String nom_produit;
	int prix_produit;
	int qte_produit_ext;
	
	public Produit(int id_produit ,String nom_produit,int prix_produit, int qte_produit) {
		this.id_produit=id_produit;
		this.nom_produit=nom_produit;
		this.prix_produit= prix_produit;
		this.qte_produit_ext=qte_produit;
	}
	public Produit(int id) {
		this.id_produit=id;
	}
	public int get_id() {
		return this.id_produit;
	}
	public String get_nomProduit() {
		return this.nom_produit;
	}
	public int get_prixProduit() {
		return this.prix_produit;
	}
	public int get_qteProduitExt() {
		return this.qte_produit_ext;
	}
	public void set_id(int id) {
		this.id_produit=id;
	}
	public void set_nom(String nom) {
		this.nom_produit=nom;
	}
	public void set_prixProduit(int prix) {
		this.prix_produit=prix;
	}
	public void set_qteProduitExt(int qte) {
		this.qte_produit_ext=qte;
	}
	@Override
	public String toString() {
		return  "id produit: "+this.id_produit+"     nom produit: "+this.nom_produit+"     quantite: "+this.qte_produit_ext+"     prix: "+this.prix_produit;
	}
	
}
