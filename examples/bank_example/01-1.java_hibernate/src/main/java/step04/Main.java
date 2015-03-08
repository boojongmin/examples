package step04;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
      							.configure("step04/hibernate.cfg.xml")
      							.buildSessionFactory();
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		StudentVo studentVo = new StudentVo("boojongmin01", "부종민01");
		PhoneNumberVo phoneNumberVo = new PhoneNumberVo("010-0000-0000");
		studentVo.setPhoneNumberVo(phoneNumberVo);
		phoneNumberVo.setStudentVo(studentVo);
		session.save(studentVo);
		
		
		StudentVo studentVo2 = new StudentVo("boojongmin02", "부종민02");
		studentVo2.setPhoneNumberVo(phoneNumberVo);
		session.save(studentVo2);
		
		tx.commit();
		System.exit(0);
		
	}
}
