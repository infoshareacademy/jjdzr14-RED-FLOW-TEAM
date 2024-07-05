package pl.infoshare.clinicweb.clinic;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.infoshare.clinicweb.patient.Address;

@Data
@AllArgsConstructor
@Embeddable

public class Clinic {
    private String clinicName;
    @Embedded
    private Address address;


    public Clinic() {

    }


    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Clinic(Address address, String clinicName) {
        this.address = address;
        this.clinicName = clinicName;
    }
}
