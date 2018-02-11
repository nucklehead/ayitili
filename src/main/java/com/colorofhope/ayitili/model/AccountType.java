package com.colorofhope.ayitili.model;

public enum AccountType {
  @BootstrapLabel("Administratè")
  ADMIN,
  @BootstrapLabel("Bibliyotekè")
  LIBRARIAN,
  @BootstrapLabel("Manm")
  MEMBER,
  @BootstrapLabel("Visitè")
  GUEST_MEMBER,
}
