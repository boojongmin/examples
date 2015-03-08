package step05;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class StudentVo {
	
	public StudentVo(){}
	public StudentVo(String name) { this.name = name; }
	
	public StudentVo(String userid, String name) { this.userid = userid; this.name = name; }

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	@Column(unique=true)
	private String userid;
	@Column(length=20)
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="studentVo")
	private PhoneNumberVo phoneNumberVo;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private SchoolVo schoolVo;
	
	public SchoolVo getSchoolVo() {
		return schoolVo;
	}
	public void setSchoolVo(SchoolVo schoolVo) {
		this.schoolVo = schoolVo;
	}
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
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public PhoneNumberVo getPhoneNumberVo() {
		return phoneNumberVo;
	}
	public void setPhoneNumberVo(PhoneNumberVo phoneNumberVo) {
		this.phoneNumberVo = phoneNumberVo;
	}
	@Override
	public String toString() {
		return String.format("uid : %d, userid : %s, name : %s, phoneNumberVo: %s", 
				uid, userid, name, phoneNumberVo);
	}
	
}
