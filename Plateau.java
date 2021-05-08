public class Plateau {

    public int taille;
    private Piece[][] grille;

    public Plateau(int taille) {
        grille = new Piece[taille][taille];
        this.taille = taille;
    }

    public void configurationInitiale(Joueur J1, Joueur J2) {

        for (int y = 0; y < grille.length; y++) {
            for (int x = (y + 1) % 2; x < grille[y].length; x += 2) {
                if (y < taille / 2 - 1) {
                    setPiece(new Pion(J2), x, y);
                } else if (y > (taille + 1) / 2) {
                    setPiece(new Pion(J1), x, y);
                }
            }
        }

    }

    public Piece getPiece(int x, int y) {
        return grille[x][y];
    }

    public void setPiece(Piece piece, int x, int y) {
        grille[x][y] = piece;
        piece.x = x;
        piece.y = y;
    }

}
