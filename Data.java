package projet_gestion_supermarche;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class Data {
	public static Connection  connection() throws ClassNotFoundException {
		String url ="jdbc:mysql://localhost:3306/supermarche";
		Connection connexion = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion=DriverManager.getConnection(url,"root","aseds");
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur");
			
		}
		return connexion;
	}
	public static void ajouter_produit(Produit produit) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete = "insert into produit values ("+produit.get_id()+','+"'"+produit.get_nomProduit()+"' ,"+"'"+produit.get_prixProduit()+"' ,"+produit.get_qteProduitExt()+");";
		int resultatreq = st.executeUpdate(requete);
	}
	public static void ajouter_client(client client) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete = "insert into client values ("+client.getid()+','+"'"+client.getnom()+"' ,"+"'"+client.getadresse()+"' ,"+client.getnum()+");";
		int resultatreq = st.executeUpdate(requete);
		
	}
	public static void ajouter_commande(commande commande) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete = "insert into commande values ("+commande.getid_cli()+','+"'"+commande.getid_prod()+"' ,"+""+commande.getid_comm()+" ,'"+commande.getdate()+"',"+commande.getquantite()+");";
		
		int resultatreq = st.executeUpdate(requete);
		
	}
	
	
	public static void main(String [] args) throws Exception {
		Connection cnx=Data.connection();
		Produit sidiali =new Produit(234,"sidi ali 1L", 6,1000);
		//ajouter_produit(sidiali);
		modifier_produit(sidiali,234,"sidi ali", 3,1000);
		achat(sidiali,3);
		Produit television =new Produit(239,"television", 5870860,1000);
		//ajouter_produit(television);
		modifier_produit(television,239,"television",2000000,20000);
		client hibakendi = new client(244,"hiba kendi "," al irfan bnsliman", 635324893) ;
		//ajouter_client(hibakendi);
		modifier_client(hibakendi,244,"abir kendi","oujda",684744533);
		commande commande_abir =new commande(1 ,234 ,244,2,new Date(2001,3,3));
		commande commande_abir2 =new commande(1 ,235 ,244 ,4,new Date(2001,3,3));
		int a=prix_total_client(1);
		String f= "C:/Users/pc/Desktop/workspace/commandes.txt";
		  Path p=Paths.get(f);
 		   File g=p.toFile();
 		   FileWriter fw=new FileWriter(f);
 		   PrintWriter pw = new PrintWriter(fw);
 			pw.println(hibakendi);
 			pw.close();
		//System.out.println(exist_client(5));
		//enregister_commande(commande_abir2 ,"C:/Users/pc/Desktop/workspace/commandes.txt");
		//(hibakendi ,"C:/Users/pc/Desktop/workspace/commandes.txt");
		
}
	public static void supprimer_produit(Produit produit) throws ClassNotFoundException, SQLException {
		Connection connexion = Data.connection();
		Statement st= connexion.createStatement();
		String requete = "delete from produit where id_produit="+produit.get_id()+";";
		
		int resultatreq = st.executeUpdate(requete);
}
	public static void supprimer_client(client client) throws ClassNotFoundException, SQLException {
		Connection connexion = Data.connection();
		Statement st= connexion.createStatement();
		String requete = "delete from client  where id_client="+client.getid()+";";
		int resultatreq = st.executeUpdate(requete);
		}
		public static void supprimer_commande(commande commande) throws ClassNotFoundException, SQLException {
			Connection connexion = Data.connection();
			Statement st= connexion.createStatement();
			String requete = "delete from commande  where id_client="+commande.getid_cli()+" and id_produit="+commande.getid_prod()+";";
			System.out.println(requete);
			int resultatreq = st.executeUpdate(requete);
			System.out.println(requete);
	}

	public static void modifier_produit(Produit produit, int id, String nom, int prix,int qte) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete = "update  produit set id_produit="+id+','+"nom_produit='"+nom+"' ,"+"prix_produit= "+prix+",qte_produit= "+qte+" where id_produit"+"="+produit.get_id();
		System.out.println(requete);
		int resultatreq = st.executeUpdate(requete);
	}
	public static void achat(Produit produit,int qte) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete ="select * from produit where id_produit="+produit.get_id();
		ResultSet resultatreq = st.executeQuery(requete);
		String nom= null;
		int prix =0;
		int qteext=0;
		
		while (resultatreq.next()) {
			nom = resultatreq.getString("nom_produit");
			prix = resultatreq.getInt("prix_produit");
			qteext = resultatreq.getInt("qte_produit");
			
		}
			
		
		modifier_produit(produit,produit.get_id(),nom,prix,qteext-qte);
	}
	public static void modifier_client(client client, int id, String nom, String adresse,int num) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete = "update  client set id_client="+id+','+"nom_client='"+nom+"' ,"+"adresse_client= '"+adresse+"' ,numero_de_telephone_client= "+num+" where id_client"+"="+client.getid();
		System.out.println(requete);
		int resultatreq = st.executeUpdate(requete);
	}
	public static void modifier_commande(commande commande, int id_comm, int id_prod, int id_cli,int qte, Date date) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		Statement st= connexion.createStatement();
		String requete = "update  commande set id_produit="+id_prod+','+"id_client="+id_cli+','+"id_commande= "+id_comm+",date_commande= "+date+",qte_commande= "+qte+" where id_produit"+"="+commande.getid_prod()+" and id_client="+commande.getid_cli();
		System.out.println(requete);
		int resultatreq = st.executeUpdate(requete);
	}
	public static int prix_total_client(int id)throws ClassNotFoundException, SQLException  {
		Connection connexion=Data.connection();
		int somme=0;
		Statement st= connexion.createStatement();
		String requete="select sum(p.prix_produit * c.qte_commande) as somme from produit p , commande c where p.id_produit=c.id_produit and id_commande="+id;
		System.out.println(requete);
		ResultSet resultatreq = st.executeQuery(requete);
		while (resultatreq.next()) {
			somme=resultatreq.getInt("somme");
		}
		return somme;
	}
	public static boolean exist_client  (int id) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		int somme=0;
		Statement st= connexion.createStatement();
		String requete ="select id_client from client where id_client="+id;
		ResultSet resultatreq = st.executeQuery(requete);
		while (resultatreq.next()) {
			somme=resultatreq.getInt("id_client");
			System.out.println(somme);
		}
		return somme!=0;	
	}
	public static boolean exist_produit  (int id) throws ClassNotFoundException, SQLException {
		Connection connexion=Data.connection();
		int somme=0;
		Statement st= connexion.createStatement();
		String requete ="select id_produit from produit where id_produit="+id;
		ResultSet resultatreq = st.executeQuery(requete);
		while (resultatreq.next()) {
			somme=resultatreq.getInt("id_produit");
			System.out.println(somme);
		}
		return somme!=0;	
	}
	public static void enregister_client(client c,String f) throws Exception {
		   Path p=Paths.get(f);
		   File g=p.toFile();
		   FileWriter fw=new FileWriter(f,true);
		   fw.write(c.getid());
		   fw.append("    ");
		   fw.write(c.getnom());
		   fw.append("    ");
		   fw.write(c.getadresse());
		   fw.append("    ");
		   fw.write(c.getnum());
		   fw.append("    ");
		   fw.append(" \n");
		   fw.flush();
		   fw.close();	  
	}
	public static void enregister_produit(Produit pro ,String f) throws Exception {
		   Path p=Paths.get(f);
		   File g=p.toFile();
		   FileWriter fw=new FileWriter(f,true)
				   ;
		   fw.write(pro.get_id());
		   fw.append("    ");
		   fw.write(pro.get_nomProduit());
		   fw.append("    ");
		   fw.write(pro.get_prixProduit());
		   fw.append("    ");
		   fw.write(pro.get_qteProduitExt());
		   fw.append("    ");
		   fw.append(" \n");
		   fw.close();
		  
	}
	public static void enregister_commande(commande c,String f) throws Exception {
		   Path p=Paths.get(f);
		   File g=p.toFile();
		   OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(g), StandardCharsets.UTF_8);
		   fw.write(c.getid_comm());
		   fw.append("    ");
		   fw.write(c.getid_prod());
		   fw.append("    ");
		   fw.write(c.getid_cli());
		   fw.append("    ");
		   fw.write(c.getquantite());
		   fw.append("    ");
		   
		   fw.append("    ");
		   fw.append(" \n");
		   fw.close();
		  
	}
	
	
	public static void enregister_objet(client c,String f) throws Exception {
		   Path p=Paths.get(f);
		   File g=p.toFile();
		   if (Files.exists(p)==false) {
			   File fichier=new File(f);
			   ObjectOutputStream os= new ObjectOutputStream(new FileOutputStream(f));
		   }
		   else {
			   ObjectOutputStream os= new ObjectOutputStream(new FileOutputStream(g, true));
		   }
		   ObjectOutputStream os= new ObjectOutputStream(new FileOutputStream(f));
		   os.writeObject(c);
		   os.writeChars("\n");
		   os.writeChars("n");
		   os.close();
	   }
	
}
