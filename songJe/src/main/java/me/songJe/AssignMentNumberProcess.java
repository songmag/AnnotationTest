package me.songJe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import com.google.auto.service.AutoService;

@AutoService(Processor.class)
public class AssignMentNumberProcess extends AbstractProcessor {
	
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		set.add(AssignMentNumber.class.getCanonicalName());
		return  set;	
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> arg0, RoundEnvironment arg1) {
		
		Set<? extends Element> elements = (Set<? extends TypeElement>) arg1.getRootElements();	
		//Set<? extends Element> elements = (Set<? extends TypeElement>) arg1.getElementsAnnotatedWith(AssignMentNumber.class);
		/*
		for(Element element : elements)
		{
			if(element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.INTERFACE)
			{
				processingEnv.getMessager().printMessage(Kind.ERROR,"Error :: Annotation Can Not allow");
			}
			TypeMirror mirror = element.asType();
			//Element epp = (Element) element.getAnnotation(AssignMentNumber.class);
			Annotation att=element.getAnnotation(AssignMentNumber.class);
			// => Name of element Name.
			
			
			processingEnv.getMessager().printMessage(Kind.ERROR,"NOTE====>" + element.getClass().toString());
		}*/
		/**
		 * Element = Class
		 * String = method name
		 */
		Map<Element,Map<String,int[]>> annotationClassList = new HashMap();
		for(Element element : elements)
		{
			annotationClassList.put(element,null);
			Map<String,int[]> methodAndValue = new HashMap<String,int[]>();
			System.out.println(element.getSimpleName());
			AssignMentNumber number = element.getAnnotation(AssignMentNumber.class);
			if(number != null)
			{
				processingEnv.getMessager().printMessage(Kind.ERROR,"ERROR====>This Annotation Can`t support Class & Interface");
			}
			/**
			 * This Method get Method And Field
			 */
			List<? extends Element> e = element.getEnclosedElements();
			for(Element elem : e)
			{
				AssignMentNumber number2= elem.getAnnotation(AssignMentNumber.class);
				if(number2 != null) {
					System.out.println(number2.number());
					methodAndValue.put(elem.getSimpleName().toString(),number2.number());
				}
			}
			if(!methodAndValue.isEmpty())
			{
				annotationClassList.put(element, methodAndValue);
			}
		}
		sourceCodeChange(annotationClassList);
		//processingEnv.getMessager().printMessage(Kind.ERROR,"NOTE====>");
		// TODO Auto-generated method stub
		return true;
	}
	public void sourceCodeChange(Map<? extends Element,Map<String,int[]>> map)
	{
		for(Element e : map.keySet())
		{
			if(map.get(e) == null) 
			{
				continue;
			}
			printJavaSourceCode(e);
			System.out.println("Class Name : "+ e);
			Map<String, int[]> p = map.get(e);
			for(String m : p.keySet())
			{
				System.out.print("Method Name : " + m + " Annotation Value : ");
				for(int v : p.get(m))
				{
					System.out.print(v+" ");
				}
				System.out.println();
			}
		}
	}
	public void printJavaSourceCode(Element e)
	{
		String packageName = e.toString();
		System.out.println("Class Name>>> "+e.getSimpleName().toString());
		packageName = packageName.replace(".", "/");
		File file = new File("src/main/java/"+packageName+".java");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			ArrayList<String> sourceCode = new ArrayList<String>();
			String line;
			while(( line = reader.readLine()) != null)
			{
				sourceCode.add(new String(line));
			}
			sourceCode.forEach(System.out::println);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
