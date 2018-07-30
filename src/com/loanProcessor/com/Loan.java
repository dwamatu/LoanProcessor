package com.loanProcessor.com;

 class Loan {
    String msisdn;
    String network;
    String period;
    String product;
    Double amount;

    public Loan(String msisdn, String network, String period, String product, Double amount) {

        this.msisdn = msisdn;
        this.network = network;
        this.period = period;
        this.product = product;
        this.amount = amount;
    }


    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return " [network=" + network + ", period=" + period + ", product=" + product + "]";
    }


}