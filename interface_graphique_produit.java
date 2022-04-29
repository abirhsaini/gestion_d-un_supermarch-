package projet_gestion_supermarche;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class interface_graphique_produit extends Application  {
	public static void main(String [] args) {
    	interface_graphique_produit.launch(args);
    }
	public void start(Stage fenetre) throws Exception {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(52,52,52,52));
		Label id_produit = new Label("id du produit: ");
		grid.add(id_produit, 0, 0);
		
		TextField id_produit_txt=new TextField();
		grid.add(id_produit_txt, 1, 0);
		
		Label nom_produit = new Label("nom du produit: ");
		grid.add(nom_produit, 0, 1);
		
		grid.setHgap(14);
		
		TextField nom_produit_txt=new TextField();
		grid.add(nom_produit_txt, 1, 1);
		
		grid.setVgap(20);
		
		Label prix_produit = new Label("prix du produit : ");
		grid.add(prix_produit, 0, 2);
		
		TextField prix_produit_txt=new TextField();
		grid.add(prix_produit_txt, 1, 2);
		
		
		Label qte_produit_ext = new Label("quantité du produit existante : ");
		grid.add(qte_produit_ext, 0, 3);
		
		TextField qte_produit_ext_txt=new TextField();
		grid.add(qte_produit_ext_txt, 1, 3);
		
		final Text at1=new Text();
		grid.add(at1, 1, 4);
		final Text at2=new Text();
		grid.add(at2, 1, 5);
		final Text at3=new Text();
		grid.add(at3, 1, 6);
		final Text at4=new Text();
		grid.add(at4, 1, 7);
		
		Button ajouter_produit = new Button("ajouter ce produit");
		grid.add(ajouter_produit, 0, 4);
		EventHandler<ActionEvent> event2 =new EventHandler<ActionEvent>  () {
			public void handle(ActionEvent evt) {
				Produit produit = null;
				try {
					produit = new Produit(Integer.parseInt(id_produit_txt.getText()),nom_produit_txt.getText(),Integer.parseInt(prix_produit_txt.getText()),Integer.parseInt(qte_produit_ext_txt.getText()));
				} catch (NumberFormatException e1) {
					
					e1.printStackTrace();
				}
				try {
					Data.ajouter_produit(produit);
					at1.setText(" produit ajoute");
					System.out.println("produit ajoute");
				} catch (ClassNotFoundException | SQLException e) {
					at1.setText(" produit non ajoute");
					e.printStackTrace();
					
				}
				at4.setText("");
				at2.setText("");
				at3.setText("");
			}
		};
		ajouter_produit.setOnAction(event2);
		Button supprimer_produit= new Button("supprimer produit");
		grid.add(supprimer_produit, 0, 5);
		EventHandler<ActionEvent> event3 =new EventHandler<ActionEvent>  () {
			public void handle(ActionEvent evt) {
				Produit produit = null;
				try {
					produit = new Produit(Integer.parseInt(id_produit_txt.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
				Data.supprimer_produit(produit);
					at2.setText(" produit suprime");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					at2.setText(" produit suprime");
				}
				at1.setText("");
				at4.setText("");
				at3.setText("");
			}
			
		};
			
		supprimer_produit.setOnAction(event3);
	Button modifier_produit= new Button("modifier un produit");
	grid.add(modifier_produit, 0, 6);
	EventHandler<ActionEvent> event4 =new EventHandler<ActionEvent>  () {
		public void handle(ActionEvent evt) {
			Produit produit = null;
			try {
				produit = new Produit(Integer.parseInt(id_produit_txt.getText()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				at3.setText("le produit est modifié");
				e1.printStackTrace();
			}
			try {
				if (Data.exist_produit(Integer.parseInt(id_produit_txt.getText()))) {
					at3.setText("le produit est modifié");
					Data.modifier_produit(produit,Integer.parseInt(id_produit_txt.getText()),nom_produit_txt.getText(),Integer.parseInt(prix_produit_txt.getText()),Integer.parseInt(qte_produit_ext_txt.getText()));
				}
				else {
					at3.setText("le produit n'existe pas");
				}
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			at1.setText("");
			at2.setText("");
			at4.setText("");
		}
	};
;


	
    modifier_produit.setOnAction(event4);
	Scene scene = new Scene(grid,500,500);	
	

	Button ecrire_produit= new Button("ecrire  produit");
	grid.add(ecrire_produit, 0, 7);
	
	EventHandler<ActionEvent> event6 =new EventHandler<ActionEvent>  () {
		public void handle(ActionEvent evt) {
		Produit produit=null;
			try {
				produit = new Produit(Integer.parseInt(id_produit_txt.getText()),nom_produit_txt.getText(),Integer.parseInt(prix_produit_txt.getText()),Integer.parseInt(qte_produit_ext_txt.getText()));
				
				String f= "C:\\Users\\pc\\Desktop\\workspace\\produit";
				  Path p=Paths.get(f);
	      		   File g=p.toFile();
	      		   FileWriter fw=new FileWriter(f,true);
	      		   PrintWriter pw = new PrintWriter(fw);
	      			pw.println(produit);
	      			pw.close();
				
				 at4.setText("produit est ecrit ");
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 at4.setText("produit n est pas  ecrit ");
			}
			at1.setText("");
			at2.setText("");
			at3.setText("");
			
		}
	};
	ecrire_produit.setOnAction(event6);

	
	fenetre.setScene(scene);
	fenetre.setTitle("gestion des produits");
	fenetre.show();
}

}
