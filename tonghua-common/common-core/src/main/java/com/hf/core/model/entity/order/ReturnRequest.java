package com.hf.core.model.entity.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Java entity class for the 'return_request' table.
 */
public class ReturnRequest {
    /**
     * 退货申请ID
     */
    private long returnRequestId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 申请时间
     */
    private Date requestTime;

    /**
     * 退货人姓名
     */
    private String returnerName;

    /**
     * 退货人电话
     */
    private String returnerPhone;

    /**
     * 处理人员
     */
    private String handler;

    /**
     * 凭证
     */
    private String credentials;

    /**
     * 备注
     */
    private String note;

    /**
     * 收货人
     */
    private String receiverName;

    /**
     * 收货时间
     */
    private Date receiptTime;

    /**
     * 收回电话
     */
    private String receiptPhone;

    /**
     * 收回地址
     */
    private String receiptAddress;

    /**
     * 收货备注
     */
    private String receiptNote;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 退货数量
     */
    private int returnQuantity;

    /**
     * 申请状态 (0: 待处理, 1: 已处理)
     */
    private Integer requestStatus;

    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品实付金额
     */
    private BigDecimal actualPayment;

    /**
     * 退货原因
     */
    private String returnReason;

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

    // Getters and setters for the above fields
    // (Getter and setter methods for deleted should be boolean-based, not using isDeleted and setDeleted)

    public long getReturnRequestId() {
        return returnRequestId;
    }

    public void setReturnRequestId(long returnRequestId) {
        this.returnRequestId = returnRequestId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getReturnerName() {
        return returnerName;
    }

    public void setReturnerName(String returnerName) {
        this.returnerName = returnerName;
    }

    public String getReturnerPhone() {
        return returnerPhone;
    }

    public void setReturnerPhone(String returnerPhone) {
        this.returnerPhone = returnerPhone;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptNote() {
        return receiptNote;
    }

    public void setReceiptNote(String receiptNote) {
        this.receiptNote = receiptNote;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(int returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
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
