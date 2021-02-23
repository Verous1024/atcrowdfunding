package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.mapper.ProjectPOMapper;
import com.atguigu.crowd.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 01:19
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectPOMapper projectPOMapper;

}
