/*Uma empresa deseja automatizar o processamento de seus contratos. 
 * O processamento de um contrato consiste em gerar as parcelas a serem pagas 
 * para aquele contrato, com base no número de meses desejado. 
 * A empresa utiliza um serviço de pagamento online para realizar o pagamento 
 * das parcelas. Os serviços de pagamento online tipicamente cobram um juro mensal, 
 * bem como uma taxa por pagamento. Por enquanto, o serviço contratado pela empresa 
 * é o do Paypal, que aplica juros simples de 1% a cada parcela, mais uma taxa 
 * de pagamento de 2%. 
 * 
 * Fazer um programa para ler os dados de um contrato (número do contrato, 
 * data do contrato, e valor total do contrato). Em seguida, o programa deve ler 
 * o número de meses para parcelamento do contrato, e daí gerar os registros 
 * de parcelas a serem pagas (data e valor), sendo a primeira parcela a ser paga um mês
 * após a data do contrato, a segunda parcela dois meses após o contrato e 
 * assim por diante. Mostrar os dados das parcelas na tela.
 * 
 * 	Entre os dados do contrato:
	Numero: 8028
	Data (dd/MM/yyyy): 25/06/2018
	Valor do contrato: 600.00
	Entre com o numero de parcelas: 3
	Parcelas:
	25/07/2018 - 206.04
	25/08/2018 - 208.08
	25/09/2018 - 210.12
	
	Cálculos (1% juro simples mensal + 2% taxa de pagamento):
	
	Parcela #1:
	200 + 1% * 1 = 202
	202 + 2% = 206.04
	
	Parcela #2:
	200 + 1% * 2 = 204
	204 + 2% = 208.08
	
	Parcela #3:
	200 + 1% * 3 = 206
	206 + 2% = 210.12 */

package application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ConstractService;
import model.services.PayPalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter with contract data: ");
		System.out.print("Contract number: ");
		int number = sc.nextInt();
		System.out.print("Contract date: ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();

		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Number of installments: ");
		int installmentsNumber = sc.nextInt();
		
		ConstractService contractService = new ConstractService(new PayPalService());

		System.out.println("Installments value: ");
		for (Installment installment : contract.getInstallments()){
			System.out.println(installment);
		}

	}

}
