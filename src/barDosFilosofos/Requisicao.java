package barDosFilosofos;

/**
*
* @Autor Ant�nio S�rgio A. Faheina // Matr�cula 1394159
* 
* @Classe Requisi��o
* 
* @Objetivos
* 
* - Servir de interface para as modifica��es feitas nas vari�veis estaticas para garantir a sincroniza��o e consist�ncia dos dados.
* - Recebe os pedidos para beber, respectivamente o ID do Filosofo que a chamou e o n�mero de garrafas que este requisitou (2 � n)
* - Bloco de c�digo SINCRONIZADO pra limitar o acesso �s variaveis est�ticas do Bar.
* - Maneja a prioridade de cada filosofo, se este tentou beber e n�o conseguiu, deve ascender de prioridade, caso beba, retorna � prioridade 1.
* 
* 
* 
*/


public class Requisicao {

	 public  void Requisitar(int id, int n) {
		 while(true) { // Loop que s� � quebrado ao se conseguir as garrafas requisitadas.
			 synchronized(this){
				 
				 System.out.println("(Filosofo " + id + ") Requisitou: " + n);
				 int garrafasConseguidas = 0;
				 
				 
				 // Verifica matriz auxiliar de garrafas para perceber se estas est�o sendo usadas (0-N�O COMPARTILHA, 1-COMPARTILHA + GARRAFA LIVRE, 2-COMPARTILHA-GARRAFA OCUPADA)
				 
				 for(int i = 0; i < Bar.bebidas.length; i++) {
					 if(Bar.bebidas[id][i] == 1) {
						 Bar.filosofo[id].requisitadas[i] = 1;   //preenche uma matriz partiuclar do filosofo que indica quais das requisitadas foram liberadas para serem bebidas
						 garrafasConseguidas++;
					 }
					 if(garrafasConseguidas>=n) {
						 break;  
					 }
				 }
				 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				 
				 if(garrafasConseguidas >= n) {   //No caso de conseguir beber.
					 Thread.currentThread().setPriority(1);
					 Bar.vezesBebidas[id]++;
					 notifyAll();
					 break;
				 }else if(Thread.currentThread().getPriority() < 10){
					 Thread.currentThread().setPriority(Thread.currentThread().getPriority()+1);  //aumenta a prioridade a cada falha.
				 }else {
					 
				 }
				 for(int i = 0; i<Bar.filosofo[id].requisitadas.length; i++) {
					 Bar.filosofo[id].requisitadas[i] = 0; //limpa a matriz de requisitadas caso n�o consiga pegar todas as garrafas que almejava.
				 }	
				 
				 try{
					 System.out.println("(Filosofo " + id +") n�o conseguiu beber (Prioridade " + Thread.currentThread().getPriority() + ")");
					 wait(1000);  //Tempo m�nimo at� que um outro filosofo possa liberar e este possa concorrer novamente pela garrafa.
				 }catch(InterruptedException ex){
					 System.out.println("Erro ao fazer o wait");
				 }
			 }
		 }	 
	}
}


