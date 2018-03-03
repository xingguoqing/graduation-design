package com.xgq.dao;

import com.xgq.mapper.IPartDirectoryMapper;
import com.xgq.po.PartDirectoryPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午10:32
 */
@Repository
public class PartDirectoryDao {

    @Autowired
    IPartDirectoryMapper partDirectoryMapper;

    public PartDirectoryPo getNameById(Long id){
        return partDirectoryMapper.getNameById(id);
    }
}
