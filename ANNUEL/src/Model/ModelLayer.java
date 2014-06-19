package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BD.Connexion;
import BD.InsertData;
import BD.SelectData;
import BD.UpdateData;
import BusinessClass.Planete;
import BusinessClass.User;
import BusinessClass.Usine;
import BusinessClass.Vaisseau;

public class ModelLayer {
	private List<User> oListUser = new ArrayList<User>();
	private List<Vaisseau> oListVaisseau = new ArrayList<Vaisseau>();
	private List<Usine> oListUsine = new ArrayList<Usine>();
	private List<Planete> oListPlanete = new ArrayList<Planete>();
	private Connexion  con = new Connexion("QAWI","localhost","root","root");
	public ModelLayer(){
		con.connect();
	}
	public String getTypeVaisseau(){
		/**
		 * 1/ Se connecter a la base	
		 * 2/ Recuperer un type de vaisseau
		 * 3/ Envoyer un type de vaisseau grace a la classe Vaisseau
		 */
		SelectData select = new SelectData(con.getConnection(),""
				+ "SELECT nom_type_vaisseau"
				+ "FROM vaisseau , type_vaisseau, planete "
				+ "WHERE planete.id_planete = vaisseau.id_planete ");
		System.out.println("Recuperer le type de vaisseau en fonction de la personne : "+ select.getResultatReqString());
		return select.getResultatReqString();
	}
	/**
	 * Recupere tous les utilisateurs en BDD
	 * @return liste d'utilisateurs
	 */
	public List<User> getAllUser(){
		try {
		SelectData select = new SelectData(con.getConnection(),""
				+ "SELECT *"
				+ "FROM utilisateur");
		ResultSet res = select.getResults();
				if(res.first() !=  false){
					
					do{
						User currentUser = new User();
						currentUser.setId(Integer.toString(res.getInt("id_util")));
						currentUser.setPseudo(res.getString("nom_util"));
						currentUser.setEmail(res.getString("email_util"));
						currentUser.setMdp(res.getString("mdp_util"));
						currentUser.setPoint(res.getInt("point_util"));
						currentUser.setIdPlanete(res.getString("id_planete"));
						oListUser.add(currentUser);
					}while(res.next());
				}
			}catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		return oListUser;
	}
	/**
	 * Recupere toutes les usines en BDD
	 * @return liste des Usines
	 */
	public List<Usine> getAllUsine(){
		try {
		SelectData select = new SelectData(con.getConnection(),""
				+ "SELECT *"
				+ "FROM usine");
		ResultSet res = select.getResults();
				if(res.first() !=  false){
					do{
						Usine currentUsine = new Usine();
						currentUsine.setId_type_usine(Integer.toString(res.getInt("id_type_usine")));
						currentUsine.setId_usine(Integer.toString(res.getInt("id_usine")));
						currentUsine.setNiveau(Integer.toString(res.getInt("niveau_usine")));
						currentUsine.setProd_usine(Integer.toString(res.getInt("prod_usine")));
						currentUsine.setPrix_usine(Integer.toString(res.getInt("prix_usine")));
						oListUsine.add(currentUsine);
					}while(res.next());
				}
			}catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		return oListUsine;
	}
	/**
	 * Recupere toutes les planetes en BDD
	 * @return liste des planetes
	 */
	public List<Planete> getAllPlanete(){
		try {
		SelectData select = new SelectData(con.getConnection(),""
				+ "SELECT *"
				+ "FROM planete");
		ResultSet res = select.getResults();
				if(res.first() !=  false){
					do{
						Planete currentPlanete = new Planete();
						currentPlanete.setId_planete(Integer.toString(res.getInt("id_planete")));
						currentPlanete.setId_util(Integer.toString(res.getInt("id_util")));
						currentPlanete.setQte_or(res.getInt("qte_or"));
						currentPlanete.setQte_argent(res.getInt("qte_argent"));
						currentPlanete.setQte_pierre(res.getInt("qte_pierre"));
						currentPlanete.setQte_nourriture(res.getInt("qte_nourriture"));
						currentPlanete.setNom_planete(res.getString("nom_planete"));
						oListPlanete.add(currentPlanete);
					}while(res.next());
				}
			}catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		return oListPlanete;
	}
	/**
	 * Montre les utilisateurs
	 */
	public void displayAllUser(){
		for (User usr : oListUser){
			System.out.println("ID USER  --->   "+ usr.getId());
			System.out.println("ID PLANETE  --->   "+usr.getIdPlanete());
			System.out.println("PSEUDO  --->   "+usr.getPseudo());
			System.out.println("MAIL  --->   "+usr.getEmail());
			System.out.println("MOT DE PASSE  --->   "+usr.getMdp());
			System.out.println("NOMBRE DE POINTS  --->   "+usr.getPoint());
			System.out.println("----------");
		}
	}
	/**
	 * Renvois la liste des vaisseaux d'un joueur
	 * @param oUser
	 * @return Liste de vaisseau
	 */
	public List<Vaisseau> getAllVaisseauByUser(User oUser){
		try {
		SelectData select = new SelectData(con.getConnection(),""
				+ "SELECT vaisseau.id_vaisseau,type_vaisseau.nom_type_vaisseau,vaisseau.id_type_vaisseau "
				+ "FROM vaisseau,type_vaisseau,planete,utilisateur "
				+ "WHERE vaisseau.id_type_vaisseau = type_vaisseau.id_type_vaisseau "
				+ "AND planete.id_planete = utilisateur.id_planete "
				+ "AND utilisateur.id_util = 0 ");
		ResultSet res = select.getResults();
				if(res.first() !=  false){
					do{
						Vaisseau currentVaisseau = new Vaisseau();
						currentVaisseau.setId(Integer.toString(res.getInt("id_vaisseau")));
						currentVaisseau.setName(res.getString("nom_type_vaisseau"));
						currentVaisseau.setTypeVaisseau(Integer.toString(res.getInt("id_type_vaisseau")));
						oListVaisseau.add(currentVaisseau);
					}while(res.next());
				}
			}catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		return oListVaisseau;
	}
	/**
	 * Montre les vaisseaux
	 */
	public void displayAllVaisseau(){
		for (Vaisseau vs : oListVaisseau){
			System.out.println("ID VAISSEAU  --->   "+vs.getId());
			System.out.println("NOM DU VAISSEAU  --->   "+vs.getName());
			System.out.println("TYPE DE VAISSEAU  --->   "+vs.getTypeVaisseau());
			System.out.println("----------");
		}
	}
	/**
	 * Renvois la liste des usines d'un joueur
	 * @param oUser
	 * @return Liste d'usine
	 */
	public List<Usine> getAllUsineByUser(User usr){
		try {
		SelectData select = new SelectData(con.getConnection(),""
				+ "SELECT usine.prix_usine,usine.id_usine,usine.id_type_usine,type_usine.nom_type_usine,usine.prod_usine,usine.niveau_usine "
				+ "FROM usine,type_usine,planete,utilisateur "
				+ "WHERE type_usine.id_type_usine = usine.id_type_usine "
				+ "AND planete.id_planete = usine.id_planete "
				+ "AND planete.id_util = utilisateur.id_util "
				+ "AND utilisateur.id_util = "+usr.getId()+" ");
		ResultSet res = select.getResults();
				if(res.first() !=  false){
					do{
						Usine currentUsine = new Usine();
						currentUsine.setId_usine(Integer.toString(res.getInt("id_usine")));
						currentUsine.setId_type_usine(Integer.toString(res.getInt("id_type_usine")));
						currentUsine.setNom_type_usine(res.getString("nom_type_usine"));
						currentUsine.setProd_usine(Integer.toString(res.getInt("prod_usine")));
						currentUsine.setNiveau(Integer.toString(res.getInt("niveau_usine")));
						currentUsine.setPrix_usine(Integer.toString(res.getInt("prix_usine")));
						oListUsine.add(currentUsine);
					}while(res.next());
				}
				return oListUsine;
			}catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		return oListUsine;
	}
	/**
	 * Montre les usines d'un joueur
	 */
	public void displayAllUsine(){
		for (Usine usine : oListUsine){
			System.out.println("ID USINE  --->   "+usine.getId_usine());
			System.out.println("ID TYPE USINE  --->   "+usine.getId_type_usine());
			System.out.println("NIVEAU USINE  --->   "+usine.getNiveau());
			System.out.println("PRODUCTION  --->   "+usine.getProd_usine());
			System.out.println("PRIX PROCHAIN NIVEAU  --->   "+usine.getPrix_usine());
			System.out.println("TYPE D'USINE  --->   "+usine.getNom_type_usine());
			System.out.println("----------");
		}
	}
	public void insertVaisseau(User oUser,Vaisseau vaiss){
		String table = "vaisseau";
		String[] field = {"id_vaisseau","id_planete","id_type_vaisseau"};
		String[] val = {null,oUser.getIdPlanete(),vaiss.getTypeVaisseau()};
		InsertData ins = new InsertData(con.getConnection(),field,table,val);
	}
	public void monterNiveauUsine(User oUser,Usine us){
		int newNiveau = Integer.parseInt(us.getNiveau())+1;
		UpdateData upd = new UpdateData(con.getConnection(),
		" UPDATE usine "
		+"SET usine.niveau_usine = "+newNiveau+" "
		+"WHERE usine.id_planete = "+oUser.getIdPlanete()+" "
		+"AND usine.id_usine = "+us.getId_usine());
	}
	/**
	 * PRODUCTION DES RESSOURCES DYNAMIQUE
	 */
	public void addRessourcesAllUsine(){
		List<Usine> listeUsines = new ArrayList<Usine>();
		List<Planete> listePlanetes = new ArrayList<Planete>();
		listeUsines = getAllUsine();
		listePlanetes = getAllPlanete();
		for(Planete pl : listePlanetes){
			for (Usine farm : listeUsines){
				switch(farm.getId_type_usine()){
					case "0" :	// AJOUT DE L'OR
								UpdateData updOr = new UpdateData(con.getConnection(),
								" UPDATE planete JOIN usine ON planete.id_planete = usine.id_planete "
								+"SET planete.qte_or = "+pl.getQte_or()+" + "+farm.getProd_usine()+" "
								+"WHERE usine.id_planete = planete.id_planete "
								+"AND usine.id_type_usine = 0 ");
								break;
								
					case "1" : //AJOUT DE L'ARGENT
								UpdateData updArgent = new UpdateData(con.getConnection(),
								" UPDATE planete JOIN usine ON planete.id_planete = usine.id_planete "
								+"SET planete.qte_argent = "+pl.getQte_argent()+" + "+farm.getProd_usine()+" "
								+"WHERE usine.id_planete = planete.id_planete "
								+"AND usine.id_type_usine = 1 ");
								break;
					case "2" : 	//AJOUT DE LA PIERRE
								UpdateData updPierre = new UpdateData(con.getConnection(),
								" UPDATE planete JOIN usine ON planete.id_planete = usine.id_planete "
								+"SET planete.qte_pierre = "+pl.getQte_pierre()+" + "+farm.getProd_usine()+" "
								+"WHERE usine.id_planete = planete.id_planete "
								+"AND usine.id_type_usine = 2 ");
								break;
					case "3" :  //AJOUT DE LA NOURRITURE
								UpdateData updNourriture = new UpdateData(con.getConnection(),
								" UPDATE planete JOIN usine ON planete.id_planete = usine.id_planete "
								+"SET planete.qte_nourriture = "+pl.getQte_nourriture()+" + "+farm.getProd_usine()+" "
								+"WHERE usine.id_planete = planete.id_planete "
								+"AND usine.id_type_usine = 3 ");
								break;
				}
				
				
			}
		}
		
	}
}