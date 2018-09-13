package com.cyx.java_enhance.mainboardDemo;

// USB 接口规范（想要插入 USB 插槽的插件都必须满足 USB 规范（该接口））
public interface IUsb {

    /**
     * 交换数据
     */
    void swapData();

}
