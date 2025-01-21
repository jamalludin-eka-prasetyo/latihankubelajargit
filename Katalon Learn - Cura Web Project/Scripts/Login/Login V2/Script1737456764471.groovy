import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
//import com.kms.katalon.core.testobject.reporter.Reporter
import org.junit.runner.notification.Failure
import org.mockito.internal.exceptions.Reporter
import org.openqa.selenium.Keys as Keys

TestData testdata = TestDataFactory.findTestData("Data Files/Test Data Login V2")


for (int i = 1 ; i<=testdata.getRowNumbers(); i++) {
	String username = testdata.getValue(1, i)
	String password = testdata.getValue(2, i)
	String expectedResult = testdata.getValue(3, i)
	
	WebUI.comment("Testing with username : " +username+ " dan password : " +password)
//	def url = "https://katalon-demo-cura.herokuapp.com/"
//	WebUI.openBrowser(GlobalVariable.url)
	
	WebUI.openBrowser('')
	WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
	
	WebUI.click(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/a_Make Appointment'))
	WebUI.setText(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/input_Username_username'), username)
	WebUI.setText(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/input_Password_password'), password)
	WebUI.click(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/button_Login'))

	if (expectedResult == "Failure") {
		WebUI.verifyElementPresent(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/p_Login failed Please ensure the username a_eb55b5'), 0, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyElementText(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/p_Login failed Please ensure the username a_eb55b5'),
			'Login failed! Please ensure the username and password are valid.',FailureHandling.STOP_ON_FAILURE)
		WebUI.comment("Login Failed as Expected. Pesan Error ditampilkan dengan benar.")
		String message_failure= "Login gagal menggunakan username : " +username+ " dan password : " +password
//		Reporter.logError(message_failure)
		println(message_failure)
	} else if (expectedResult == "Success") {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/Login V2/Page_CURA Healthcare Service/p_Login failed Please ensure the username a_eb55b5'), 0, FailureHandling.STOP_ON_FAILURE)
		WebUI.comment("Login Success")
		String message_success = "Login berhasil menggunakan username : " +username+ " password : " +password
//		WebUI.fail(message_success)
		println(message_success)
	}
	
WebUI.closeBrowser()
	}