package com.r2soft.freedoministriesinternational.packages.payment;

public class PaymentService implements PaymentPort {

    private PaymentPort paymentPort = null;

    public PaymentService(PaymentPort paymentPort){
        this.paymentPort = paymentPort;
    }

    @Override
    public boolean cardIsValid() {
        return paymentPort.cardIsValid();
    }

    @Override
    public void setCardNumber(String cardNumber) {
      paymentPort.setCardNumber(cardNumber);
    }

    @Override
    public void setCardExpiryMonth(int cardExpiryMonth) {
        paymentPort.setCardExpiryMonth(cardExpiryMonth);
    }

    @Override
    public void setCardExpiryYear(int cardExpiryYear) {
        paymentPort.setCardExpiryYear(cardExpiryYear);
    }

    @Override
    public void setCardCvv(String cardCvv) {
        paymentPort.setCardCvv(cardCvv);
    }

    @Override
    public void setEmail(String email) {
        paymentPort.setEmail(email);
    }

    @Override
    public void setAmount(int amount) {
        paymentPort.setAmount(amount);
    }

    @Override
    public void doPayment() {
        init();
        if (cardIsValid()){
            paymentPort.doPayment();
        }else{
            showErrorMessage();
        }
    }

    @Override
    public void showErrorMessage() {
        paymentPort.showErrorMessage();
    }

    @Override
    public void init() {
        paymentPort.init();
    }

    @Override
    public void setRef(String ref) {
        paymentPort.setRef(ref);
    }

    @Override
    public String getRef() {
        return paymentPort.getRef();
    }

}

