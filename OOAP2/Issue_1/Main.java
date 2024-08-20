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

		//������� �����
		SimpleReport simple = new SimpleReport("12004", "20.08.2024", "������ ���� ��������");
		
		//������������: StatementOfAccountReport ��������� SimpleReport
		StatementOfAccountReport acc = new StatementOfAccountReport("48487", "20.08.2024", "������ ���� ��������", "40817810859881022222", new BigDecimal(10000.50));
		
		//������������: PaymentPlanReport ��������� SimpleReport
		PaymentPlanReport paym = new PaymentPlanReport("15140", "20.08.2024", "������ ���� ��������");
		paym.setCreditNumber("0008-CL-12345");
		HashMap<String, BigDecimal> pp = new HashMap<String, BigDecimal>();
		pp.put("01.06.2024", new BigDecimal(1000.00));
		pp.put("01.07.2024", new BigDecimal(1000.00));
		pp.put("01.08.2024", new BigDecimal(1000.00));
		paym.setPaymentPlan(pp);
		
		//����������: MailSenderImpl ���������� � PaymentPlanReport � ��������� ��� ���������
		paym.setMailSender(new MailSenderImpl("ivanov@mail.ru"));
		
		//�����������: � ������ allReports ����������� ��� ���� �������
		List<SimpleReport> allReports = new ArrayList<SimpleReport>();
		allReports.add(simple);
		allReports.add(acc);
		allReports.add(paym);
		
		for (SimpleReport report : allReports) {
			System.out.println(report.print());//�����������: ����� print() ���������� ��� ������ ���� ������
			System.out.println("-------");
			if(report instanceof MailSender){
				((MailSender) report).send();//����������: ����� send() �������� ����� ������ MailSenderImpl.send()
			}
				
		}
	}

}
