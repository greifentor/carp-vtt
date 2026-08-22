package de.ollie.carp.vtt.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimeController {

	@GetMapping("/time-controller")
	public String getTime(Model model) {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("time", now);
		return "time";
	}
}
