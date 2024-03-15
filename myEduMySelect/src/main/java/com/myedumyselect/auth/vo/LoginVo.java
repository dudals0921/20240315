package com.myedumyselect.auth.vo;

import lombok.Data;

@Data
public class LoginVo {
    private String id;
    private String name;
    private String passwd;
    private int memberTypeId;
}
