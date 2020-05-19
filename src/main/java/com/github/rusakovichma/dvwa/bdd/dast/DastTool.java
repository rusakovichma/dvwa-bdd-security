package com.github.rusakovichma.dvwa.bdd.dast;

import com.github.rusakovichma.dvwa.bdd.dast.model.DastReport;
import org.zaproxy.clientapi.core.ClientApiException;

public interface DastTool {

    public void doSpidering() throws DastScanException;

    public void doPassiveScan() throws DastScanException;

    public void doActiveScan() throws DastScanException;

    public DastReport getReport() throws DastScanException;

}
