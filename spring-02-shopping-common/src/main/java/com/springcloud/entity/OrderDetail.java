package com.springcloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ORDERDETAIL表对应的实体类，用于封装一行订单明细信息
 * @author 李星
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3238462403669581196L;

	/**
	 * 订单明祥编号
	 */
	private Integer orderDetailId;

	/**
	 * 订单编号
	 */
    private Integer orderId;

    /**
     * 商品编号
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 成交价
     */
    private Double transactionPrice;

    /**
     * 成交数量
     */
    private Integer transactionCount;

    
}