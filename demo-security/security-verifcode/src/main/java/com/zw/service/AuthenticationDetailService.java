package com.zw.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-11
 */
@Service
@Slf4j
public class AuthenticationDetailService {

    public void getDetail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(">>>>>>>>>>>>>authentication info is "+ authentication);
    }

}
