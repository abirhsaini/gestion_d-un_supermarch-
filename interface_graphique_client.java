package projet_gestion_supermarche;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class interface_graphique_client extends Application{
	@Override
	public void start(Stage fenetre) throws Exception{
		fenetre.setTitle("Gestion des client");
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(25, 25, 25, 25));
		gp.setVgap(10);
		gp.setHgap(10);		
		Label lb1=new Label("id_client:");
		gp.add(lb1, 0, 0);
		
		Label lb2=new Label("nom:");
		gp.add(lb2, 0, 1);
		Label lb3=new Label("adresse:");
		gp.add(lb3, 0, 2);
		
		Label lb4=new Label("numero de telephone:");
		gp.add(lb4, 0, 3);
		
		
		TextField txt1= new TextField();
		gp.add(txt1, 1, 0);
		
		TextField txt2= new TextField();
		gp.add(txt2, 1, 1);
		
		TextField txt3= new TextField();
		gp.add(txt3, 1, 2);

		TextField txt4= new TextField();
		gp.add(txt4, 1, 3);
		
		Button bt1= new Button("Ajouter un  client");
		gp.add(bt1, 0, 4);
		
		Button bt2= new Button("Supprimer un client");
		gp.add(bt2, 0, 5);
		
		Button bt3= new Button("modiffier un client");
		gp.add(bt3, 0, 6);
		
		
		final Text at1=new Text();
		gp.add(at1, 1, 4);
		
		final Text at2=new Text();
		gp.add(at2, 1,5);
		
		final Text at3=new Text();
		gp.add(at3, 1, 6);
		
		final Text at4=new Text();
		gp.add(at4, 1, 7);
		
		
		
		bt1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				client client=new client(Integer.parseInt(txt1.getText()),txt2.getText(),txt3.getText(),Integer.parseInt(txt4.getText()));
				try {
					Data.ajouter_client(client);
					at1.setText("le client est ajouté");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					at1.setText("le client existe deja");
				}
				
				at2.setText("");
				at3.setText("");
				at4.setText("");
		        
			}
		});
		
		
		bt2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				client client=new client(Integer.parseInt(txt1.getText()),txt2.getText(),txt3.getText(),Integer.parseInt(txt4.getText()));
				try {
					Data.supprimer_client(client);
					at2.setText("le client estsupprimé");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				at4.setText("");
				at1.setText("");
				at3.setText("");
			}
			
		});
		
		
		bt3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				client client=new client(Integer.parseInt(txt1.getText()));
		
				try {
					if (Data.exist_client(Integer.parseInt(txt1.getText()))) {
						at3.setText("le client est modifié");
						Data.modifier_client(client,Integer.parseInt(txt1.getText()),txt2.getText(),txt3.getText(),Integer.parseInt(txt4.getText()));
					}
					else {
						at3.setText("le client n'existe pas");
						at2.setText("");
						at1.setText("");
					}
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				at4.setText("");
				at2.setText("");
				at1.setText("");
			}
		});
		
		Button ecrire_produit= new Button("ecrire  commande");
		gp.add(ecrire_produit, 0, 7);
		
		EventHandler<ActionEvent> event6 =new EventHandler<ActionEvent>  () {
			public void handle(ActionEvent evt) {
			client client=null;
				try {
					client=new client(Integer.parseInt(txt1.getText()),txt2.getText(),txt3.getText(),Integer.parseInt(txt4.getText()));
					
					String f= "C:\\Users\\pc\\Desktop\\workspace\\client";
					  Path p=Paths.get(f);
		      		   File g=p.toFile();
		      		   FileWriter fw=new FileWriter(f,true);
		      		   PrintWriter pw = new PrintWriter(fw);
		      			pw.println(client);
		      			pw.close();
					
					 at4.setText("client est ecrit ");
					
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 at4.setText("client n est pas  ecrit ");
				}
				at1.setText("");
				at2.setText("");
				at3.setText("");
				
			}
		};
		ecrire_produit.setOnAction(event6);

		
		Scene scene=new Scene(gp, 400, 400);
		fenetre.setScene(scene);
		fenetre.show();
	}
	
		
	
	public static void main(String[] args) {
		interface_graphique_client.launch(args);
	}

}