package step01;

public class StudentVo {
	
	public StudentVo(){}
	public StudentVo(String name) { this.name = name; }
	
	public StudentVo(String userid, String name) { this.userid = userid; this.name = name; }

	private long uid;
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
