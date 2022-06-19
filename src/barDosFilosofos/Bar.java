
package barDosFilosofos;

import java.io.File;

/**
 *
 * @Autor Ant�nio S�rgio A. Faheina // Matr�cula 1394159
 * 
 * @Classe Bar
 * 
 * @Objetivos
 * 
 * - Instanciar e atribuir valores � vari�veis est�ticas da simula��o.
 * - Instanciar as threads filosofos.
 * - Ler txt. com matriz 
 * - Possui a main
 * 
 * 
 * 
 */

import java.util.Scanner;

public class Bar {

	public static int[][] matriz;  //Guardar a matriz do grafo de intera��o entre os filosofos. (atribuida posteriormente)
	public static int[] estado;    // Armazena o estado de cada filosofo de acordo com a conven��o feita na requis��o do trabalho 
	public static Filosofo[] filosofo; // Armazena os objetos dos filosofos que implementam a interface runnable (threads)
	public static int[] vezesBebidas;  // Armazena o n�mero de vezes que cada fil�sofo bebeu
	public static int[][] bebidas;  // Uma matriz auxiliar que deriva da matriz principal para determinar se uma determinada garrafa est� ou n�o sendo bebida.
	public static int nBebidas = 6; // N�mero de vezes que um filosofo deve beber para se satisfazer.
	
	static int nFilosofos;
	
	 public static void main(String[] args) {
		 Start();
	 }

	 public static void Start() {   // Recebe caminho da matriz, atribui valores iniciais e inicia as threads filosofos. 

		 matriz = receberMatriz("matriz01.txt");
		
		 nFilosofos = matriz.length;
		 bebidas = matriz;
		 estado = new int[nFilosofos];
		 filosofo = new Filosofo[nFilosofos];
		 vezesBebidas = new int[nFilosofos];
		 
		 
		 for(int i = 0; i < nFilosofos; i++) {
			 filosofo[i] = new Filosofo(i);
		 }
		 
	 }
	 
	 public static int[][] receberMatriz(String diretorio){  // Recebe o caminho do txt e retorna a matriz
		try {
		 File txt = new File(diretorio);
		 Scanner s = new Scanner(txt);
		 int contadorNumeros = 0;
		 while(s.hasNextInt()) {
			 contadorNumeros++;
			 s.nextInt();
		 }
		 int linhas = (int) Math.sqrt(contadorNumeros);
		 
		 int [][]  matrizFinal = new int[linhas][linhas];
		 
		 Scanner s1 = new Scanner(txt);
		 
		 for(int i=0;i< linhas; i++) {
			 for(int j=0;j< linhas; j++) {
				 matrizFinal[i][j] = s1.nextInt();
			 }
		 }
		 s1.close();
		 s.close();
		 return matrizFinal;
		}catch(Exception e) {
			return null;
		}
	 }
	
}
