package com.xgq.mapper;

import com.xgq.po.AddrPo;
import com.xgq.po.UserPo;
import dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:52
 */
@Mapper
public interface IAddrMapper {


    int selectCount(@Param("userId") Long userId);

    List<AddrPo> selectPageList(@Param("userId") Long userId, @Param("page") PageDto pageDto);

    AddrPo getAddrById(@Param("addrId") Long addrId);

    void updateDefaultById(@Param("addrId") Long addrId);

    void cancleDefaultByUserId(@Param("userId") Long userId);

    void delAddr(@Param("addrId") Long addrId);

    void updateAddrById(@Param("addr") AddrPo addr);

    void addAddr(@Param("addr") AddrPo addrPo);
}
