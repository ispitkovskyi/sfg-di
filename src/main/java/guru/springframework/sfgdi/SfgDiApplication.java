package guru.springframework.sfgdi;

import guru.springframework.sfgdi.controllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//Configure which root-packages Spring scan should look into (by default it looks only into current package and packages underneath)
@ComponentScan(basePackages = {"guru.springframework.sfgdi", "com.springframework.pets"})
@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);
		//Spring scans project for classes, annotated as Spring components and creates instances of bin-classes,
		//and puts created instances into a Spring context
		// NO NEED to explicitly create instance of MyController class
		// NOTE: Spring will scan classes, located on the same level with this class AND in al packages sarting from this level down
		// So, Spring will be looking into this "guru.springframework.sfgdi" pakcage and all the packages underneath
		// NOTE: Any classes, located outside the package, which this class belongs to, will NOT be scanned
		//By default when Spring creates a bin, the bin name will be same as the class name, but in camel-case style:
		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println("======= Profile-defined Bean");
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");
		//String greetings = myController.sayHello(); - don't create instance explicitly, let Spring do it due to @Primary annotation
		System.out.println("======= @Primary Bean example");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());
	}

}
