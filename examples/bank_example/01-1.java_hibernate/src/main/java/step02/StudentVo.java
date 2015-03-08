package step02;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String name;
	
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
	@Override
	public String toString() {
		return String.format("uid : %d, userid : %s, name : %s", uid, userid, name);
	}
	
}
