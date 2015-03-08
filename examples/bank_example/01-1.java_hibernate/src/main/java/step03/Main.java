package step03;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
//		StudentVo studentVo1 = new StudentVo("boojongmin", "부종민");
//		StudentVo studentVo2 = new StudentVo("boojongmin01", "부종민");
//		
//		session.save(studentVo1);
//		session.save(studentVo2);
//		System.out.println("commit 후 : " + studentVo1);
//		System.out.println("commit 후 : " + studentVo2);
//		tx.commit();
//		StudentVo returnStudentVo = (StudentVo) session.get(StudentVo.class, 1L);
//		System.out.println(returnStudentVo);
//		
//		List<StudentVo> list = session.createQuery("from StudentVo").list();
//		list.forEach(x -> System.out.println(x));
//		
//		List<Object[]> objectList = session.
//					createQuery("select count(*), sum(uid) from StudentVo").list();
//		
//		for (Object[] row : objectList) {
//			System.out.println("count : " + row[0]);
//			System.out.println("sum : " + row[1]);
//		}
		
		CommonDao<StudentVo> dao = new CommonDao<StudentVo>(StudentVo.class);
		dao.insertVo(new StudentVo("boojongmin", "부종민"));
		StudentVo vo = dao.selectById(1L);
		System.out.println(vo);
		List<StudentVo> list = dao.selectByKeyword("userid", "boojongmin");
		list.forEach(x -> System.out.println(x));
		//CommonDao<StudentVo> dao2 = new CommonDao<StudentVo>(StudentVo.class);
		//System.out.println(dao.factory == dao2.factory);
		
		//update
		vo.setName("부종민 변경");
		dao.updateVo(vo);
		System.out.println(dao.selectById(vo.getUid()));
		
		dao.deleteVo(vo);
		System.out.println("조회된 개수 : " + dao.selectByKeyword("userid", "boojongmin").size());
		
		
		dao.insertVo(new StudentVo("boojongmin01", "부종민01"));
		List<StudentVo> allList = dao.listAll();
		System.out.println("전체 개수 : " + allList.size());
		
		dao.deleteAll();
		allList = dao.listAll();
		System.out.println("전체 개수 : " + allList.size());
		
		System.out.println("------------------------------------");
		System.out.println("전체 개수 : " + dao.count());
		
		
		for(int i=0; i < 100; i++){
			dao.insertVo(new StudentVo("boojongmin" + i, "부종민" + i));
		}
		
		list = dao.getPaginList(3, 15);
		list.forEach(x -> System.out.println(x));
		
		System.exit(0);
		
	}
}
