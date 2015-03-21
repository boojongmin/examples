package com.school.domain;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class AuditEntityListener {
   @PrePersist
   public void prePersist(AbstractEntity e) {
//    User user = (User)SecurityContextHolder .getContext().getAuthentication().getPrincipal();
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    User user =(User)attr.getRequest().getSession().getAttribute("User");
    e.setCreatedBy(user);
    e.setCreatedDate(new Date());

   }

   @PreUpdate
   public void preUpdate(AbstractEntity e) {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    User user =(User)attr.getRequest().getSession().getAttribute("User");
    e.setLastModifiedDate(new Date());
   }
}
