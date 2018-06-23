
public class Aa{
  static int R=0;
  static int N=1;

  int value;
  int cor;
  Aa esq,dir;

  //WARNING desse modo Esq e Dir podem nao ser conceitualmente do tipo AA
  Aa(int v, int c, Aa Esq, Aa Dir){
    value = v;
    cor = c;
    esq = Esq;
    dir = Dir;
  }
  Aa(Aa a){
    this.value = a.value;
    this.cor = a.cor;
    this.dir = (a.dir == null)?null:a.dir;
    this.esq = (a.esq == null)?null:a.esq;
  }

  void copy(Aa a){
    this.value = a.value;
    this.cor = a.cor;
    this.dir = (a.dir == null)?null:a.dir;
    this.esq = (a.esq == null)?null:a.esq;
  }

  static String infixe(Aa a){
    if(a == null)return new String("");
    return infixe(a.esq) +" "+Integer.toString(a.value)+ " " +infixe(a.dir);
  }
  static Integer nivel(Aa a){
    if(a == null)return 0;
    if(a.cor == N)return 1 + nivel(a.esq);
    return nivel(a.esq);
  }
  //TODO rotacao para a direta apartir da Raiz a
  static void rodeDir(Aa a){
    if(a.esq.cor == N)return;
    Aa tmp = new Aa(a.value, a.cor, a.esq.dir, a.dir);
    a.copy(new Aa(a.esq.value, a.esq.cor, a.esq.esq, tmp));
    new Fenetre(a,"A");
  }

  static void teste1(){
    Aa a = new Aa (3, N,
    new Aa (1, N, null, null),
    new Aa (8, R,
    new Aa (5, N,
    null,
    new Aa (6, R, null, null)),
    new Aa (9, N,
    null,
    new Aa (11, R, null, null))));

    new Fenetre(a,new String("teste"));
    System.out.println (infixe(a));
    System.out.println(nivel(a));
  }

  static void teste2(){
    Aa a = new Aa (4, R,
    new Aa (2, R,
    new Aa (1, N, null, null),
    new Aa (3, N, null, null)),
    new Aa (5, N, null, null));
    new Fenetre(a,new String("Pré rotação"));
    rodeDir(a);
    new Fenetre(a,new String("Pos rotação"));
  }

  public static void main (String [] args) {
    // teste1();
    teste2();
  }
}
