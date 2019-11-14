package com.r2soft.freedoministriesinternational.packages.payment;

import co.paystack.android.Transaction;

public interface PaymentPort {

    boolean cardIsValid();
    void setCardNumber(String cardNumber);
    void setCardExpiryMonth(int cardExpiryMonth);
    void setCardExpiryYear(int cardExpiryYear);
    void setCardCvv(String cardCvv);
    void setEmail(String email);

    void setAmount(int amount);

    void doPayment();

    void showErrorMessage();

    void init();
    void setRef(String ref);
    String getRef();
//    void showSuccessMessage();

}
