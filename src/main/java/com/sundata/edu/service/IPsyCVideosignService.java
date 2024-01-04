package com.sundata.edu.service;

import java.util.List;
import com.sundata.edu.domain.PsyCVideosign;

/**
 * 课程视频信息表Service接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface IPsyCVideosignService 
{
    /**
     * 查询课程视频信息表
     * 
     * @param videoId 课程视频信息表主键
     * @return 课程视频信息表
     */
    public PsyCVideosign selectPsyCVideosignByVideoId(Long videoId);

    /**
     * 查询课程视频信息表列表
     * 
     * @param psyCVideosign 课程视频信息表
     * @return 课程视频信息表集合
     */
    public List<PsyCVideosign> selectPsyCVideosignList(PsyCVideosign psyCVideosign);

    /**
     * 新增课程视频信息表
     * 
     * @param psyCVideosign 课程视频信息表
     * @return 结果
     */
    public int insertPsyCVideosign(PsyCVideosign psyCVideosign);

    /**
     * 修改课程视频信息表
     * 
     * @param psyCVideosign 课程视频信息表
     * @return 结果
     */
    public int updatePsyCVideosign(PsyCVideosign psyCVideosign);

    /**
     * 批量删除课程视频信息表
     * 
     * @param videoIds 需要删除的课程视频信息表主键集合
     * @return 结果
     */
    public int deletePsyCVideosignByVideoIds(String videoIds);

    /**
     * 删除课程视频信息表信息
     * 
     * @param videoId 课程视频信息表主键
     * @return 结果
     */
    public int deletePsyCVideosignByVideoId(Long videoId);
}
