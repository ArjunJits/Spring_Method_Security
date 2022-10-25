package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@PreAuthorize("hasAuthority('read')")
	@GetMapping("/demo1")
	public String demo() {
		return "Hello Arjun";
	}

	@PreAuthorize("hasAnyAuthority('read','write') ")
	@GetMapping("/demo2")
	public String demo2() {
		return "Hello Arjun2";
	}

	@PreAuthorize("#name == authentication.name")
	@GetMapping("/demo3/{name}")
	public String demo3(@PathVariable String name) {
		System.out.println("Security context :" + SecurityContextHolder.getContext().getAuthentication());
		return "Hello Krishna";
	}

	@PreAuthorize(" @securityCondition.condition()") // defining securityCondition bean with condition.
	@GetMapping("/demo4")
	public String demo4() {
		return "demo4 executed";
	}
	
	@PostAuthorize(" returnObject == 'demo5 is executed'") // works based on return Object
	@GetMapping("/demo5")
	public String demo5() {
		System.out.println("Security context :" + SecurityContextHolder.getContext().getAuthentication());
		return "demo5 is executed";
	}

	
	@PreFilter(" filterObject.contains('a')")
	@GetMapping("/demo6")
	public List<String> demo6(@RequestBody List<String> names) {
		return names;
	}
	
	@PostFilter(" filterObject.contains('a')")
	@GetMapping("/demo7")
	public List<?> demo7() {
	var list = new ArrayList<>();
	list.add("abcd");
	list.add("xyxz");
	list.add("acd");
	//Note: List.of(...) is immutable hence we cannot use here
	return list;
	}
	
	
	
	
	
	
}
