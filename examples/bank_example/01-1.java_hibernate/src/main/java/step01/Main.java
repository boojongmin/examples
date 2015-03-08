package step01;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
      							.configure("step01/hibernate.cfg.xml")
      							.buildSessionFactory();
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		StudentVo studentVo1 = new StudentVo("boojongmin", "부종민");
		StudentVo studentVo2 = new StudentVo("boojongmin01", "부종민");
		
		session.save(studentVo1);
		session.save(studentVo2);
		System.out.println("commit 후 : " + studentVo1);
		System.out.println("commit 후 : " + studentVo2);
		tx.commit();
		StudentVo returnStudentVo = (StudentVo) session.get(StudentVo.class, 1L);
		System.out.println(returnStudentVo);
		
		List<StudentVo> list = session.createQuery("from StudentVo").list();
		list.forEach(x -> System.out.println(x));
		
		List<Object[]> objectList = session.
					createQuery("select count(*), sum(uid) from StudentVo").list();
		
		for (Object[] row : objectList) {
			System.out.println("count : " + row[0]);
			System.out.println("sum : " + row[1]);
		}
		
		
		System.exit(0);
		
	}
}
