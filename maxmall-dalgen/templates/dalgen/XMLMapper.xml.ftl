<@pp.dropOutputFile />
<#import "../lib/lib.ftl" as lib/>
<#import "../lib/function.ftl" as fun/>
<#list dalgen.xmlMappers as xmlMapper>
<@pp.changeOutputFile name = "/main/resources/${xmlMapper.doMapper.xmlPath}/${xmlMapper.doMapper.className}.xml" />
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${xmlMapper.doMapper.packageName}.${xmlMapper.doMapper.className}">
    <!-- 自动生成,请修改 ${xmlMapper.table.sqlName}.xml -->
<#--生成BaseResultMap-->
    <resultMap id="BaseResultMap"  type="${xmlMapper.doClass.packageName}.${xmlMapper.doClass.className}">
        <#list xmlMapper.table.columnList as column>
        <#if column.sqlName =="ID"><id column="${column.sqlName?lower_case}" property="${column.javaName}" jdbcType="${column.sqlType}" javaType="${column.javaType}"/>
        <#else><result column="${column.sqlName?lower_case}" property="${column.javaName}" jdbcType="${column.sqlType}" javaType="${column.javaType}"/></#if>
        </#list>
    </resultMap>

<#-- baseSql -->
    <sql id="Base_Column_List">
        <#list xmlMapper.table.columnList as column>
        <#if column_index gt 0>,</#if>t.${column.sqlName?lower_case} as ${column.javaName}
        </#list>
    </sql>
</mapper>
</#list>
