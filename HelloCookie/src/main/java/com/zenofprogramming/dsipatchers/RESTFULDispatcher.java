package com.zenofprogramming.dsipatchers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class RESTFULDispatcher {

	@GetMapping ("/")
	public String landingpage ()
	{
		return ("landingpage.html");
	}
	
	
}
