class Main {

  public static void main(String[] args) {
    
    Joueur J1 = new Joueur("\u001b[31m");
    Joueur J2 = new Joueur("\u001b[36m");
    Joueur[] joueurs = {J1, J2};
    int tour = 0;
    Piece[][] plateau = initialiserPlateau(J1, J2);
    affichePlateau(plateau);

    boolean jeu = true;
    Joueur joueurActif;
    while (jeu) {
      joueurActif = joueurs[tour % 2];
      joueurActif.coup();
      tour ++;
    }
  }

  public static Piece[][] initialiserPlateau(Joueur J1, Joueur J2) {
    Piece[][] plateau = new Piece[10][10];
    
    for (int y = 1; y < plateau.length; y += 2) {
      for (int x = 0; x < plateau[y].length; x += 2) {
        if (y < 4) {
          plateau[x][y] = new Pion(J1, y, x);
        } else if (y > 5) {
          plateau[x][y] = new Pion(J2, y, x);
        }
      }
    }

    for (int y = 0; y < plateau.length; y += 2) {
      for (int x = 1; x < plateau[y].length; x += 2) {
        if (y < 4) {

          plateau[x][y] = new Pion(J1, y, x);
        } else if (y > 5){
          plateau[x][y] = new Pion(J2, y, x);
        }
      }
    }

    return plateau;
  }

  public static void affichePlateau(Piece[][] plateau){
    
    String affichage = "";
    affichage += "■\u001b[30m";
    for(int i = 0; i < plateau.length; i++){
      affichage += (i + 1) % 10;
    }
    affichage += "\u001b[0m\n";
    
    for (int y = 0; y < plateau.length; y++){
      affichage += (y + 1) % 10;
      for (int x = 0; x < plateau[y].length ; x++) {
        if ((y + x) % 2 == 0) {
          affichage += "\u001b[30m"+"█"+"\u001b[0m";
        } else {
          Piece piece = plateau[x][y];
          if (piece instanceof Piece) {
            affichage += piece.toString() + "\u001b[0m";
          } else {
            affichage += " ";
          }
        }
      }
      affichage += (y + 1) % 10;
      affichage += "\n";
    }
    affichage += "■\u001b[30m";
    for(int i = 0; i < plateau.length; i++){
      affichage += (i + 1) % 10;
    }
    affichage += "\u001b[0m\n";
    System.out.println(affichage);
    System.out.println();
  }
}
