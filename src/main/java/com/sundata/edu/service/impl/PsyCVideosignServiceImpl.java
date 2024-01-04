package com.sundata.edu.service.impl;

import java.util.Date;
import java.util.List;

import com.sundata.edu.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sundata.edu.dao.PsyCVideosignMapper;
import com.sundata.edu.domain.PsyCVideosign;
import com.sundata.edu.service.IPsyCVideosignService;


/**
 * 课程视频信息表Service业务层处理
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Service
public class PsyCVideosignServiceImpl implements IPsyCVideosignService 
{
    @Autowired
    private PsyCVideosignMapper psyCVideosignMapper;

    /**
     * 查询课程视频信息表
     * 
     * @param videoId 课程视频信息表主键
     * @return 课程视频信息表
     */
    @Override
    public PsyCVideosign selectPsyCVideosignByVideoId(Long videoId)
    {
        return psyCVideosignMapper.selectPsyCVideosignByVideoId(videoId);
    }

    /**
     * 查询课程视频信息表列表
     * 
     * @param psyCVideosign 课程视频信息表
     * @return 课程视频信息表
     */
    @Override
    public List<PsyCVideosign> selectPsyCVideosignList(PsyCVideosign psyCVideosign)
    {
        return psyCVideosignMapper.selectPsyCVideosignList(psyCVideosign);
    }

    /**
     * 新增课程视频信息表
     * 
     * @param psyCVideosign 课程视频信息表
     * @return 结果
     */
    @Override
    public int insertPsyCVideosign(PsyCVideosign psyCVideosign)
    {
        psyCVideosign.setCreateTime(new Date());
        return psyCVideosignMapper.insertPsyCVideosign(psyCVideosign);
    }

    /**
     * 修改课程视频信息表
     * 
     * @param psyCVideosign 课程视频信息表
     * @return 结果
     */
    @Override
    public int updatePsyCVideosign(PsyCVideosign psyCVideosign)
    {
        psyCVideosign.setUpdateTime(new Date());
        return psyCVideosignMapper.updatePsyCVideosign(psyCVideosign);
    }

    /**
     * 批量删除课程视频信息表
     * 
     * @param videoIds 需要删除的课程视频信息表主键
     * @return 结果
     */
    @Override
    public int deletePsyCVideosignByVideoIds(String videoIds)
    {
        return psyCVideosignMapper.deletePsyCVideosignByVideoIds(Convert.toStrArray(videoIds));
    }

    /**
     * 删除课程视频信息表信息
     * 
     * @param videoId 课程视频信息表主键
     * @return 结果
     */
    @Override
    public int deletePsyCVideosignByVideoId(Long videoId)
    {
        return psyCVideosignMapper.deletePsyCVideosignByVideoId(videoId);
    }
}
