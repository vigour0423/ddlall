package com.ddl.jvm.classloader.namespace_runpack;

/**
 * description:
 * @author liuddl
 * @version 1.0
 * @date 2019-05-06 16:42:44
 */
public class MyPerson {

    private MyPerson person;

    public void setPerson(Object object) {
        //
        this.person = (MyPerson) object;
    }
}
