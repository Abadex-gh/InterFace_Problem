package model.services;

public interface OnlinePaymentService {
	
	public static Double paymentFee(Double amount) {
		return amount;
	}
	
	public static Double interest(Double amount, Integer months) {
		return amount;	
	}

}
