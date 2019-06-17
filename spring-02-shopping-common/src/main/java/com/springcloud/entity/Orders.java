package com.springcloud.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * ORDERS表对应的实体类，用于封装一行订单信息
 * @author 李星
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8637324993553198139L;

	/**
	 * 订单编号
	 */
	private Integer orderId;

	/**
	 * 当前订单的用户信息
	 */
    private Users user;

    /**
	 * 收货人姓名
	 */
    private String consigneeName;

    /**
	 * 收货人电话
	 */
    private String consigneeNumber;

    /**
	 * 收货人地址
	 */
    private String consigneeSite;

    /**
	 * 下单时间,默认为当前时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    /**
	 * 订单总额
	 */
    private Double orderAmount;

    /**
	 * 订单状态:0:待发货,1:待付款,2:待收货,3:已付款,4:已退货
	 */
    private Integer orderStatus;
    
    /**
     *  查询条件：订单的起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDateMin;
    
    /**
     * 查询条件：订单的终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDateMax;
    
    /**
     * 查询条件：起始年月
     */
    private String startMonth;
    
    /**
     * 查询条件：终止年月
     */
    private String endMonth;
    
    /**
     * 统计结果的年月
     */
    private String ordersMonth;
    /**
     * 统计的销售额，BigDecimal可以保存比较大的数据
     */
    private Double orderPrice;

}