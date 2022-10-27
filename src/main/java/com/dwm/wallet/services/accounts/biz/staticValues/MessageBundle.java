package com.dwm.wallet.services.accounts.biz.staticValues;

/**
 * improvement possibility: placed @ independent module and share between all services
 */
public enum MessageBundle {
    validateSuccess,
    saveSuccess,
    validateFail_UnknownPlayer,
    validateFail_DuplicateAccount,
    validateFail_UnValidRequestedAmount,
    validateFail_UnknownAccount,
    validateFail_AccountClosed,
    validateFail_InsufficientBalance
}
