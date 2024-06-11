package pl.infoshare.clinicweb.user.model;

public enum Role {
    PATIENT("Patient"),
    DOCTOR("Doctor"),
    ADMIN("Admin");

    private final String roleDescription;

    Role(String roleDescription) {

        this.roleDescription = roleDescription;

    }

    public String getRoleDescription() {

        return roleDescription;
    }


}
