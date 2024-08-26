package org.atg.assignment.dto;

public class PetDTO {
    private Long id;
    private final String name;
    private final String status;

    public PetDTO(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public PetDTO(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
