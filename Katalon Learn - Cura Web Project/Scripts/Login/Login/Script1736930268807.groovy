import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory


// Mendapatkan Test Data
TestData testData = TestDataFactory.findTestData('Data Files/New Test Data')

WebUI.openBrowser('')
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment'))

// Iterasi melalui setiap baris data
for (int i = 1; i <= testData.getRowNumbers(); i++) {
	String username = testData.getValue(1, i)
	String password = testData.getValue(2, i)

	WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Username_username'), username)
	WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/input_Password_password'), password)
	WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/button_Login'))

	if (WebUI.verifyElementText(findTestObject('Object Repository/Page_CURA Healthcare Service/h2_Make Appointment'), 'Make Appointment', FailureHandling.STOP_ON_FAILURE)) {
		// Login Berhasil
		WebUI.comment("Login Berhasil")
		Reporter.logTestStep("Login Berhasil: Username = " + username + ", Password = " + "[redacted]", true)
		WebUI.takeScreenshot("Login_berhasil_" + i + ".png")
	} else {
		// Login Gagal
		WebUI.comment("Login Gagal")
		Reporter.logTestStep("Login Gagal: Username = " + username + ", Password = " + "[redacted]", false)
		WebUI.takeScreenshot("Login_gagal_" + i + ".png")
		WebUI.fail("Login gagal. Teks 'Make Appointment' tidak ditemukan.")
	}
} // Penutup loop ditambahkan

WebUI.closeBrowser()
