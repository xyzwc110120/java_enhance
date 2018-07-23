package annotation; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* JdkAnnotationDemo Tester. 
* 
* @author <Authors name> 
* @since <pre>07/13/2018</pre> 
* @version 1.0 
*/ 
public class JdkAnnotationDemoTest { 

@Before
public void before() throws Exception {
    System.out.println("开始");
} 

@After
public void after() throws Exception {
    System.out.println("结束");
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: MyTest goes here...
} 

/** 
* 
* Method: doOldWork1() 
* 
*/ 
@Test
public void testDoOldWork1() throws Exception { 
//TODO: MyTest goes here...
} 

/** 
* 
* Method: doOldWork2() 
* 
*/ 
@Test
public void testDoOldWork2() throws Exception { 
//TODO: MyTest goes here...
} 

/** 
* 
* Method: doOldWork() 
* 
*/ 
@Test
public void testDoOldWork() throws Exception { 
//TODO: MyTest goes here...
} 

/** 
* 
* Method: doWork() 
* 
*/ 
@Test
public void testDoWork() throws Exception { 
//TODO: MyTest goes here...
} 

/** 
* 
* Method: suppressWarnings() 
* 
*/ 
@Test
public void testSuppressWarnings() throws Exception { 
//TODO: MyTest goes here...
} 


} 
