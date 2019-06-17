package com.springcloud.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

/**
 * ��Ʒģ����Ʋ�
 * @author 李星
 * ��select�Ӿ��г����˾ۺϺ�����ǾۺϺ�������ʱ������ʹ��group by�Ӿ�
 * --查询商品表中价格最高的商品，显示：商品名称，商品价格
 * SQL语句：select goods_name,goods_prices from goods where goods_prices=(select max(goods_prices) from goods);
 * --查询商品表中价格最高的前3件商品，显示：商品名称，商品价格
 *<1>select goods_name,goods_prices from goods group by goods_prices desc limit  3;
 *<2>sql server语句：select top 3 goods_name,goods_prices from goods group by goods_prices desc
 *<3>Oracle语句：select goods_name,goods_prices from(select goods_name,goods_prices from goods order by goods_prices desc) where rownum <=3;
 *<4>MySQL：select goods_prices from goods order by goods_prices desc limit 3
 *	select goods_name,goods_prices from goods g join (select distinct goods_prices from goods order by goods_prices desc limit 3) g1 on g.goods_prices=g1.goods_prices;
        ��ʹ��group by�Ӿ�ʱ��select�Ӿ��зǾۺϺ������б��������group by�Ӿ���
   select c1.class1_name,count(g.goods_id) from class1 c1 join class2 c2 on c1.class1_id=c2.class1_id join goods g on c2.class2_id=g.class2_id group by c1.class1_name;
   
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//����service��Ӧ�ķ�������µ���Ʒ��Ϣ�����ҵõ�����Ƿ�ɹ�
			Integer insert = this.goodsService.insert(goods);
			//�����ѯ�ɹ�
			if(insert > 0) {
				//���ý��״̬Ϊ0
				rv.setCode(0);
				rv.setMessage("��Ʒ��Ϣ¼��ɹ�");
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("��Ʒ��Ϣ¼��ʧ��");
		return rv;
	}
	
	@RequestMapping(value = "/select")
	public ResultValue select(Goods goods,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			//查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			//从分页信息中获取商品信息
			List<Goods> list = pageInfo.getList();
			//如果查询到了满足条件的商品信息
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				//将查询到的信息添加到指定键的Map集合中
				map.put("goodsList", list);
				
				//设置分页信息
				PageUtils pageUtils = new PageUtils(pageInfo.getPages()*PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				//将分页信息以指定的名字添加到Map集合中
				map.put("pageUtils", pageUtils);
				
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的商品信息");
		return rv;
	}
	
	/**
	 * 修改商品信息（热卖、新品、状态、商品图片）
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/updateById")
	public ResultValue updateById(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateGoodsById = this.goodsService.updateGoodsById(goods);
			if(updateGoodsById > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品信息修改失败");
		return rv;
	}
	
	/**
	 * 修改指定编号的商品信息
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ResultValue update(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateGoodsById = this.goodsService.update(goods);
			if(updateGoodsById > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品信息修改失败");
		return rv;
	} 
	
	/**
	 * 查询销量前10的商品信息
	 * @return
	 */
	@RequestMapping(value = "/selectGroup")
	public ResultValue selectGroup() {
		ResultValue rv = new ResultValue();
		try {
			List<Goods> list = this.goodsService.selectGroup();
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//创建两个集合用于保存柱状图，x轴和y轴的数据
				List<String> x = new ArrayList<>();
				List<Integer> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合，销量存入y集合
				for (Goods goods : list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
				}
				//创建一个Map用于保存获取到的x轴和y轴的数据
				Map<String, Object> map = new HashMap<>();
				//将x轴和y轴的数据添加到Map集合中
				map.put("x", x);
				map.put("y", y);
				//将Map集合添加到ResultValue中
				rv.setDataMap(map);
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("获取失败！！！");
		return rv;
	}
}
