package com.atguigu.crowd.handler;

import com.atguigu.crowd.service.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 01:20
 */
@RestController
public class ProjectHandler {
    @Autowired
    private ProjectService projectService;

}
