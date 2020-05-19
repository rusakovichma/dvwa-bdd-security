package com.github.rusakovichma.dvwa.bdd.dast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class DastReport implements Serializable {

    @JsonProperty("@version")
    private String version;
    @JsonProperty("@generated")
    private String generated;
    @JsonProperty("site")
    private ArrayList<Site> site = new ArrayList<Site>();

    public static class Site implements Serializable{
        @JsonProperty("@name")
        private String name;
        @JsonProperty("@host")
        private String host;
        @JsonProperty("@port")
        private int port;
        @JsonProperty("@ssl")
        private boolean ssl;
        @JsonProperty("alerts")
        private ArrayList<Alert> alerts = new ArrayList<Alert>();

        public static class Alert implements Serializable {
            @JsonProperty("pluginid")
            private int pluginid;
            @JsonProperty("alert")
            private String alert;
            @JsonProperty("name")
            private String name;
            @JsonProperty("riskcode")
            private int riskcode;
            @JsonProperty("confidence")
            private int confidence;
            @JsonProperty("riskdesc")
            private String riskdesc;
            @JsonProperty("desc")
            private String desc;
            @JsonProperty("instances")
            private ArrayList<AlertInstance> instances = new ArrayList<AlertInstance>();;

            public static class AlertInstance implements Serializable {
                @JsonProperty("uri")
                private String uri;
                @JsonProperty("method")
                private String method;
                @JsonProperty("param")
                private String param;

                public String getUri() {
                    return uri;
                }

                public void setUri(String uri) {
                    this.uri = uri;
                }

                public String getMethod() {
                    return method;
                }

                public void setMethod(String method) {
                    this.method = method;
                }

                public String getParam() {
                    return param;
                }

                public void setParam(String param) {
                    this.param = param;
                }
            }

            @JsonProperty("count")
            private int count;
            @JsonProperty("solution")
            private String solution;
            @JsonProperty("otherinfo")
            private String otherinfo;
            @JsonProperty("reference")
            private String reference;
            @JsonProperty("cweid")
            private int cweid;
            @JsonProperty("wascid")
            private int wascid;
            @JsonProperty("sourceid")
            private int sourceid;

            public int getPluginid() {
                return pluginid;
            }

            public void setPluginid(int pluginid) {
                this.pluginid = pluginid;
            }

            public String getAlert() {
                return alert;
            }

            public void setAlert(String alert) {
                this.alert = alert;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRiskcode() {
                return riskcode;
            }

            public void setRiskcode(int riskcode) {
                this.riskcode = riskcode;
            }

            public int getConfidence() {
                return confidence;
            }

            public void setConfidence(int confidence) {
                this.confidence = confidence;
            }

            public String getRiskdesc() {
                return riskdesc;
            }

            public void setRiskdesc(String riskdesc) {
                this.riskdesc = riskdesc;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public ArrayList<AlertInstance> getInstances() {
                return instances;
            }

            public void setInstances(ArrayList<AlertInstance> instances) {
                this.instances = instances;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getSolution() {
                return solution;
            }

            public void setSolution(String solution) {
                this.solution = solution;
            }

            public String getOtherinfo() {
                return otherinfo;
            }

            public void setOtherinfo(String otherinfo) {
                this.otherinfo = otherinfo;
            }

            public String getReference() {
                return reference;
            }

            public void setReference(String reference) {
                this.reference = reference;
            }

            public int getCweid() {
                return cweid;
            }

            public void setCweid(int cweid) {
                this.cweid = cweid;
            }

            public int getWascid() {
                return wascid;
            }

            public void setWascid(int wascid) {
                this.wascid = wascid;
            }

            public int getSourceid() {
                return sourceid;
            }

            public void setSourceid(int sourceid) {
                this.sourceid = sourceid;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isSsl() {
            return ssl;
        }

        public void setSsl(boolean ssl) {
            this.ssl = ssl;
        }

        public ArrayList<Alert> getAlerts() {
            return alerts;
        }

        public void setAlerts(ArrayList<Alert> alerts) {
            this.alerts = alerts;
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public ArrayList<Site> getSite() {
        return site;
    }

    public void setSite(ArrayList<Site> site) {
        this.site = site;
    }
}
