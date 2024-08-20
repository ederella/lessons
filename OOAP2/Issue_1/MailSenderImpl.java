package ooap;

public class MailSenderImpl implements MailSender{

	private String email;
	private String text;
	public MailSenderImpl(String email){
		this.email = email;
	}
	public void setText(String text){
		this.text = text;
	}
	public void send(){	
		System.out.println(text  + " sended to " + email);
	}
}
