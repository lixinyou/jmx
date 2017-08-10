package com.java.jmx.mbean;

public class Hello implements HelloMBean {

  private String name = "lxy";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void printHello() {
    System.out.println("Hello world, "+ name);
  }

  public void printHello(String antherName) {
    System.out.println("Hello, "+ antherName);
  }
}
