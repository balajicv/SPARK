package ethoca.challenge.magicmouse.testng;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagicMousePurchase {

	private static Logger logger = LogManager.getLogger(MagicMousePurchase.class);
	
	private static final String WIN_FIRFOX_PATH = "C:/Marcel/knowledge/Selenium/Firefox3801/firefox.exe";
	
	private WebDriver fireFoxdriver = null;
	
	private int mouseQuantity = 0;
	
	public MagicMousePurchase(){
		logger.debug("Ethoce Challenge... Fire up Magic Mouse Purchase Workflow;");
	}
	
	/**
	 * The annotated method will be run only once before all tests in this suite have run.
	 */
	 @BeforeSuite
	 public void beforeSuite() {
	      logger.debug("1 > Before Suite;");
	      
	 }
	 
	 /**
	  * The annotated method will be run only once before the first test method in the current class is invoked.
	  */
	 @BeforeClass
	 public void beforeClass() {
		 logger.debug("2 > Before Class;");
	 }
	 
	 /**
	  * The annotated method will be run before any test method belonging to the classes inside the <test> tag is run.
	  */
	 @BeforeTest
	 public void beforeTest() {
	      logger.debug("3 > Before Test > Set up Browser Driver;");
	      
	      System.setProperty( "webdriver.firefox.bin", new File(WIN_FIRFOX_PATH).getAbsolutePath());
	      fireFoxdriver = new FirefoxDriver();
	      if(fireFoxdriver!=null) {
	    	  logger.debug("3 > Before Test > FireFox Driver Okay;");
	      }
	     
	 }
	 
	 /**
	  * The annotated method will be run before each test method.
	  */
	 @BeforeMethod
	 public void beforeMethod() {
	      logger.debug("4 > Before Method;");
	 }
	
	 /**
	  * Test Case ID:TC_AU_01
	  * Test Title/Name: Verify URL availability
	  */
	 @Test
	 public void loadUpStep1() {
		 logger.debug("[Purchase Flow] > STEP 1: Test Case ID:TC_AU_01;");
			
		    if(fireFoxdriver!=null) {
			    fireFoxdriver.get("http://store.demoqa.com/");  
			    fireFoxdriver.manage().window().maximize();
		    }
	 }
	
	 /**
	  * Test Case ID:TC_AU_02
	  * Test Title/Name: Select Accessories on Product category
	  */
	 @Test
	 public void loadUpStep2() {
		 logger.debug("[Purchase Flow] > STEP 2: Test Case ID:TC_AU_02;");
		 this.pause(5000);
		    if(fireFoxdriver!=null) {
		    	
		    	//locate the menu to hover over using its xpath
		    	WebElement rootMenu = fireFoxdriver.findElement(By.xpath("//a[text()='Product Category']"));
		    	WebElement subMenu = fireFoxdriver.findElement(By.xpath("//a[text()='Accessories']"));
		    	
		    	//Initiate mouse action using Actions class
		    	Actions builder = new Actions(fireFoxdriver);
		    	builder.moveToElement(rootMenu).perform();
		    	this.pause(2000);
		    	builder.moveToElement(subMenu).perform();
		    	this.pause(2000);
		    	subMenu.click();
		    }
	 }
	 
	 /**
	  * Test Case ID:TC_AU_03
	  * Test Title/Name: Click on “Add to Cart” for just Magic Mouse
	  */
	 @Test
	 public void loadUpStep3() {
		 logger.debug("[Purchase Flow] > STEP 3: Test Case ID:TC_AU_03;");
		 this.pause(5000);
		    if(fireFoxdriver!=null) {
		    	List<WebElement> addToCart = fireFoxdriver.findElements(By.name("Buy"));
		    	for(int i=0;i<addToCart.size();i++){
		    		if(i==0) {
		    		 logger.debug("...[TC_03] Clicking at [Add to Cart]  button for a Magic Mouse product;");
		    		 addToCart.get(i).click();
		    		 String addToCartValue = addToCart.get(i).getAttribute("value");
		    		 assertEquals(addToCartValue,"Add To Cart");
		    		}
		    	}
		    }
	 }
	 
	 /**
	  * Test Case ID:TC_AU_04
	  * Test Title/Name: Confirm we have 1 mouse unit at Check-Out Page
	  */
	 @Test
	 public void loadUpStep4() {
		 logger.debug("[Purchase Flow] > STEP 4: Test Case ID:TC_AU_04;");
		 this.pause(5000);
		    if(fireFoxdriver!=null) {
		    	/**
		    	 * <a href="http://store.demoqa.com/products-page/checkout/" title="Checkout" class="cart_icon">
		    	 */
		    	WebElement checkOutMenu = fireFoxdriver.findElement(By.xpath("//a[@href='http://store.demoqa.com/products-page/checkout/']"));
		    	this.pause(2000);
		    	checkOutMenu.click();
		    	/*
		    	 * <input type="text" name="quantity" size="2" value="1">
		    	 */
		    	List<WebElement> quantity = fireFoxdriver.findElements(By.name("quantity"));
		    	for(int i=0;i<quantity.size();i++){
		    		if(i==0) {
		    		 logger.debug("...[TC_04] Confirming total amount of mouse units at Check-Out Page;");
		    		 if(quantity.get(i)!=null && quantity.get(i).getAttribute("value")!=null) {
		    			 mouseQuantity = Integer.parseInt(quantity.get(i).getAttribute("value"));
		    			 logger.debug("...[TC_04] Mouse Quantity: " + mouseQuantity);
		    			 assertEquals(mouseQuantity, 1);
		    		 }
		    		}
		    	}
		    	
		    }
	 }
	 
	 /**
	  * Test Case ID:TC_AU_05
	  * Test Title/Name: “Continue” button available and working
	  */
	 @Test
	 public void loadUpStep5() {
		 logger.debug("[Purchase Flow] > STEP 5: Test Case ID:TC_AU_05;");
		 this.pause(2000);
		    if(fireFoxdriver!=null) {
		    	/*
		    	 * <a href="#" class="step2"><span>Continue</span></a>
		    	 */
		    	By byText = By.xpath("//span[text()='Continue']");
		    	By byClass = By.xpath("//div[@class='settings-padding']/span");
		    	WebElement continueBtn = fireFoxdriver.findElement(byText);
		    	continueBtn.click();
		    }
	 }
	 
	 /**
	  * Test Case ID:TC_AU_06
	  * Test Title/Name: Successfully provide billing/contact and click Purchase 
	  */
	 @Test
	 public void loadUpStep6() {
		 logger.debug("[Purchase Flow] > STEP 6: Test Case ID:TC_AU_06;");
		 this.pause(2000);
		    if(fireFoxdriver!=null) {
		    	
		    	// ----- Calculate Shipping Price ------------------------
		    	// Current Country Dropdown - id='current_country'
		        By titleShippingPrice = By.xpath("//select[@id='current_country']");
		        WebElement currentCountry = fireFoxdriver.findElement(titleShippingPrice);
		        Select currentCountryDD = new Select(currentCountry);
		        currentCountryDD.selectByVisibleText("Canada");
		        
		        titleShippingPrice = By.xpath("//input[@title='shippingstate']");
		        WebElement shippingstate = fireFoxdriver.findElement(titleShippingPrice);
		        shippingstate.sendKeys("ONTARIO");
		        
		        titleShippingPrice = By.xpath("//input[@value='Calculate']");
		        WebElement calculateShipping = fireFoxdriver.findElement(titleShippingPrice);
		        calculateShipping.click();
		        this.pause(3000);
		    	// -------------------------------------------------------
		    	
		    	
		    	// 1 ) Email - title='billingemail'
		    	By title = By.xpath("//input[@title='billingemail']");
		        WebElement billingemail = fireFoxdriver.findElement(title);
		        billingemail.sendKeys("double.double@gmail.com");
		        
		        // 2 ) First Name - title='billingfirstname'
		    	title = By.xpath("//input[@title='billingfirstname']");
		        WebElement billingfirstname = fireFoxdriver.findElement(title);
		        billingfirstname.sendKeys("Double");
		        
		        // 3 ) Last Name - title='billinglastname'
		    	title = By.xpath("//input[@title='billinglastname']");
		        WebElement billinglastname = fireFoxdriver.findElement(title);
		        billinglastname.sendKeys("Double");
		        
		        // 4 ) Address - title='billingaddress'
		    	title = By.xpath("//textarea[@title='billingaddress']");
		        WebElement billingaddress = fireFoxdriver.findElement(title);
		        billingaddress.sendKeys("140 Erskine Avenue, unit 503");
		        
		        // 5 ) City - title='billingcity'
		    	title = By.xpath("//input[@title='billingcity']");
		        WebElement billingcity = fireFoxdriver.findElement(title);
		        billingcity.sendKeys("TORONTO");
		        
		        // 6 ) Province/State - title='billingstate'
		    	title = By.xpath("//input[@title='billingstate']");
		        WebElement billingstate = fireFoxdriver.findElement(title);
		        billingstate.sendKeys("ONTARIO");
		        
		        // 7 ) Country Dropdown - title='billingcountry'
		        title = By.xpath("//select[@title='billingcountry']");
		        WebElement billingcountry = fireFoxdriver.findElement(title);
		        Select dropdown = new Select(billingcountry);
		        dropdown.selectByVisibleText("Canada");
		        
		        // 8 ) Postal Code - title='billingpostcode'
		    	title = By.xpath("//input[@title='billingpostcode']");
		        WebElement billingpostcode = fireFoxdriver.findElement(title);
		        billingpostcode.sendKeys("M4P 1Z2");
		        
		        // 9 ) Postal Code - title='billingphone'
		    	title = By.xpath("//input[@title='billingphone']");
		        WebElement billingphone = fireFoxdriver.findElement(title);
		        billingphone.sendKeys("6465557788");
		        
		        // 10 ) Shipping Address Same as Billing Address - name='shippingSameBilling'
		    	title = By.xpath("//input[@name='shippingSameBilling']");
		        WebElement shippingSameBilling = fireFoxdriver.findElement(title);
		        shippingSameBilling.click();
		        
		        // ----- Purchase Button ------------------------
		        By purchaseTitle = By.xpath("//input[@value='Purchase']");
		        WebElement purchase = fireFoxdriver.findElement(purchaseTitle);
		        purchase.click();
		        
		    }
	 }
	 
	 /**
	  * Test Case ID:TC_AU_07
	  * Test Title/Name: Confirm the Mouse Order in ‘Transaction Results’ page
	  */
	 @Test
	 public void loadUpStep7() {
		 logger.debug("[Purchase Flow] > STEP 7: Test Case ID:TC_AU_07;");
		 this.pause(2000);
		    if(fireFoxdriver!=null) {
		    	logger.debug("[Purchase Flow] > STEP 7: Confirming that you have placed the Order in ‘Transaction Results’ page");
		    	List<WebElement> rows = fireFoxdriver.findElements(By.xpath("//table[@class='wpsc-purchase-log-transaction-results']"));
		    	for (WebElement row: rows) {
		    		
		    		/*
		    		 *  Alternative XPath:
		    		 *  By.xpath("//table[@class='wpsc-purchase-log-transaction-results']//tbody//tr
		    		 */
		    	    List<WebElement> listOfRows = row.findElements(By.tagName("tr"));
		    	    for (WebElement position: listOfRows) {
		    	    	 logger.debug("...[Step 7] Transaction Results Rows: " + row.getText());
		    	    	
		    	        List<WebElement> cellList = position.findElements(By.tagName("td"));
		    	    	for (WebElement singleCell: cellList) {
		    	    			
		    	    		if(singleCell!=null && singleCell.getText()!=null) {
		    	    			String cellContent = singleCell.getText().trim();
		    	    			if(cellContent.equalsIgnoreCase("Magic Mouse")) {
		    	    				assertEquals(cellContent,"Magic Mouse");
		    	    				 logger.debug("......[Step 7] Mouse Order successfully placed in Transaction Results");
		    	    			}

		    	    		}
		   		 	    	
		    	    	}
		    	    }
		    	}
		    }
	 }
	 
	/**
	 * 
	 * The annotated method will be run after each test method.
	 */
	 @AfterMethod
	 public void afterMethod() {
		 logger.debug("5 > After Method;");
	 }
	
	 /*
	  * The annotated method will be run only once after all tests in this suite have run.
	  */
	 @AfterSuite
	 public void afterSuite() {
	      logger.debug("6 > Do it only once after all tests in this suite have run;");
	      logger.debug("6 > Closing Browser in 10 seconds...");
	      this.pause(10000);
		    
		 fireFoxdriver.close();
	 }
	
	private void pause(long pmills) {
        try {
            Thread.sleep(pmills);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  }
	
}
