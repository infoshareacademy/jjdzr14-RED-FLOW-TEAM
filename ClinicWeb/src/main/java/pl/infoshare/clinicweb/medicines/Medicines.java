package pl.infoshare.clinicweb.medicines;

import java.util.List;

public class Medicines {
    String name;
    String producer;
    int quantity;
    boolean OrOnPrescription;
List<Medicines>medicines;

    public Medicines(String name, String producer, int quantity, boolean orOnPrescription) {
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        OrOnPrescription = orOnPrescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOrOnPrescription() {
        return OrOnPrescription;
    }

    public void setOrOnPrescription(boolean orOnPrescription) {
        OrOnPrescription = orOnPrescription;
    }

    public List<Medicines> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicines> medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "Lekarstwo : " +
                "Nazwa :" + name + '\'' +
                "Producent :  " + producer + '\'' +
                ", Iość w opakowaniu :" + quantity +  " szt." +
                ", Lek na receptę" + (OrOnPrescription ? " tak " : " nie " );
    }
}
