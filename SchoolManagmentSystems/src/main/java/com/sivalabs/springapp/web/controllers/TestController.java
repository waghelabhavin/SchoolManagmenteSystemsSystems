package com.sivalabs.springapp.web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.palominolabs.crm.sf.core.Id;
import com.palominolabs.crm.sf.core.SObject;
import com.palominolabs.crm.sf.rest.ApiException;
import com.palominolabs.crm.sf.rest.RestQueryResult;
import com.palominolabs.crm.sf.rest.RestSObject;
import com.sivalabs.springapp.Util.RestConnectionFactory;

@Controller
public class TestController {
	@RequestMapping(value="/View", method=RequestMethod.GET)
	
	public ModelAndView shopListPage() {
		ModelAndView mav = new ModelAndView("TestView");		
		mav.addObject("noOfRecord", getNoOfRecord());
		return mav;
	}

	private int getNoOfRecord() {
	 List<RestSObject> sObjects = null;
        try {
	            StringBuilder approverQueryString = new StringBuilder();
	            approverQueryString.append("SELECT id, Name FROM  Home_Work__c ");	            
	            System.out.println("the query toget user for edit " + approverQueryString.toString());
	            RestQueryResult result = RestConnectionFactory.getConnection().query(approverQueryString.toString());
	            sObjects = result.getSObjects();           
	        } catch (Exception e) {
	            System.out.println("Error in Method "+e);
	        }
        return sObjects.size();		
	}
	
}
