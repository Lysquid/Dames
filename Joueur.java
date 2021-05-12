import java.util.ArrayList;

public class Joueur {

  public boolean blanc;
  public ArrayList<Piece> pieces;
  public String couleur;
  private String name;
  public int[] historiqueCoups;
  

  public Joueur(boolean blanc, String name, String couleur) {
    this.blanc = blanc;
    this.name = name;
    this.couleur = couleur;
    pieces = new ArrayList<String>();
  }

  public String toString() {
    return couleur + name + Affichage.ANSI_RESET;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupLegaux = new ArrayList<Coup>();
    for (int i = 0; i < coupLegaux.size(); i++) {
      coupLegaux.addAll(pieces.get(i).calculerCoupsLegaux());
    }
    return coupLegaux;
  }

}