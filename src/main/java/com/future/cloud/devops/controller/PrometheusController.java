package com.future.cloud.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author guangyuan.zhuang
 * @date 2021年04月17日  19:26
 */
@Controller
@RequestMapping("/api")
public class PrometheusController {

    /**
     * @description 重定向到Prometheus
     */
    @GetMapping("/v1/redirectPrometheus")
    public void redirectPrometheus(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("https://www.zgy1314.cn/prometheus");
    }

}
