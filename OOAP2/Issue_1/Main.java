package ooap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//базовый отчет
		SimpleReport simple = new SimpleReport("12004", "20.08.2024", "Иванов Иван Иванович");
		
		//НАСЛЕДОВАНИЕ: StatementOfAccountReport наследует SimpleReport
		StatementOfAccountReport acc = new StatementOfAccountReport("48487", "20.08.2024", "Иванов Иван Иванович", "40817810859881022222", new BigDecimal(10000.50));
		
		//НАСЛЕДОВАНИЕ: PaymentPlanReport наследует SimpleReport
		PaymentPlanReport paym = new PaymentPlanReport("15140", "20.08.2024", "Иванов Иван Иванович");
		paym.setCreditNumber("0008-CL-12345");
		HashMap<String, BigDecimal> pp = new HashMap<String, BigDecimal>();
		pp.put("01.06.2024", new BigDecimal(1000.00));
		pp.put("01.07.2024", new BigDecimal(1000.00));
		pp.put("01.08.2024", new BigDecimal(1000.00));
		paym.setPaymentPlan(pp);
		
		//КОМПОЗИЦИЯ: MailSenderImpl содержится в PaymentPlanReport и добавляет ему поведение
		paym.setMailSender(new MailSenderImpl("ivanov@mail.ru"));
		
		//ПОЛИМОРФИЗМ: в список allReports добавляются все типы отчетов
		List<SimpleReport> allReports = new ArrayList<SimpleReport>();
		allReports.add(simple);
		allReports.add(acc);
		allReports.add(paym);
		
		for (SimpleReport report : allReports) {
			System.out.println(report.print());//ПОЛИМОРФИЗМ: метод print() вызывается для своего типа отчета
			System.out.println("-------");
			if(report instanceof MailSender){
				((MailSender) report).send();//КОМПОЗИЦИЯ: метод send() содержит вызов метода MailSenderImpl.send()
			}
				
		}
	}

}
