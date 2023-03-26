package com.yystudy.mhl.domain;


//javabean类，对应数据库中的employee表
public class Employee {

    //该javabean类属性名要与数据库中的employee表的字段名一一对应(当然也可以在查询语句中利用As设置一个别名，与属性名对应)
    private Integer id;
    private String empid;
    private String pwd;
    private String name;
    private String job;

    //无参构造器一定要有，dbutils要用到反射利用无参构造器来创建对象
    public Employee() {
    }


    //有参构造器可有可无，因为封装时，dbutils工具类是利用反射来创建对象，然后调用set与get方法给对象中的属性赋值的
    public Employee(Integer id, String empid, String pwd, String name, String job) {
        this.id = id;
        this.empid = empid;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empid='" + empid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
