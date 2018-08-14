package xml.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;


/**
 * DOM4J 的简单操作，更多细节查阅 API（https://dom4j.github.io/）
 */
public class DOM4JTest {

    private File file = new File("src/xml/students.xml");

    /*
        获取元素信息
    */
    @Test
    public void testGetElement() throws Exception {
        // 获取文档对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        // 获取根元素
        Element root = document.getRootElement();
        // 获取根元素下的指定子元素
        List<Element> studentList = root.elements("student");
        // 获取元素中指定子元素的文本内容
        for (Element student: studentList) {
            // String name = student.element("name").getText();
            // 更简单的方法
            String name = student.elementText("name");
            System.out.println(name);
            // 获取元素的属性值
            String id = student.attributeValue("id");
            System.out.println(id);
            System.out.println("-----------------------------------------------------------------");
        }
    }

    /*
        新增一个元素
    */
    @Test
    public void testAddElement() throws Exception {
        // 创建文档对象
        Document document = new SAXReader().read(file);
        // 获取根元素
        Element root = document.getRootElement();
        // 创建 student 元素并设置为根元素的子元素，然后为 student 元素设置属性
        Element student = root.addElement("student").addAttribute("id", "20180720000347");
        // 创建 name，age 元素，并作为 student 元素的子元素，再设置文本内容
        student.addElement("name").addText("轰击给");
        student.addElement("age").addText("25");

        // 同步
        // 该类未实现自动关闭接口
        XMLWriter writer = new XMLWriter();
        try (
                FileWriter fileWriter = new FileWriter(file)) {

            /* 此同步未做格式化处理 */
            // writer.setWriter(fileWriter);

            /* 此同步是使用的默认格式类型，也就是压缩格式，输出成一行 */
            // OutputFormat format = OutputFormat.createCompactFormat();

            /* 此同步将会做格式化处理，会进行缩进 */
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new XMLWriter(fileWriter, format);

            writer.write(document);
        } finally {
            /* 记住，一定要关闭流 */
            writer.close();
        }
    }

}
