package com.milo;

import com.milo.domain.CheckGroup;

public interface CheckGroupService {
    //添加检查组
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
}
