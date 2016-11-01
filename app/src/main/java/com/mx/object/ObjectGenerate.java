package com.mx.object;

import java.io.Serializable;

/**
 * Created by mx on 2016/10/31.
 */

public class ObjectGenerate implements Cloneable,Serializable {
    public String name="Object";
    public ObjectGenerate(){
        System.out.println("执行默认构造函数");
    }

    /**
     * 何时重写 hashCode()和 equals(Object obj)方法：
     *
     * <p>生成两个对象同时，会生成对应的 hashCode，
     * 当需要比较两个对象时，java先比较他们的hashCode，
     * 若不同，则两个对象就不同，若相同，再调用equals（）方法，判断是否相同。
     * 此处本来要比较，但直接打印了hashcode,已经比较直观，故不在比较
     * <p/>
     * */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (obj instanceof ObjectGenerate){
            return true;
        }else {
                ObjectGenerate objectGenerate= (ObjectGenerate) obj;
            if (objectGenerate.name.equals(name)){
                return true;

            }else {
                return false;
            }
        }
    }

    /**
     * 需重写clone函数
     * */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
