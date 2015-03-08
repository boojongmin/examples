package step05;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
      							.configure("step05/hibernate.cfg.xml")
      							.buildSessionFactory();
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
//		StudentVo studentVo = new StudentVo("boojongmin01", "부종민01");
//		PhoneNumberVo phoneNumberVo = new PhoneNumberVo("010-0000-0000");
//		studentVo.setPhoneNumberVo(phoneNumberVo);
//		//phoneNumberVo.setStudentVo(studentVo);
//		session.save(studentVo);
//		
//		StudentVo studentVo2 = new StudentVo("boojongmin02", "부종민02");
//		studentVo2.setPhoneNumberVo(phoneNumberVo);
//		session.save(studentVo2);
		
		SchoolVo schoolVo = new SchoolVo("학교01");
		StudentVo studentVo1 = new StudentVo("boojongmin03", "부종민01");
		StudentVo studentVo2 = new StudentVo("boojongmin04", "부종민02");
		
		studentVo1.setSchoolVo(schoolVo);
		studentVo2.setSchoolVo(schoolVo);
		schoolVo.getStudentList().add(studentVo1);
		schoolVo.getStudentList().add(studentVo2);
		session.save(schoolVo);
		tx.commit();
		System.exit(0);
		
	}
}
