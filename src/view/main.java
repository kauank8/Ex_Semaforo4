package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.TransacaoController;

public class main {

	public static void main(String[] args) {
		Random  gerador = new Random();
		int valortransacao;
		int saldo;
		int operacao;
		Semaphore semaforo = new Semaphore(2);
		Semaphore semaforo2 = new Semaphore(1);
		for(int i=0;i<20;i++) {
			operacao=gerador.nextInt(2)+1;
			saldo=gerador.nextInt(50)+50;
			valortransacao=gerador.nextInt(90)+10;
			Thread transacoes = new TransacaoController(i,saldo,valortransacao,semaforo,operacao,semaforo2);
			transacoes.start();
		}

	}

}
