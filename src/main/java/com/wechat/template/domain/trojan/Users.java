package com.wechat.template.domain.trojan;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "users")
public class Users implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "passwordShow")
    private String passwordshow;

    @TableField(value = "quota")
    private Long quota;

    @TableField(value = "download")
    private Long download;

    @TableField(value = "upload")
    private Long upload;

    @TableField(value = "useDays")
    private Integer usedays;

    @TableField(value = "expiryDate")
    private String expirydate;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_PASSWORDSHOW = "passwordShow";

    public static final String COL_QUOTA = "quota";

    public static final String COL_DOWNLOAD = "download";

    public static final String COL_UPLOAD = "upload";

    public static final String COL_USEDAYS = "useDays";

    public static final String COL_EXPIRYDATE = "expiryDate";
}