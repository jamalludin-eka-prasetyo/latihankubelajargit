import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.lang.runtime.SwitchBootstraps as SwitchBootstraps
import org.openqa.selenium.support.FindBy as FindBy
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

TestData testdata = TestDataFactory.findTestData('Data Files/New Make An Appoiment v2')

WebUI.callTestCase(findTestCase('Login/Login'), [:], FailureHandling.STOP_ON_FAILURE)
for (int i = 1; i <= testdata.getRowNumbers(); i++) {

    String facility = testdata.getValue(1, i)
    String comment = testdata.getValue(2, i)
    Boolean checkBox = Boolean.parseBoolean(testdata.getValue(3, i))
    String programKesehatan = testdata.getValue(4, i)
	String tanggal_visit = testdata.getValue(5, i)
	
    WebUI.comment('Iteration ' + i)

    TestObject selectHealthcare = findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/select_Healthcare')

    WebUI.selectOptionByLabel(selectHealthcare, facility, false)

    if (checkBox) {
        WebUI.check(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/input_Apply for hospital readmission_hospit_63901f'))

        WebUI.comment('Memilih CheckBox')
    } else {
        WebUI.uncheck(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/input_Apply for hospital readmission_hospit_63901f'))

        WebUI.comment('tidak Memilih Checkbox')
    }
    
    if (programKesehatan == 'Medicaid') {
        WebUI.click(findTestObject('Object Repository/Radio Button/Page_CURA Healthcare Service/input_Medicaid_programs'))

        WebUI.comment('Memilih Radio Button Medicaid')
    } else if (programKesehatan == 'None') {
        WebUI.click(findTestObject('Object Repository/Radio Button/Page_CURA Healthcare Service/input_None_programs'))

        WebUI.comment('Memilih Radio Button None')
    } else if (programKesehatan == 'Medicare') {
        WebUI.click(findTestObject('Object Repository/Radio Button/Page_CURA Healthcare Service/input_Medicare_programs'))

        WebUI.comment('Memilih Radio Buttoh Medicare')
    } else {
        WebUI.comment('Tidak Ada Radio Button yang dipilih')
    }
    
    //WebUI.click(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/input_Apply for hospital readmission_hospit_63901f'))
    //WebUI.click(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/input_Medicaid_programs'))
	
	WebUI.setText(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/I_Visit_Date'), tanggal_visit)
	
	WebUI.delay(5)
	
//  WebUI.click(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/td_28'))
	
	WebUI.click(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/textarea_Comment_comment'))
	
    WebUI.setText(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/textarea_Comment_comment'), 
        comment)

    WebUI.click(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/button_Book Appointment'))

    WebUI.verifyElementText(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/h2_Appointment Confirmation'), 
        'Appointment Confirmation')

    WebUI.takeFullPageScreenshot()

    WebUI.click(findTestObject('Object Repository/New Make An Appoiment/Page_CURA Healthcare Service/a_Go to Homepage'))
}

WebUI.closeBrowser()

