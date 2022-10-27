package com.dwm.wallet.services.accounts.biz;

import com.dwm.wallet.services.accounts.biz.staticValues.MessageBundle;
import com.dwm.wallet.services.accounts.model.MonetaryAccount;

/**
 * class is suitable for functions which need more than one output in terms of:
 *      message and int/string, as well.
 */
public class FunctionMultiOutput {
    /**
     * outputs involve one message-bundle and monetary account object
     */
    public class outputByMonetaryAccount {
        public void outputByMonetaryAccount()
        { }
        /**
         /** **** variable **** */
        MessageBundle outputMessageBundle;
        MonetaryAccount outputMonAccount;

        /** **** getter & setter ****/
        public MessageBundle getOutputMessageBundle() {
            return outputMessageBundle;
        }

        public void setOutputMessageBundle(MessageBundle outputMessageBundle) {
            this.outputMessageBundle = outputMessageBundle;
        }

        public void setOutputMonAccount(MonetaryAccount outputMonAccount) {
            this.outputMonAccount = outputMonAccount;
        }

        public MonetaryAccount getOutputMonAccount() {
            return outputMonAccount;
        }
    }

    /**
     * outputs involve one message-bundle and int number
     */
    public class outputByInteger {
        public void outputByInteger(){}
        /**
        /** **** variable **** */
        MessageBundle outputMessageBundle;
        int outputInteger;

        /** **** getter & setter ****/
        public MessageBundle getOutputMessageBundle() {
            return outputMessageBundle;
        }

        public void setOutputMessageBundle(MessageBundle outputMessageBundle) {
            this.outputMessageBundle = outputMessageBundle;
        }

        public void setOutputInteger(int outputInteger) {
            this.outputInteger = outputInteger;
        }

        public int getOutputInteger() {
            return outputInteger;
        }
    }

    /**
     * outputs involve one message-bundle and string value
     */
    public class outputByString {
        /** **** variable **** */
        MessageBundle outputMessageBundle;
        String outputString;

        /** **** getter & setter ****/
        public MessageBundle getOutputMessageBundle() {
            return outputMessageBundle;
        }

        public void setOutputMessageBundle(MessageBundle outputMessageBundle) {
            this.outputMessageBundle = outputMessageBundle;
        }

        public String getOutputString() {
            return outputString;
        }

        public void setOutputString(String outputString) {
            this.outputString = outputString;
        }
    }
}