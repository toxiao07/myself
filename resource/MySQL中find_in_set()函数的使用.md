# MySQL中find_in_set()函数的使用

在此最近的一个项目的设计中，有以下场景：部门和职员的两张表，部门是有很多节点的，比如XXX部门下面还细分了很多组，而职员表中是使用一个字段和具体的部门组关联的。如果我需要查询XXX部门下的所有职员呢？

### 部门表

```mysql
    create table department  
    (  
        dept_id bigint auto_increment comment '部门id'  
            primary key,  
        parent_id bigint default 0 null comment '父部门id',  
        ancestors varchar(50) default '' null comment '祖级列表',  
        dept_name varchar(30) default '' null comment '部门名称',  
        create_by varchar(64) default '' null comment '创建者',  
        create_time datetime null comment '创建时间',  
        update_by varchar(64) default '' null comment '更新者',  
        update_time datetime null comment '更新时间'  
    )  
    comment '部门表';  
```

其中的ancestors字段就会用于记录当前的节点的所有父节点，比如：XXX部门的id为1，XXX部门下的XXX小组的id为101，XXX部门下的XXX小组的XXX临时小组的id为201，那么此时的数据如下：

1. 首先是XXX部门的ancestors的值为1

2. 其次是XXX部门下的XXX小组的ancestors为1,101

3. 最后XXX部门下的XXX小组的XXX临时小组的ancestors为1,101,201

```mysql
create table staff  
(  
    sta_id bigint auto_increment  
        primary key,  
    sta_name varchar(128) null comment '学生的名称',  
    sta_num varchar(128) null comment '学号',  
    dept_id bigint null comment '所属班级的id',  
    create_by varchar(128) null,  
    create_time datetime null,  
    update_by varchar(128) null,  
    update_time datetime null,  
    constraint eams_student_eams_profession_prof_id_fk  
        foreign key (prof_id) references eams_profession (prof_id)  
)  
comment '职员表'; 
```

以上表中的dept_id就是记录的所属部门的id；

### 功能实现

为了实现引言所提到的功能，需要用到以下MySQL函数FIND_IN_SET(str,strlist)，

str 表示要查询的字符串

strlist为字段名，以,隔开，如(1,101,201)

其意思为：查询字段strList中包含str的结果，返回null或者记录

**注**：假如字符串str在由N个子链组成的字符串列表strlist 中，则返回值的范围在 1 到 N 之间。 一个字符串列表就是一个由一些被 ‘,’ 符号分开的子链组成的字符串。如果第一个参数是一个常数字符串，而第二个是type  SET列，则FIND_IN_SET() 函数被优化，使用比特计算。 如果str不在strlist 或strlist 为空字符串，则返回值为 0  。如任意一个参数为NULL，则返回值为 NULL。这个函数在第一个参数包含一个逗号(‘,’)时将无法正常运行。

那么功能实现的写法为：

```mysql
    SELECT s.*  
     FROM eams.staff s  
     LEFT JOIN department p  
     ON s.prof_id = p.prof_id  
     WHERE (s.prof_id = 100 OR s.prof_id IN (SELECT t.prof_id  
     FROM department t  
     WHERE FIND_IN_SET(1, ancestors)))   
```

