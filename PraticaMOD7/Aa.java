
public class Aa{
  static int R=0;
  static int N=1;

  int valor;
  int cor;
  Aa esq,dir;

  //WARNING desse modo Esq e Dir podem nao ser conceitualmente do tipo AA
  Aa(int v, int c, Aa Esq, Aa Dir){
    valor = v;
    cor = c;
    esq = Esq;
    dir = Dir;
  }

  static String infixe(Aa a){
    if(a == null)return new String("");
    return infixe(a.esq) +" "+Integer.toString(a.valor)+ " " +infixe(a.dir);
  }
  static Integer nivel(Aa a){
    if(a == null)return 0;
    if(a.cor == N)return 1 + nivel(a.esq);
    return nivel(a.esq);
  }

  public static void main (String [] args) {
    Aa a = new Aa (3, N,
    new Aa (1, N, null, null),
    new Aa (8, R,
    new Aa (5, N,
    null,
    new Aa (6, R, null, null)),
    new Aa (9, N,
    null,
    new Aa (11, R, null, null))));
    System.out.println (infixe(a));
    System.out.println(nivel(a));
  }
}
