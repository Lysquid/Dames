import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    Joueur J1 = new Joueur(true, "J1", Affichage.ANSI_CYAN);
    Joueur J2 = new Joueur(false, "J2", Affichage.ANSI_RED);
    Joueur[] joueurs = { J1, J2 };

    Plateau plateau = new Plateau(10);
    plateau.configurationInitiale(J1, J2);

    int tour = 0;
    boolean jeu = true;
    boolean coupLegal;
    Coup coup;
    Joueur joueurActif;

    while (jeu) {

      Affichage.afficher(plateau);
      joueurActif = joueurs[tour % 2];
      coupLegal = false;
      ArrayList<Coup> coupLegaux; 
      boolean prisePossible = false;

      while (!coupLegal) {

        coupLegaux = joueurActif.calculerCoupsLegaux(plateau);
        coup = Affichage.demanderCoup(joueurActif);
        plateau.deplacerPiece(coup);
       /* while (!prisePossible){
          prisePossible = coupLegaux.prise;
        }

        

        
         
        while (!coup.prise){
          cou
          
        }
        
        coupLegal = true;

        */
      }
      tour++;
    }

    Affichage.quitter();

  }

}
