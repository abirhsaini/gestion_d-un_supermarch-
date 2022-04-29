package projet_gestion_supermarche;
import java.sql.Date;
public class commande {
	

	
		int id_commande;
		int id_produit;
		int id_client;
		int quantite;
		Date date;
		public commande(int id_commande, int id_produit, int id_client, int quantite, Date date) {
			this.id_commande=id_commande;
			this.id_produit=id_produit;
			this.id_client=id_client;
			this.quantite=quantite;
			this.date=date;
			
		}
		public commande(int id ) {
			this.id_commande=id;
		}
		public int getid_comm() {
			return this.id_commande;
		}
		public int getid_prod(){
			return this.id_produit;
		}
		public int getid_cli(){
			return this.id_client;
		}
		public int getquantite(){
			return this.quantite;
		}
		public Date getdate(){
			return this.date;
		}
		public void setid_prod(int id) {
			this.id_produit=id;
		}
		public void setid_cli(int id) {
			this.id_client=id;
		}
		public void setid_comm(int id) {
			this.id_commande=id;
		}
		public void setquantite(int quantite) {
			this.quantite=quantite;
		}
		public void setdate(Date date) {
				this.date=date;
		
		}
		
		@Override
		public String toString() {
			return  "id commande :"+this.id_commande+"     id client: "+this.id_client+"      id produit: "+this.id_produit+"     quantite: "+this.quantite+"     date: "+this.date;
		}
		
	}

