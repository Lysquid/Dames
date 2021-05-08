
public class Joueur {

  public Piece[] pieces;
  public String couleur;
  private String name;
  public int[] historiqueCoups;

  public Joueur(String name, String couleur) {
    this.name = name;
    this.couleur = couleur;
  }

  public String toString() {
    return couleur + name + Affichage.ANSI_RESET;
  }

}