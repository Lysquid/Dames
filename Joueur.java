import java.util.Scanner;
class Joueur {
  
  Piece[] pieces;
  String couleur;
  int[] historiqueCoups;

  public Joueur(String couleur) {
    this.couleur = couleur;
  }

  public static int[] coup() {
    Scanner scanner = new Scanner(System.in);
    System.out.println( "Saisissez votre coup : " );
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    int coords[] = {x, y};
    return coords;
  }


}