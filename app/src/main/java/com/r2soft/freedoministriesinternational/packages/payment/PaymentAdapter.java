package com.r2soft.freedoministriesinternational.packages.payment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.r2soft.freedoministriesinternational.packages.ajax.AjaxAdapter;
import com.r2soft.freedoministriesinternational.packages.ajax.AjaxService;
import com.r2soft.freedoministriesinternational.packages.apis.DonationApiRequestAdapter;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class PaymentAdapter implements PaymentPort {

    String cardNumber = "";
    int cardExpiryMonth = 0;
    int cardExpiryYear = 0;
    String cardCvv = "";
    int amount = 0;
    String email = "";

    Card card = null;

    Activity act = null;
    View view = null;

    private static String ref = "";

    public PaymentAdapter(Activity act, View view){
        this.act = act;
        this.view = view;
        PaystackSdk.initialize(act.getApplicationContext());
    }



    @Override
    public boolean cardIsValid() {
        return card.isValid();
    }

    @Override
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void setCardExpiryMonth(int cardExpiryMonth) {
        this.cardExpiryMonth = cardExpiryMonth;
    }

    @Override
    public void setCardExpiryYear(int cardExpiryYear) {
        this.cardExpiryYear = cardExpiryYear;
    }

    @Override
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    @Override
    public void setEmail(String email) {
       this.email = email;
    }

    @Override
    public void setAmount(int amount) {
      this.amount = amount;
    }

    private void showSuccess(Transaction transaction){
        setRef(transaction.getReference());
        logToServer();
        AlertDialog.Builder alt = new AlertDialog.Builder(act);
        alt.setTitle("Payment success");
        alt.setMessage("Your payment was successfull.");
        alt.show();
    }

    private void logToServer(){
        AjaxService ajaxService = new AjaxService(new AjaxAdapter(act));
        DonationApiRequestAdapter donationApiRequestAdapter = new DonationApiRequestAdapter(view,act);
        ajaxService.post(donationApiRequestAdapter);
    }

    private void showError(){
        AlertDialog.Builder alt = new AlertDialog.Builder(act);
        alt.setTitle("Payment error");
        alt.setMessage("Your payment was not successfull.");
        alt.show();
    }

    @Override
    public void doPayment() {

        final ProgressDialog progressDialog = new ProgressDialog(act);
        progressDialog.setTitle("Processing Payment ... ");
        progressDialog.show();

        Charge charge = new Charge();
        charge.setCard(card);
        charge.setAmount(amount * 100); //convert to kobo
        charge.setEmail(email);

        PaystackSdk.setPublicKey("pk_live_2ef5768c12027e11f1cb9fef46df2b521b676b47");
        //pk_live_2ef5768c12027e11f1cb9fef46df2b521b676b47
        //pk_test_5277568ad00c96f65f9db390d525b05029736023

        try {
            PaystackSdk.chargeCard(act, charge, new Paystack.TransactionCallback() {
                @Override
                public void onSuccess(Transaction transaction) {
                    progressDialog.dismiss();
                    showSuccess(transaction);
                }

                @Override
                public void beforeValidate(Transaction transaction) {
                    progressDialog.dismiss();
//                showError();
                }

                @Override
                public void onError(Throwable error, Transaction transaction) {
                    showError();
                }

            });
        }catch (Exception ex){
            AlertDialog.Builder alt = new AlertDialog.Builder(act);
            alt.setTitle("Payment error");
            alt.setMessage(ex.getMessage());
            alt.show();
        }

    }

    @Override
    public void showErrorMessage() {
        AlertDialog.Builder alt = new AlertDialog.Builder(act);
        alt.setTitle("Card error");
        alt.setMessage("Invalid Card Details.");
        alt.show();
    }

    @Override
    public void init() {
        card = new Card(cardNumber,cardExpiryMonth,cardExpiryYear,cardCvv);
    }

    @Override
    public void setRef(String ref) {
        PaymentAdapter.ref = ref;
    }

    @Override
    public String getRef() {
        return ref;
    }


}
