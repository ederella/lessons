package ooap;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

public class PaymentPlanReport extends SimpleReport implements MailSender{
	private String creditNumber;
	private HashMap<String, BigDecimal> paymentPlan;
	private MailSenderImpl ms;
	
	public PaymentPlanReport(String number, String dateOfReport, String customerName) {
		super(number, dateOfReport, customerName);
	}
	
	public void setCreditNumber(String creditNumber){
		this.creditNumber = creditNumber;
	}
	
	public void setPaymentPlan(HashMap<String, BigDecimal> paymentPlan){
		this.paymentPlan = paymentPlan;
	}
	
	public void setMailSender(MailSenderImpl ms){
		this.ms = ms;
	}
	
	public String print(){
		return super.print()
				+"\n номер кредита: " + creditNumber
				+ "\n график платежей :\n" + toStringPP();
	}

	private String toStringPP() {
		Set<String> dates = paymentPlan.keySet();
		String result = "";
		for (String date : dates) {
			result+="  " + date+": " + paymentPlan.get(date)+ '\n';
		}
		return result;
	}

	public void send() {
		ms.setText(print());
		ms.send();
	}
}
