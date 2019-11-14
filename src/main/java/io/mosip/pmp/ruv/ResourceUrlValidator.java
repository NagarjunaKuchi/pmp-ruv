package io.mosip.pmp.ruv;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.mosip.pmp.ruv.validate.Validator;

@Test
public class ResourceUrlValidator 
{	
	@BeforeSuite
	public void beforeSuite(){
		
	}
	
	@Test
	private void run(){
		Validator validator = new Validator();
		boolean isSuccess = validator.valiadte();
		if(!isSuccess){
			Assert.fail();
		}
	}
}
