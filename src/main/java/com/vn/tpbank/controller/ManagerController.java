package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.service.IManagerService;

@RestController
@RequestMapping("/tpbank/manager")
public class ManagerController {
	@Autowired
	IManagerService iManagerService; 
}
