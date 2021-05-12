public class Coup {

  public int [] coord_init;
  public int [] coord_fin;
  public boolean prise;

  public Coup (int a, int b, int c, int d, boolean prise) {
    coord_init[0] = a;
    coord_init[1] = b;
    coord_fin[0] = c;
    coord_fin[1] = d;
    this.prise = prise;
  }
    
}