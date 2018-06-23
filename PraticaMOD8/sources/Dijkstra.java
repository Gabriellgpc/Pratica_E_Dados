import java.util.ArrayList;
import java.util.PriorityQueue;

// Algoritmo de Dijkstra
public class Dijkstra {
	final Graph g; 
	final int n; 
	final int source; 
	final int dest; 
        int[] dist;
        int[] pred;
        boolean[] settled;
	Fenetre f; 
        PriorityQueue<Node> naoacomodados;

	// construtor
	Dijkstra(Graph g, int source, int   dest) {
		this.g = g;
		n = g.n;
		this.source = source;
		this.dest = dest;
                this.dist = new int[n];
                this.pred = new int[n];
                this.settled = new boolean[n];
                this.naoacomodados = new PriorityQueue<>(n);
                for(int i=0;i<n;i++){
                    dist[i]=Integer.MAX_VALUE;  
                    pred[i]=-1;
                    settled[i] = false;
                }
                dist[source] = 0;
                pred[source] = source;
                naoacomodados.add(new Node(source,0));
        }
	
	// atualizacao da distancia, da prioridade, e do predecessor de um no
	void update(int y, int x) {
		if(dist[y] > dist[x] + g.value(x, y)){
                    dist[y] = dist[x] + g.value(x, y);
                    pred[y] = x;
                    naoacomodados.add(new Node(y,dist[y]));
                    g.drawUnsettledPoint(f,y);
                }
	}
	
	// retorna o próximo nó a ser acomodado
	int nextNode() {
            
            if(naoacomodados.isEmpty())
                return -1;
            
            else{
                int no;
                do{
                    no = naoacomodados.poll().id;
                    //if(naoacomodados.isEmpty()) return -1;
                    
                }while(settled[no]);  
                
                return no;
            }
           
	}
	
	// uma etapa do algoritmo de Dijkstra
	int oneStep() {
            slow();
            int settledNode = this.nextNode();
            if(settledNode == -1) return -1;
            settled[settledNode] = true;
            g.drawSettledPoint(f,settledNode);
            
            ArrayList sucessores = g.successors(settledNode);
           
            
            for(int i=0;i<sucessores.size();i++){
               
                update((int)sucessores.get(i),settledNode);
            }
            return settledNode;
	}
        void p(boolean[] a){
            System.out.print("[");
            for (int i = 0; i < n; i++) {
                System.out.print(settled[i] +", ");
            }
            System.out.print("]\n");
        }
        
	// algoritmo de Dijsktra completo
	int compute() {
		while(!settled[dest]){
                    if(oneStep() == -1) return -1;
            
                }
                return dist[dest];
	}
	
	// desacelera o visualizador
	void slow(){
	    if(f == null) return;
	    try {
	        Thread.sleep(1);
	    } catch (InterruptedException e) {}
	}
}
