package controller;

import java.util.concurrent.Semaphore;

public class TransacaoController extends Thread {
	private int codigo;
	private int saldo;
	private int valortransacao;
	private Semaphore semaforo;
	private Semaphore semaforo2;
	private int operacao;
	private static int aux=1;
	private static int x=0;
	private static int y =0;
	public TransacaoController(int codigo,int saldo, int valortransacao, Semaphore semaforo, int operacao,Semaphore semaforo2) {
		this.codigo=codigo;
		this.saldo=saldo;
		this.valortransacao=valortransacao;
		this.semaforo=semaforo;
		this.operacao=operacao;
		this.semaforo2=semaforo2;
	}
	
	@Override
	public void run() {
		//	Essa logica de impedir saques e depositos simultaneos os ultimos serao igual;
			if(operacao==1) {
				try {
					semaforo.acquire();
					RealizaTransacao();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaforo.release();
				}
			}
			if(operacao==2) {
				try {
					semaforo2.acquire();
					RealizaTransacao();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaforo2.release();
				}
			}
		}
	
	
	public void RealizaTransacao(){
		
		if(operacao==1) {
			System.out.println("Operacao solicitada:Saque. Pela conta #" + codigo +" Saldo Atual:" + saldo);
			if(saldo<valortransacao) {
				System.out.println("Voce nao possui esse valor total para saque #"+codigo+ "CONSULTE SEU SALDO ");
				System.out.println("A operaçao da conta #"+codigo+" Terminou");
			}
			else {
				int resultado = saldo-valortransacao;
				System.out.println("Valor Sacado: " +valortransacao + " Saldo Restante:" +resultado +" Da conta:#" +codigo  );
				System.out.println("A operaçao da conta #"+codigo+" Terminou");
			}
		}
		if(operacao==2) {
			System.out.println("Operacao solicitada:Deposito. Pela conta #" + codigo + " Saldo Inicial:" +saldo);
			int deposito=saldo+valortransacao ;
			System.out.println("Deposito:" + valortransacao + " Saldo Atualizado:"+deposito + " Da conta:#" +codigo);
			System.out.println("A operaçao da conta #"+codigo+" Terminou");
		}
	}
	
	public void VerificaOperacoes() {
		 {
			semaforo.release();
		}
		
	}
}
	