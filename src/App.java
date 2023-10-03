import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList; 


class Cour {
    private double id;
    private String nom;
    private String description;
    private double coef;

    public Cour(double id, String nom, String description, double coef) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.coef = coef;
    }

    public String getNom() {
      return this.nom;
    }

    public String getDescription() {
      return this.description;
    }

    public double getCoef() {
      return this.coef;
    }

    public double getId() {
      return this.id;
    }

}

public class App extends JFrame implements ActionListener {


  private static ArrayList<Cour> listeCours = new ArrayList<Cour>();
  private static DefaultTableModel tableModel;
  private static JTable coursTable;

  public static void createTable() {
    String[] columnNames = {"ID", "Nom", "Description", "Coefficient"};
    tableModel = new DefaultTableModel(columnNames, 0);
    coursTable = new JTable(tableModel); 
  }

  
  

  public static void listerCours() {
    System.out.println("Liste des cours :");
    for (Cour cours : listeCours) {
        System.out.println("ID : " + cours.getId());
        System.out.println("Nom : " + cours.getNom());
        System.out.println("Description : " + cours.getDescription());
        System.out.println("Coefficient : " + cours.getCoef());
        System.out.println("------------------------");
    }
  }

  public static void confirmer(){
    System.out.println(" je suis ici ");
    JFrame frameAjouterCour= new JFrame("Message");
    frameAjouterCour.setSize(400, 150);
    frameAjouterCour.setResizable(false);
    frameAjouterCour.setLocationRelativeTo(null);
    frameAjouterCour.setVisible(true);      
    JPanel panelConfirmation = new JPanel(); 
    JLabel cours  = new JLabel("Cours Ajouté");
    JButton boutonAjouter = new JButton("OK");
    panelConfirmation.add(cours); 
    panelConfirmation.add(boutonAjouter); 

    boutonAjouter.addActionListener(new ActionListener() {
      public void actionPerformed( ActionEvent e){
        frameAjouterCour.dispose();
        listerCours();
      }
    });
    frameAjouterCour.add(panelConfirmation);
  } 

  public static void ajouterCours(){ 
    System.out.println(" je suis ici ");
    JFrame frame= new JFrame("Ajouter cours");
    frame.setSize(400, 300);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    JPanel panelBase = new JPanel(); 
    panelBase.setLayout(new GridLayout(5,2));
    JLabel idCours = new JLabel("id cours :");
    JLabel NomDuCours  = new JLabel("Nom du cour :");
    JLabel descriptionDuCour  = new JLabel("Description du cours :");
    JLabel coefCours  = new JLabel("Coef du cours :");

    JTextField fildCours = new JTextField(); 
    JTextField fildName = new JTextField(); 
    JTextField fildDescription = new JTextField(); 
    JTextField fildCoef= new JTextField(); 

    fildCours.setBounds(20,20,200,30);

    panelBase.add(idCours);
    panelBase.add(fildCours); 

    panelBase.add(NomDuCours);
    panelBase.add(fildName); 

    panelBase.add(descriptionDuCour);
    panelBase.add(fildDescription); 

    panelBase.add(coefCours);
    panelBase.add(fildCoef);

    JButton boutonAjouter = new JButton("Ajouter");
    JButton boutonFermer = new JButton("Fermer");

    panelBase.add(boutonAjouter); 
    panelBase.add(boutonFermer); 

    boutonAjouter.addActionListener(new ActionListener() {
      public void actionPerformed( ActionEvent e){
        // recupration des information du cours 
        double idCour = Integer.parseInt(fildCours.getText()); 
        String nom = fildName.getText(); 
        String description = fildDescription.getText(); 
        double coef = Integer.parseInt(fildCoef.getText());
        // creation de notre nouveau cour 
        Cour nouveauCour = new Cour(idCour,nom,description,coef); 
        // j'ajoute a la liste de cours 
        listeCours.add(nouveauCour); 

        // Ajouter le cours à la table
        Object[] rowData = {idCour, nom, description, coef};
        tableModel.addRow(rowData);

        fildCours.setText("");
        fildName.setText("");
        fildDescription.setText("");
        fildCoef.setText("");

        confirmer();  
      }

    } );
      
    boutonFermer.addActionListener(new ActionListener() {
        public void actionPerformed( ActionEvent e){
            frame.dispose();
        }
    } );
    frame.add(panelBase);

  }


  public static void main(String[] args) {
    JFrame frame= new JFrame("Gestionnaire de cours");
    frame.setSize(800, 600);
    frame.setResizable(false);
    JMenu menuItem, menuItem1, menuItem2, menuItem3, menuItem4/*, submenuItem*/;
    JMenuItem itm1, itm2, itm3;
    
    // Créez la table et ajoutez-la à un JScrollPane
    createTable();
    frame.add(new JScrollPane(coursTable));

    itm1=new JMenuItem("Ajouter");
    itm1.addActionListener(new ActionListener() {
      public void actionPerformed( ActionEvent e){
        ajouterCours();
      }
    });
    itm2=new JMenuItem("Lister");
    itm3=new JMenuItem("Supprimer");

    JMenuBar mbar=new JMenuBar();

    menuItem=new JMenu("Fichier");
    menuItem2=new JMenu("Etudiants"); 
    menuItem3=new JMenu("Connexion");
    menuItem4=new JMenu("A propos");

    menuItem1=new JMenu("Cours");

    menuItem1.add(itm1);
    menuItem1.add(itm2);	   
    menuItem1.add(itm3);



    mbar.add(menuItem);
    mbar.add(menuItem1);
    mbar.add(menuItem2);
    mbar.add(menuItem3);
    mbar.add(menuItem4);



    frame.setJMenuBar(mbar);

    frame.setLocationRelativeTo(null);

    frame.setVisible(true);

  }
  @Override
  public void actionPerformed(ActionEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
  }
}