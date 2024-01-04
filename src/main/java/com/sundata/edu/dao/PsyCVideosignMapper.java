package com.sundata.edu.dao;

import java.util.List;
import com.sundata.edu.domain.PsyCVideosign;
import org.apache.ibatis.annotations.Param;

/**
 * 课程视频信息表Mapper接口
 * 
 * @author ljg
 * @date 2023-11-09
 */
public interface PsyCVideosignMapper 
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


    List<PsyCVideosign> selectPsyCVideosignListByunitsId(@Param("list") List<Long> unitsId);
    List<PsyCVideosign> selectPsyCVideosignListByIds(@Param("list") List<String> videoId);

    /**
     * 批量插入数据
     * @param psyCVideosign
     * @return
     */
    int insertPsyCVideosignList(@Param("list")List<PsyCVideosign> psyCVideosign);
    /**
     * 批量更新数据
     * @param psyCVideosign
     * @return
     */
    int updatePsyCVideosignList(@Param("list")List<PsyCVideosign> psyCVideosign);

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
     * 删除课程视频信息表
     * 
     * @param videoId 课程视频信息表主键
     * @return 结果
     */
    public int deletePsyCVideosignByVideoId(Long videoId);

    /**
     * 批量删除课程视频信息表
     * 
     * @param videoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyCVideosignByVideoIds(String[] videoIds);
}
