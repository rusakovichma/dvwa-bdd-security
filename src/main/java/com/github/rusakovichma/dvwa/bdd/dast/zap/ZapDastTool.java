package com.github.rusakovichma.dvwa.bdd.dast.zap;

import com.github.rusakovichma.dvwa.bdd.dast.DastScanException;
import com.github.rusakovichma.dvwa.bdd.dast.DastTool;
import com.github.rusakovichma.dvwa.bdd.dast.model.DastReport;
import com.github.rusakovichma.dvwa.bdd.util.JsonUtil;

public class ZapDastTool implements DastTool {

    private final ZapApi zapApi;

    public ZapDastTool(String zapAddr, int zapPort, String zapApiKey, String target) {
        this.zapApi = new ZapApi(zapAddr, zapPort, zapApiKey, target);
    }

    public void doSpidering() throws DastScanException {
        try {
            int progress;
            String spiderTaskId = zapApi.getSpiderTaskId();
            do {
                progress = zapApi.getSpiderProgress(spiderTaskId);
            } while (progress < 100);
        } catch (Exception ex) {
            throw new DastScanException(ex);
        }
    }

    public void doPassiveScan() throws DastScanException {
        try {
            int recordsToScan;
            do {
                recordsToScan = zapApi.getNumberOfUnscannedRecods();
            } while (recordsToScan != 0);
        } catch (Exception ex) {
            throw new DastScanException(ex);
        }

    }

    public void doActiveScan() throws DastScanException {
        try {
            String activeScanTaskId = zapApi.getActiveScanTaskId();
            int progress;
            do {
                progress = zapApi.getActiveScanProgress(activeScanTaskId);
            } while (progress < 100);
        } catch (Exception ex) {
            throw new DastScanException(ex);
        }
    }

    public DastReport getReport() throws DastScanException {
        try {
            return JsonUtil.createMapper().readValue(
                    zapApi.getJsonReport(), DastReport.class);
        } catch (Exception ex) {
            throw new DastScanException(ex);
        }
    }
}
