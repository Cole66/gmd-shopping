package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.ClassService;
import com.springcloud.vo.ResultValue;

/**
 * һ�������������Ŀ�����
 * @author 李星
 *
 */
@RestController
@RequestMapping("type")
public class ClassController {

	@Autowired
	private ClassService classService;
	/**
	 * ��ѯ����һ��������Ϣ
	 * @return
	 */
	@RequestMapping(value = "/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();
		try {
			//����service��Ӧ�ķ�����ѯ����һ��������Ϣ���������ѯ���
			List<Class1> allClass1 = this.classService.selectAllClass1();
			//�����ѯ�ɹ�
			if(allClass1 != null && allClass1.size() > 0) {
				//���ý��״̬Ϊ0
				rv.setCode(0);
				//����map����
				Map<String, Object> map = new HashMap<>();
				//����ѯ�Ľ������Map������
				map.put("allClass1", allClass1);
				//��Map���ϴ���ResultValue������
				rv.setDataMap(map);
				
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
	/**
	 * ����һ�����ı�Ų�ѯ��Ӧ�Ķ��������Ϣ
	 * @param class1Id	һ�������
	 * @return
	 */
	@RequestMapping(value = "/selectById")
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		ResultValue rv = new ResultValue();
		try {
			//����service��Ӧ�ķ�����ѯ����һ��������Ϣ���������ѯ���
			List<Class2> class2ByClass1Id = this.classService.selectClass2ByClass1Id(class1Id);
			//�����ѯ�ɹ�
			if(class2ByClass1Id != null && class2ByClass1Id.size() > 0) {
				//���ý��״̬Ϊ0
				rv.setCode(0);
				//����map����
				Map<String, Object> map = new HashMap<>();
				//����ѯ�Ľ������Map������
				map.put("class2ByClass1Id", class2ByClass1Id);
				//��Map���ϴ���ResultValue������
				rv.setDataMap(map);
				
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
}
