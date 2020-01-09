package com.data.masterandslave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * <p>
 * 基础控制器
 * </p>
 *
 * @author Alan
 * @since 2017-09-22
 */
@CrossOrigin(value = "*")
public class BaseController {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

}
