package review.mainboardDemo;

import java.util.*;

// 主板类（因为一个电脑只有一个主板，所以私有化主板对象，不然外界创建主板对象，使用枚举实现单例模式）
public enum  Mainboard {

    MAINBOARD;

    // 记录插入主办的插件
    private Map<String, IUsb> plugins = new HashMap<>();

    /**
     * 获取指定的 USB 设备名称
     */
    private List<String> getUsbName(IUsb usb) {
        List<String> names = new ArrayList<>();
        if (!plugins.containsValue(usb)) {
            throw new NullPointerException("未找到该 USB 设备");
        }
        Set<Map.Entry<String, IUsb>> set = new HashSet<>(plugins.entrySet());
        for (Map.Entry<String, IUsb> entry : set) {
            if (Objects.equals(usb, entry.getValue())) {
                names.add(entry.getKey());
            }
        }
        return names;
    }

    /**
     * 将 USB 插件插入主板
     *
     * @param name 插件名称
     * @param plugin 插件对象（想要插入 USB 插槽的插件都必须满足 USB 规范（IUsb 接口））
     */
    public void installUSB(String name, IUsb plugin) {
        plugins.put(name, plugin);
        System.out.println("插入" + name + "。");
    }

    /**
     * 获取主板上所有 USB 设备
     */
    public Map<String, IUsb> getUsbPlugins() {
        return plugins;
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

    /**
     * 从主板拔出 USB 设备
     */
    public void removeUsbPlugins(IUsb usb) {
        if (plugins.containsValue(usb)) {
            List<String> names = getUsbName(usb);
            if (!names.isEmpty()) {
                for (String name : names) {
                    plugins.remove(name);
                    System.out.println("USB 设备：" + name + "，已拔出");
                }
            }
        }
    }

}
