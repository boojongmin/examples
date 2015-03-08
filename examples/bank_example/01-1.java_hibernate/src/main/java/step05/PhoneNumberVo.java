package step05;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.UniqueConstraint;

@Entity
public class PhoneNumberVo {
	
	public PhoneNumberVo(){}
	public PhoneNumberVo(String phoneNumber) { this.phoneNumber = phoneNumber; }
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long uid;
	@Column(unique=true)
	private String phoneNumber;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private StudentVo studentVo;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public StudentVo getStudentVo() {
		return studentVo;
	}
	public void setStudentVo(StudentVo studentVo) {
		this.studentVo = studentVo;
	}
	
	@Override
	public String toString() {
		return String.format("uid: %d, phoneNumber: %s, studentVo: %s"
							, uid, phoneNumber, studentVo);
	}
	
}
