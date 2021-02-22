package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloDockerController {

	@GetMapping("/")
	public String landingpage() {
		return ("landingpage.html");
	}

}
