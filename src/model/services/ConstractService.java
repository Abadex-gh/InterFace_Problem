package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ConstractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ConstractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}


	public void processContract(Contract contract, Integer months) {
		
		double basicQuota = contract.getTotalValue() / months;
		/*para ter o valor básico de parcela:
		* basic quota = parcela básica = contrato / meses */
		
		/*gerando o duedate, ou data de vencimento adicionando os meses:
		 * data do contrato + mês (de acordo com a quantidade que está em contrato*/
		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			/*calcular o valor do juro e da taxa para cada mês:
			 * 
			 * pegar o valor de juros da interface com a quantidade de mês "i" */
			double interest = onlinePaymentService.interest(basicQuota, i);
			/*achar o valor da parcela com o juros aplicado. 
			 * 
			 * aplicar o juro já em cima da quota:
			 * quota básica + juros (Achado acima):*/
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			/*achar agora o valor TOTAL da parcela, com taxa:
			 * 
			 * somar os dois acima + taxa:*/
			double quota = basicQuota + interest + fee;
			
			/*instanciar como contrato e instanciar como nova parcela total */
			contract.getInstallments().add(new Installment(dueDate, quota));
			
		}
	}
}
