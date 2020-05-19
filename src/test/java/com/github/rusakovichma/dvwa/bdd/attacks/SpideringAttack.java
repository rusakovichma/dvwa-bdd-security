package com.github.rusakovichma.dvwa.bdd.attacks;

import com.github.rusakovichma.dvwa.bdd.dast.DastTool;
import com.github.rusakovichma.dvwa.bdd.dast.model.DastReport;
import com.github.rusakovichma.dvwa.bdd.dast.zap.ZapDastTool;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static com.github.rusakovichma.dvwa.bdd.DvwaSettings.*;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class SpideringAttack {

    private DastTool dastTool;
    private String target;

    @Given("^perform spidering with 'Zap' DAST tool on the main page: '(.*)'$")
    public void perform_spidering_with_Zap_DAST_tool_on_the_main_page(String target) throws Throwable {
        this.dastTool = new ZapDastTool(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY, target);
        this.target = target;

        dastTool.doSpidering();
    }

    @Then("^there is no any unrestricted URLs, functionality or High security issues found$")
    public void no_any_unrestricted_URLs_functionality_or_security_issues_found() throws Throwable {
        Thread.sleep(10000l);
        DastReport report = dastTool.getReport();
        assertNotNull(report);

        for (final DastReport.Site site: report.getSite()){
            for (final DastReport.Site.Alert alert: site.getAlerts()){
                //Verify High level alerts
                assertTrue(alert.getRiskcode() < 3);

                //Verify to view the directory listing
                if (alert.getAlert().equals("Directory Browsing") && alert.getConfidence() >=2 ){
                    assertTrue(alert.getInstances().isEmpty());
                }
            }
        }
    }

}
