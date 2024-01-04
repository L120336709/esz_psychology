package com.sundata.edu.service.impl;

import java.util.*;

import com.sundata.edu.dao.InvigilatorMapper;
import com.sundata.edu.domain.Invigilator;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.service.IInvigilatorService;
import com.sundata.edu.util.Convert;
import com.sundata.edu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务层实现
 * 
 * @author shunguo
 * @date 2021-12-10
 */
@Service
public class InvigilatorServiceImpl implements IInvigilatorService
{
	@Autowired
	private InvigilatorMapper invigilatorMapper;

	/**
     * 查询信息
     * 
     * @param teachid ID
     * @return 信息
     */
    @Override
	public Invigilator selectInvigilatorById(String teachid)
	{
	    return invigilatorMapper.selectInvigilatorById(teachid);
	}

	/**
     * 查询列表
     * 
     * @param invigilator 信息
     * @return 集合
     */
	@Override
	public List<Invigilator> selectInvigilatorList(Invigilator invigilator)
	{
	    return invigilatorMapper.selectInvigilatorList(invigilator);
	}

	/**
	 * 随机排序监考老师监考安排
	 * @param Invigilators
	 * @return
	 */
	@Override
	public String[] randomSorting(String[] Invigilators){
		for (int i=Invigilators.length-1;i>=0;--i)
		{
			int x;
			if(i>0){
				x= new Random().nextInt(i);
			}else {
				x=0;
			}
			String y=Invigilators[i];
			Invigilators[i]=Invigilators[x];
			Invigilators[x]=y;
		}

		return  Invigilators;
	}
	
    /**
     * 新增
     * 
     * @param invigilator 信息
     * @return 结果
     */
	@Override
	public int insertInvigilator(Invigilator invigilator)
	{
	    return invigilatorMapper.insertInvigilator(invigilator);
	}
	
	/**
     * 修改
     * 
     * @param invigilator 信息
     * @return 结果
     */
	@Override
	public int updateInvigilator(Invigilator invigilator)
	{
	    return invigilatorMapper.updateInvigilator(invigilator);
	}

	/**
     * 删除对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteInvigilatorByIds(String ids)
	{
		return invigilatorMapper.deleteInvigilatorByIds(Convert.toStrArray(ids));
	}

	/**
	 * 导入监考人员信息信息
	 * @param InvigilatorList
	 * @param updateSupport
	 * @return
	 */
	public String InvigilatorListImport(List<Invigilator> InvigilatorList, boolean updateSupport){
		if (StringUtils.isNull(InvigilatorList) || InvigilatorList.size() == 0)
		{
			throw new ServiceException("导入用户数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		List<Invigilator> invigilatorListinsert=new ArrayList<>();
		List<Invigilator> invigilatorListupdate=new ArrayList<>();
		List<String> Idnumber=new ArrayList<>();

		for (Invigilator invigilator : InvigilatorList)
		{
			Idnumber.add(invigilator.getIdnumber());
		}

		//查询已经存在的数据
		List<Invigilator> invigilators=invigilatorMapper.findUserListByIdnumberList(Idnumber);

		//把存在的和不存在的数据分别保存，进行插入和更新
		for(Invigilator invigilatornew : InvigilatorList){
			//判断是否存在，存在放入exaPostgraduatelistupdate，不存在放入exaPostgraduatelistinsert
			boolean i=false;//用于判断是否已经放入存在的集合
			for (Invigilator invigilatorold:invigilators){
				if(invigilatornew.getIdnumber().equals(invigilatorold.getIdnumber())){
					invigilatornew.setTeachid(invigilatorold.getTeachid());
					invigilatorListupdate.add(invigilatornew);
					i=true;
				}
			}
			if(i==false){
				String uuid = UUID.randomUUID().toString().replaceAll("-","");
				invigilatornew.setTeachid(uuid);
				invigilatorListinsert.add(invigilatornew);
			}

		}
		//分别对新增数据和更新数据进行操作
		if(invigilatorListinsert.size()>0){
			invigilatorMapper.insertInvigilatorList(invigilatorListinsert);
			successNum=successNum+invigilatorListinsert.size();
			for(Invigilator mes : invigilatorListinsert){
				successMsg.append("<br/>" +  mes.getTeachname() + "监考工作人员信息 "  + " 导入成功");
			}

		}
		if(invigilatorListupdate.size()>0){
			if(updateSupport){
				invigilatorMapper.updateInvigilatorList(invigilatorListupdate);
				successNum=successNum+invigilatorListupdate.size();
				for(Invigilator mes : invigilatorListupdate){
					successMsg.append("<br/>" + mes.getTeachname() + "监考工作人员信息 " + " 更新成功");
				}
			}else {
				failureNum=failureNum+invigilatorListupdate.size();
				for(Invigilator mes : invigilatorListupdate){
					failureMsg.append("<br/>" + mes.getTeachname() + "监考工作人员信息 "  + " 已存在");
				}

			}
		}

		if (failureNum > 0)
		{
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new ServiceException(failureMsg.toString(),new Throwable());
		}
		else
		{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}

}
