package com.antony.helpdesk.enums;

public enum Status {

    ABERTO(0,"ABERTO"),
    ANDAMENTO(1,"ANDAMENTO"),
    ENCERRADO(2,"ENCERRADO");

    private Integer id;
    private String description;

    private Status(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Status toEnum(Integer id){
        if(id == null){
            return null;
        }

        for(Status status : Status.values()){
            if(id.equals(status.getId())){
                return status;
            }
        }

        throw new IllegalArgumentException("Invalido Status");
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
