package com.java.jmx.mbean;

import javax.management.Descriptor;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.RuntimeOperationsException;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.management.modelmbean.RequiredModelMBean;

public class HelloModelMBean extends RequiredModelMBean {

  private static final boolean READABLE = true;
  private static final boolean WRITABLE = true;
  private static final boolean BOOLEAN = true;
  private static final String STRING_CLASS = "java.lang.String";

  public HelloModelMBean()
      throws MBeanException, RuntimeOperationsException, InvalidTargetObjectTypeException, InstanceNotFoundException {
    ModelMBeanInfo mbi = createModelMBeanInfo();
    setManagedResource(new Hello(), "ObjectReference");
    setModelMBeanInfo(mbi);
  }

  private ModelMBeanInfo createModelMBeanInfo() {

    //////////////////////////////////////////////////////////////////
    //                        属性                                        //
    //////////////////////////////////////////////////////////////////
    // 构造name属性信息
    Descriptor portAttrDesc = new DescriptorSupport();
    portAttrDesc.setField("name", "Name");
    portAttrDesc.setField("descriptorType", "attribute");
    portAttrDesc.setField("displayName", "Name");
    portAttrDesc.setField("getMethod", "getName");
    portAttrDesc.setField("setMethod", "setName");
    ModelMBeanAttributeInfo nameAttrInfo = new ModelMBeanAttributeInfo(//
        "Name", // 属性名
        STRING_CLASS, //属性类型
        "people name", // 描述文字
        READABLE, WRITABLE, !BOOLEAN, // 读写
        portAttrDesc // 属性描述
    );

    //////////////////////////////////////////////////////////////////
    //                        方法                                        //
    //////////////////////////////////////////////////////////////////
    // 构造 getName操作描述符信息
    Descriptor getStateDesc = new DescriptorSupport(new String[]{
        "name=getName",
        "descriptorType=operation",
        "class=com.java.jmx.mbean.Hello",
        "role=operation"
    });

    ModelMBeanOperationInfo getName = new ModelMBeanOperationInfo(
        "getName",
        "get name attribute",
        null,
        "java.lang.String",
        MBeanOperationInfo.ACTION,
        getStateDesc
    );

    // 构造 setName操作描述符信息
    Descriptor setStateDesc = new DescriptorSupport(new String[]{
        "name=setName", "descriptorType=operation", "class=com.java.jmx.mbean.Hello",
        "role=operation"});

    MBeanParameterInfo[] setStateParms = new MBeanParameterInfo[]{(new MBeanParameterInfo(
        "name", "java.lang.String", "new name value"))};

    ModelMBeanOperationInfo setName = new ModelMBeanOperationInfo(
        "setName",
        "set name attribute",
        setStateParms,
        "void",
        MBeanOperationInfo.ACTION,
        setStateDesc
    );

    //构造 printHello()操作的信息
    ModelMBeanOperationInfo print1Info = new ModelMBeanOperationInfo(
        "printHello",
        null,
        null,
        "void",
        MBeanOperationInfo.INFO,
        null
    );

    // 构造printHello(String whoName)操作信息
    ModelMBeanOperationInfo print2Info;
    MBeanParameterInfo[] param2 = new MBeanParameterInfo[1];
    param2[0] = new MBeanParameterInfo("whoName", STRING_CLASS, "say hello to who");
    print2Info = new ModelMBeanOperationInfo(//
        "printHello",
        null,
        param2,//
        "void",
        MBeanOperationInfo.INFO, //
        null//
    );

    //////////////////////////////////////////////////////////////////
    //                        最后总合                                    //
    //////////////////////////////////////////////////////////////////
    // create ModelMBeanInfo
    ModelMBeanInfo mbeanInfo = new ModelMBeanInfoSupport(//
        RequiredModelMBean.class.getName(), // MBean类
        null, // 描述文字
        new ModelMBeanAttributeInfo[]{ // 所有的属性信息（数组）
            nameAttrInfo},//只有一个属性
        null, // 所有的构造函数信息
        new ModelMBeanOperationInfo[]{ // 所有的操作信息（数组）
            getName,
            setName,
            print1Info,
            print2Info},
        null, // 所有的通知信息(本例无)
        null//MBean描述
    );
    return mbeanInfo;
  }
}
