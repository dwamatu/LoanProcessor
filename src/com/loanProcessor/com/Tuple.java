package com.loanProcessor.com;

class Tuple {

    int count;
    double sum;

    public Tuple(int count, double sum) {
        this.count = count;
        this.sum = sum;

    }

    public Tuple(Object s) {


    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }


    @Override
    public String toString() {
        return " [count=" + count + ", sum=" + sum + "]";
    }


}