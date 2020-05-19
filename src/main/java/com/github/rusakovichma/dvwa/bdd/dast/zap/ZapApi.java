package com.github.rusakovichma.dvwa.bdd.dast.zap;

import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ZapApi {

    private String target;
    private ClientApi api;

    public ZapApi(String zapAddr, int zapPort, String zapApiKey, String target) {
        this.target = target;
        api = new ClientApi(zapAddr, zapPort, zapApiKey);
    }

    public int getSpiderProgress(String taskId) throws ClientApiException {
        String status = ((ApiResponseElement) api.spider.status(taskId)).getValue();
        return Integer.parseInt(status);
    }

    public int getActiveScanProgress(String taskId) throws ClientApiException {
        String status = ((ApiResponseElement) api.ascan.status(taskId)).getValue();
        return Integer.parseInt(status);
    }

    public int getNumberOfAlerts() throws ClientApiException {
        return Integer.parseInt(((ApiResponseElement) api.core.numberOfAlerts(target)).getValue());
    }

    public int getNumberOfUnscannedRecods() throws ClientApiException {
        return Integer.parseInt(((ApiResponseElement) api.pscan.recordsToScan()).getValue());
    }

    public String getActiveScanTaskId() throws ClientApiException {
        return ((ApiResponseElement) getScanApiResponse()).getValue();
    }

    public String getSpiderTaskId() throws ClientApiException {
        return ((ApiResponseElement) getSpideringApiResponse()).getValue();
    }

    public String getJsonReport() throws ClientApiException {
        return new String(api.core.jsonreport());
    }

    public void generateHtmlReport(String filePath) throws ClientApiException {
        String report = new String(api.core.htmlreport());

        FileWriter fWriter;
        BufferedWriter writer;
        try {
            fWriter = new FileWriter(filePath);
            writer = new BufferedWriter(fWriter);
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public void printAlerts() throws ClientApiException {
        System.out.println("DastReport:");
        System.out.println(new String(api.core.xmlreport()));
    }

    private ApiResponse getSpideringApiResponse() throws ClientApiException {
        return api.spider.scan(target, null, null, null, null);
    }

    private ApiResponse getScanApiResponse() throws ClientApiException {
        return api.ascan.scan(target, "True", "False", null, null, null);
    }
}
