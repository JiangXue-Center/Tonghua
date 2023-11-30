package com.hf.core.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OrderDetailDTO> orderDetailDTOs;

    /**
     * 配送方式 (0: 快递, 1: 自提, 2: 其他)
     */
    @NotNull
    private Integer deliveryMethod;

    /**
     * 发票抬头 (0: 个人, 1: 公司)
     */
    @NotBlank
    private String invoiceTitle;

    /**
     * 发票类型 (0: 纸质发票, 1: 电子普通发票)
     */
    @NotNull
    private Integer invoiceType;

    /**
     * 发票内容
     */
    @NotBlank
    private String invoiceContent;

    /**
     * 收票人电话
     */
    @NotBlank
    private String invoiceReceiverPhone;

    /**
     * 收票人
     */
    @NotBlank
    private String invoiceReceiver;

    /**
     * 运费金额
     */
    @NotNull
    private BigDecimal shippingFee;

    /**
     * 商品总额
     */
    @NotNull
    private BigDecimal productTotal;

    /**
     * 抵扣金额
     */
    @NotNull
    private BigDecimal deductionAmount;

    /**
     * 订单来源 (0: 网站, 1: 移动应用, 2: 电话订单)
     */
    @NotNull
    private Integer orderSource;

    /**
     * 收货人姓名
     */
    @NotNull
    private String receiverName;

    /**
     * 收货人电话
     */
    @NotNull
    private String receiverPhone;

    /**
     * 一级收货地址
     */
    @NotBlank
    private String firstLevelReceiverAddress;

    /**
     * 二级收货地址
     */
    @NotBlank
    private String secondLevelReceiverAddress;

    /**
     * 三级收货地址
     */
    @NotBlank
    private String thirdLevelReceiverAddress;

    /**
     * 四级收货地址
     */
    @NotBlank
    private String fourthLevelReceiverAddress;

    /**
     * 五级收货地址
     */
    @NotBlank
    private String fifthLevelReceiverAddress;

    /**
     * 详细地址
     */
    @NotBlank
    private String detailedAddress;

    /**
     * 订单备注
     */
    private String orderNote;

    public List<OrderDetailDTO> getOrderDetailDTOs() {
        return orderDetailDTOs;
    }

    public void setOrderDetailDTOs(List<OrderDetailDTO> orderDetailDTOs) {
        this.orderDetailDTOs = orderDetailDTOs;
    }

    public Integer getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
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

    public String getInvoiceReceiver() {
        return invoiceReceiver;
    }

    public void setInvoiceReceiver(String invoiceReceiver) {
        this.invoiceReceiver = invoiceReceiver;
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

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
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
}
