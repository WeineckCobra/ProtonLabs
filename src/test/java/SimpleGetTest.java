import com.google.gson.Gson;

import java.io.*;
import java.util.UUID;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

    private ExtentReports extent;
    private ExtentTest extentTest;
    String uuid = UUID.randomUUID().toString();

    @Test
    public void get1() {

        String filePath = System.getProperty("java.io.tmpdir") + "reports"+ File.separatorChar;
        extent = new ExtentReports(filePath + "report-" + uuid + ".html", false);
        extentTest = extent.startTest("VPN Test Functionality");

        RestAssured.baseURI = "http://api.protonmail.ch/vpn/logicals";
        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET);

        String responseBody = response.getBody().asString();
        // System.out.println("Response Body is =>  " + responseBody);

        Gson g = new Gson();
        Proton logicalServers1 = g.fromJson(responseBody, Proton.class);

        int i = 0;
        int basic = 0;
        int secured = 0;
        int free = 0;

        for (i = 0; i < logicalServers1.getLogicalServers().size() ; i++) {
            if (logicalServers1.getLogicalServers().get(i).getName() != null) {
                if (logicalServers1.getLogicalServers().get(i).getEntryCountry() != null) {
                    if (logicalServers1.getLogicalServers().get(i).getExitCountry() != null) {
                        if (logicalServers1.getLogicalServers().get(i).getDomain() != null) {
                            if (logicalServers1.getLogicalServers().get(i).getFeatures() >= 0 && logicalServers1.getLogicalServers().get(i).getFeatures() <= 4) {
                                if (logicalServers1.getLogicalServers().get(i).getID() != null) {
                                    if (logicalServers1.getLogicalServers().get(i).getStatus() == 1) {
                                        if (logicalServers1.getLogicalServers().get(i).getLoad() > 0) {

                                            for (int j = 0; j < logicalServers1.getLogicalServers().get(i).getServers().size(); j++) {

                                                if (logicalServers1.getLogicalServers().get(i).getServers().get(j).getEntryIP() != null) {
                                                    if (logicalServers1.getLogicalServers().get(i).getServers().get(j).getExitIP() != null) {
                                                        if (logicalServers1.getLogicalServers().get(i).getServers().get(j).getID() != null) {

                                                            String input = logicalServers1.getLogicalServers().get(i).getDomain();
                                                            boolean isFound = input.contains("-free");

                                                            if (logicalServers1.getLogicalServers().get(i).getFeatures() == 0) {
                                                                basic++;
                                                            } else if (logicalServers1.getLogicalServers().get(i).getFeatures() == 1) {
                                                                secured++;
                                                            }

                                                            if (isFound) {
                                                                free++;
                                                            }
                                                        }
                                                        else extentTest.log(LogStatus.ERROR,"A server in "+logicalServers1.getLogicalServers().get(i).getName()+" is missing ID." );
                                                    }
                                                    else extentTest.log(LogStatus.ERROR,"A server in "+logicalServers1.getLogicalServers().get(i).getName()+" is missing ExitIP." );
                                                }
                                                else extentTest.log(LogStatus.ERROR,"A server in "+logicalServers1.getLogicalServers().get(i).getName()+" is missing EntryIP." );
                                            }

                                        }
                                        else extentTest.log(LogStatus.ERROR,"The server "+logicalServers1.getLogicalServers().get(i).getName()+" has a negative load." );
                                    }
                                    else extentTest.log(LogStatus.ERROR, "The server "+logicalServers1.getLogicalServers().get(i).getName()+" is offline.");
                                }
                                else extentTest.log(LogStatus.ERROR,"The server "+logicalServers1.getLogicalServers().get(i).getName()+" is missing ID." );
                            }
                            else extentTest.log(LogStatus.ERROR,"The server "+logicalServers1.getLogicalServers().get(i).getName()+" has invalid feature indicator." );
                        }
                        else extentTest.log(LogStatus.ERROR,"The server "+logicalServers1.getLogicalServers().get(i).getName()+" is missing Domain." );
                    }
                    else extentTest.log(LogStatus.ERROR,"The server "+logicalServers1.getLogicalServers().get(i).getName()+" is missing ExitCountry." );
                }
                else extentTest.log(LogStatus.ERROR,"The server "+logicalServers1.getLogicalServers().get(i).getName()+" is missing EntryCountry." );
            }
            else extentTest.log(LogStatus.ERROR,"The with ID: "+logicalServers1.getLogicalServers().get(i).getID()+" is missing name." );
        }

        if(basic>0){
            if(secured>0){
                if(free>0){
                    extentTest.log(LogStatus.PASS, "The VPN can work properly. There is a Basic, Secured and Free server available.");
                }
                else
                    extentTest.log(LogStatus.ERROR,"There is no Free server available.");
            }
            else
                extentTest.log(LogStatus.ERROR,"There is no Secured server available.");
        }
        else
            extentTest.log(LogStatus.ERROR,"There is no Basic server available");


        extent.endTest(extentTest);
        extent.flush();

    }

}
