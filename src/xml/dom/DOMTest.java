package xml.dom;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMTest {

    private File file = new File("src/xml/students.xml");

    @Test
    public void testGetDocument() throws Exception {
        /*
            在 org.w3c.dom.Document 接口中，并没有获取该接口实现类对象的方法，需要通过 javax.xml.parsers.DocumentBuilder
            抽象类来获取 Document 对象，但是 DocumentBuilder 抽象类不能实例化，所以该类的实例要从
            javax.xml.parsers.DocumentBuilderFactory 抽象类中的 newDocumentBuilder() 方法获取，
            而 DocumentBuilderFactory 抽象类要想实例化，需要调用类中的静态方法 newInstance() 来获取 DocumentBuilderFactory 对象。
        */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = null;
        System.out.println(doc);    // 打印后：null，这个才是 Document 对象为空。
        if (file.exists()) {
            doc = builder.parse(file);
        } else {
            doc = builder.newDocument();
        }
        System.out.println(doc);    // 打印后：[#document: null]，这个不是表示 Document 对象为空，后面的 null 是指元素名称。
    }

    /*
        得到某个具体元素的文本内容（取出第二个学生的名字）
    */
    @Test
    public void testGetElementTextContent() throws Exception {
        // 获取 Document 文档对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        // 获取 XML 中的根元素
        Element root = document.getDocumentElement();
        // 获取根元素中指定名称的元素集合
        NodeList students = root.getElementsByTagName("student");
        // 获取元素集合中第二个元素对象（因为返回的是 Node 对象，是 Element 接口的父接口，所以要强转）
        Element student = (Element) students.item(1);
        /*
        获取文档对象中指定名称的元素对象
        Element student = (Element) document.getElementsByTagName("student").item(1);
        */
        // 获取元素的指定子元素
        Element name = (Element) student.getElementsByTagName("name").item(0);
        System.out.println(name.getTextContent());
        // 获取元素的文本内容，并使用断查看是否正确
        Assert.assertEquals("李子", name.getTextContent());
    }

    /*
        修改某个元素的文本内容（第一个学生的姓名）
    */
    @Test
    public void testUpdateElementTextContent() throws Exception {
        // 获取 Document 对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        // 获取文档对象中制定的元素对象
        Element nameEle = (Element) document.getElementsByTagName("name").item(0);
        // 设置元素的文本内容
        nameEle.setTextContent("哇哈哈");

        /*
            同步操作：使用核心类（Transformer）：
                将内存中以修改的 Document 对象同步至 XML 文件中
        */
        // Transformer 类是抽象类，无法实例化，需要通过 TransformerFactory 工厂类来实例化 Transformer 类
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer former = factory.newTransformer();
        /*
            同步方法：
                void transform(Source xmlSource, Result outputTarget)：
                参数：
                    xmlSource：源数据
                    outputTarget：目标数据
        */
        // 创建一个 Source 对象，Source 为接口，所以要实例化一个它的实现类，Source 接口有5个实现类，
        // 这里我们的源数据为 Document 对象，所以使用实现类 DOMSource 类。
        Source source = new DOMSource(document);
        // 创建一个 Result 对象，与 Source 接口一样，需要实例化一个它的实现类，Result 接口有6个实现类，
        // 这里我们的目标数据为 XML 文档，需要用流来写入数据，所以使用实现类 StreamResult 类。
        Result result = new StreamResult(file);
        // 同步
        former.transform(source, result);
    }

    /*
        新增一个 XML 片段（新增 student 元素片段）
    */
    @Test
    public void testAddElement() throws Exception {
        // 获取文档对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        // 获取根元素对象
        Element root = document.getDocumentElement();
        // 创建 student，name，age 元素
        Element student = document.createElement("student");
        Element name = document.createElement("name");
        Element age = document.createElement("age");
        // 设置 name，age 元素文本内容
        name.setTextContent("非洲大老板");
        age.setTextContent("24");
        // 将 name，age 元素作为 student 元素的子元素
        student.appendChild(name);
        student.appendChild(age);
        // 将 student 元素作为根元素的子元素
        root.appendChild(student);
        // 同步操作
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(file));
        /*
            使用这种方式同步的 XML 文件不会做格式处理，生成的 XML 文件会不好看，而 DOM4J 则会做格式处理
        */
    }

    /*
        新增或修改元素的属性（修改第三个学生的 id）
    */
    @Test
    public void addOrUpdateAttribute() throws Exception {
        // 获取文档对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        // 获取指定元素（第三个 student 元素）
        Element student = (Element) document.getElementsByTagName("student").item(2);
        // 获取元素中的属性
        System.out.println(student.getAttribute("id"));
        // 修改属性
        student.setAttribute("id", "20180720000501");
        // 同步操作
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(file));
    }

    /*
        删除元素（删除第3个 student 元素）
    */
    @Test
    public void removeElement() throws Exception {
        // 获取文档对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        // 获取指定元素（第三个 student 元素）
        Element student = (Element) document.getElementsByTagName("student").item(2);
        // 获取 student 元素对象的父元素对象，并删除指定子元素
        student.getParentNode().removeChild(student);
        // 同步操作
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(file));
    }

    /*
        创建文档对象
    */
    @Test
    public void createDocument() throws Exception {
        file = new File("src/xml/newStudents.xml");
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document;

        // 判断 XML 文件是否存在，若存在则解析 XML 文件生成 Document 对象，否则直接创建 Document 对象
        if (file.exists()) {
            document = builder.parse(file);
        } else {
            document = builder.newDocument();
            // 新建的 document 对象是空的，所以新建一个节点，并设置该节点为 document 对象的父节点
            document.appendChild(document.createElement("students"));
        }

        // 获取根元素对象
        Element root = document.getDocumentElement();
        // 创建 student，name，age 元素
        Element student = document.createElement("student");
        Element name = document.createElement("name");
        Element age = document.createElement("age");
        // 设置 name，age 元素文本内容
        name.setTextContent("非洲大老板");
        age.setTextContent("24");
        // 将 name，age 元素作为 student 元素的子元素
        student.appendChild(name);
        student.appendChild(age);
        // 将 student 元素作为根元素的子元素
        root.appendChild(student);
        // 同步操作
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

}
