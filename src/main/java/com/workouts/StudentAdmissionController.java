package com.workouts;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentAdmissionController {
	/*
	 * @Controller - consider as controller class, no need to extend any class(AbstractController), no need bean creation entry in the xml file. To 
	 * enable @controller we need context:component-scan in xml.
	 * 
	 * @RequestMapping(value="/welcome", method=RequestMethod.GET) - maps the incoming request
	 * 
	 * @RequestMapping("/welcome/{countryName}" @PathVariable("countryName") String country - curly braces, end user can substitute the values.
	 * @PathVariable Map<String, String> pathVars String countryName = pathVars.get("countryName"); - in order to use this, we need to enable
	 * mvc:annotation-driven in the xml level.
	 * 
	 * @RequestParam("studentName") String sname - gets the query parameter values
	 * @RequestParam Map<String, String> reqPar String sname = reqPar.get("studentName"); 
	 * @RequestParam(value="studentName", defaultValue="Mr. ABC") - provide default value
	 * 
	 * @ModelAttribute("student1") Student stud1 - we can use this instead of getting values from reqParam for Object(Student) types,
	 * don't need any explicit changes, this alone is enough to get values in xml(${stud1.studentname}), jsp
	 * form name and object(Student) names should match, this is the only criteria, can be used in method level.If we need to pass
	 * any value which is common, we can have a separate method and add this annotation in the method level. This
	 * method only invoked first before calling other request handler methods. This supported String,Long,Date,ArrayList bydefault,
	 * but when Userdefined object is used, that also will work provided using the studentAddress.country as per the object attributes name.
	 * 
	 * BindingResult - When a string value is entered in the studentmobile field instead of Long, it will throw error, to handle
	 * it, use this. It will catch all binding related errors, so that we can make like result.hasErrors().
	 * <%@ taglib prefix="form" url="http://www.springframework.org/tags/form"%>, <form:errors path="student1.*"/>
	 * This job is to display binding related errors occured in the Server to Client. 
	 * 
	 * @InitBinder - When you submit, what if you want to save all fields except studentMobile. 
	 * WebDataBinder - To disallow specific fields on submitting the form. Also, we can use this to
	 * specifying the custom format, suppose if we want to store studentDOB in yyyy****MM****dd format,
	 * we can use PropertyEditors(here CustomDateEditor) inside the binder to get this working. examples
	 * of propertyEditors are FileEditor, ClassEditor, CustomNumberEditor etc. Spring mvc uses
	 * property editor classes in order to perform type conversion while performing data binding. You
	 * can use a particular property editor class in order to customize databinding for a particular
	 * data type.
	 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/propertyeditors/package-summary.html
	 * 
	 * 
	 * Own Custom property editor class - Take a concept, when a submit is performed, before data binding
	 * the fields, we need to write a custom editor to check if the Gender(Saluation) is present before 
	 * the name in the studentName field. If not provided, spring mvc should consider Ms. as the default
	 * value. When user submits this form, then before performing this data binding task, the call goes
	 * to the customeditor created and process the method
	 * 
	 * @Valid - When you provide annotations in the object(Student) class, add the @Validate along
	 * with the @ModelAttribute annotation for validating the fields. To make use of @Size or @Validate
	 * annotation, we need a third party library.
	 * 
	 * HandlerInterceptorAdapter
	 * preHandle()-before the request is invoked, this method gets executed.
	 * postHandle() - this method would be called after Spring MVC executes 
	 * the request handler method for this request.
	 * afterCompletion - this method would be called after the response 
	 * object is produced by the view for this request.
	 * 
	 * @ResponseBody - Please don't look for any view tech, converts that object to the desired format, add json jars to convert to json
	 * by detecting the jars.
	 * 
	 * @RequestBody - Maps the information to the properties of the java object, converts from the requested format to the java object. This checks
	 * the json/xml related jars and maps the json values to the student object properties.
	 * 
	 * @Consumes = MediaType.APPLICATION_XML_VALUE, can restrict the client request to support only xml(as per the given val in consumes) formats.
	 *
	 * @ResponseEntity - new ResponseEntity<Void>(HttpStatus.SUCCESS) - to send the appropriate status code, we can send response headers,
	 * body as well.
	 *
	 *
	 *
	 *
	 */
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//binder.setDisallowedFields(new String[] {"studentMobile"});
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy****MM****dd");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, "studentName", new StudentNameEditor()); 	
	}
	
	@ModelAttribute
	public void addingCommonObjects(Model model1) {
		model1.addAttribute("headerMessage", "Engineering College common for all"); 
	}
	
	@RequestMapping(value="/admissionForm.html", method=RequestMethod.GET)
	public ModelAndView getAdmissionForm() throws Exception {
		String exceptionOccured = "NULL_POINTER";
		if(exceptionOccured.equalsIgnoreCase("NULL_POINTER")) {
			throw new NullPointerException("Null Pointer Exception");
		}
		ModelAndView model = new ModelAndView("AdmissionForm");
		return model;
	}
	
	/*@RequestMapping(value="/submitAdmissionForm.html", method=RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@RequestParam(value="studentName", defaultValue="Mr. ABC") String sname, @RequestParam("studentHobby") String shobby) {
		// @PathVariable Map<String, String> path
		// @RequestParam Map<String, String> reqParam
		// String sname =  reqParam.get("studentName");
		// String shobby = reqParam.get("studentHobby");
		Student stud1 = new Student();
		stud1.setStudentName(sname);
		stud1.setStudentHobby(shobby);
		
		ModelAndView model = new ModelAndView("AdmissionSuccess");
		model.addObject("msg","Details submitted by you:: Name: "+sname+ ", Hobby: " +shobby);
		return model;
	}*/
	
	@RequestMapping(value="/submitAdmissionForm.html", method=RequestMethod.POST)
	public ModelAndView submitAdmissionForm(/*@Valid*/ @ModelAttribute("Student1") Student stud1, BindingResult result) {
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("AdmissionForm");
			return model;
		}
		ModelAndView model = new ModelAndView("AdmissionSuccess");
		return model;
	}
	
	/*@ExceptionHandler(value=NullPointerException.class)
	public String handleNullPointerException(Exception e) {
		System.out.println("Null Pointer exception occured: " + e);
		return "Null Pointer Exception"; //returning view name.
	}*/
	

	@ExceptionHandler(value=Exception.class)
	public String handleException(Exception e) {
		System.out.println("Exception occured: " + e);
		return "Exception"; //returning view name.
	}
	
}