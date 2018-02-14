package com.xgq.dao;

import com.xgq.mapper.IAddrMapper;
import com.xgq.po.AddrPo;
import com.xgq.po.UserPo;
import dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:51
 */
@Repository
public class AddrDao {

    @Autowired
    IAddrMapper addrMapper;

    public int selectCount(Long userId) {
        return addrMapper.selectCount(userId);
    }

    public List<AddrPo> selectPageList(Long userId, PageDto pageDto) {
        return addrMapper.selectPageList(userId,pageDto);
    }

    public AddrPo getAddrById(Long addrId) {
        return addrMapper.getAddrById(addrId);
    }

    public void updateDefaultById(Long addrId) {
        addrMapper.updateDefaultById(addrId);
    }

    public void cancleDefaultByUserId(Long userId) {
        addrMapper.cancleDefaultByUserId(userId);
    }

    public void delAddr(Long addrId) {
        addrMapper.delAddr(addrId);
    }

    public void updateAddrById(AddrPo addr) {
        addrMapper.updateAddrById(addr);
    }

    public void addAddr(AddrPo addrPo) {
        addrMapper.addAddr(addrPo);
    }
}
