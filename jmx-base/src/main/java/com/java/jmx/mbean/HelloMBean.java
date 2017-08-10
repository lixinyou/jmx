package com.java.jmx.mbean;

public interface HelloMBean {

  String getName();

  void setName(String name);

  void printHello();

  void printHello(String whoName);
}
