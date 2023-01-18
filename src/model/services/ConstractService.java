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
		
		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			/*calcular o valor do juro e da taxa para cada mês:*/
			double interest = onlinePaymentService.interest(basicQuota, i);
			/*achar o o valor com o juros aplicado. aplicar o juro já em cima da quota:*/
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			/*somar os dois acima, que é igual a parcela.*/
			double quota = basicQuota + interest + fee;
			
			contract.getInstallments().add(new Installment(dueDate, quota));
			
		}
	}
}
