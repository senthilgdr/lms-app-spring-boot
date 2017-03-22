package in.spinsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public String index(){
		System.out.println("HomeController->index");
		return "index";
		
	}
	
	@GetMapping("/home")
	public String home(){
		System.out.println("HomeController->home");
		return "home";
		
	}

}
