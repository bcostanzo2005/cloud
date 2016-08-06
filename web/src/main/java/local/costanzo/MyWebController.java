package local.costanzo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ConfigurationProperties("demo")
public class MyWebController {
	
	private String message = "Error loading message";
	private String title = "Cloud Config Demo";
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@RequestMapping("/")
	public String demo(Model model) {
		model.addAttribute("message", getMessage());
		model.addAttribute("title", getTitle());
		return "Demo";
	}
	
}
