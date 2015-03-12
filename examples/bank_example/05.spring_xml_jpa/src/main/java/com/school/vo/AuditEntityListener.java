package com.school.vo;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.school.login.LoginManager;

public class AuditEntityListener {
   @PrePersist
   public void prePersist(AbstractEntity e) {

    e.setCreatedBy(LoginManager.loginMap.get("User"));
    e.setCreatedDate(new Date());

   }

   @PreUpdate
   public void preUpdate(AbstractEntity e) {
    e.setLastModifiedBy(LoginManager.loginMap.get("User"));
    e.setLastModifiedDate(new Date());
   }
}
