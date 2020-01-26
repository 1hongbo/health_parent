package com.zhang.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhang.dao.MemberDao;
import com.zhang.entity.PageResult;
import com.zhang.pojo.Member;
import com.zhang.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务
 */

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService
{
    @Autowired
    private MemberDao memberDao;

    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //添加会员信息
    public void add(Member member) {
        String password = member.getPassword();
        if(password != null){
            //使用MD5对密码进行加密
            String md5 = MD5Utils.md5(password);
            member.setPassword(md5);
        }
        memberDao.add(member);
    }

    //根据月份进行会员数量统计
    public List<Integer> findMemberCountByMonth(List<String> months) {
        List<Integer> list = new ArrayList<>();
        for (String month : months) {//2019.05.31
            month = month + ".31";
            Integer count = memberDao.findMemberCountBeforeDate(month);
            list.add(count);
        }
        return list;
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //调用分页助手，指定分页条件，在执行SQL之前进行拦截，处理SQL（加入分页关键字）
        PageHelper.startPage(currentPage,pageSize);
        Page<Member> page = memberDao.pageQuery(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
