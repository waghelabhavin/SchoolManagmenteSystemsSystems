/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.sivalabs.springapp.web.controllers;

/**
 * 
 * @author Pham Thai Thinh
 * 
 */
public class BaseController {
	private String successView;
	private String failureView;
	private String redirectView;

	public BaseController setSuccessView(String success) {
		this.successView = success;
		return this;
	}

	public BaseController setFailureView(String failure) {
		this.failureView = failure;
		return this;
	}
	public BaseController setRedirectView(String redirect) {
		this.redirectView = redirect;
		return this;
	}

	public final String getSuccessView() {
		return this.successView;
	}
	public final String getRedirectView() {
		return this.redirectView;
	}
	public final String getFailureView() {
		if (this.failureView == null || this.failureView.length() == 0) {
			return getSuccessView();
		}
		return this.failureView;
	}
}
