package com.school.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;


@MappedSuperclass
@SuppressWarnings("serial")
@EntityListeners(value = { AuditEntityListener.class })
public abstract class AbstractEntity extends AbstractPersistable<Long> /*implements Auditable<User, Long>*/{

	@ManyToOne
	@CreatedBy
	private User createdBy;
	@CreatedDate
//	@Type(type="org.joda.time.contrib.hibernate.PersistentDate")
//	오류 참조 : http://stackoverflow.com/questions/8974747/hibernate-4-and-joda-time
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDate")
	@Temporal(TemporalType.DATE)
	@Column( insertable = true, updatable = false)
    private Date createdDate;
	@ManyToOne
	@LastModifiedBy
    private User lastModifiedBy;
	@LastModifiedDate
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDate")
	@Temporal(TemporalType.DATE )
	@Column(insertable = false, updatable = true)
    private Date lastModifiedDate;


	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}




}
