package com.colorofhope.ayitili.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class MemberAction extends DBModel {
  @DBRef public Account member;
  @BootstrapLabel(value = "Kalite", showInForm = false)
  public MemberActionType type;

  public MemberAction() {}

  public MemberAction(Account member, MemberActionType type) {
    this.member = member;
    this.type = type;
  }
}
