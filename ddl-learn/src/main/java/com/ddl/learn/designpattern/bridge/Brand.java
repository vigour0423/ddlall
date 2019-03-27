package com.ddl.learn.designpattern.bridge;

/**
 * 品牌
 * 桥接模式可以取代多层继承的方案，多层继承方案违背了单一职责原则，
 * 复用性较差，类的个数也非常多，桥接模式可以极大的减少子类的个数，从而降低管理和维护的成本。
 * 桥接模式极大的提高了系统的可扩展性，在两个变化维度中任意扩展一个维度，都不需要修改原有的系统，符合开闭原则。
 */
public interface Brand {
    void sale();
}

class Lenovo implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }

}

class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售Dell电脑");
    }

}

class Shenzhou implements Brand {

    @Override
    public void sale() {
        System.out.println("销售神舟电脑");
    }

}
