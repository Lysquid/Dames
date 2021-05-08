
public class Joueur {

  public int id;
  public Piece[] pieces;
  public String couleur;
  private String name;
  public int[] historiqueCoups;

  public Joueur(int id, String name, String couleur) {
    this.id = id;
    this.name = name;
    this.couleur = couleur;
  }

  public String toString() {
    return couleur + name + Affichage.ANSI_RESET;
  }

}