package com.hf.core.model.entity.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Java entity class for the 'payment_info' table.
 */
public class PaymentInfo {
    /**
     * 支付信息ID
     */
    private long paymentInfoId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 交易流水号
     */
    private String transactionId;

    /**
     * 支付总金额
     */
    private BigDecimal totalPaymentAmount;

    /**
     * 交易内容
     */
    private String transactionContent;

    /**
     * 支付状态 (0: 未支付, 1: 已支付, 2: 支付失败)
     */
    private Integer paymentStatus;

    /**
     * 回调内容
     */
    private String callbackContent;

    /**
     * 回调时间
     */
    private Date callbackTime;

    /**
     * Creation Timestamp
     */
    private Date gmtCreate;

    /**
     * Last Modification Timestamp
     */
    private Date gmtModify;

    /**
     * Soft Delete Flag (0 for active, 1 for deleted)
     */
    private boolean deleted;

    public PaymentInfo() {
    }

    // Getters and setters for the above fields
    // (Getter and setter methods for deleted should be boolean-based, not using isDeleted and setDeleted)

    public long getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(long paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCallbackContent() {
        return callbackContent;
    }

    public void setCallbackContent(String callbackContent) {
        this.callbackContent = callbackContent;
    }

    public Date getCallbackTime() {
        return callbackTime;
    }

    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
