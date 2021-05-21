public class Coup {

  // Coordonnées initiales
  public int x1;
  public int y1;
  // Coordonnées finales
  public int x2;
  public int y2;
  // Coordonneés de la prise (si prise il y a)
  public int x3;
  public int y3;
  public boolean prise;

  public Coup(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public Coup(int x1, int y1, int x2, int y2, int x3, int y3) {
    this(x1, y1, x2, y2);
    this.prise = true;
    this.x3 = x3;
    this.y3 = y3;
  }

  public boolean equals(Coup coup) {
    return (x1 == coup.x1 && y1 == coup.y1 && x2 == coup.x2 && y2 == coup.y2);
  }

  public String toString() {
    String string = "";
    string += Affichage.coordToChar(x1, true);
    string += Affichage.coordToChar(y1, false);
    string += (prise) ? "x" : "-";
    string += Affichage.coordToChar(x2, true);
    string += Affichage.coordToChar(y2, false);
    return string;
  }

  public boolean surCaseBlanche() {
    return (x1 + y1) % 2 == 1 || (x2 + y2) % 2 == 1;
  }

}