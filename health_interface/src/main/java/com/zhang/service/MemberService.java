package com.zhang.service;

import com.zhang.entity.PageResult;
import com.zhang.pojo.Member;

import java.util.List;

public interface MemberService {
    public Member findByTelephone(String telephone);
    public void add(Member member);
    public List<Integer> findMemberCountByMonth(List<String> months);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
