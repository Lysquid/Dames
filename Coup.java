public class Coup {

  public int [] coord_init;
  public int [] coord_fin;
  public boolean prise;

  public Coup (int a, int b, int c, int d, boolean prise) {
    coup.coord_init[0] = a;
    coup.coord_init[1] = b;
    coup.coord_fin[0] = c;
    coup.coord_fin[1] = d;
    this.prise = prise;
  }
    
}