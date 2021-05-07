class Main {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  static final String CORNER_SYMBOL = "■";

  public static void main(String[] args) {
    
    Joueur J1 = new Joueur(ANSI_RED);
    Joueur J2 = new Joueur(ANSI_CYAN);
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
        } else if (y > 5) {
          plateau[x][y] = new Pion(J2, y, x);
        }
      }
    }
    return plateau;
  }

  public static void affichePlateau(Piece[][] plateau){
     
    String affichage = "";
    String bordure_ligne = "";
    
    bordure_ligne += ANSI_BLACK + CORNER_SYMBOL;
    for(int i = 0; i < plateau.length; i++){
      bordure_ligne += " " + (i + 1) % 10;
    }
    bordure_ligne += " " + CORNER_SYMBOL + ANSI_RESET;
    
    affichage += bordure_ligne  + "\n";
    for (int y = 0; y < plateau.length; y++){
      affichage += ANSI_BLACK + (y + 1) % 10 + ANSI_RESET;
      if (y % 2 == 1) {
        affichage += " ";
      }
      for (int x = 0; x < plateau[y].length ; x++) {
        if ((y + x) % 2 == 0) {
          affichage += ANSI_BLACK + "▐█▌" + ANSI_RESET;
        } else {
          Piece piece = plateau[x][y];
          if (piece instanceof Piece) {
            affichage += piece.toString() + ANSI_RESET;
          } else {
            affichage += " ";
          }
        }
      }
      if (y % 2 == 0) {
        affichage += " ";
      }
      affichage += ANSI_BLACK + (y + 1) % 10 + ANSI_RESET + "\n";
    }
    affichage += bordure_ligne  + "\n";
    System.out.println(affichage);
  }
}
