package projet_gestion_supermarche;

import java.sql.SQLException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.sql.Time;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class interface_graphique_commande extends Application {
	public static void main(String [] args) {
    	interface_graphique_produit.launch(args);
    }
	public void start(Stage fenetre) throws Exception {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(52,52,52,52));
		Label id_commande = new Label("id du commande: ");
		grid.add(id_commande, 0, 0);
		
		TextField id_commande_txt=new TextField();
		grid.add(id_commande_txt, 1, 0);
		
		Label id_client = new Label("id du client: ");
		grid.add(id_client, 0, 1);
		
		grid.setHgap(14);
		
		
		
		TextField id_client_txt=new TextField();
		grid.add(id_client_txt, 1, 1);
		
		grid.setVgap(20);
		
		Label id_produit = new Label("id du produit : ");
		grid.add(id_produit, 0, 2);
		
		TextField id_produit_txt=new TextField();
		grid.add(id_produit_txt, 1, 2);
		
		
		Label qte_commande = new Label("quantité du commande: ");
		grid.add(qte_commande, 0, 3);
		
		TextField qte_commande_txt=new TextField();
		grid.add(qte_commande_txt, 1, 3);
		
	
		
		Button ajouter_commande = new Button("ajouter cette commande");
		grid.add(ajouter_commande, 0, 4);
		
		final Text at1=new Text();
		grid.add(at1, 1, 4);
		
		final Text at2=new Text();
		grid.add(at2, 1,5);
		
		final Text at3=new Text();
		grid.add(at3, 1, 6);
		
		final Text at4=new Text();
		grid.add(at4, 1, 7);
		
		
		final Text at5=new Text();
		grid.add(at5, 1, 8);
		
		
		EventHandler<ActionEvent> event2 =new EventHandler<ActionEvent>  () {
			public void handle(ActionEvent evt) {
				commande commande = null;
				final Date date = new Date(0);
				Produit produit=null;
				try {
					produit= new Produit(Integer.parseInt(id_produit_txt.getText())
							);
					commande = new commande(Integer.parseInt(id_commande_txt.getText()),Integer.parseInt(id_produit_txt.getText()),Integer.parseInt(id_client_txt.getText()),Integer.parseInt(qte_commande_txt.getText()),date);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					
				}
				try {
					Data.ajouter_commande(commande);
					Data.achat(produit,Integer.parseInt(qte_commande_txt.getText()));
					at1.setText("la commande  est ajoutée");
				} catch (ClassNotFoundException | SQLException e) {
					at1.setText("la commande  existe deja");
					e.printStackTrace();
					
				}
				at2.setText("");
				at3.setText("");
				at4.setText("");
				at5.setText("");
			}
		};
		ajouter_commande.setOnAction(event2);
		
		Button supprimer_commande= new Button("supprimer commande");
		grid.add(supprimer_commande, 0, 5);
		EventHandler<ActionEvent> event3 =new EventHandler<ActionEvent>  () {
			public void handle(ActionEvent evt) {
				commande commande = null;
				final Date date = new Date(0);
				try {
					commande = new commande(Integer.parseInt(id_commande_txt.getText()),Integer.parseInt(id_produit_txt.getText()),Integer.parseInt(id_client_txt.getText()),Integer.parseInt(qte_commande_txt.getText()),date);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Data.supprimer_commande(commande);
					at2.setText("commande supprimee");
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					at2.setText("il n existe pas");
				}
				at1.setText("");
				at3.setText("");
				at4.setText("");
				at5.setText("");
			}
		};
		
		supprimer_commande.setOnAction(event3);
		
	Button modifier_commande= new Button("modifier une commande");
	grid.add(modifier_commande, 0, 6);
	EventHandler<ActionEvent> event4 =new EventHandler<ActionEvent>  () {
		public void handle(ActionEvent evt) {
			commande commande = null;final Date date = new Date(0);
			
			try {
				commande = new commande(Integer.parseInt(id_commande_txt.getText()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				
					
					Data.modifier_commande(commande,Integer.parseInt(id_commande_txt.getText()),Integer.parseInt(id_produit_txt.getText()),Integer.parseInt(id_client_txt.getText()),Integer.parseInt(qte_commande_txt.getText()),date);
					at3.setText("la commande  est modifiée");
				}
				
			 catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				at3.setText("le commande n'existe pas");
			}
			at1.setText("");
			at2.setText("");
			at4.setText("");
			at5.setText("");
		}
	};
	
	
	
    modifier_commande.setOnAction(event4);
	Scene scene = new Scene(grid,500,500);
	
	

	Button prix_total= new Button("prix total");
	grid.add(prix_total, 0, 7);
	
	EventHandler<ActionEvent> event5 =new EventHandler<ActionEvent>  () {
		public void handle(ActionEvent evt) {
		int somme=0;
			try {
				
				 somme= Data.prix_total_client(Integer.parseInt(id_commande_txt.getText()));
				 at4.setText(String.valueOf(somme));
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			at1.setText("");
			at2.setText("");
			at3.setText("");
			at5.setText("");
		}
	};
	prix_total.setOnAction(event5);
	
	
	Button ecrire_commande= new Button("ecrire  commande");
	grid.add(ecrire_commande, 0, 8);
	
	EventHandler<ActionEvent> event6 =new EventHandler<ActionEvent>  () {
		public void handle(ActionEvent evt) {
		Date date = null;
			try {
				
				commande commande = new commande(Integer.parseInt(id_commande_txt.getText()),Integer.parseInt(id_produit_txt.getText()),Integer.parseInt(id_client_txt.getText()),Integer.parseInt(qte_commande_txt.getText()),date);
				String f= "C:\\Users\\pc\\Desktop\\workspace\\commandes";
				  Path p=Paths.get(f);
	      		   File g=p.toFile();
	      		   FileWriter fw=new FileWriter(f,true);
	      		   PrintWriter pw = new PrintWriter(fw);
	      			pw.println(commande);
	      			pw.close();
				
				 at5.setText("commande est ecrite ");
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 at5.setText("commande n est pas  ecrite ");
			}
			at1.setText("");
			at2.setText("");
			at3.setText("");
			at4.setText("");
		}
	};
	ecrire_commande.setOnAction(event6);
	
	
	fenetre.setScene(scene);
	fenetre.setTitle("gestion des commandes");
	fenetre.show();
}
}