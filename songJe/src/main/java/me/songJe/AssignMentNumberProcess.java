package me.songJe;

import java.lang.invoke.MethodType;
import java.util.HashSet;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
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
		Set<? extends Element> elements = (Set<? extends TypeElement>) arg1.getElementsAnnotatedWith(AssignMentNumber.class);
		for(Element element : elements)
		{
			if(element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.INTERFACE)
			{
				processingEnv.getMessager().printMessage(Kind.ERROR,"Error :: Annotation Can Not allow");
			}
			processingEnv.getMessager().printMessage(Kind.ERROR,"NOTE====>" + element.getClass().toString());
		}
		// TODO Auto-generated method stub
		return true;
	}

}
