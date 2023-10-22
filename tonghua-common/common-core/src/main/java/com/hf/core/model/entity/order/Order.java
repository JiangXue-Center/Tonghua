package com.hf.core.model.entity.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Java entity class for the 'order' table.
 */
public class Order {
    /**
     * 订单ID
     */
    private long orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 配送方式 (0: 快递, 1: 自提, 2: 其他)
     */
    private Integer deliveryMethod;

    /**
     * 收货状态 (0: 未收货, 1: 已收货)
     */
    private Integer receiptStatus;

    /**
     * 物流单号
     */
    private String trackingNumber;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 发票类型 (0: 个人, 1: 公司)
     */
    private Integer invoiceType;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 收票人电话
     */
    private String invoiceReceiverPhone;

    /**
     * 收票人邮箱
     */
    private String invoiceReceiverEmail;

    /**
     * 订单总额
     */
    private BigDecimal orderTotal;

    /**
     * 运费金额
     */
    private BigDecimal shippingFee;

    /**
     * 商品总额
     */
    private BigDecimal productTotal;

    /**
     * 抵扣金额
     */
    private BigDecimal deductionAmount;

    /**
     * 实付金额
     */
    private BigDecimal actualPayment;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    private Date receiptConfirmTime;

    /**
     * 订单来源 (0: 网站, 1: 移动应用, 2: 电话订单)
     */
    private Integer orderSource;

    /**
     * 支付方式 (0: 支付宝, 1: 微信支付, 2: 银行卡, 3: 货到付款)
     */
    private Integer paymentMethod;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 订单状态 (0: 待付款, 1: 待发货, 2: 已发货, 3: 已完成, 4: 已关闭)
     */
    private Integer orderStatus;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverZipCode;

    /**
     * 一级收货地址
     */
    private String firstLevelReceiverAddress;

    /**
     * 二级收货地址
     */
    private String secondLevelReceiverAddress;

    /**
     * 三级收货地址
     */
    private String thirdLevelReceiverAddress;

    /**
     * 四级收货地址
     */
    private String fourthLevelReceiverAddress;

    /**
     * 五级收货地址
     */
    private String fifthLevelReceiverAddress;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 订单备注
     */
    private String orderNote;

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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceReceiverPhone() {
        return invoiceReceiverPhone;
    }

    public void setInvoiceReceiverPhone(String invoiceReceiverPhone) {
        this.invoiceReceiverPhone = invoiceReceiverPhone;
    }

    public String getInvoiceReceiverEmail() {
        return invoiceReceiverEmail;
    }

    public void setInvoiceReceiverEmail(String invoiceReceiverEmail) {
        this.invoiceReceiverEmail = invoiceReceiverEmail;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(BigDecimal productTotal) {
        this.productTotal = productTotal;
    }

    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiptConfirmTime() {
        return receiptConfirmTime;
    }

    public void setReceiptConfirmTime(Date receiptConfirmTime) {
        this.receiptConfirmTime = receiptConfirmTime;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }

    public String getFirstLevelReceiverAddress() {
        return firstLevelReceiverAddress;
    }

    public void setFirstLevelReceiverAddress(String firstLevelReceiverAddress) {
        this.firstLevelReceiverAddress = firstLevelReceiverAddress;
    }

    public String getSecondLevelReceiverAddress() {
        return secondLevelReceiverAddress;
    }

    public void setSecondLevelReceiverAddress(String secondLevelReceiverAddress) {
        this.secondLevelReceiverAddress = secondLevelReceiverAddress;
    }

    public String getThirdLevelReceiverAddress() {
        return thirdLevelReceiverAddress;
    }

    public void setThirdLevelReceiverAddress(String thirdLevelReceiverAddress) {
        this.thirdLevelReceiverAddress = thirdLevelReceiverAddress;
    }

    public String getFourthLevelReceiverAddress() {
        return fourthLevelReceiverAddress;
    }

    public void setFourthLevelReceiverAddress(String fourthLevelReceiverAddress) {
        this.fourthLevelReceiverAddress = fourthLevelReceiverAddress;
    }

    public String getFifthLevelReceiverAddress() {
        return fifthLevelReceiverAddress;
    }

    public void setFifthLevelReceiverAddress(String fifthLevelReceiverAddress) {
        this.fifthLevelReceiverAddress = fifthLevelReceiverAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
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

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
