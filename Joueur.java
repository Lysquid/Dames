import java.util.ArrayList;

public class Joueur {

  public boolean blanc;
  public boolean ordi;
  public ArrayList<Piece> listePieces;
  public String couleur;
  private String name;

  public String symbolePion;
  public String symboleDame;

  public Joueur(boolean blanc, String name, String couleur, boolean ordi) {
    this.blanc = blanc;
    this.name = name;
    this.couleur = couleur;
    this.ordi = ordi;
    listePieces = new ArrayList<Piece>();
    if (Options.COULEUR) {
      symbolePion = "●";
      symboleDame = "♥";
    } else {
      symbolePion = blanc ? "●" : "○";
      symboleDame = blanc ? "♥" : "♡";
    }
    // autres symboles (unicode, peu lisibles) : ⛀⛁ ⛂⛃ couronnes : ♔♕♚♛
  }

  public String toString() {
    return couleur + name + Affichage.ANSI_RESET;
  }

  public String fancyName() {
    return couleur + symbolePion + " " + name + " " + symboleDame + Affichage.ANSI_RESET;
  }

  public ArrayList<Coup> calculerCoupsLegaux(Plateau plateau) {
    ArrayList<Coup> coupLegaux = new ArrayList<Coup>();
    for (Piece piece : listePieces) {
      coupLegaux.addAll(piece.calculerCoupsLegaux(plateau));
    }
    return coupLegaux;
  }

  public ArrayList<Coup> calculerCoupsForces(Plateau plateau) {
    ArrayList<Coup> coupLegaux = new ArrayList<Coup>();
    for (Piece piece : listePieces) {
      coupLegaux.addAll(piece.calculerCoupsForces(plateau));
    }
    return coupLegaux;
  }

  public void enleverPiece(Piece piece) {
    listePieces.remove(piece);
  }

}