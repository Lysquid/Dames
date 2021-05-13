public class Coup {

  public int x1;
  public int y1;
  public int x2;
  public int y2;
  public boolean prise;

  public Coup(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public Coup(int x1, int y1, int x2, int y2, boolean prise) {
    this(x1, y1, x2, y2);
    this.prise = prise;
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

}