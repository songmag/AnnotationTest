Annotation 을 Source로 잡았을 때 compile 시에 처리되는 것으로 비용처리
========


# 프로그램 제작 동기
 > 어노테이션이라는 자바의 기능이 있으나 사용하는데 있어서 Retain에 있는 Source, Class, Runtime 에 대해 생각하며, 이런 Retain에 대한 것도 생각해 보고 싶었다.

# 프로그램 제작 시간 : 20 시간 

# 사용 Library : 
<pre> <code> 
<dependency>
    <groupId>com.google.auto.service</groupId>
    <artifactId>auto-service</artifactId>
    <version>1.0-rc6</version>
</dependency>
</code>
</pre>

> ~~Annotation 을 Processor을 통하여 해당 소스코드를 바이트코드 변환시에 조작하는 방법에대해 고심한다.~~

> Annotation 을 입력한 Source Code 에 대한 정보를 가지고 Build 시에 System.out.println 을 통해 출력한다.

> Annotation이 붙어있는 메소드가 존재한다면, 해당 클래스의 소스코드를 출력하고, 클래스 이름, 어노테이션이 붙은 메소드 이름, 어노테이션 값을 표시한다.


<pre>
<code>
ex)
package me.songJe2;
import me.songJe.AssignMentNumber;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    @AssignMentNumber(number = {1,2})    
    public void testAnnotation()
    {
    	
    }
}
</code>
</pre>
> Result
<pre><code>
package me.songJe2;

import me.songJe.AssignMentNumber;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    @AssignMentNumber(number = {1,2})    
    public void testAnnotation()
    {
    	
    }
}
Class Name : me.songJe2.App
Method Name : testAnnotation Annotation Value : 1 2 
</code>
</pre> 

# AssignMentNumber Annotation & AssignMentNumber Processor

> Processor 을 활용하여 해당 어노테이션이 붙어있는지 확인한다.

> Method 는 Element로 받을 수 있지만, TypeElement 로 캐스팅이 되지 않는다. 해당 내역을 알아내는 방법은 Element 를 이용하여 toString을 찍어서 이름을 가져올 수 있다.

> Annotation 이 붙어있는 요소인지 확인하는 Method 는 Element 에 getAnnotation 메소드로 해당 element에 Annotation 요소가 붙어있다면 null이 아닌 다른 값을 리턴한다. 이 요소를 가지고 해당 Element에 Annotation이 붙어있는지 확인이 가능하다.

