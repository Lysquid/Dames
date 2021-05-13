import java.util.ArrayList;

public class Joueur {

  public boolean blanc;
  public ArrayList<Piece> listePieces;
  public String couleur;
  private String name;
  public int[] historiqueCoups;

  public Joueur(boolean blanc, String name, String couleur) {
    this.blanc = blanc;
    this.name = name;
    this.couleur = couleur;
    listePieces = new ArrayList<Piece>();
  }

  public String toString() {
    return couleur + name + Affichage.ANSI_RESET;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupLegaux = new ArrayList<Coup>();
    for (Piece piece : listePieces) {
      coupLegaux.addAll(piece.calculerCoupsLegaux(plateau));
    }
    return coupLegaux;
  }

}