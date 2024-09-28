package com.antony.helpdesk.enums;

public enum Priority {

    BAIXA(0,"BAIXA"),
    MEDIA(1,"MEDIA"),
    ALTA(2,"ALTA");

    private Integer id;
    private String description;

    private Priority(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Priority toEnum(Integer id){
        if(id == null){
            return null;
        }
        
        for(Priority status : Priority.values()){
            if(id.equals(status.getId())){
                return status;
            }
        }

        throw new IllegalArgumentException("Prioridade invalida");
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
