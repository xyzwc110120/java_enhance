package com.cyx.java_enhance.mainboardDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public enum  NewMainboard {
    MAINBOARD;

    private static Map<String, IUsb> plugins = new HashMap<>();

    /*
        在 Mainboard 类中，如果要添加新的设备，每次都需要更改代码，所以我们直接将需要添加的设备放置 plugins.properties 文件中，
        这样每次只需要修改 plugins.properties 文件，如果要添加打印机之类的设备，也只用先新增一个 Printer 类，
        再在 plugins.properties 文件中添加就行了
    */
    static {
        // 获取资源文件
        try (
                // 通过相对于 classpath 的根路径来获取 plugins.properties 文件（此时得使用 ClassLoader （类加载器））
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("plugins.properties")
        ) {
            Properties properties = new Properties();
            properties.load(in);
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                String className = entry.getValue().toString();
                // 通过反射获取对象
                IUsb usb = (IUsb) Class.forName(className).newInstance();
                plugins.put(entry.getKey().toString(), usb);
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主板开始工作
     */
    public void doWork() {
        for (Map.Entry<String, IUsb> plugin : plugins.entrySet()) {
            System.out.print(plugin.getKey() + "：");
            plugin.getValue().swapData();
        }
    }

}
