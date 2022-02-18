package guru.springframework.sfgdi;

import guru.springframework.sfgdi.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);
		//Spring scans project for classes, annotated as Spring components and creates instances of bin-classes,
		//and puts created instances into a Spring context
		// NO NEED to explicitly create instance of MyController class
		//By default when Spring creates a bin, the bin name will be same as the class name, but in camel-case style:
		MyController myController = (MyController) ctx.getBean("myController");
		String greetings = myController.sayHello();
		System.out.println(greetings);
	}

}
