package com.yizhi.student.service.impl;

import com.yizhi.common.utils.PageUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.yizhi.student.dao.StudentInfoDao;
import com.yizhi.student.domain.StudentInfoDO;
import com.yizhi.student.service.StudentInfoService;



@Service
public class StudentInfoServiceImpl implements StudentInfoService {



	@Autowired
	private StudentInfoDao studentInfoDao;
	
	@Override
	public StudentInfoDO get(Integer id){
		System.out.println("======service层中传递过来的id参数是：" + id + "======");
		return studentInfoDao.get(id);
	}

	//TODO 分页查询
	@Override
	public PageUtils list(Map<String, Object> map){
		int currPage = Integer.valueOf((String)map.get("currPage"));
		int pageSize = Integer.valueOf((String)map.get("pageSize"));
		int start = (currPage - 1) * pageSize;
		map.put("start",start);
		map.put("currPage",currPage);
		map.put("pageSize",pageSize);

		List<StudentInfoDO> list = studentInfoDao.list(map);
		int count = studentInfoDao.count(null);
		PageUtils page = new PageUtils(list,count,currPage,pageSize);

		return page;
	}

	//"===================================================================================="


	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoDO studentInfo){
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoDO studentInfo){
		return studentInfoDao.update(studentInfo);
	}
	
	@Override
	public int remove(Integer id){
		return studentInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		List<Integer> list = Arrays.asList(ids);
		return studentInfoDao.batchRemove(list);
	}
	
}
