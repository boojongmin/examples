package step05;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SchoolVo {
	public SchoolVo(String name) { this.name = name; }
	private long uid;
	private String name;
	private Set<StudentVo> studentList = new HashSet<StudentVo>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="schoolVo", cascade=CascadeType.ALL)
	public Set<StudentVo> getStudentList() {
		return studentList;
	}
	public void setStudentList(Set<StudentVo> studentList) {
		this.studentList = studentList;
	}
}
