package com.antony.helpdesk.enums;

public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer code;
    private String description;


    private Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer code){
        if(code == null){
            return null;
        }

        for (Profile perfil : Profile.values()) {
            if(code.equals(perfil.getCode())){
                return perfil;
            }
        }

        throw new IllegalArgumentException("Perfil invalido");
    }


}
