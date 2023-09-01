package com.example.loginconbasededatos;

public class Usuario {

    private String codigo;
    private String esp;
    private String doct;
    private String fecha;
    private String horario;
    private Integer id;





    public String getcodigo() {
        return codigo;
    }

    public void setCodigo(String codig){
        this.codigo=codig;
    }


    public String getEsp() {
        return esp;
    }

    public void setEsp(String especialidad) {
        this.esp = especialidad;
    }

    public String getDoct(){
        return  doct;
    }

    public void setDoct(String doctor) {
        this.doct =doctor;
    }
    public Integer getid(){
        return  id;
    }

    public void setId(Integer ids) {
        this.id =ids;
    }
    public String getFecha(){
        return  fecha;
    }

    public void setFecha(String fech) {
        this.fecha =fech;
    }
    public String getHorario(){
        return  horario;
    }

    public void setHorario(String hor) {
        this.horario =hor;
    }


}

